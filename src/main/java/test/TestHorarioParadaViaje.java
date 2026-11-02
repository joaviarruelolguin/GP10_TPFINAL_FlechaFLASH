package test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import modelo.Colectivo;
import modelo.HorarioParadaViaje;
import modelo.ParadaRecorrido;
import modelo.Recorrido;
import modelo.RutaViaje;
import persistencia.ColectivoData;
import persistencia.HorarioParadaViajeData;
import persistencia.ParadaRecorridoData;
import persistencia.RutaViajeData;

public class TestHorarioParadaViaje {

    public static void main(String[] args) {

        RutaViajeData rutaData = new RutaViajeData();
        HorarioParadaViajeData horarioData = new HorarioParadaViajeData();
        ColectivoData colectivoData = new ColectivoData();
        ParadaRecorridoData paradaData = new ParadaRecorridoData();

        // 1. Recuperamos los datos existentes
        Recorrido recorrido = new Recorrido(3, "Recorrido de prueba", true);
        Colectivo colectivo = colectivoData.buscarColectivo(1);

        if (colectivo == null) {
            System.out.println("No se encontró el colectivo.");
            return;
        }

        List<ParadaRecorrido> paradas =
                paradaData.listarParadasPorRecorrido(3);

        if (paradas.isEmpty()) {
            System.out.println("El recorrido no tiene paradas.");
            return;
        }

        // 2. Creamos y guardamos una ruta nueva, sin horarios
        RutaViaje ruta = new RutaViaje(
                recorrido,
                colectivo,
                LocalDateTime.of(2026, 11, 3, 8, 0),
                LocalDateTime.of(2026, 11, 3, 9, 0),
                new BigDecimal("3500.00"),
                true
        );

        if (!rutaData.guardarRutaViaje(ruta)) {
            System.out.println("No se pudo guardar la ruta de prueba.");
            return;
        }

        System.out.println("Ruta creada. ID: " + ruta.getIdRutaViaje());

        // 3. GUARDAR HORARIO
        HorarioParadaViaje horario = new HorarioParadaViaje(
                ruta,
                paradas.get(0),
                LocalDateTime.of(2026, 11, 3, 8, 0),
                LocalDateTime.of(2026, 11, 3, 8, 5)
        );

        boolean guardado = horarioData.guardarHorarioParadaViaje(horario);

        System.out.println("\nGUARDAR HORARIO:");
        System.out.println(guardado);
        System.out.println("ID generado: " + horario.getIdHorarioParadaViaje());

        if (!guardado) {
            rutaData.eliminarRutaViaje(ruta.getIdRutaViaje());
            return;
        }

        // 4. LISTAR HORARIOS
        System.out.println("\nLISTAR HORARIOS:");

        for (HorarioParadaViaje h :
                horarioData.listarHorariosPorRuta(ruta.getIdRutaViaje())) {
            System.out.println(h);
        }

        // 5. BUSCAR HORARIO
        System.out.println("\nBUSCAR HORARIO:");

        HorarioParadaViaje encontrado =
                horarioData.buscarHorarioParadaViaje(
                        horario.getIdHorarioParadaViaje());

        System.out.println(encontrado);

        // 6. ACTUALIZAR HORARIO
        System.out.println("\nACTUALIZAR HORARIO:");

        encontrado.setFechaHoraLlegada(
                LocalDateTime.of(2026, 11, 3, 8, 10));

        encontrado.setFechaHoraSalida(
                LocalDateTime.of(2026, 11, 3, 8, 15));

        boolean actualizado =
                horarioData.actualizarHorarioParadaViaje(encontrado);

        System.out.println(actualizado);

        // 7. ELIMINAR HORARIO
        System.out.println("\nELIMINAR HORARIO:");

        boolean eliminado =
                horarioData.eliminarHorarioParadaViaje(
                        horario.getIdHorarioParadaViaje());

        System.out.println(eliminado);

        HorarioParadaViaje eliminadoDB =
                horarioData.buscarHorarioParadaViaje(
                        horario.getIdHorarioParadaViaje());

        System.out.println("¿El horario sigue existiendo? "
                + (eliminadoDB != null));

        // 8. Eliminamos la ruta de prueba
        rutaData.eliminarRutaViaje(ruta.getIdRutaViaje());
    }
    
    
}