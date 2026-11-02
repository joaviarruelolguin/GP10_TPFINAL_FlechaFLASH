
package persistencia;

import java.sql.Connection;
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
import modelo.ParadaRecorrido;

public class HorarioParadaViajeData {
       
    private Connection connection;

    public HorarioParadaViajeData() {
        connection = Conexion.getInstancia().getConnection();
    }
    
    
    public boolean guardarHorarioParadaViaje(HorarioParadaViaje horario) {

        if (horario == null
                || horario.getRutaViaje() == null
                || horario.getParadaRecorrido() == null
                || horario.getRutaViaje().getRecorrido() == null
                || horario.getParadaRecorrido().getRecorrido() == null) {
            return false;
        }

        int idRuta = horario.getRutaViaje().getIdRutaViaje();
        int idParada = horario.getParadaRecorrido().getIdParadaRecorrido();

        int idRecorridoRuta = horario.getRutaViaje().getRecorrido().getIdRecorrido();
        int idRecorridoParada = horario.getParadaRecorrido().getRecorrido().getIdRecorrido();

        if (idRuta <= 0 || idParada <= 0
                || idRecorridoRuta <= 0
                || idRecorridoParada <= 0) {
            return false;
        }

        if (idRecorridoRuta != idRecorridoParada) {
            System.out.println("La parada no pertenece al recorrido de la ruta.");
            return false;
        }

        if (horario.getFechaHoraLlegada() == null
                || horario.getFechaHoraSalida() == null) {
            return false;
        }

        if (horario.getFechaHoraSalida().isBefore(horario.getFechaHoraLlegada())) {
            System.out.println("La salida no puede ser anterior a la llegada.");
            return false;
        }

        String sql = "INSERT INTO horarioparadaviaje "
                + "(idRutaViaje, idRecorrido, idParadaRecorrido, "
                + "fechaHoraLlegada, fechaHoraSalida) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idRuta);
            ps.setInt(2, idRecorridoRuta);
            ps.setInt(3, idParada);

            ps.setTimestamp(4,
                    Timestamp.valueOf(horario.getFechaHoraLlegada()));

            ps.setTimestamp(5,
                    Timestamp.valueOf(horario.getFechaHoraSalida()));

            int filas = ps.executeUpdate();

            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        horario.setIdHorarioParadaViaje(rs.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar horario: " + e.getMessage());
        }

        return false;
    }
    
    
    
    public List<HorarioParadaViaje> listarHorariosPorRuta(int idRutaViaje) {

        List<HorarioParadaViaje> horarios = new ArrayList<>();

        if (idRutaViaje <= 0) {
            return horarios;
        }

        String sql = "SELECT "
                + "hpv.idHorarioParadaViaje, "
                + "hpv.fechaHoraLlegada AS llegadaParada, "
                + "hpv.fechaHoraSalida AS salidaParada, "
                + "rv.idRutaViaje, rv.fechaHoraSalida AS salidaRuta, "
                + "rv.fechaHoraLlegada AS llegadaRuta, "
                + "rv.precioBase, rv.estado AS estadoRuta, "
                + "r.idRecorrido, r.descripcion, r.estado AS estadoRecorrido, "
                + "c.idColectivo, c.matricula, c.marca, c.modelo, "
                + "c.capacidad, c.caducidad, c.chofer, c.estado AS estadoColectivo, "
                + "pr.idParadaRecorrido, pr.nombreLugar, pr.orden, "
                + "pr.distanciaAcumuladaKm "
                + "FROM horarioparadaviaje hpv "
                + "INNER JOIN rutaviaje rv "
                + "ON hpv.idRutaViaje = rv.idRutaViaje "
                + "INNER JOIN recorrido r "
                + "ON rv.idRecorrido = r.idRecorrido "
                + "INNER JOIN colectivo c "
                + "ON rv.idColectivo = c.idColectivo "
                + "INNER JOIN paradarecorrido pr "
                + "ON hpv.idParadaRecorrido = pr.idParadaRecorrido "
                + "AND hpv.idRecorrido = pr.idRecorrido "
                + "WHERE hpv.idRutaViaje = ? "
                + "ORDER BY pr.orden";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRutaViaje);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Recorrido recorrido = new Recorrido(
                            rs.getInt("idRecorrido"),
                            rs.getString("descripcion"),
                            rs.getBoolean("estadoRecorrido")
                    );

                    java.sql.Date fechaCaducidadSQL = rs.getDate("caducidad");

                    LocalDate caducidad = null;

                    if (fechaCaducidadSQL != null) {
                        caducidad = fechaCaducidadSQL.toLocalDate();
                    }

                    Colectivo colectivo = new Colectivo(
                            rs.getInt("idColectivo"),
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("capacidad"),
                            caducidad,
                            rs.getBoolean("estadoColectivo"),
                            rs.getString("chofer")
                    );

                    RutaViaje ruta = new RutaViaje(
                            rs.getInt("idRutaViaje"),
                            recorrido,
                            colectivo,
                            rs.getTimestamp("salidaRuta").toLocalDateTime(),
                            rs.getTimestamp("llegadaRuta").toLocalDateTime(),
                            rs.getBigDecimal("precioBase"),
                            rs.getBoolean("estadoRuta")
                    );

                    ParadaRecorrido parada = new ParadaRecorrido(
                            rs.getInt("idParadaRecorrido"),
                            recorrido,
                            rs.getString("nombreLugar"),
                            rs.getInt("orden"),
                            rs.getDouble("distanciaAcumuladaKm")
                    );

                    HorarioParadaViaje horario = new HorarioParadaViaje(
                            rs.getInt("idHorarioParadaViaje"),
                            ruta,
                            parada,
                            rs.getTimestamp("llegadaParada").toLocalDateTime(),
                            rs.getTimestamp("salidaParada").toLocalDateTime()
                    );

                    horarios.add(horario);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar horarios: " + e.getMessage());
        }

        return horarios;
    }
    
    
    public HorarioParadaViaje buscarHorarioParadaViaje(int idHorarioParadaViaje) {

        if (idHorarioParadaViaje <= 0) {
            return null;
        }

        String sql = "SELECT idRutaViaje FROM horarioparadaviaje "
                + "WHERE idHorarioParadaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idHorarioParadaViaje);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int idRutaViaje = rs.getInt("idRutaViaje");

                    List<HorarioParadaViaje> horarios
                            = listarHorariosPorRuta(idRutaViaje);

                    for (HorarioParadaViaje horario : horarios) {

                        if (horario.getIdHorarioParadaViaje()
                                == idHorarioParadaViaje) {
                            return horario;
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar horario: " + e.getMessage());
        }

        return null;
    }

    
    public boolean actualizarHorarioParadaViaje(HorarioParadaViaje horario) {

        if (horario == null
                || horario.getIdHorarioParadaViaje() <= 0
                || horario.getRutaViaje() == null
                || horario.getParadaRecorrido() == null
                || horario.getRutaViaje().getRecorrido() == null
                || horario.getParadaRecorrido().getRecorrido() == null) {
            return false;
        }

        int idRuta = horario.getRutaViaje().getIdRutaViaje();
        int idParada = horario.getParadaRecorrido().getIdParadaRecorrido();

        int idRecorridoRuta = horario.getRutaViaje().getRecorrido().getIdRecorrido();
        int idRecorridoParada = horario.getParadaRecorrido().getRecorrido().getIdRecorrido();

        if (idRuta <= 0 || idParada <= 0
                || idRecorridoRuta <= 0 || idRecorridoParada <= 0) {
            return false;
        }

        if (idRecorridoRuta != idRecorridoParada) {
            System.out.println("La parada no pertenece al recorrido de la ruta.");
            return false;
        }

        if (horario.getFechaHoraLlegada() == null
                || horario.getFechaHoraSalida() == null) {
            return false;
        }

        if (horario.getFechaHoraSalida().isBefore(horario.getFechaHoraLlegada())) {
            System.out.println("La salida no puede ser anterior a la llegada.");
            return false;
        }

        String sql = "UPDATE horarioparadaviaje "
                + "SET idRutaViaje = ?, idRecorrido = ?, idParadaRecorrido = ?, "
                + "fechaHoraLlegada = ?, fechaHoraSalida = ? "
                + "WHERE idHorarioParadaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idRuta);
            ps.setInt(2, idRecorridoRuta);
            ps.setInt(3, idParada);

            ps.setTimestamp(4,
                    Timestamp.valueOf(horario.getFechaHoraLlegada()));

            ps.setTimestamp(5,
                    Timestamp.valueOf(horario.getFechaHoraSalida()));

            ps.setInt(6, horario.getIdHorarioParadaViaje());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar horario: " + e.getMessage());
        }

        return false;
    }
    
    
    public boolean eliminarHorarioParadaViaje(int idHorarioParadaViaje) {

        if (idHorarioParadaViaje <= 0) {
            return false;
        }

        String sql = "DELETE FROM horarioparadaviaje "
                + "WHERE idHorarioParadaViaje = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idHorarioParadaViaje);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar horario: " + e.getMessage());
        }

        return false;
    }
    
}
