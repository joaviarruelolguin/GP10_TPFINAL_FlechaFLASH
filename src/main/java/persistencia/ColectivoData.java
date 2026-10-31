package persistencia;

import modelo.Colectivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import persistencia.Conexion;

public class ColectivoData {

    private Connection connection;

    public ColectivoData() {

        connection = Conexion.getInstancia().getConnection();

    }

    public boolean guardarColectivo(Colectivo colectivo) {

        String sql = "INSERT INTO colectivo"
                + "(matricula, marca, modelo, capacidad,caducidad,estado) "
                + "VALUES (?,?,?,?,?,?)";

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
}
