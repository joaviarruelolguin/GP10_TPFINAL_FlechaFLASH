
package vistas;

import java.util.List;
import modelo.ParadaRecorrido;
import modelo.Recorrido;
import persistencia.ParadaRecorridoData;
import persistencia.RecorridoData;

public class TestParadaRecorrido {

    public static void main(String[] args) {

        // Línea de código para que aparezcan las tildes y la ñ.
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        // Conexión a la BD gracias al connection de los constructores.
        RecorridoData recorridoData = new RecorridoData();
        ParadaRecorridoData paradaData = new ParadaRecorridoData();

        // Crear un recorrido de prueba.
        Recorrido recorrido = new Recorrido("Recorrido de prueba", true);

        System.out.println("\n GUARDAR RECORRIDO \n");
        System.out.println(recorridoData.guardarRecorrido(recorrido));

        // Obtener el ID generado.
        int idRecorrido = recorrido.getIdRecorrido();
        System.out.println("ID del recorrido: " + idRecorrido);

        // Crear paradas de prueba.
        ParadaRecorrido parada1 = new ParadaRecorrido(recorrido, "San Luis", 1, 0);
        ParadaRecorrido parada2 = new ParadaRecorrido(recorrido, "La Punta", 2, 15);
        ParadaRecorrido parada3 = new ParadaRecorrido(recorrido, "La Carolina", 3, 80);

        // Guardar paradas.
        System.out.println("\n GUARDAR PARADAS \n");
        System.out.println("Parada 1: " + paradaData.guardarParadaRecorrido(parada1));
        System.out.println("Parada 2: " + paradaData.guardarParadaRecorrido(parada2));
        System.out.println("Parada 3: " + paradaData.guardarParadaRecorrido(parada3));

        // Obtener los IDs generados.
        int idParada1 = parada1.getIdParadaRecorrido();
        int idParada2 = parada2.getIdParadaRecorrido();

        // Listar paradas del recorrido.
        System.out.println("\n LISTADO DE PARADAS \n");

        List<ParadaRecorrido> lista = paradaData.listarParadasPorRecorrido(idRecorrido);

        for (ParadaRecorrido parada : lista) {
            System.out.println(parada);
        }

        // Buscar parada.
        ParadaRecorrido paradaBuscada = paradaData.buscarParadaRecorrido(idParada2);

        System.out.println("\n PARADA BUSCADA \n");
        System.out.println(paradaBuscada);

        // Modificar parada.
        if (paradaBuscada != null) {
            paradaBuscada.setNombreLugar("La Punta - Modificada");
            paradaBuscada.setDistanciaAcumuladaKm(20);

            System.out.println("\n ACTUALIZAR PARADA \n");
            System.out.println(paradaData.actualizarParadaRecorrido(paradaBuscada));
            System.out.println(paradaData.buscarParadaRecorrido(idParada2));
        }

        // Eliminar primera parada y renumerar las restantes.
        System.out.println("\n ELIMINAR PRIMERA PARADA \n");
        System.out.println(paradaData.eliminarParadaRecorrido(idParada1));

        // Listado final para comprobar la renumeración.
        System.out.println("\n LISTADO FINAL \n");

        for (ParadaRecorrido parada : paradaData.listarParadasPorRecorrido(idRecorrido)) {
            System.out.println(parada);
        }
    }

}
