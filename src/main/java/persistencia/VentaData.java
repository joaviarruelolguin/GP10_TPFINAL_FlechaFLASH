package persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.MedioPago;
import modelo.Venta;
import modelo.Pasaje;
import modelo.Pasajero;
import modelo.TipoPasajero;

public class VentaData {

    private Connection con;

    public VentaData() {
        con = Conexion.getInstancia().getConnection();
    }
    private static final String PUNTO_VENTA = "0001";

    private String generarNumeroComprobante(int idVenta) {

        return PUNTO_VENTA + "-"
                + String.format(java.util.Locale.ROOT, "%08d", idVenta);
    }

    // Comprueba que todos los pasajes tengan los datos necesarios
    // antes de comenzar a registrar la venta en la base de datos.
    private void validarPasajes(List<Pasaje> pasajes) {

        if (pasajes == null || pasajes.isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe incluir al menos un pasaje.");
        }

        // Por cada objeto pasaje que encuentre en la lista,
        // ejecuta las siguientes comprobaciones.
        for (Pasaje pasaje : pasajes) {

            // Comprobamos que el pasaje tenga todos los objetos necesarios.
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
            // sean válidos.
            if (pasaje.getPasajero().getIdPasajero() <= 0
                    || pasaje.getRutaViaje().getIdRutaViaje() <= 0
                    || pasaje.getAsiento().getIdAsiento() <= 0
                    || pasaje.getParadaOrigen().getIdParadaRecorrido() <= 0
                    || pasaje.getParadaDestino().getIdParadaRecorrido() <= 0) {

                throw new IllegalArgumentException(
                        "Hay referencias inválidas en un pasaje.");
            }

            // Comprobamos que la ruta y las paradas pertenezcan al mismo recorrido.
            if (pasaje.getRutaViaje().getRecorrido() == null
                    || pasaje.getParadaOrigen().getRecorrido() == null
                    || pasaje.getParadaDestino().getRecorrido() == null) {

                throw new IllegalArgumentException(
                        "La ruta y las paradas deben tener un recorrido asignado.");
            }

            int idRecorrido = pasaje.getRutaViaje()
                    .getRecorrido().getIdRecorrido();

            if (pasaje.getParadaOrigen().getRecorrido().getIdRecorrido() != idRecorrido
                    || pasaje.getParadaDestino().getRecorrido().getIdRecorrido() != idRecorrido
                    || pasaje.getParadaOrigen().getOrden()
                    >= pasaje.getParadaDestino().getOrden()) {

                throw new IllegalArgumentException(
                        "Las paradas deben pertenecer al recorrido y respetar el orden de la ruta.");
            }
            // Comprobamos que el asiento pertenezca al colectivo asignado a la ruta.
            if (pasaje.getRutaViaje().getColectivo() == null
                    || pasaje.getAsiento().getColectivo() == null) {

                throw new IllegalArgumentException(
                        "La ruta y el asiento deben tener un colectivo asignado.");
            }

            int idColectivoRuta = pasaje.getRutaViaje()
                    .getColectivo().getIdColectivo();

            int idColectivoAsiento = pasaje.getAsiento()
                    .getColectivo().getIdColectivo();

            if (idColectivoRuta != idColectivoAsiento) {
                throw new IllegalArgumentException(
                        "El asiento no pertenece al colectivo asignado a la ruta.");
            }

            // El precio del pasaje no puede ser negativo.
            if (pasaje.getPrecioFinal().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "El precio del pasaje no puede ser negativo.");
            }
            // Comprobamos que la ruta esté habilitada.
            if (!pasaje.getRutaViaje().isEstado()) {
                throw new IllegalArgumentException(
                        "No se pueden vender pasajes para una ruta deshabilitada.");
            }
            // Comprobamos que el colectivo asignado a la ruta esté activo.
            if (pasaje.getRutaViaje().getColectivo() == null
                    || !pasaje.getRutaViaje().getColectivo().isEstado()) {

                throw new IllegalArgumentException(
                        "No se pueden vender pasajes para un colectivo inactivo.");
            }
            // Comprobamos que la fecha de salida de la ruta sea futura.
            if (pasaje.getRutaViaje().getFechaHoraSalida() == null
                    || !pasaje.getRutaViaje().getFechaHoraSalida()
                            .isAfter(java.time.LocalDateTime.now())) {

                throw new IllegalArgumentException(
                        "No se pueden vender pasajes para una ruta cuya salida ya pasó.");
            }
        }
    }

    // Se establece una constante con el interés del crédito (10%).
    private static final BigDecimal INTERES_CREDITO
            = new BigDecimal("0.10");

    public BigDecimal calcularImporteTotal(
            // Recibe la lista de pasajes, la cantidad de cuotas
            // y el medio de pago. Si no hay financiación, no se aplica interés.
            List<Pasaje> pasajes,
            int cantidadCuotas,
            MedioPago medioPago) {

        // Si no recibo una lista o no contiene pasajes,
        // no se calcula la venta.
        if (pasajes == null || pasajes.isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe incluir al menos un pasaje.");
        }

        // Debe haber un medio de pago seleccionado.
        if (medioPago == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un medio de pago.");
        }

        // Las cuotas deben estar entre 0 y 6.
        if (cantidadCuotas < 0 || cantidadCuotas > 6) {
            throw new IllegalArgumentException(
                    "La cantidad de cuotas debe estar entre 0 y 6.");
        }

        // Solo se permite financiar con tarjeta de crédito.
        if (medioPago != MedioPago.CREDITO && cantidadCuotas != 0) {
            throw new IllegalArgumentException(
                    "Solo las tarjetas de crédito permiten financiar en cuotas.");
        }

        // Si se utiliza crédito, debe seleccionarse al menos una cuota.
        if (medioPago == MedioPago.CREDITO && cantidadCuotas == 0) {
            throw new IllegalArgumentException(
                    "Debe seleccionar entre 1 y 6 cuotas para pagar con crédito.");
        }

        // Variable de subtotal inicializada en cero.
        BigDecimal subtotal = BigDecimal.ZERO;

        // Por cada objeto pasaje que encuentre en la lista,
        // ejecuta las instrucciones.
        for (Pasaje pasaje : pasajes) {

            // Validamos que exista el pasaje, que tenga un precio
            // y que ese precio no sea negativo.
            if (pasaje == null || pasaje.getPrecioFinal() == null
                    || pasaje.getPrecioFinal().compareTo(BigDecimal.ZERO) < 0) {

                throw new IllegalArgumentException(
                        "El pasaje tiene un precio inválido.");
            }

            // Toma el subtotal que ya tenemos y suma el precio actual.
            subtotal = subtotal.add(pasaje.getPrecioFinal());

        } // Obtenemos el subtotal de todos los pasajes.

        // Aplicamos el interés únicamente si se paga con crédito.
        if (medioPago == MedioPago.CREDITO) {

            BigDecimal interes = subtotal.multiply(INTERES_CREDITO);

            subtotal = subtotal.add(interes);
        }

        // Redondeamos el importe final a dos decimales.
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    /*
     * SYNCHRONIZED:
     * Se utiliza para controlar el acceso concurrente a una sección de código.
     * En Java, varios hilos pueden ejecutar tareas al mismo tiempo.
     * Si dos hilos intentan acceder a una sección protegida por el mismo
     * bloqueo, uno debe esperar a que el otro termine y libere ese bloqueo.
     *
     * En FlechaFLASH compartimos una única conexión a MariaDB mediante
     * el patrón Singleton. Por eso sincronizamos sobre el objeto Connection,
     * que es el recurso compartido que necesitamos proteger durante
     * las transacciones.
     *
     * IMPORTANTE: las demás clases que utilicen esta conexión también
     * deberán respetar el mismo bloqueo para evitar interferencias.
     */
    public boolean registrarVenta(
            Venta venta, List<Pasaje> pasajes) throws SQLException {

        // Debe existir un objeto Venta.
        if (venta == null) {
            throw new IllegalArgumentException(
                    "La venta es obligatoria.");
        }

        // Debe haber un comprador con un identificador válido.
        if (venta.getPasajeroComprador() == null
                || venta.getPasajeroComprador().getIdPasajero() <= 0) {

            throw new IllegalArgumentException(
                    "Debe seleccionar un comprador válido.");
        }

        // Debe existir una fecha y hora para registrar la venta.
        if (venta.getFechaHoraVenta() == null) {
            throw new IllegalArgumentException(
                    "La fecha y hora de la venta son obligatorias.");
        }

        // Debe haber un medio de pago seleccionado.
        if (venta.getMedioPago() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un medio de pago.");
        }

        // Validamos todos los pasajes antes de iniciar la transacción.
        validarPasajes(pasajes);

        // Calculamos el importe total teniendo en cuenta
        // los precios de los pasajes, las cuotas y el medio de pago.
        BigDecimal importeTotal = calcularImporteTotal(
                pasajes,
                venta.getCantidadCuotas(),
                venta.getMedioPago());

        // Guardamos el importe calculado en el objeto Venta.
        venta.setImporteTotal(importeTotal);

        // Obtenemos la cantidad de pasajes a partir del tamaño de la lista.
        venta.setCantidadPasajes(pasajes.size());

        // Comprobamos que la conexión con la base de datos esté disponible.
        if (con == null || con.isClosed()) {
            throw new SQLException(
                    "No hay una conexión disponible con la base de datos.");
        }

        // Insertamos un registro en la tabla venta completando sus datos.
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
        // De esta manera, las operaciones que utilicen este mismo bloqueo
        // no podrán ejecutar simultáneamente sus transacciones.
        synchronized (con) {

            // Guardamos el estado anterior de AutoCommit para poder restaurarlo
            // una vez finalizada la operación.
            boolean autoCommitAnterior = con.getAutoCommit();

            // Indicamos si la transacción llegó a iniciarse.
            boolean transaccionIniciada = false;

            // Guardamos la excepción principal para no perderla
            // si posteriormente falla el rollback o la restauración.
            Throwable errorPrincipal = null;

            try {

                // Iniciar la transacción.
                // Desactivamos AutoCommit para que las operaciones no se confirmen
                // individualmente, sino todas juntas al finalizar.
                con.setAutoCommit(false);

                transaccionIniciada = true;

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

                    //  Recuperar el ID generado por MariaDB.
                    // Este identificador será necesario para relacionar
                    // los pasajes con la venta recién registrada.
                    try (ResultSet rs = psVenta.getGeneratedKeys()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "No se pudo obtener el ID de la venta.");
                        }

                        // Guardamos el ID generado en el objeto Venta.
                        // Guardamos el ID generado por MariaDB.
                        venta.setIdVenta(rs.getInt(1));

// Generamos el número del comprobante interno.
                        String numeroComprobante
                                = generarNumeroComprobante(venta.getIdVenta());

// Establecemos el tipo y número de comprobante en el objeto.
                        venta.setTipoComprobante("TICKET");
                        venta.setNumeroComprobante(numeroComprobante);

// Actualizamos los datos del comprobante en la base de datos.
                        String sqlComprobante
                                = "UPDATE venta SET tipoComprobante = ?, "
                                + "numeroComprobante = ? WHERE idVenta = ?";

                        try (PreparedStatement psComprobante
                                = con.prepareStatement(sqlComprobante)) {

                            psComprobante.setString(1, venta.getTipoComprobante());
                            psComprobante.setString(2, venta.getNumeroComprobante());
                            psComprobante.setInt(3, venta.getIdVenta());

                            int filasActualizadas = psComprobante.executeUpdate();

                            if (filasActualizadas != 1) {
                                throw new SQLException(
                                        "No se pudo guardar el comprobante de la venta.");
                            }
                        }
                    }
                }
                // Creamos el objeto que permite comprobar la disponibilidad de los asientos.
                PasajeData pasajeData = new PasajeData();

                // Insertar todos los pasajes asociados a la venta.
                // Utilizamos un PreparedStatement para ejecutar la misma
                // consulta varias veces, cambiando sus parámetros.
                try (PreparedStatement psPasaje = con.prepareStatement(sqlPasaje)) {

                    // Recorremos la lista de pasajes que forman parte de la venta.
                    for (Pasaje pasaje : pasajes) {

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

                        psPasaje.setTimestamp(6,
                                Timestamp.valueOf(venta.getFechaHoraVenta()));

                        psPasaje.setBigDecimal(7, pasaje.getPrecioFinal());
                        psPasaje.setBoolean(8, true);
                        psPasaje.setInt(9, venta.getIdVenta());
                        // Comprobamos que el asiento esté disponible para el tramo solicitado.
                        boolean disponible = pasajeData.asientoDisponible(
                                pasaje.getRutaViaje().getIdRutaViaje(),
                                pasaje.getAsiento().getIdAsiento(),
                                pasaje.getParadaOrigen().getOrden(),
                                pasaje.getParadaDestino().getOrden());

                        if (!disponible) {
                            throw new IllegalArgumentException(
                                    "El asiento no está disponible para el tramo seleccionado.");
                        }

                        // Ejecutamos la inserción del pasaje.
                        pasaje.setEstado(true);
                        psPasaje.executeUpdate();

                        // Asociamos el objeto pasaje con la venta.
                        pasaje.setVenta(venta);
                    }
                }

                // Confirmar todas las operaciones.
                // Si la venta y todos sus pasajes se insertaron correctamente,
                // guardamos definitivamente los cambios en MariaDB.
                con.commit();

                // Devolvemos true para indicar que la venta fue registrada.
                return true;

            } catch (SQLException | RuntimeException e) {

                // Guardamos la excepción original para no perderla
                // si posteriormente falla el rollback o la restauración.
                errorPrincipal = e;

                // Deshacer las operaciones si algo falla.
                // Solo intentamos el rollback si la transacción se inició.
                if (transaccionIniciada) {

                    try {

                        con.rollback();

                    } catch (SQLException errorRollback) {

                        // Si también falla el rollback, agregamos ese error
                        // como información secundaria de la excepción original.
                        e.addSuppressed(errorRollback);
                    }
                }

                // Propagamos la excepción original.
                throw e;

            } finally {

                // Restaurar el comportamiento anterior.
                // Recuperamos la configuración de AutoCommit que tenía
                // la conexión antes de comenzar la transacción.
                try {

                    con.setAutoCommit(autoCommitAnterior);

                } catch (SQLException errorRestauracion) {

                    // Si ya existía un error, agregamos la falla de
                    // restauración sin ocultar la excepción original.
                    if (errorPrincipal != null) {

                        errorPrincipal.addSuppressed(errorRestauracion);

                    } else {

                        // Si no había otro error, propagamos este.
                        throw errorRestauracion;
                    }
                }
            }
        }
    }

    // Devuelve todas las ventas (la más reciente primero), con el comprador cargado.
    public List<Venta> listarVentas() {

        List<Venta> ventas = new ArrayList<>();

        String sql = "SELECT v.idVenta, v.fechaHoraVenta, v.medioPago, "
                + "v.cantidadCuotas, v.cantidadPasajes, v.importeTotal, "
                + "v.tipoComprobante, v.numeroComprobante, v.estado, "
                + "p.idPasajero, p.dni, p.nombre, p.apellido, "
                + "p.correo, p.telefono, p.tipoPasajero "
                + "FROM venta v "
                + "JOIN pasajero p ON p.idPasajero = v.idPasajeroComprador "
                + "ORDER BY v.fechaHoraVenta DESC, v.idVenta DESC";

        synchronized (con) {
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Pasajero comprador = new Pasajero(
                            rs.getInt("idPasajero"),
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getString("telefono"),
                            TipoPasajero.desdeCodigo(rs.getInt("tipoPasajero")));

                    Venta venta = new Venta(
                            rs.getInt("idVenta"),
                            comprador,
                            rs.getTimestamp("fechaHoraVenta").toLocalDateTime(),
                            MedioPago.valueOf(rs.getString("medioPago")),
                            rs.getInt("cantidadCuotas"),
                            rs.getInt("cantidadPasajes"),
                            rs.getBigDecimal("importeTotal"),
                            rs.getString("tipoComprobante"),
                            rs.getString("numeroComprobante"),
                            rs.getBoolean("estado"));

                    ventas.add(venta);
                }

            } catch (SQLException e) {
                System.out.println("Error al listar ventas: " + e.getMessage());
            }
        }

        return ventas;
    }
}
