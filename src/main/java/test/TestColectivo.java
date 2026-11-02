package test;

import modelo.Colectivo;
import persistencia.ColectivoData;
import java.time.LocalDate;
import java.util.List;
import modelo.Asiento;

public class TestColectivo {

    public static void main(String[] args) {

        ColectivoData colectivoData = new ColectivoData();

        // Recuperamos un colectivo existente
        Colectivo colectivo = colectivoData.buscarColectivo(3);

        if (colectivo == null) {
            System.out.println("No se encontró el colectivo.");
            return;
        }

        System.out.println("\nRECUPERANDO COLECTIVO Y SUS ASIENTOS:");
        System.out.println("ID: " + colectivo.getIdColectivo());
        System.out.println("Capacidad: " + colectivo.getCapacidad());
        System.out.println("Cantidad de asientos: " + colectivo.getAsientos().size());

        for (Asiento asiento : colectivo.getAsientos()) {
            System.out.println("Asiento N° " + asiento.getNumeroAsiento());
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

        Colectivo encontrado = colectivoData.buscarColectivo(colectivo.getIdColectivo());

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

        Colectivo colectivoModificar = colectivoData.buscarColectivo(colectivo.getIdColectivo());

        if (colectivoModificar != null) {

            colectivoModificar.setMarca("Mercedes Benz");
            colectivoModificar.setModelo("OF 1724");
            colectivoModificar.setChofer("Carlos Gomez");

            boolean actualizado = colectivoData.actualizarColectivo(colectivoModificar);

            if (actualizado) {
                System.out.println("Colectivo actualizado correctamente.");

                Colectivo actualizadoDB = colectivoData.buscarColectivo(colectivo.getIdColectivo());
                System.out.println(actualizadoDB);
            } else {
                System.out.println("No se pudo actualizar el colectivo.");
            }

        } else {
            System.out.println("No existe el colectivo.");
        }

        System.out.println("\nDESACTIVANDO COLECTIVO:");

        boolean desactivado = colectivoData.desactivarColectivo(colectivo.getIdColectivo());

        if (desactivado) {
            System.out.println("Colectivo desactivado correctamente.");

            Colectivo colectivoDesactivado
                    = colectivoData.buscarColectivo(colectivo.getIdColectivo());

            System.out.println("Estado: "
                    + colectivoDesactivado.isEstado());

        } else {
            System.out.println("No se pudo desactivar el colectivo.");
        }
        System.out.println("\nELIMINANDO COLECTIVO:");

        boolean eliminado = colectivoData.eliminarColectivo(colectivo.getIdColectivo());

        if (eliminado) {
            System.out.println("Colectivo eliminado correctamente.");

            Colectivo colectivoEliminado
                    = colectivoData.buscarColectivo(colectivo.getIdColectivo());

            if (colectivoEliminado == null) {
                System.out.println("Comprobación: el colectivo ya no existe.");
            }

        } else {
            System.out.println("No se pudo eliminar el colectivo.");
        }

    }

}
