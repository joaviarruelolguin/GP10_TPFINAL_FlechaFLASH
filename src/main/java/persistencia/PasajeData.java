
package persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.RutaViaje;
import modelo.ParadaRecorrido;

public class PasajeData {

    private Connection con;

    public PasajeData() {
        con = Conexion.getInstancia().getConnection();
    }
    
    // recibe una ruta, una parada de origen y una parada destino

    public BigDecimal calcularPrecioPasaje(
            RutaViaje ruta,
            ParadaRecorrido origen,
            ParadaRecorrido destino) throws SQLException {

        // si la ruta, el origen o el destino no existen, informo que los datos requeridos son obligatorios
        if (ruta == null || origen == null || destino == null) {
            throw new IllegalArgumentException(
                    "La ruta, el origen y el destino son obligatorios.");
        }

        // recibo la ruta, obtengo el recorrido y guardo el id para consultar las paradas que tiene asignadas
        int idRecorrido = ruta.getRecorrido().getIdRecorrido();

        // Compruebo que las paradas pertenezcan al recorrido caso contrario rechazo el calculo
        if (origen.getRecorrido().getIdRecorrido() != idRecorrido
                || destino.getRecorrido().getIdRecorrido() != idRecorrido) {

            throw new IllegalArgumentException(
                    "Las paradas deben pertenecer al recorrido de la ruta.");
        }

        // El destino siempre debe ser despues del origen dentro del recorrido
        if (origen.getOrden() >= destino.getOrden()) {
            throw new IllegalArgumentException(
                    "La parada de origen debe ser anterior al destino.");
        }

        // busca la distancia total del recorrido
        String sql = "SELECT distanciaAcumuladaKm "
                   + "FROM paradarecorrido "
                   + "WHERE idRecorrido = ? "
                   + "ORDER BY orden DESC LIMIT 1";  // ordena desde el orden mas alto al ams bajo y devuelve solo un registro

        double distanciaTotal;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new IllegalArgumentException(
                            "El recorrido no tiene paradas registradas.");
                }

                distanciaTotal = rs.getDouble("distanciaAcumuladaKm");
            }
        }

        // comprueba que tenga una distancia valida
        if (distanciaTotal <= 0) {
            throw new IllegalArgumentException(
                    "La distancia total del recorrido debe ser mayor a cero.");
        }

        // Calcula los kilómetros del tramo
        double distanciaTramo =
                destino.getDistanciaAcumuladaKm()
                - origen.getDistanciaAcumuladaKm();

        if (distanciaTramo <= 0) {
            throw new IllegalArgumentException(
                    "La distancia del tramo debe ser mayor a cero.");
        }

        // aplicamos la regla de tres simple
        BigDecimal kmTramo = BigDecimal.valueOf(distanciaTramo);
        BigDecimal kmTotal = BigDecimal.valueOf(distanciaTotal);
        BigDecimal precioBase = ruta.getPrecioBase();

        if (precioBase == null || precioBase.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El precio base debe ser mayor o igual a cero.");
        }

        BigDecimal precioPasaje = kmTramo
                .multiply(precioBase)   //multiplica los km del tramo por el precio base
                .divide(kmTotal, 2, RoundingMode.HALF_UP); // divide por los km totales y redodndea decimales

        return precioPasaje; // devuelve el resultado 
    }
}
