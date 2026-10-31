package persistencia;

import modelo.Colectivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColectivoData {

    private Connection connection;

    public ColectivoData() {

        connection = Conexion.getInstancia().getConnection();

    }

    public boolean guardarColectivo(Colectivo colectivo) {

        String sql = "INSERT INTO colectivo"
                + "(matricula, marca, modelo, capacidad, caducidad, estado, chofer) "
                + "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, colectivo.getMatricula());
            ps.setString(2, colectivo.getMarca());
            ps.setString(3, colectivo.getModelo());
            ps.setInt(4, colectivo.getCapacidad());

            if (colectivo.getCaducidad() != null) {
                ps.setDate(5, java.sql.Date.valueOf(colectivo.getCaducidad()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            ps.setBoolean(6, colectivo.isEstado());
            ps.setString(7, colectivo.getChofer());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        colectivo.setIdColectivo(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar colectivo: " + e.getMessage());
        }

        return false;
    }

    public List<Colectivo> listarColectivos() {

        List<Colectivo> colectivos = new ArrayList<>();

        String sql = "SELECT * FROM colectivo ORDER BY idColectivo";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                LocalDate caducidad = null;

                if (rs.getDate("caducidad") != null) {
                    caducidad = rs.getDate("caducidad").toLocalDate();
                }

                Colectivo colectivo = new Colectivo(
                        rs.getInt("idColectivo"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("capacidad"),
                        caducidad,
                        rs.getBoolean("estado"),
                        rs.getString("chofer")
                );

                colectivos.add(colectivo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar colectivos: " + e.getMessage());
        }

        return colectivos;
    }

    public Colectivo buscarColectivo(int idColectivo) {

        String sql = "SELECT * FROM colectivo WHERE idColectivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idColectivo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    LocalDate caducidad = null;

                    if (rs.getDate("caducidad") != null) {
                        caducidad = rs.getDate("caducidad").toLocalDate();
                    }

                    Colectivo colectivo = new Colectivo(
                            rs.getInt("idColectivo"),
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("capacidad"),
                            caducidad,
                            rs.getBoolean("estado"),
                            rs.getString("chofer")
                    );

                    return colectivo;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar colectivo: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizarColectivo(Colectivo colectivo) {

        String sql = "UPDATE colectivo SET "
                + "matricula = ?, marca = ?, modelo = ?, "
                + "capacidad = ?, caducidad = ?, estado = ?, chofer = ? "
                + "WHERE idColectivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, colectivo.getMatricula());
            ps.setString(2, colectivo.getMarca());
            ps.setString(3, colectivo.getModelo());
            ps.setInt(4, colectivo.getCapacidad());

            if (colectivo.getCaducidad() != null) {
                ps.setDate(5, java.sql.Date.valueOf(colectivo.getCaducidad()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setBoolean(6, colectivo.isEstado());
            ps.setString(7, colectivo.getChofer());
            ps.setInt(8, colectivo.getIdColectivo());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar colectivo: " + e.getMessage());
        }

        return false;
    }

    public boolean desactivarColectivo(int idColectivo) {

        String sql = "UPDATE colectivo SET estado = 0 "
                + "WHERE idColectivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idColectivo);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al desactivar colectivo: " + e.getMessage());
        }

        return false;
    }
    
    
    public boolean eliminarColectivo(int idColectivo) {

        String sql = "DELETE FROM colectivo WHERE idColectivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idColectivo);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println(
                    "No se pudo eliminar el colectivo. "
                    + "Puede tener asientos o rutas de viaje asociados: "
                    + e.getMessage()
            );
        }

        return false;
    }


}
