
package vistas;

import java.util.List;
import modelo.Recorrido;
import persistencia.RecorridoData;

public class TestRecorrido {
    
    public static void main(String[] args) {

        // Línea de código para que aparezcan las tildes y la ñ.
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        // Conexión a la BD gracias al connection del constructor.
        RecorridoData recorridoData = new RecorridoData();

        // Crear recorridos de prueba.
        Recorrido recorrido1 = new Recorrido("San Luis - La Punta", true);
        Recorrido recorrido2 = new Recorrido("San Luis - La Carolina", true);

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

        // Listado final.
        System.out.println("\n LISTADO FINAL \n");

        for (Recorrido recorrido : recorridoData.listarRecorridos()) {
            System.out.println(recorrido);
        }
    } 
    
}

