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

        // Comprobamos que los tres objetos sean obligatorios
        if (ruta == null || origen == null || destino == null) {
            throw new IllegalArgumentException(
                    "La ruta, el origen y el destino son obligatorios.");
        }

        if (ruta.getRecorrido() == null) {
            throw new IllegalArgumentException(
                    "La ruta debe tener un recorrido asignado.");
        }

        if (origen.getRecorrido() == null
                || destino.getRecorrido() == null) {
            throw new IllegalArgumentException(
                    "Las paradas deben tener un recorrido asignado.");
        }
        // si todo esta correcto obtengo el id del recorrido
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
        String sql = "SELECT "
                + "MAX(CASE WHEN idParadaRecorrido = ? "
                + "THEN distanciaAcumuladaKm END) AS distanciaOrigen, "
                + "MAX(CASE WHEN idParadaRecorrido = ? "
                + "THEN distanciaAcumuladaKm END) AS distanciaDestino, "
                + "MAX(distanciaAcumuladaKm) AS distanciaTotal "
                + "FROM paradarecorrido "
                + "WHERE idRecorrido = ?"; // ordena desde el orden mas alto al ams bajo y devuelve solo un registro

        double distanciaOrigen;
        double distanciaDestino;
        double distanciaTotal;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, origen.getIdParadaRecorrido());
            ps.setInt(2, destino.getIdParadaRecorrido());
            ps.setInt(3, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {

                rs.next();

                distanciaOrigen = rs.getDouble("distanciaOrigen");
                boolean origenNulo = rs.wasNull();

                distanciaDestino = rs.getDouble("distanciaDestino");
                boolean destinoNulo = rs.wasNull();

                distanciaTotal = rs.getDouble("distanciaTotal");
                boolean totalNulo = rs.wasNull();

                if (origenNulo || destinoNulo || totalNulo) {
                    throw new IllegalArgumentException(
                            "No se encontraron las distancias de las paradas.");
                }
            }
        }
        // Calcula los kilómetros del tramo
        double distanciaTramo = distanciaDestino - distanciaOrigen;

        // validaciones de distancia
        if (distanciaTramo <= 0) {
            throw new IllegalArgumentException(
                    "La distancia del tramo debe ser mayor a cero.");
        }
        if (distanciaTotal <= 0) {
            throw new IllegalArgumentException(
                    "La distancia total del recorrido debe ser mayor a cero.");
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
                .multiply(precioBase) //multiplica los km del tramo por el precio base
                .divide(kmTotal, 2, RoundingMode.HALF_UP); // divide por los km totales y redodndea decimales

        return precioPasaje; // devuelve el resultado 
    }

    // Comprueba si un asiento está disponible para el tramo solicitado.
    public boolean asientoDisponible(
            int idRutaViaje,
            int idAsiento,
            int ordenOrigen,
            int ordenDestino) throws SQLException {

        if (idRutaViaje <= 0 || idAsiento <= 0) {
            throw new IllegalArgumentException(
                    "La ruta y el asiento deben ser válidos.");
        }

        if (ordenOrigen >= ordenDestino) {
            throw new IllegalArgumentException(
                    "El origen debe ser anterior al destino.");
        }
        // Comprobamos que exista una transacción activa.
        if (con.getAutoCommit()) {
            throw new SQLException(
                    "La disponibilidad debe comprobarse dentro de una transacción.");
        }

// Bloqueamos el asiento mientras se procesa la compra.
        String sqlBloqueo
                = "SELECT idAsiento FROM asiento WHERE idAsiento = ? FOR UPDATE";

        try (PreparedStatement ps = con.prepareStatement(sqlBloqueo)) {

            ps.setInt(1, idAsiento);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new IllegalArgumentException(
                            "El asiento seleccionado no existe.");
                }
            }
        }

        String sql = "SELECT COUNT(*) "
                + "FROM pasaje p "
                + "INNER JOIN paradarecorrido origen "
                + "ON p.idParadaOrigen = origen.idParadaRecorrido "
                + "INNER JOIN paradarecorrido destino "
                + "ON p.idParadaDestino = destino.idParadaRecorrido "
                + "WHERE p.idRutaViaje = ? "
                + "AND p.idAsiento = ? "
                + "AND p.estado = 1 "
                + "AND origen.orden < ? "
                + "AND destino.orden > ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRutaViaje);
            ps.setInt(2, idAsiento);
            ps.setInt(3, ordenDestino);
            ps.setInt(4, ordenOrigen);

            try (ResultSet rs = ps.executeQuery()) {

                rs.next();

                return rs.getInt(1) == 0;
            }
        }
    }
    //consulta la disponibilidad sin bloquear el asiento ni exigir una transacción.

    public boolean consultarDisponibilidad(
            int idRutaViaje,
            int idAsiento,
            int ordenOrigen,
            int ordenDestino) throws SQLException {

        if (ordenOrigen >= ordenDestino) {
            throw new IllegalArgumentException(
                    "El origen debe ser anterior al destino.");
        }

        String sql = "SELECT COUNT(*) "
                + "FROM pasaje p "
                + "INNER JOIN paradarecorrido origen "
                + "ON p.idParadaOrigen = origen.idParadaRecorrido "
                + "INNER JOIN paradarecorrido destino "
                + "ON p.idParadaDestino = destino.idParadaRecorrido "
                + "WHERE p.idRutaViaje = ? "
                + "AND p.idAsiento = ? "
                + "AND p.estado = 1 "
                + "AND origen.orden < ? "
                + "AND destino.orden > ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRutaViaje);
            ps.setInt(2, idAsiento);
            ps.setInt(3, ordenDestino);
            ps.setInt(4, ordenOrigen);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) == 0;
            }
        }
    }

    public int contarPasajesEmitidos() {
        String sql = "SELECT COUNT(*) FROM pasaje WHERE estado = TRUE";

        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error al contar pasajes: " + e.getMessage());
        }

        return 0;
    }

}
