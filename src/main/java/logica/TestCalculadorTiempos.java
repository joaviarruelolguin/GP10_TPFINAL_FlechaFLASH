package logica;

import java.util.List;
import modelo.ParadaRecorrido;
import persistencia.ParadaRecorridoData;

public class TestCalculadorTiempos {

    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        ParadaRecorridoData paradaData = new ParadaRecorridoData();
        CalculadorTiempos calculador = new CalculadorTiempos();

        // ID del recorrido creado en TestParadaRecorrido.
        int idRecorrido = 3;

        // Obtener las paradas del recorrido.
        List<ParadaRecorrido> paradas = paradaData.listarParadasPorRecorrido(idRecorrido);

        System.out.println("\n PARADAS DEL RECORRIDO \n");

        for (ParadaRecorrido parada : paradas) {
            
            System.out.println(parada.getOrden() + " - " + parada.getNombreLugar()
                + " | Distancia acumulada: " + parada.getDistanciaAcumuladaKm() + " km"
            );
        }

        // Calcular tiempo total.
        double tiempoTotal = calculador.calcularTiempoTotal(paradas);

        System.out.println("\n TIEMPO TOTAL ESTIMADO \n");
        System.out.println(tiempoTotal + " minutos");
    }
    
}