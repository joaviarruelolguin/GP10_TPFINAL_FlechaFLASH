
package persistencia;

import modelo.ParadaRecorrido;
import modelo.Recorrido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParadaRecorridoData {
    
    private Connection connection;

    public ParadaRecorridoData() {
        connection = Conexion.getInstancia().getConnection();
    }
   
    private boolean tieneSalidasAsociadas(int idRecorrido) {
        
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

    private boolean existeOrden(int idRecorrido, int orden) {

        String sql = "SELECT idParadaRecorrido FROM paradarecorrido WHERE idRecorrido = ? AND orden = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);
            ps.setInt(2, orden);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar orden: " + e.getMessage());
        }

        return false;
    }
    
    
    // Método para controlar que las distancias acumuladas de las paradas de un mismo recorrido respeten el orden del trayecto.
    // Ya que distanciaAcumuladaKm representa la distancia desde el punto de partida hasta cada parada, 
    // no la distancia entre dos paradas consecutivas.
    private boolean distanciaValida(int idRecorrido, int orden, double distancia) {
        String sql = "SELECT orden, distanciaAcumuladaKm FROM paradarecorrido WHERE idRecorrido = ? ORDER BY orden";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {
                
                while (rs.next()) {
                    
                    int ordenExistente = rs.getInt("orden");
                    double distanciaExistente = rs.getDouble("distanciaAcumuladaKm");

                    if (ordenExistente < orden && distanciaExistente >= distancia) {
                        return false;
                    }

                    if (ordenExistente > orden && distanciaExistente <= distancia) {
                        return false;
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al validar la distancia: " + ex.getMessage());
            return false;
        }

        return true;
    }
    
    
    public boolean guardarParadaRecorrido(ParadaRecorrido parada) {

        if (parada == null) {
            return false;
        }
        
        if (parada.getNombreLugar() == null || parada.getNombreLugar().trim().isEmpty()) {
            return false;
        }

        if (parada.getRecorrido() == null || parada.getRecorrido().getIdRecorrido() <= 0) {
            return false;
        }

        if (parada.getOrden() <= 0 || parada.getDistanciaAcumuladaKm() < 0 || parada.getDistanciaAcumuladaKm() > 20000) {
            return false;
        }

        int idRecorrido = parada.getRecorrido().getIdRecorrido();

        if (tieneSalidasAsociadas(idRecorrido)) {
            System.out.println("No se pueden agregar paradas a un recorrido con salidas asociadas.");
            return false;
        }

        
        if (existeOrden(idRecorrido, parada.getOrden())) {
            
            System.out.println("Ya existe una parada con ese orden en el recorrido.");
            return false;
        }

        if (!distanciaValida(idRecorrido, parada.getOrden(), parada.getDistanciaAcumuladaKm())) {
            
            System.out.println("La distancia acumulada no es coherente con el orden de las paradas.");
            return false;
        }

        String sql = "INSERT INTO paradarecorrido (idRecorrido, nombreLugar, orden, distanciaAcumuladaKm) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idRecorrido);
            ps.setString(2, parada.getNombreLugar().trim());
            ps.setInt(3, parada.getOrden());
            ps.setDouble(4, parada.getDistanciaAcumuladaKm());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    
                    if (rs.next()) {
                        
                        parada.setIdParadaRecorrido(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar parada: " + e.getMessage());
        }

        return false;
    }
    
    
    public List<ParadaRecorrido> listarParadasPorRecorrido(int idRecorrido) {

        List<ParadaRecorrido> paradas = new ArrayList<>();

        String sql = "SELECT * FROM paradarecorrido "
                + "WHERE idRecorrido = ? ORDER BY orden";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Recorrido recorrido = new Recorrido();
                    recorrido.setIdRecorrido(rs.getInt("idRecorrido"));

                    ParadaRecorrido parada = new ParadaRecorrido(
                            rs.getInt("idParadaRecorrido"),
                            recorrido,
                            rs.getString("nombreLugar"),
                            rs.getInt("orden"),
                            rs.getDouble("distanciaAcumuladaKm")
                    );

                    paradas.add(parada);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar paradas del recorrido: " + e.getMessage());
        }

        return paradas;
    }

    
    
    
    public ParadaRecorrido buscarParadaRecorrido(int idParadaRecorrido) {

        String sql = "SELECT * FROM paradarecorrido "
                + "WHERE idParadaRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idParadaRecorrido);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Recorrido recorrido = new Recorrido();
                    recorrido.setIdRecorrido(rs.getInt("idRecorrido"));

                    ParadaRecorrido parada = new ParadaRecorrido(
                            rs.getInt("idParadaRecorrido"),
                            recorrido,
                            rs.getString("nombreLugar"),
                            rs.getInt("orden"),
                            rs.getDouble("distanciaAcumuladaKm")
                    );

                    return parada;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar parada: " + e.getMessage());
        }

        return null;
    }
    
    
    
    private boolean existeOrden(int idRecorrido, int orden, int idParadaRecorrido) {

        String sql = "SELECT idParadaRecorrido FROM paradarecorrido "
                + "WHERE idRecorrido = ? AND orden = ? "
                + "AND idParadaRecorrido <> ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);
            ps.setInt(2, orden);
            ps.setInt(3, idParadaRecorrido);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar orden: " + e.getMessage());
        }

        return false;
    }
    
    
    // Método (sobrecarga) para validar que la distancia sea coherente tanto con la parada anterior como con la siguiente.
    private boolean distanciaValida(int idRecorrido, int orden, double distancia, int idParadaRecorrido) {

        String sql = "SELECT orden, distanciaAcumuladaKm FROM paradarecorrido "
                + "WHERE idRecorrido = ? AND idParadaRecorrido <> ? "
                + "ORDER BY orden";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);
            ps.setInt(2, idParadaRecorrido);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    int ordenExistente = rs.getInt("orden");
                    double distanciaExistente = rs.getDouble("distanciaAcumuladaKm");

                    if (ordenExistente < orden && distanciaExistente >= distancia) {
                        return false;
                    }

                    if (ordenExistente > orden && distanciaExistente <= distancia) {
                        return false;
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al comprobar distancia: " + e.getMessage());
        }

        return false;
    }

    
    public boolean actualizarParadaRecorrido(ParadaRecorrido parada) {

        if (parada == null) {
            return false;
        }

        if (parada.getIdParadaRecorrido() <= 0) {
            return false;
        }

        if (parada.getNombreLugar() == null || parada.getNombreLugar().trim().isEmpty()) {
            return false;
        }

        if (parada.getRecorrido() == null || parada.getRecorrido().getIdRecorrido() <= 0) {
            return false;
        }

        if (parada.getOrden() <= 0 || parada.getDistanciaAcumuladaKm() < 0 || parada.getDistanciaAcumuladaKm() > 20000) {
            return false;
        }

        int idRecorrido = parada.getRecorrido().getIdRecorrido();
        
         if (tieneSalidasAsociadas(idRecorrido)) {
            System.out.println("No se pueden modificar paradas de un recorrido con salidas asociadas.");
            return false;
        }

        if (existeOrden(idRecorrido, parada.getOrden(), parada.getIdParadaRecorrido())) {
            
            System.out.println("Ya existe otra parada con ese orden en el recorrido.");
            return false;
        }

        if (!distanciaValida(idRecorrido, parada.getOrden(), parada.getDistanciaAcumuladaKm(), parada.getIdParadaRecorrido())) {
            
            System.out.println("La distancia no es coherente con las demás paradas.");
            return false;
        }

        String sql = "UPDATE paradarecorrido SET nombreLugar = ?, orden = ?, distanciaAcumuladaKm = ? "
                + "WHERE idParadaRecorrido = ? AND idRecorrido = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, parada.getNombreLugar().trim());
            ps.setInt(2, parada.getOrden());
            ps.setDouble(3, parada.getDistanciaAcumuladaKm());
            ps.setInt(4, parada.getIdParadaRecorrido());
            ps.setInt(5, idRecorrido);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar parada: " + e.getMessage());
        }

        return false;
    }

    
    public boolean eliminarParadaRecorrido(int idParadaRecorrido) {
        
        if (idParadaRecorrido <= 0) {
            return false;
        }
        
        String sqlBuscar = "SELECT idRecorrido FROM paradarecorrido WHERE idParadaRecorrido = ?";
        String sqlEliminar = "DELETE FROM paradarecorrido WHERE idParadaRecorrido = ?";
        String sqlListar = "SELECT idParadaRecorrido FROM paradarecorrido WHERE idRecorrido = ? ORDER BY orden";
        String sqlActualizar = "UPDATE paradarecorrido SET orden = ? WHERE idParadaRecorrido = ?";

        boolean autoCommitAnterior = true;

        try {
            autoCommitAnterior = connection.getAutoCommit();
            connection.setAutoCommit(false);

            int idRecorrido;

            // Obtener el recorrido de la parada
            try (PreparedStatement ps = connection.prepareStatement(sqlBuscar)) {
                ps.setInt(1, idParadaRecorrido);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        connection.rollback();
                        return false;
                    }
                    idRecorrido = rs.getInt("idRecorrido");
                }
            }
            
            
            if (tieneSalidasAsociadas(idRecorrido)) {
                System.out.println("No se pueden eliminar paradas de un recorrido con salidas asociadas.");
                connection.rollback();
                return false;
            }

            // Eliminar la parada
            try (PreparedStatement ps = connection.prepareStatement(sqlEliminar)) {
                ps.setInt(1, idParadaRecorrido);

                if (ps.executeUpdate() == 0) {
                    connection.rollback();
                    return false;
                }
            }

            // Obtener los identificadores de las paradas restantes
            List<Integer> idsParadas = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sqlListar)) {
                ps.setInt(1, idRecorrido);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        idsParadas.add(rs.getInt("idParadaRecorrido"));
                    }
                }
            }

            // Renumerar las paradas desde 1
            try (PreparedStatement ps = connection.prepareStatement(sqlActualizar)) {
                int nuevoOrden = 1;

                for (int id : idsParadas) {
                    ps.setInt(1, nuevoOrden++);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                }
            }

            connection.commit();
            return true;

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.out.println("Error al revertir la operación: " + e.getMessage());
            }

            System.out.println("Error al eliminar la parada: " + ex.getMessage());
            return false;

        } finally {
            try {
                connection.setAutoCommit(autoCommitAnterior);
            } catch (SQLException ex) {
                System.out.println("Error al restablecer AutoCommit: " + ex.getMessage());
            }
        }
    }

    
}
