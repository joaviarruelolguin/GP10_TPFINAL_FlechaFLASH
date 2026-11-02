
package test;

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
        Recorrido recorrido = new Recorrido();
        recorrido.setDescripcion("San Luis - Buenos Aires");
        recorrido.setEstado(true);

        recorrido.agregarParada(new ParadaRecorrido(null, "San Luis", 1, 0));
        recorrido.agregarParada(new ParadaRecorrido(null, "Villa Mercedes", 2, 100));
        recorrido.agregarParada(new ParadaRecorrido(null, "Río Cuarto", 3, 300));
        recorrido.agregarParada(new ParadaRecorrido(null, "Junín", 4, 600));
        recorrido.agregarParada(new ParadaRecorrido(null, "Buenos Aires", 5, 800));

        System.out.println("\n GUARDAR RECORRIDO \n");
        System.out.println(recorridoData.guardarRecorrido(recorrido));

        // Obtener el ID generado.
        int idRecorrido = recorrido.getIdRecorrido();
        System.out.println("ID del recorrido: " + idRecorrido);
        
           // Obtener los IDs generados de las paradas.
        int idParada1 = recorrido.getParadas().get(0).getIdParadaRecorrido();
        int idParada2 = recorrido.getParadas().get(1).getIdParadaRecorrido();


        // Listar paradas del recorrido.
        System.out.println("\n LISTADO DE PARADAS \n");

        List<ParadaRecorrido> lista = paradaData.listarParadasPorRecorrido(idRecorrido);

        for (ParadaRecorrido parada : lista) {
            System.out.println(parada.getOrden() + ". "
                    + parada.getNombreLugar() + " - "
                    + parada.getDistanciaAcumuladaKm() + " km");
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
