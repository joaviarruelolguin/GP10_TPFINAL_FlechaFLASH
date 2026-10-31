package vistas;

import modelo.Colectivo;
import persistencia.ColectivoData;
import java.time.LocalDate;
import java.util.List;

public class TestColectivo {

    public static void main(String[] args) {

        ColectivoData colectivoData = new ColectivoData();

        Colectivo colectivo = new Colectivo(
                "TEST-CO-001",
                "Mercedes Benz",
                "OF 1721",
                45,
                null,
                true,
                "Juan Perez"
        );

        boolean guardado = colectivoData.guardarColectivo(colectivo);

        if (guardado) {
            System.out.println("¡Colectivo guardado correctamente!");
            System.out.println("ID generado: " + colectivo.getIdColectivo());
        } else {
            System.out.println("No se pudo guardar el colectivo.");
        }
        
        List<Colectivo> lista = colectivoData.listarColectivos();

        if (lista.isEmpty()) {
            System.out.println("No hay colectivos registrados.");
        } else {
            for (Colectivo c : lista) {
                System.out.println(
                        "ID: " + c.getIdColectivo()
                        + " | Matrícula: " + c.getMatricula()
                        + " | Marca: " + c.getMarca()
                        + " | Modelo: " + c.getModelo()
                        + " | Capacidad: " + c.getCapacidad()
                        + " | Estado: " + c.isEstado()
                        + " | Chofer: " + c.getChofer()
                );
            }
        }

        System.out.println("\nBUSCANDO COLECTIVO:");

        Colectivo encontrado = colectivoData.buscarColectivo(2);

        if (encontrado != null) {
            System.out.println("Colectivo encontrado:");
            System.out.println("ID: " + encontrado.getIdColectivo());
            System.out.println("Matrícula: " + encontrado.getMatricula());
            System.out.println("Marca: " + encontrado.getMarca());
            System.out.println("Modelo: " + encontrado.getModelo());
            System.out.println("Chofer: " + encontrado.getChofer());
        } else {
            System.out.println("No se encontró el colectivo.");
        }

        System.out.println("\nACTUALIZANDO COLECTIVO:");

        Colectivo colectivoModificar = colectivoData.buscarColectivo(2);

        if (colectivoModificar != null) {

            colectivoModificar.setMarca("Mercedes Benz");
            colectivoModificar.setModelo("OF 1724");
            colectivoModificar.setChofer("Carlos Gomez");

            boolean actualizado = colectivoData.actualizarColectivo(colectivoModificar);

            if (actualizado) {
                System.out.println("Colectivo actualizado correctamente.");

                Colectivo actualizadoDB = colectivoData.buscarColectivo(2);
                System.out.println(actualizadoDB);
            } else {
                System.out.println("No se pudo actualizar el colectivo.");
            }

        } else {
            System.out.println("No existe el colectivo.");
        }

        System.out.println("\nDESACTIVANDO COLECTIVO:");

        boolean desactivado = colectivoData.desactivarColectivo(2);

        if (desactivado) {
            System.out.println("Colectivo desactivado correctamente.");

            Colectivo colectivoDesactivado
                    = colectivoData.buscarColectivo(2);

            System.out.println("Estado: "
                    + colectivoDesactivado.isEstado());

        } else {
            System.out.println("No se pudo desactivar el colectivo.");
        }
        System.out.println("\nELIMINANDO COLECTIVO:");

        boolean eliminado = colectivoData.eliminarColectivo(2);

        if (eliminado) {
            System.out.println("Colectivo eliminado correctamente.");

            Colectivo colectivoEliminado
                    = colectivoData.buscarColectivo(2);

            if (colectivoEliminado == null) {
                System.out.println("Comprobación: el colectivo ya no existe.");
            }

        } else {
            System.out.println("No se pudo eliminar el colectivo.");
        }

    }

}
