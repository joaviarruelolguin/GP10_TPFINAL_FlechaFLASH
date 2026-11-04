package persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode; // para redondear el resultado
import java.util.List;

import modelo.Pasaje;

public class VentaData {

    private static final BigDecimal INTERES_CREDITO
            = new BigDecimal("0.10");   // se establece una constante con el intres del credito (10%)

    public BigDecimal calcularImporteTotal( // recibe la lista de pasajes y la cantidad de cuotas, si no hay cuotas no se aplica el interes
            List<Pasaje> pasajes, int cantidadCuotas) {

        // si no recibo lista o la lista no contiene pasaje, no se calcla la venta
        if (pasajes == null || pasajes.isEmpty()) {
            throw new IllegalArgumentException(
                    "La venta debe incluir al menos un pasaje.");
        }

        // las cuotas deben ser entre 0 y6 sino rechazo el calculo
        if (cantidadCuotas < 0 || cantidadCuotas > 6) {
            throw new IllegalArgumentException(
                    "La cantidad de cuotas debe estar entre 0 y 6.");
        }

        // variable de subtotal inicializada en 0
        BigDecimal subtotal = BigDecimal.ZERO;

        // por cada objeto pasaje que encuentre en la lista de pasajes ejecuta las instrucciones 
        for (Pasaje pasaje : pasajes) {
            
             // validamos cada pasaje (que exista el objeto pasaje, que tenga un precio y que el precio no sea negativo
            if (pasaje == null || pasaje.getPrecioFinal() == null
                    || pasaje.getPrecioFinal().compareTo(BigDecimal.ZERO) < 0) {

                throw new IllegalArgumentException(
                        "El pasaje tiene un precio inválido.");
            }

            subtotal = subtotal.add(pasaje.getPrecioFinal());  // toma el subtotal que ya tengo y suma el precio actual
        }   // obtenemos el subtotala de todos los pasajes

        //  aplicamos el interes si corresponde
        if (cantidadCuotas > 0) {

            BigDecimal interes = subtotal.multiply(INTERES_CREDITO);

            subtotal = subtotal.add(interes);
        }

        // Redondeamos el importe final
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

}

