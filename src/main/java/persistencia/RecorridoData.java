
package persistencia;

import modelo.Recorrido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ParadaRecorrido;


public class RecorridoData {
    
    private Connection connection;

    public RecorridoData() {
        connection = Conexion.getInstancia().getConnection();
    }

    
    // Método para evitar modificar o eliminar un recorrido sin saber si tiene salidas.
    public boolean tieneSalidasAsociadas(int idRecorrido) {
        String sql = "SELECT 1 FROM RutaViaje WHERE idRecorrido = ? LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar salidas asociadas: " + e.getMessage());
            return true;
        }
    }


    private boolean existeDescripcion(String descripcion) {

        String sql = "SELECT idRecorrido FROM recorrido WHERE LOWER(TRIM(descripcion)) = LOWER(TRIM(?))";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, descripcion);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar descripción: " + e.getMessage());
            return true;
        }
    }

    
    public boolean guardarRecorrido(Recorrido recorrido) {

        if (recorrido == null || recorrido.getParadas() == null || recorrido.getParadas().isEmpty()) {
            System.out.println("El recorrido debe tener al menos una parada.");
            return false;
        }
              
        if (recorrido.getDescripcion() == null || recorrido.getDescripcion().trim().isEmpty()) {
            
            return false;
        }

        String descripcion = recorrido.getDescripcion().trim();

        if (existeDescripcion(descripcion)) {
            
            System.out.println("Ya existe un recorrido con esa descripción.");
            return false;
        }

        String sql = "INSERT INTO recorrido (descripcion, estado) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, descripcion);
            ps.setBoolean(2, recorrido.isEstado());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    
                    if (rs.next()) {

                        recorrido.setIdRecorrido(rs.getInt(1));

                        ParadaRecorridoData paradaData = new ParadaRecorridoData();

                        for (ParadaRecorrido parada : recorrido.getParadas()) {

                            if (parada == null) {
                                System.out.println("El recorrido contiene una parada nula.");
                                return false;
                            }

                            parada.setRecorrido(recorrido);

                            if (!paradaData.guardarParadaRecorrido(parada)) {
                                return false;
                            }
                        }

                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar recorrido: " + e.getMessage());
        }

        return false;
    }

    
    public List<Recorrido> listarRecorridos() {

        List<Recorrido> recorridos = new ArrayList<>();

        String sql = "SELECT * FROM recorrido ORDER BY idRecorrido";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Recorrido recorrido = new Recorrido(
                        rs.getInt("idRecorrido"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );

                ParadaRecorridoData paradaData = new ParadaRecorridoData();
                recorrido.setParadas(paradaData.listarParadasPorRecorrido(recorrido.getIdRecorrido()));
                recorridos.add(recorrido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar recorridos: " + e.getMessage());
        }

        return recorridos;
    }
    
    
    public List<Recorrido> listarRecorridosActivos() {
        
        List<Recorrido> recorridos = new ArrayList<>();
        String sql = "SELECT * FROM recorrido WHERE estado = TRUE ORDER BY idRecorrido";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Recorrido recorrido = new Recorrido(
                        rs.getInt("idRecorrido"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );
                
                ParadaRecorridoData paradaData = new ParadaRecorridoData();
                recorrido.setParadas(paradaData.listarParadasPorRecorrido(recorrido.getIdRecorrido()));
                recorridos.add(recorrido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar recorridos activos: " + e.getMessage());
        }

        return recorridos;
    }
    
    
    public Recorrido buscarRecorrido(int idRecorrido) {

        if (idRecorrido <= 0) {
            return null;
        }

        String sql = "SELECT * FROM recorrido WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Recorrido recorrido = new Recorrido(
                            rs.getInt("idRecorrido"),
                            rs.getString("descripcion"),
                            rs.getBoolean("estado")
                    );

                    ParadaRecorridoData paradaData = new ParadaRecorridoData();
                    recorrido.setParadas(paradaData.listarParadasPorRecorrido(recorrido.getIdRecorrido()));
                    
                    
                    return recorrido;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar recorrido: " + e.getMessage());
        }

        return null;
    }
    
    
    // Sobrecarga de método existeDescripcion
    private boolean existeDescripcion(String descripcion, int idRecorrido) {

        String sql = "SELECT idRecorrido FROM recorrido "
                + "WHERE LOWER(TRIM(descripcion)) = LOWER(TRIM(?)) "
                + "AND idRecorrido <> ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, descripcion);
            ps.setInt(2, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar descripción: " + e.getMessage());
        }

        return false;
    }
    
    
       // Método que incluye la posibilidad de reactivar un recorrido.
       // Si tiene salidas asociadas, se permite cambiar el estado, pero no la descripción.
    public boolean actualizarRecorrido(Recorrido recorrido) {
        if (recorrido == null || recorrido.getIdRecorrido() <= 0) {
            return false;
        }

        if (recorrido.getDescripcion() == null || recorrido.getDescripcion().trim().isEmpty()) {
            return false;
        }

        String descripcion = recorrido.getDescripcion().trim();

        Recorrido actual = buscarRecorrido(recorrido.getIdRecorrido());

        if (actual == null) {
            return false;
        }

        if (tieneSalidasAsociadas(recorrido.getIdRecorrido())) {
            if (!actual.getDescripcion().equalsIgnoreCase(descripcion)) {
                System.out.println("No se puede modificar la descripción de un recorrido con salidas asociadas.");
                return false;
            }
        } else if (existeDescripcion(descripcion, recorrido.getIdRecorrido())) {
            System.out.println("Ya existe otro recorrido con esa descripción.");
            return false;
        }

        String sql = "UPDATE recorrido SET descripcion = ?, estado = ? WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            ps.setBoolean(2, recorrido.isEstado());
            ps.setInt(3, recorrido.getIdRecorrido());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar recorrido: " + e.getMessage());
        }

        return false;
    }

    public boolean actualizarRecorridoCompleto(Recorrido recorrido) {
        if (recorrido == null || recorrido.getIdRecorrido() <= 0 || recorrido.getParadas() == null || recorrido.getParadas().size() < 2) {
            System.out.println("El recorrido debe tener al menos dos paradas.");
            return false;
        }

        int idRecorrido = recorrido.getIdRecorrido();
        String descripcion = recorrido.getDescripcion() == null ? "" : recorrido.getDescripcion().trim();

        if (descripcion.isEmpty()) {
            System.out.println("La descripción del recorrido es obligatoria.");
            return false;
        }

        if (tieneSalidasAsociadas(idRecorrido)) {
            System.out.println("No se puede modificar un recorrido con salidas asociadas.");
            return false;
        }

        Recorrido actual = buscarRecorrido(idRecorrido);

        if (actual == null) {
            return false;
        }

        if (existeDescripcion(descripcion, idRecorrido)) {
            System.out.println("Ya existe otro recorrido con esa descripción.");
            return false;
        }

        double distanciaAnterior = -1;

        for (int i = 0; i < recorrido.getParadas().size(); i++) {
            ParadaRecorrido parada = recorrido.getParadas().get(i);

            if (parada == null || parada.getNombreLugar() == null || parada.getNombreLugar().trim().isEmpty()) {
                System.out.println("Hay una parada sin nombre.");
                return false;
            }

            double distancia = parada.getDistanciaAcumuladaKm();

            if (distancia < 0 || distancia > 20000 || (i == 0 && distancia != 0) || (i > 0 && distancia <= distanciaAnterior)) {
                System.out.println("Las distancias de las paradas no son válidas.");
                return false;
            }

            distanciaAnterior = distancia;
        }

        String sqlActualizar = "UPDATE recorrido SET descripcion = ? WHERE idRecorrido = ?";
        String sqlEliminarParadas = "DELETE FROM paradarecorrido WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sqlActualizar)) {
            ps.setString(1, descripcion);
            ps.setInt(2, idRecorrido);

            if (ps.executeUpdate() == 0) {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar la descripción: " + e.getMessage());
            return false;
        }

        try (PreparedStatement ps = connection.prepareStatement(sqlEliminarParadas)) {
            ps.setInt(1, idRecorrido);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar las paradas anteriores: " + e.getMessage());
            return false;
        }

        ParadaRecorridoData paradaData = new ParadaRecorridoData();

        for (ParadaRecorrido parada : recorrido.getParadas()) {
            parada.setRecorrido(actual);

            if (!paradaData.guardarParadaRecorrido(parada)) {
                System.out.println("No se pudieron guardar todas las paradas.");
                return false;
            }
        }

        return true;
    }


    
    public boolean desactivarRecorrido(int idRecorrido) {

        if (idRecorrido <= 0) {
            System.out.println("El ID del recorrido no es válido.");
            return false;
        }
        
        String sql = "UPDATE recorrido SET estado = FALSE WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al desactivar recorrido: " + e.getMessage());
        }

        return false;
    }

    public boolean eliminarRecorrido(int idRecorrido) {
        
        if (idRecorrido <= 0) {
            System.out.println("El ID del recorrido no es válido.");
            return false;
        }

        if (tieneSalidasAsociadas(idRecorrido)) {
            System.out.println("No se puede eliminar un recorrido que tiene salidas asociadas.");
            return false;
        }

        String sql = "DELETE FROM recorrido WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idRecorrido);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar recorrido: " + e.getMessage());
        }

        return false;
    }
    
}
