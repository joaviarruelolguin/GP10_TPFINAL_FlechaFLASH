package persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode; // para redondear el resultado
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import modelo.Venta;
import modelo.Pasaje;

public class VentaData {

    private Connection con;

    public VentaData() {
        con = Conexion.getInstancia().getConnection();
    }

    private static final BigDecimal INTERES_CREDITO
            = new BigDecimal("0.10");   // se establece una constante con el intres del credito (10%)

    public BigDecimal calcularImporteTotal( // recibe la lista de pasajes y la cantidad de cuotas, si no hay cuotas no se aplica el interes
            List<Pasaje> pasajes, int cantidadCuotas) {

        // si no recibo lista o la lista no contiene pasaje, no se calcla la venta
        if (pasajes == null || pasajes.isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe incluir al menos un pasaje.");
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

        //  aplicamos el interes si corresponde
        if (cantidadCuotas > 0) {

            BigDecimal interes = subtotal.multiply(INTERES_CREDITO);

            subtotal = subtotal.add(interes);
        }

        // Redondeamos el importe final
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean registrarVenta(Venta venta, List<Pasaje> pasajes)
            throws SQLException {

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

        if (venta.getCantidadCuotas() < 0 // las cuotas deben estar entre 0 y 6
                || venta.getCantidadCuotas() > 6) {

            throw new IllegalArgumentException(
                    "La cantidad de cuotas debe estar entre 0 y 6.");
        }

        BigDecimal importeTotal = calcularImporteTotal( // llamamos al metodo y le damos la lista de pasajes y la cant de cuotas

                pasajes, venta.getCantidadCuotas()); 
                venta.setImporteTotal(importeTotal);
                venta.setCantidadPasajes(pasajes.size());  // devolvemos la cantidad de pasajes que tiene la lista

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

        boolean autoCommitAnterior = con.getAutoCommit();

        try {
            // 4. Iniciar la transacción
            con.setAutoCommit(false);

            // 5. Insertar la venta
            try (PreparedStatement psVenta = con.prepareStatement(
                    sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS)) {

                psVenta.setInt(1,
                        venta.getPasajeroComprador().getIdPasajero());

                psVenta.setTimestamp(2,
                        Timestamp.valueOf(venta.getFechaHoraVenta()));

                psVenta.setString(3, venta.getMedioPago());
                psVenta.setInt(4, venta.getCantidadCuotas());
                psVenta.setInt(5, venta.getCantidadPasajes());
                psVenta.setBigDecimal(6, venta.getImporteTotal());
                psVenta.setString(7, venta.getTipoComprobante());
                psVenta.setString(8, venta.getNumeroComprobante());
                psVenta.setBoolean(9, venta.isEstado());

                psVenta.executeUpdate();

                // 6. Recuperar el ID generado por MariaDB
                try (ResultSet rs = psVenta.getGeneratedKeys()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "No se pudo obtener el ID de la venta.");
                    }

                    venta.setIdVenta(rs.getInt(1));
                }
            }

            // 7. Insertar todos los pasajes
            try (PreparedStatement psPasaje = con.prepareStatement(sqlPasaje)) {

                for (Pasaje pasaje : pasajes) {

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

                    if (pasaje.getPasajero().getIdPasajero() <= 0
                            || pasaje.getRutaViaje().getIdRutaViaje() <= 0
                            || pasaje.getAsiento().getIdAsiento() <= 0
                            || pasaje.getParadaOrigen().getIdParadaRecorrido() <= 0
                            || pasaje.getParadaDestino().getIdParadaRecorrido() <= 0) {

                        throw new IllegalArgumentException(
                                "Hay referencias inválidas en un pasaje.");
                    }

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
                    psPasaje.setBoolean(8, pasaje.isEstado());
                    psPasaje.setInt(9, venta.getIdVenta());

                    psPasaje.executeUpdate();

                    // Asociar el objeto pasaje con la venta
                    pasaje.setVenta(venta);
                }
            }

            // 8. Confirmar todas las operaciones
            con.commit();

            return true;

        } catch (SQLException | RuntimeException e) {

            // 9. Deshacer las operaciones si algo falla
            con.rollback();

            throw e;

        } finally {

            // 10. Restaurar el comportamiento anterior
            con.setAutoCommit(autoCommitAnterior);
        }
    }

}
