
package persistencia;

import modelo.Recorrido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RecorridoData {
    
    private Connection connection;

    public RecorridoData() {
        connection = Conexion.getInstancia().getConnection();
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
        }

        return false;
    }
    
    
    
    public boolean guardarRecorrido(Recorrido recorrido) {

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
                    }
                }
                return true;
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
                recorridos.add(recorrido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar recorridos activos: " + e.getMessage());
        }

        return recorridos;
    }
    
    public Recorrido buscarRecorrido(int idRecorrido) {

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
    
    
    
    // Método que incluye la posibilidad de reactivar un recorrido
    public boolean actualizarRecorrido(Recorrido recorrido) {

        if (recorrido.getDescripcion() == null || recorrido.getDescripcion().trim().isEmpty()) {
            return false;
        }

        String descripcion = recorrido.getDescripcion().trim();

        if (existeDescripcion(descripcion, recorrido.getIdRecorrido())) {
            
            System.out.println("Ya existe otro recorrido con esa descripción.");
            return false;
        }

        String sql = "UPDATE recorrido SET descripcion = ?, estado = ? WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, descripcion);
            ps.setBoolean(2, recorrido.isEstado());
            ps.setInt(3, recorrido.getIdRecorrido());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar recorrido: " + e.getMessage());
        }

        return false;
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

        String sql = "DELETE FROM recorrido WHERE idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println(
                    "No se pudo eliminar el recorrido. "
                    + "Puede tener rutas de viaje o paradas asociadas: "
                    + e.getMessage()
            );
        }

        return false;
    }
    
    
}
