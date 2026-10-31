
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Asiento;
import modelo.Colectivo;


public class AsientoData {
    
    private Connection connection;

    public AsientoData() {
        connection = Conexion.getInstancia().getConnection();
    }
    
    
    public boolean guardarAsiento(Asiento asiento) {
        String sql = "INSERT INTO asiento (idColectivo, numeroAsiento) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, asiento.getColectivo().getIdColectivo());
            ps.setInt(2, asiento.getNumeroAsiento());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el asiento: " + e.getMessage());
            return false;
        }
    }
    
    
    
    public ArrayList<Asiento> listarAsientosPorColectivo(Colectivo colectivo) {
        ArrayList<Asiento> lista = new ArrayList<>();

        String sql = "SELECT a.idAsiento, a.numeroAsiento, "
                + "c.idColectivo, c.matricula, c.marca, c.modelo, "
                + "c.capacidad, c.caducidad, c.estado, c.chofer "
                + "FROM asiento a "
                + "JOIN colectivo c ON a.idColectivo = c.idColectivo "
                + "WHERE c.idColectivo = ? "
                + "ORDER BY a.numeroAsiento";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, colectivo.getIdColectivo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Colectivo col = new Colectivo(
                            rs.getInt("idColectivo"),
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("capacidad"),
                            rs.getDate("caducidad").toLocalDate(),
                            rs.getBoolean("estado"),
                            rs.getString("chofer")
                    );

                    Asiento asiento = new Asiento(
                            rs.getInt("idAsiento"),
                            col,
                            rs.getInt("numeroAsiento")
                    );

                    lista.add(asiento);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar asientos por colectivo: " + e.getMessage());
        }

        return lista;
    }
    
      public Asiento buscarAsiento(int idAsiento) {
        String sql = "SELECT a.idAsiento, a.numeroAsiento, "
                + "c.idColectivo, c.matricula, c.marca, c.modelo, "
                + "c.capacidad, c.caducidad, c.estado, c.chofer "
                + "FROM asiento a "
                + "JOIN colectivo c ON a.idColectivo = c.idColectivo "
                + "WHERE a.idAsiento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idAsiento);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Colectivo colectivo = new Colectivo(
                            rs.getInt("idColectivo"),
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("capacidad"),
                            rs.getDate("caducidad").toLocalDate(),
                            rs.getBoolean("estado"),
                            rs.getString("chofer")
                    );

                    return new Asiento(
                            rs.getInt("idAsiento"),
                            colectivo,
                            rs.getInt("numeroAsiento")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar asiento: " + e.getMessage());
        }

        return null;
    }

      
      
    public boolean actualizarAsiento(Asiento asiento) {
        String sql = "UPDATE asiento SET idColectivo = ?, numeroAsiento = ? "
                + "WHERE idAsiento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, asiento.getColectivo().getIdColectivo());
            ps.setInt(2, asiento.getNumeroAsiento());
            ps.setInt(3, asiento.getIdAsiento());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Asiento actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se pudo actualizar el asiento.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar asiento: " + e.getMessage());
            return false;
        }
    }

    
    public boolean eliminarAsiento(int idAsiento) {
        String sql = "DELETE FROM asiento WHERE idAsiento = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idAsiento);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Asiento eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el asiento.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el asiento: " + e.getMessage());
            return false;
        }
    }

}

