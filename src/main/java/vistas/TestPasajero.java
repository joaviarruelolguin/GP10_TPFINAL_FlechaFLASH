package vistas;

import java.util.ArrayList;
import modelo.Pasajero;
import modelo.TipoPasajero;
import persistencia.PasajeroData;

public class TestPasajero {

    public static void main(String[] args) {

        // Línea de código para que aparezcan las tildes y la ñ.
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        // Conexión a la BD gracias al connection del constructor.
        PasajeroData pasajeroData = new PasajeroData();

        Pasajero pasajero1 = new Pasajero("30111222", "Hernán", "López", "hernan@mail.com", "2664111222", TipoPasajero.GENERAL);
        Pasajero pasajero2 = new Pasajero("31222333", "Brenda", "Martínez", "brenda@mail.com", "2664222333", TipoPasajero.ESTUDIANTE);

        // Guardar pasajeros en la BD:
        pasajeroData.guardarPasajero(pasajero1);
        pasajeroData.guardarPasajero(pasajero2);

        // Listar y mostrar todos los pasajeros:
        ArrayList<Pasajero> listaDePasajeros = pasajeroData.listarPasajeros();

        System.out.println("\n LISTADO DE PASAJEROS \n");

        for (Pasajero pasajero : listaDePasajeros) {
            System.out.println(pasajero);
        }

        // Los dos últimos de la lista son los que se acaban de guardar.
        int id1 = listaDePasajeros.get(listaDePasajeros.size() - 2).getIdPasajero();
        int id2 = listaDePasajeros.get(listaDePasajeros.size() - 1).getIdPasajero();

        // Buscar pasajero:
        Pasajero pasajeroBuscado = pasajeroData.buscarPasajero(id1);
        System.out.println("\n PASAJERO BUSCADO \n");
        System.out.println(pasajeroBuscado);

        // Modificar pasajero:
        pasajeroBuscado.setNombre("Hernán Modificado");
        pasajeroData.actualizarPasajero(pasajeroBuscado);
        System.out.println("\n PASAJERO MODIFICADO \n");
        System.out.println(pasajeroData.buscarPasajero(id1));

        // Baja física (también deja la tabla limpia para volver a probar):
        pasajeroData.borrarPasajero(id1);
        pasajeroData.borrarPasajero(id2);

        System.out.println("\n LISTADO FINAL \n");

        for (Pasajero pasajero : pasajeroData.listarPasajeros()) {
            System.out.println(pasajero);
        }
    }
}
