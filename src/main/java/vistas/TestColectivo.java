
package vistas;

import modelo.Colectivo;
import persistencia.ColectivoData;
import java.time.LocalDate;

public class TestColectivo {

    public static void main(String[] args) {

        ColectivoData colectivoData = new ColectivoData();

        Colectivo colectivo = new Colectivo(
                "TEST-CO-001",
                "Mercedes Benz",
                "OF 1721",
                45,
                null,
                true
        );

        boolean guardado = colectivoData.guardarColectivo(colectivo);

        if (guardado) {
            System.out.println("¡Colectivo guardado correctamente!");
            System.out.println("ID generado: " + colectivo.getIdColectivo());
        } else {
            System.out.println("No se pudo guardar el colectivo.");
        }
    }
}
