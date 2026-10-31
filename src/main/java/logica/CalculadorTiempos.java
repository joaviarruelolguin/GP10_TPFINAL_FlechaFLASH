
package logica;

import java.util.List;
import modelo.ParadaRecorrido;

//Velocidad media estimada: 90 km/h.
//Tiempo por kilómetro: 40 segundos.
//Detención en origen: 0 minutos. El tiempo empieza a correr una vez iniciado el recorrido.
//Detención en parada intermedia: 5 minutos.
//Detención en destino: 0 minutos.


public class CalculadorTiempos {
    
    private static final double VELOCIDAD_MEDIA_KMH = 90.0;
    private static final int TIEMPO_PARADA_INTERMEDIA = 5;
    
    
    // Método para calcular el tiempo de viaje entre dos paradas, utilizando sus distancias acumuladas. Colectivo en circulación.
    public double calcularTiempoViaje(ParadaRecorrido origen, ParadaRecorrido destino) {
        
        // Obtengo la distancia entre las dos paradas restando sus distancias acumuladas.
        double distancia = destino.getDistanciaAcumuladaKm() - origen.getDistanciaAcumuladaKm();
        
        // Divido la distancia obtenida por la velocidad media y transformo el resultado en minutos.
        return (distancia / VELOCIDAD_MEDIA_KMH) * 60;
        
    }
    
    
    // Método para calcular el tiempo de detención de una parada. Colectivo detenido.
    public int calcularTiempoDetencion(ParadaRecorrido parada, int totalParadas) {
        
        if (parada.getOrden() == 1 || parada.getOrden() == totalParadas) {
            
            return 0; // 0 minutos si se trata de parada origen o parada destino final
        }
        return TIEMPO_PARADA_INTERMEDIA;
    }
    
    
    // Método para calcular el tiempo total del recorrido.
    public double calcularTiempoTotal(List<ParadaRecorrido> paradas) {
        
        double tiempoTotal = 0;

        for (int i = 0; i < paradas.size() - 1; i++) {   // paradas.size() - 1 porque la última parada no tiene una siguiente.
            
            ParadaRecorrido origen = paradas.get(i);
            ParadaRecorrido destino = paradas.get(i + 1);

            tiempoTotal += calcularTiempoViaje(origen, destino); // cantidad de tiempo del traslado entre una parada y la otra (utilizando la velocidad media).
            
            tiempoTotal += calcularTiempoDetencion(origen, paradas.size()); // tiempo de detención: 0 o 5 minutos, depende del return.
            
        }

        return tiempoTotal; // 
    }
    
}
