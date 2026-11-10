package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Colectivo;
import modelo.HorarioParadaViaje;
import modelo.Recorrido;
import modelo.RutaViaje;

public class RutaViajeData {

    private Connection connection;  // conexion con base de datos

    private HorarioParadaViajeData horarioData;

    // Acceso de RutaViajeData al DAO de horarios para poder delegar en HorarioParadaViajeData 
    // las operaciones de guardar y recuperar los horarios, sin duplicar consultas SQL.
    public RutaViajeData() {

        connection = Conexion.getInstancia().getConnection();
        horarioData = new HorarioParadaViajeData();

    }

    // INSERTAMOS UNA NUEVA RUTA
    public boolean guardarRutaViaje(RutaViaje ruta) {

        if (ruta == null) {
            System.out.println("No se puede guardar una ruta nula.");
            return false;
        }

        if (ruta.getRecorrido() == null || ruta.getColectivo() == null) {
            System.out.println("La ruta debe tener un recorrido y un colectivo.");
            return false;
        }

        if (ruta.getFechaHoraSalida() == null
                || ruta.getFechaHoraLlegada() == null) {
            System.out.println("Las fechas de salida y llegada son obligatorias.");
            return false;
        }

        if (!ruta.getFechaHoraLlegada().isAfter(ruta.getFechaHoraSalida())) {
            System.out.println("La llegada debe ser posterior a la salida.");
            return false;
        }

        if (ruta.getPrecioBase() == null) {
            System.out.println("El precio base es obligatorio.");
            return false;
        }

        String sql = "INSERT INTO rutaviaje "
                + "(idRecorrido, idColectivo, fechaHoraSalida, "
                + "fechaHoraLlegada, precioBase, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, ruta.getRecorrido().getIdRecorrido());
            ps.setInt(2, ruta.getColectivo().getIdColectivo());

            ps.setTimestamp(3,
                    Timestamp.valueOf(ruta.getFechaHoraSalida()));

            ps.setTimestamp(4,
                    Timestamp.valueOf(ruta.getFechaHoraLlegada()));

            ps.setBigDecimal(5, ruta.getPrecioBase());
            ps.setBoolean(6, ruta.isEstado());
            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        ruta.setIdRutaViaje(rs.getInt(1));
                    }
                }

                for (HorarioParadaViaje horario : ruta.getHorarios()) {
                    
                    horario.setRutaViaje(ruta);
                    
                    if (!horarioData.guardarHorarioParadaViaje(horario)) {
                        System.out.println("No se pudo guardar uno de los horarios.");
                        return false;
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar la ruta: "
                    + e.getMessage());
        }

        return false;
    }

    // LISTAMOS O RECUPERMAOS LAS LISTAS YA CREADAS
    public List<RutaViaje> listarRutasViaje() {

        List<RutaViaje> rutas = new ArrayList<>();

        String sql = "SELECT "
                + "rv.idRutaViaje, rv.fechaHoraSalida, "
                + "rv.fechaHoraLlegada, rv.precioBase, rv.estado, "
                + "r.idRecorrido AS recorridoId, "
                + "r.descripcion AS recorridoDescripcion, "
                + "r.estado AS recorridoEstado, "
                + "c.idColectivo AS colectivoId, "
                + "c.matricula, c.marca, c.modelo, "
                + "c.capacidad, c.caducidad, c.chofer, "
                + "c.estado AS colectivoEstado "
                + "FROM rutaviaje rv "
                + "INNER JOIN recorrido r "
                + "ON rv.idRecorrido = r.idRecorrido "
                + "INNER JOIN colectivo c "
                + "ON rv.idColectivo = c.idColectivo "
                + "ORDER BY rv.fechaHoraSalida";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Recorrido recorrido = new Recorrido(
                        rs.getInt("recorridoId"),
                        rs.getString("recorridoDescripcion"),
                        rs.getBoolean("recorridoEstado")
                );

                Date fechaCaducidadSQL = rs.getDate("caducidad");

                LocalDate caducidad = null;

                if (fechaCaducidadSQL != null) {
                    caducidad = fechaCaducidadSQL.toLocalDate();
                }

                Colectivo colectivo = new Colectivo(
                        rs.getInt("colectivoId"),
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("capacidad"),
                        caducidad,
                        rs.getBoolean("colectivoEstado"),
                        rs.getString("chofer")
                );

                RutaViaje ruta = new RutaViaje(
                        rs.getInt("idRutaViaje"),
                        recorrido,
                        colectivo,
                        rs.getTimestamp("fechaHoraSalida").toLocalDateTime(),
                        rs.getTimestamp("fechaHoraLlegada").toLocalDateTime(),
                        rs.getBigDecimal("precioBase"),
                        rs.getBoolean("estado")
                );

                List<HorarioParadaViaje> horarios
                        = horarioData.listarHorariosPorRuta(ruta.getIdRutaViaje());

                ruta.setHorarios(horarios); // setHorarios llama a agregarHorario()

                rutas.add(ruta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las rutas: "
                    + e.getMessage());
        }

        return rutas;

    }

    // BUSCAMOS UNA RUTA POR SU ID
    public RutaViaje buscarRutaViaje(int idRutaViaje) {

        String sql = "SELECT "
                + "rv.idRutaViaje, rv.fechaHoraSalida, "
                + "rv.fechaHoraLlegada, rv.precioBase, rv.estado, "
                + "r.idRecorrido AS recorridoId, "
                + "r.descripcion AS recorridoDescripcion, "
                + "r.estado AS recorridoEstado, "
                + "c.idColectivo AS colectivoId, "
                + "c.matricula, c.marca, c.modelo, "
                + "c.capacidad, c.caducidad, c.chofer, "
                + "c.estado AS colectivoEstado "
                + "FROM rutaviaje rv "
                + "INNER JOIN recorrido r "
                + "ON rv.idRecorrido = r.idRecorrido "
                + "INNER JOIN colectivo c "
                + "ON rv.idColectivo = c.idColectivo "
                + "WHERE rv.idRutaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRutaViaje);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Recorrido recorrido = new Recorrido(
                            rs.getInt("recorridoId"),
                            rs.getString("recorridoDescripcion"),
                            rs.getBoolean("recorridoEstado")
                    );

                    java.sql.Date fechaCaducidadSQL
                            = rs.getDate("caducidad");

                    java.time.LocalDate caducidad = null;

                    if (fechaCaducidadSQL != null) {
                        caducidad = fechaCaducidadSQL.toLocalDate();
                    }

                    Colectivo colectivo = new Colectivo(
                            rs.getInt("colectivoId"),
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("capacidad"),
                            caducidad,
                            rs.getBoolean("colectivoEstado"),
                            rs.getString("chofer")
                    );

                    RutaViaje ruta = new RutaViaje(
                            rs.getInt("idRutaViaje"),
                            recorrido,
                            colectivo,
                            rs.getTimestamp("fechaHoraSalida")
                                    .toLocalDateTime(),
                            rs.getTimestamp("fechaHoraLlegada")
                                    .toLocalDateTime(),
                            rs.getBigDecimal("precioBase"),
                            rs.getBoolean("estado")
                    );

                    List<HorarioParadaViaje> horarios
                            = horarioData.listarHorariosPorRuta(ruta.getIdRutaViaje());

                    ruta.setHorarios(horarios);

                    return ruta;

                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la ruta: "
                    + e.getMessage());
        }

        return null;

    }

    private boolean sincronizarHorarios(RutaViaje ruta) {

        List<HorarioParadaViaje> horariosBD
                = horarioData.listarHorariosPorRuta(ruta.getIdRutaViaje());

        // Actualizamos o guardamos los horarios recibidos.
        for (HorarioParadaViaje horario : ruta.getHorarios()) {

            horario.setRutaViaje(ruta);

            if (horario.getIdHorarioParadaViaje() > 0) {

                boolean pertenece = false;

                for (HorarioParadaViaje horarioBD : horariosBD) {
                    if (horario.getIdHorarioParadaViaje()
                            == horarioBD.getIdHorarioParadaViaje()) {
                        pertenece = true;
                        break;
                    }
                }

                if (!pertenece) {
                    System.out.println("El horario no pertenece a esta ruta.");
                    return false;
                }

                if (!horarioData.actualizarHorarioParadaViaje(horario)) {
                    return false;
                }

            } else {

                horario.setRutaViaje(ruta);
                
                if (!horarioData.guardarHorarioParadaViaje(horario)) {
                    return false;
                }
            }
        }

        // Eliminamos los horarios que ya no están en la lista recibida.
        for (HorarioParadaViaje horarioBD : horariosBD) {

            boolean existe = false;

            for (HorarioParadaViaje horario : ruta.getHorarios()) {
                if (horario.getIdHorarioParadaViaje()
                        == horarioBD.getIdHorarioParadaViaje()) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                if (!horarioData.eliminarHorarioParadaViaje(
                        horarioBD.getIdHorarioParadaViaje())) {
                    return false;
                }
            }
        }

        return true;
    }

    // MODIFICACOMS UNA RUTA QUE YA EXISTIA 
    public boolean actualizarRutaViaje(RutaViaje ruta) {

        if (ruta == null) {
            System.out.println("No se puede actualizar una ruta nula.");
            return false;
        }

        if (ruta.getRecorrido() == null || ruta.getColectivo() == null) {
            System.out.println("La ruta debe tener un recorrido y un colectivo.");
            return false;
        }

        if (ruta.getFechaHoraSalida() == null || ruta.getFechaHoraLlegada() == null) {
            System.out.println("Las fechas de salida y llegada son obligatorias.");
            return false;
        }

        if (!ruta.getFechaHoraLlegada().isAfter(ruta.getFechaHoraSalida())) {
            System.out.println("La llegada debe ser posterior a la salida.");
            return false;
        }

        String sql = "UPDATE rutaviaje SET "
                + "idRecorrido = ?, "
                + "idColectivo = ?, "
                + "fechaHoraSalida = ?, "
                + "fechaHoraLlegada = ?, "
                + "precioBase = ?, "
                + "estado = ? "
                + "WHERE idRutaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, ruta.getRecorrido().getIdRecorrido());
            ps.setInt(2, ruta.getColectivo().getIdColectivo());

            ps.setTimestamp(3,
                    Timestamp.valueOf(ruta.getFechaHoraSalida()));

            ps.setTimestamp(4,
                    Timestamp.valueOf(ruta.getFechaHoraLlegada()));

            ps.setBigDecimal(5, ruta.getPrecioBase());
            ps.setBoolean(6, ruta.isEstado());

            ps.setInt(7, ruta.getIdRutaViaje());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return sincronizarHorarios(ruta);
            }

            return false;

        } catch (SQLException e) {
            System.out.println("Error al actualizar la ruta: "
                    + e.getMessage());
        }

        return false;

    }

    // REALIZA LA BAJA LOGICA 
    public boolean desactivarRutaViaje(int idRutaViaje) {

        String sql = "UPDATE rutaviaje "
                + "SET estado = FALSE "
                + "WHERE idRutaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRutaViaje);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al desactivar la ruta: "
                    + e.getMessage());
        }

        return false;

    }

    // REALIZA EL ALTA LOGICA
    public boolean activarRutaViaje(int idRutaViaje) {
        String sql = "UPDATE rutaviaje SET estado = TRUE WHERE idRutaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idRutaViaje);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al activar la ruta: " + e.getMessage());
        }

        return false;
    }

   
    // REALIZA LA BAJA FISICA (solo la que coincida con el ID)
    public boolean eliminarRutaViaje(int idRutaViaje) {

        String sql = "DELETE FROM rutaviaje "
                + "WHERE idRutaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRutaViaje);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                System.out.println("La ruta tiene pasajes asociados. Se realizará la baja lógica.");
                return desactivarRutaViaje(idRutaViaje);
            } else {
                System.out.println("Error al eliminar la ruta: " + e.getMessage());
            }
        }

        return false;

    }

}
