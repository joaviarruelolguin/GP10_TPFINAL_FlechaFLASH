package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pasajero;
import modelo.TipoPasajero;

public class PasajeroData {

    private Connection connection;

    public PasajeroData() {
        connection = Conexion.getInstancia().getConnection();
    }

    public boolean guardarPasajero(Pasajero pasajero) {
        String sql = "INSERT INTO pasajero(dni, nombre, apellido, correo, telefono, tipoPasajero) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, pasajero.getDni());
            ps.setString(2, pasajero.getNombre());
            ps.setString(3, pasajero.getApellido());
            ps.setString(4, pasajero.getCorreo());
            ps.setString(5, pasajero.getTelefono());
            ps.setInt(6, pasajero.getTipoPasajero().getCodigo());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el pasajero: " + e.getMessage());
            return false;
        }
    }

    public boolean borrarPasajero(int idPasajero) {
        String sql = "DELETE FROM pasajero WHERE idPasajero = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idPasajero);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pasajero eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se ha encontrado el Pasajero.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el pasajero.");
            return false;
        }
    }

    public boolean actualizarPasajero(Pasajero pasajero) {
        String sql = "UPDATE pasajero SET dni = ?, nombre = ?, apellido = ?, correo = ?, "
                + "telefono = ?, tipoPasajero = ? WHERE idPasajero = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pasajero.getDni());
            ps.setString(2, pasajero.getNombre());
            ps.setString(3, pasajero.getApellido());
            ps.setString(4, pasajero.getCorreo());
            ps.setString(5, pasajero.getTelefono());
            ps.setInt(6, pasajero.getTipoPasajero().getCodigo());
            ps.setInt(7, pasajero.getIdPasajero());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pasajero actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se pudo actualizar al pasajero.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar pasajero " + e.getMessage());
            return false;
        }
    }

    public Pasajero buscarPasajero(int idPasajero) {
        String sql = "SELECT * FROM pasajero WHERE idPasajero = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, idPasajero);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Pasajero pasajero = new Pasajero(
                        rs.getInt("idPasajero"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        TipoPasajero.desdeCodigo(rs.getInt("tipoPasajero"))
                );

                rs.close();
                ps.close();

                return pasajero;
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {

            System.out.println("Error al buscar pasajero: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Pasajero> listarPasajeros() {

        ArrayList<Pasajero> lista = new ArrayList<>();

        String sql = "SELECT * FROM pasajero";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Pasajero pasajero = new Pasajero(
                        rs.getInt("idPasajero"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        TipoPasajero.desdeCodigo(rs.getInt("tipoPasajero"))
                );

                lista.add(pasajero);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al listar pasajeros: " + e.getMessage());
        }

        return lista;
    }

}
