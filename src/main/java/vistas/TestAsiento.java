package vistas;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Asiento;
import modelo.Colectivo;
import persistencia.AsientoData;
import persistencia.ColectivoData;

public class TestAsiento {

    public static void main(String[] args) {

        AsientoData asientoData = new AsientoData();
        ColectivoData colectivoData = new ColectivoData();

        // Crear y guardar un colectivo
        Colectivo colectivo = new Colectivo(
                "AA123BB",
                "Mercedes-Benz",
                "OF 1721",
                45,
                LocalDate.of(2030, 12, 31),
                true,
                "Juan Pérez"
        );

        if (!colectivoData.guardarColectivo(colectivo)) {
            System.out.println("No se pudo guardar el colectivo.");
            return;
        }

        System.out.println("Colectivo guardado. ID generado: "
                + colectivo.getIdColectivo());

        // Guardar tres asientos
        Asiento asiento1 = new Asiento(colectivo, 1);
        Asiento asiento2 = new Asiento(colectivo, 2);
        Asiento asiento3 = new Asiento(colectivo, 3);

        System.out.println("\nGUARDAR ASIENTOS:");
        System.out.println("Asiento 1: " + asientoData.guardarAsiento(asiento1));
        System.out.println("Asiento 2: " + asientoData.guardarAsiento(asiento2));
        System.out.println("Asiento 3: " + asientoData.guardarAsiento(asiento3));

        // Listar los asientos del colectivo
        System.out.println("\nASIENTOS DEL COLECTIVO:");

        ArrayList<Asiento> asientos =
                asientoData.listarAsientosPorColectivo(colectivo);

        for (Asiento asiento : asientos) {
            System.out.println(asiento.getIdAsiento() + " - "
                    + asiento.getColectivo().getMatricula() + " - "
                    + asiento.getNumeroAsiento());
        }

        // Buscar un asiento
        if (!asientos.isEmpty()) {
            Asiento asientoBuscado =
                    asientoData.buscarAsiento(asientos.get(0).getIdAsiento());

            System.out.println("\nASIENTO BUSCADO:");
            System.out.println(asientoBuscado);
        }

        // Actualizar un asiento
        if (!asientos.isEmpty()) {
            Asiento asientoModificar = asientos.get(0);
            asientoModificar.setNumeroAsiento(10);

            System.out.println("\nACTUALIZAR ASIENTO: "
                    + asientoData.actualizarAsiento(asientoModificar));
        }
    }
}