package persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode; // para redondear el resultado
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import modelo.MedioPago;

import modelo.Venta;
import modelo.Pasaje;

public class VentaData {

    private Connection con;

    public VentaData() {
        con = Conexion.getInstancia().getConnection();
    }

    private static final BigDecimal INTERES_CREDITO
            = new BigDecimal("0.10");   // se establece una constante con el intres del credito (10%)

    public BigDecimal calcularImporteTotal( // recibe la lista de pasajes, la cantidad de cuotas y los medios de pago
            //si no hay cuotas no se aplica el interes
            List<Pasaje> pasajes, int cantidadCuotas, MedioPago medioPago) {

        // si no recibo lista o la lista no contiene pasaje, no se calcla la venta
        if (pasajes == null || pasajes.isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe incluir al menos un pasaje.");
        }
        if (medioPago == null) { // debe haber un mediod e pago sino no se vende el pasaje
            throw new IllegalArgumentException(
                    "Debe seleccionar un medio de pago.");
        }

        if (medioPago != MedioPago.CREDITO && cantidadCuotas != 0) {  // tiene que ser credito para que permita las cuotas
            throw new IllegalArgumentException(
                    "Solo las tarjetas de crédito permiten financiar en cuotas.");
        }

        if (medioPago == MedioPago.CREDITO && cantidadCuotas == 0) { // se debe seleccionar al menos una cuota
            throw new IllegalArgumentException(
                    "Debe seleccionar entre 1 y 6 cuotas para pagar con crédito.");
        }

        // las cuotas deben ser entre 0 y6 sino rechazo el calculo
        if (cantidadCuotas < 0 || cantidadCuotas > 6) {
            throw new IllegalArgumentException(
                    "La cantidad de cuotas debe estar entre 0 y 6.");
        }

        // variable de subtotal inicializada en 0
        BigDecimal subtotal = BigDecimal.ZERO;

        // por cada objeto pasaje que encuentre en la lista de pasajes ejecuta las instrucciones 
        for (Pasaje pasaje : pasajes) {

            // validamos cada pasaje (que exista el objeto pasaje, que tenga un precio y que el precio no sea negativo
            if (pasaje == null || pasaje.getPrecioFinal() == null
                    || pasaje.getPrecioFinal().compareTo(BigDecimal.ZERO) < 0) {

                throw new IllegalArgumentException(
                        "El pasaje tiene un precio inválido.");
            }

            subtotal = subtotal.add(pasaje.getPrecioFinal());  // toma el subtotal que ya tengo y suma el precio actual
        }   // obtenemos el subtotala de todos los pasajes

        //  aplicamos el interes si corresponde que corresponde exclusivamente al medio de pago
        if (medioPago == MedioPago.CREDITO) {
            BigDecimal interes = subtotal.multiply(INTERES_CREDITO);
            subtotal = subtotal.add(interes);
        }

        // Redondeamos el importe final
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    /*
 * SYNCHRONIZED:
 * Se utiliza para controlar el acceso concurrente al método.
 * En Java, varios hilos pueden ejecutar tareas al mismo tiempo.
 * Si dos hilos intentan acceder a una misma sección de código
 * protegida por el mismo bloqueo, uno debe esperar a que el otro
 * termine y libere ese bloqueo.
 *
 * En FlechaFLASH compartimos una única conexión a MariaDB mediante
 * el patrón Singleton. Por eso es importante controlar el acceso
 * a la conexión durante las transacciones, evitando que distintas
 * operaciones interfieran entre sí.
 *
 * IMPORTANTE: synchronized en un método de instancia bloquea
 * únicamente el objeto actual (this). Si existen varias instancias
 * de VentaData, este modificador por sí solo no protege la conexión
 * compartida.
     */
    public synchronized boolean registrarVenta(
            Venta venta, List<Pasaje> pasajes) throws SQLException {

        if (venta == null) { // debe haber un objeto venta
            throw new IllegalArgumentException("La venta es obligatoria.");
        }

        if (venta.getPasajeroComprador() == null // debe haber un id valido de comprador
                || venta.getPasajeroComprador().getIdPasajero() <= 0) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un comprador válido.");
        }

        if (pasajes == null || pasajes.isEmpty()) {   // debe haber un pasaje
            throw new IllegalArgumentException(
                    "La venta debe incluir al menos un pasaje.");
        }
        if (venta.getMedioPago() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un medio de pago.");
        }

        if (venta.getCantidadCuotas() < 0 // las cuotas deben estar entre 0 y 6
                || venta.getCantidadCuotas() > 6) {

            throw new IllegalArgumentException(
                    "La cantidad de cuotas debe estar entre 0 y 6.");
        }
        if (venta.getMedioPago() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un medio de pago.");
        }

        BigDecimal importeTotal = calcularImporteTotal(
                // llamamos al metodo y le damos la lista de pasajes, la cant de cuotas y el medio de pagp

                pasajes,
                venta.getCantidadCuotas(),
                venta.getMedioPago());

        // insertamos un registro en la tabla ventas  completando los datos
        String sqlVenta = "INSERT INTO venta "
                + "(idPasajeroComprador, fechaHoraVenta, medioPago, "
                + "cantidadCuotas, cantidadPasajes, importeTotal, "
                + "tipoComprobante, numeroComprobante, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlPasaje = "INSERT INTO pasaje "
                + "(idPasajero, idRutaViaje, idAsiento, "
                + "idParadaOrigen, idParadaDestino, fechaHoraVenta, "
                + "precioFinal, estado, idVenta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

// Sincronizamos sobre la conexión compartida.
// De esta manera, dos operaciones que utilicen este mismo bloqueo
// no podrán ejecutar simultáneamente sus transacciones.
        synchronized (con) {

            // Guardamos el estado anterior de AutoCommit para poder restaurarlo
            // una vez finalizada la operación.
            boolean autoCommitAnterior = con.getAutoCommit();

            try {

                // Iniciar la transacción.
                // Desactivamos AutoCommit para que las operaciones no se confirmen
                // individualmente, sino todas juntas al finalizar.
                con.setAutoCommit(false);

                // Insertar la venta.
                // Preparamos la consulta SQL y solicitamos que MariaDB
                // devuelva el ID generado automáticamente.
                try (PreparedStatement psVenta = con.prepareStatement(
                        sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS)) {

                    // Establecemos los valores de cada parámetro de la consulta.
                    psVenta.setInt(1,
                            venta.getPasajeroComprador().getIdPasajero());

                    psVenta.setTimestamp(2,
                            Timestamp.valueOf(venta.getFechaHoraVenta()));

                    // Convertimos el enum MedioPago a texto para guardarlo
                    // en el campo VARCHAR de la base de datos.
                    psVenta.setString(3, venta.getMedioPago().name());

                    psVenta.setInt(4, venta.getCantidadCuotas());
                    psVenta.setInt(5, venta.getCantidadPasajes());
                    psVenta.setBigDecimal(6, venta.getImporteTotal());
                    psVenta.setString(7, venta.getTipoComprobante());
                    psVenta.setString(8, venta.getNumeroComprobante());
                    psVenta.setBoolean(9, venta.isEstado());

                    // Ejecutamos la inserción de la venta.
                    psVenta.executeUpdate();

                    // Recuperar el ID generado por MariaDB.
                    // Este identificador será necesario para relacionar
                    // los pasajes con la venta recién registrada.
                    try (ResultSet rs = psVenta.getGeneratedKeys()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "No se pudo obtener el ID de la venta.");
                        }

                        // Guardamos el ID generado en el objeto Venta.
                        venta.setIdVenta(rs.getInt(1));
                    }
                }

                // nsertar todos los pasajes asociados a la venta.
                // Utilizamos un PreparedStatement para ejecutar la misma
                // consulta varias veces, cambiando sus parámetros.
                try (PreparedStatement psPasaje = con.prepareStatement(sqlPasaje)) {

                    // Recorremos la lista de pasajes que forman parte de la venta.
                    for (Pasaje pasaje : pasajes) {

                        // Comprobamos que el pasaje tenga todos los objetos
                        // necesarios para poder registrarse.
                        if (pasaje == null
                                || pasaje.getPasajero() == null
                                || pasaje.getRutaViaje() == null
                                || pasaje.getAsiento() == null
                                || pasaje.getParadaOrigen() == null
                                || pasaje.getParadaDestino() == null
                                || pasaje.getPrecioFinal() == null) {

                            throw new IllegalArgumentException(
                                    "Hay un pasaje con datos incompletos.");
                        }

                        // Comprobamos que los identificadores de las referencias
                        // sean válidos y correspondan a registros existentes.
                        if (pasaje.getPasajero().getIdPasajero() <= 0
                                || pasaje.getRutaViaje().getIdRutaViaje() <= 0
                                || pasaje.getAsiento().getIdAsiento() <= 0
                                || pasaje.getParadaOrigen().getIdParadaRecorrido() <= 0
                                || pasaje.getParadaDestino().getIdParadaRecorrido() <= 0) {

                            throw new IllegalArgumentException(
                                    "Hay referencias inválidas en un pasaje.");
                        }

                        // Asignamos los valores correspondientes a cada parámetro.
                        psPasaje.setInt(1,
                                pasaje.getPasajero().getIdPasajero());

                        psPasaje.setInt(2,
                                pasaje.getRutaViaje().getIdRutaViaje());

                        psPasaje.setInt(3,
                                pasaje.getAsiento().getIdAsiento());

                        psPasaje.setInt(4,
                                pasaje.getParadaOrigen().getIdParadaRecorrido());

                        psPasaje.setInt(5,
                                pasaje.getParadaDestino().getIdParadaRecorrido());

                        // Registramos la fecha y hora de la venta.
                        psPasaje.setTimestamp(6,
                                Timestamp.valueOf(venta.getFechaHoraVenta()));

                        psPasaje.setBigDecimal(7, pasaje.getPrecioFinal());
                        psPasaje.setBoolean(8, pasaje.isEstado());

                        // Relacionamos el pasaje con la venta mediante su ID.
                        psPasaje.setInt(9, venta.getIdVenta());

                        // Ejecutamos la inserción del pasaje.
                        psPasaje.executeUpdate();

                        // Asociamos también los objetos en memoria.
                        pasaje.setVenta(venta);
                    }
                }

                // Confirmar todas las operaciones.
                // Si la venta y todos sus pasajes se insertaron correctamente,
                // confirmamos los cambios en la base de datos.
                con.commit();

                return true;

            } catch (SQLException | RuntimeException e) {

                // Deshacer las operaciones si algo falla.
                // Si ocurre un error, intentamos cancelar todos los cambios
                // realizados durante esta transacción.
                con.rollback();

                // Volvemos a lanzar la excepción para que pueda ser tratada
                // por la clase que invocó este método.
                throw e;

            } finally {

                // Restaurar el comportamiento anterior.
                // Recuperamos la configuración de AutoCommit que tenía
                // la conexión antes de comenzar la transacción.
                con.setAutoCommit(autoCommitAnterior);
            }
        }
    }
}
