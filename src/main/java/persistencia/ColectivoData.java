package persistencia;

import modelo.Colectivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Asiento;

public class ColectivoData {

    private Connection connection;

    public ColectivoData() {

        connection = Conexion.getInstancia().getConnection();

    }

    
    // Recuperar los asientos que ya existen en la base de datos.
    // Cada asiento queda asociado al colectivo recuperado.
    // No generar asientos nuevos.
    // La colección asientos se completa mediante agregarAsiento().
    private void cargarAsientos(Colectivo colectivo) {
        String sql = "SELECT idAsiento, numeroAsiento FROM asiento WHERE idColectivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, colectivo.getIdColectivo());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asiento asiento = new Asiento(
                            rs.getInt("idAsiento"),
                            colectivo,
                            rs.getInt("numeroAsiento")
                    );

                    colectivo.agregarAsiento(asiento);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar los asientos del colectivo: " + e.getMessage());
        }
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

                        AsientoData asientoData = new AsientoData();

                        for (Asiento asiento : colectivo.getAsientos()) {
                            if (!asientoData.guardarAsiento(asiento)) {
                                return false;
                            }
                        }
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

                cargarAsientos(colectivo);
                colectivos.add(colectivo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar colectivos: " + e.getMessage());
        }

        return colectivos;
    }
    
    
   
    // Agrego:
    public List<Colectivo> buscarColectivos(Integer id, String matricula, String marca) {
        List<Colectivo> colectivos = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM colectivo WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (id != null) {
            sql.append(" AND idColectivo = ?");
            parametros.add(id);
        }

        if (matricula != null && !matricula.isEmpty()) {
            sql.append(" AND UPPER(matricula) LIKE ?");
            parametros.add("%" + matricula.toUpperCase() + "%");
        }

        if (marca != null && !marca.isEmpty()) {
            sql.append(" AND UPPER(marca) LIKE ?");
            parametros.add("%" + marca.toUpperCase() + "%");
        }

        sql.append(" ORDER BY idColectivo");

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
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

                    cargarAsientos(colectivo);
                    colectivos.add(colectivo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar colectivos: " + e.getMessage());
        }

        return colectivos;
    }

    
    public List<Colectivo> listarColectivosActivos() {

        List<Colectivo> colectivos = new ArrayList<>();
        String sql = "SELECT * FROM colectivo WHERE estado = TRUE ORDER BY idColectivo";

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

                cargarAsientos(colectivo);
                colectivos.add(colectivo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar colectivos activos: " + e.getMessage());
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
                    
                    cargarAsientos(colectivo);
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
    
      public boolean activarColectivo(int idColectivo) {

        String sql = "UPDATE colectivo SET estado = TRUE "
                + "WHERE idColectivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idColectivo);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al activar colectivo: " + e.getMessage());
        }

        return false;
    }
    

    public boolean desactivarColectivo(int idColectivo) {

        String sql = "UPDATE colectivo SET estado = FALSE "
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
            if ("23000".equals(e.getSQLState())) {
                System.out.println("El colectivo tiene registros asociados. Se realizará la baja lógica.");
                return desactivarColectivo(idColectivo);
            } else {
                System.out.println("Error al eliminar el colectivo: " + e.getMessage());
            }
        }

        return false;
    }


}
