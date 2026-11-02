
package test;

import java.util.List;
import modelo.Recorrido;
import persistencia.RecorridoData;
import modelo.ParadaRecorrido;

public class TestRecorrido {

    public static void main(String[] args) {

        // Línea de código para que aparezcan las tildes y la ñ.
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        // Conexión a la BD gracias al connection del constructor.
        RecorridoData recorridoData = new RecorridoData();

        // Crear recorrido 1 con sus paradas.
        Recorrido recorrido1 = new Recorrido();
        recorrido1.setDescripcion("San Luis - Buenos Aires");
        recorrido1.setEstado(true);
        recorrido1.agregarParada(new ParadaRecorrido(null, "San Luis", 1, 0.0));   
        recorrido1.agregarParada(new ParadaRecorrido(null, "Villa Mercedes", 2, 90.0));   
        recorrido1.agregarParada(new ParadaRecorrido(null, "Río Cuarto", 3, 180.0));
        recorrido1.agregarParada(new ParadaRecorrido(null, "Junín", 4, 450.0));
        recorrido1.agregarParada(new ParadaRecorrido(null, "Buenos Aires", 5, 800.0)); // Distancias acumuladas desde el origen, no distancias entre paradas consecutivas.

        // Crear recorrido 2 con sus paradas.
        Recorrido recorrido2 = new Recorrido();
        recorrido2.setDescripcion("San Luis - Mendoza");
        recorrido2.setEstado(true);
        recorrido2.agregarParada(new ParadaRecorrido(null, "San Luis", 1, 0.0));
        recorrido2.agregarParada(new ParadaRecorrido(null, "La Paz", 2, 180.0));
        recorrido2.agregarParada(new ParadaRecorrido(null, "Santa Rosa", 3, 230.0));
        recorrido2.agregarParada(new ParadaRecorrido(null, "Mendoza", 4, 260.0));  // Distancias acumuladas desde el origen, no distancias entre paradas consecutivas.

        // Guardar recorridos.
        System.out.println("\n GUARDAR RECORRIDOS \n");
        System.out.println("Recorrido 1: " + recorridoData.guardarRecorrido(recorrido1));
        System.out.println("Recorrido 2: " + recorridoData.guardarRecorrido(recorrido2));

        // Obtener los IDs generados.
        int id1 = recorrido1.getIdRecorrido();
        int id2 = recorrido2.getIdRecorrido();

        // Listar recorridos.
        System.out.println("\n LISTADO DE RECORRIDOS \n");

        List<Recorrido> lista = recorridoData.listarRecorridos();

        for (Recorrido recorrido : lista) {
            System.out.println(recorrido);
        }

        // Buscar recorrido.
        Recorrido recorridoBuscado = recorridoData.buscarRecorrido(id1);

        System.out.println("\n RECORRIDO BUSCADO \n");
        System.out.println(recorridoBuscado);

        // Modificar recorrido.
        if (recorridoBuscado != null) {
            recorridoBuscado.setDescripcion("San Luis - La Punta Modificado");

            System.out.println("\n ACTUALIZAR RECORRIDO \n");
            System.out.println(recorridoData.actualizarRecorrido(recorridoBuscado));
            System.out.println(recorridoData.buscarRecorrido(id1));
        }

        // Baja lógica.
        System.out.println("\n DESACTIVAR RECORRIDO \n");
        System.out.println(recorridoData.desactivarRecorrido(id2));

        // Baja física.
        System.out.println("\n ELIMINAR RECORRIDOS \n");
        System.out.println(recorridoData.eliminarRecorrido(id1));
        System.out.println(recorridoData.eliminarRecorrido(id2));

        // Listado final: recorridos con sus paradas asociadas (COMPOSICIÓN)
        System.out.println("\n LISTADO FINAL \n");

        for (Recorrido recorrido : lista) {
            System.out.println(recorrido);
            for (ParadaRecorrido parada : recorrido.getParadas()) {
                System.out.println("  " + parada.getOrden() + ". "
                        + parada.getNombreLugar() + " - "
                        + parada.getDistanciaAcumuladaKm() + " km");
            }
        }
    }

}
