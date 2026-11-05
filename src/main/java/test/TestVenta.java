package test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import modelo.Pasajero;
import modelo.RutaViaje;
import modelo.Asiento;
import modelo.ParadaRecorrido;
import modelo.Pasaje;
import modelo.Venta;
import modelo.MedioPago;

import persistencia.PasajeroData;
import persistencia.RutaViajeData;
import persistencia.AsientoData;
import persistencia.ParadaRecorridoData;
import persistencia.PasajeData;
import persistencia.VentaData;

public class TestVenta {

    public static void main(String[] args) throws Exception {

        // 1. Creamos los objetos de persistencia.
        PasajeroData pasajeroData = new PasajeroData();
        RutaViajeData rutaData = new RutaViajeData();
        AsientoData asientoData = new AsientoData();
        ParadaRecorridoData paradaData = new ParadaRecorridoData();
        PasajeData pasajeData = new PasajeData();
        VentaData ventaData = new VentaData();

        // 2. Recuperamos los registros existentes.
//        Pasajero pasajero = pasajeroData.buscarPasajero(1);
//        RutaViaje ruta = rutaData.buscarRutaViaje(2);
//        Asiento asiento = asientoData.buscarAsiento(1);
        Pasajero pasajero1 = pasajeroData.buscarPasajero(1);
        Pasajero pasajero2 = pasajeroData.buscarPasajero(2);

        RutaViaje ruta = rutaData.buscarRutaViaje(2);

//        Asiento asiento1 = asientoData.buscarAsiento(4);
//        Asiento asiento2 = asientoData.buscarAsiento(5);
        Asiento asiento1 = asientoData.buscarAsiento(8);
        Asiento asiento2 = asientoData.buscarAsiento(9);

//        Asiento asiento1 = asientoData.buscarAsiento(2);
//        Asiento asiento2 = asientoData.buscarAsiento(3);
        ParadaRecorrido origen
                = paradaData.buscarParadaRecorrido(2);

        ParadaRecorrido destino
                = paradaData.buscarParadaRecorrido(3);

        // 3. Comprobamos que los registros existan.
//        if (pasajero == null || ruta == null || asiento == null
//                || origen == null || destino == null) {
//
//            System.out.println("No se pudieron recuperar todos los datos.");
//            return;
//        }
        if (pasajero1 == null || pasajero2 == null
                || ruta == null || asiento1 == null || asiento2 == null
                || origen == null || destino == null) {

            System.out.println("No se pudieron recuperar todos los datos.");
            return;
        }

        // 4. Calculamos el precio del pasaje.
        BigDecimal precio = pasajeData.calcularPrecioPasaje(
                ruta, origen, destino);

        System.out.println("Precio calculado: $" + precio);

        // 5. Creamos el objeto Pasaje.
        Pasaje pasaje1 = new Pasaje(
                pasajero1,
                ruta,
                asiento1,
                origen,
                destino,
                LocalDateTime.now(),
                precio,
                false,
                null
        );

        Pasaje pasaje2 = new Pasaje(
                pasajero2,
                ruta,
                asiento2,
                origen,
                destino,
                LocalDateTime.now(),
                precio,
                false,
                null
        );
//        Pasaje pasaje = new Pasaje(
//                pasajero,
//                ruta,
//                asiento,
//                origen,
//                destino,
//                LocalDateTime.now(),
//                precio,
//                false,
//                null
//        );

        // 6. Creamos la venta.
        Venta venta = new Venta(
                pasajero1,
                LocalDateTime.now(),
                MedioPago.BILLETERA_VIRTUAL,
                0,
                2,
                BigDecimal.ZERO,
                null,
                null,
                true
        );
//        Venta venta = new Venta(
//                pasajero1,
//                LocalDateTime.now(),
//                MedioPago.EFECTIVO,
//                0,
//                2,
//                BigDecimal.ZERO,
//                null,
//                null,
//                true
//        );
//        Venta venta = new Venta(
//                pasajero,
//                LocalDateTime.now(),
//                MedioPago.EFECTIVO,
//                0,
//                1,
//                BigDecimal.ZERO,
//                null,
//                null,
//                true
//        );

        // 7. Registramos la venta y el pasaje.
        System.out.println("\nREGISTRANDO VENTA:");

        try {
            boolean registrada = ventaData.registrarVenta(
                    venta,
                    java.util.List.of(pasaje1, pasaje2)
            );
            if (registrada) {
                System.out.println("Venta registrada correctamente.");
                System.out.println("ID de venta: " + venta.getIdVenta());
                System.out.println("Importe total: $" + venta.getImporteTotal());
//                System.out.println("Estado del pasaje: " + pasaje.isEstado());
                System.out.println("Estado del pasaje 1: " + pasaje1.isEstado());
                System.out.println("Estado del pasaje 2: " + pasaje2.isEstado());

            } else {
                System.out.println("No se pudo registrar la venta.");
            }

        } catch (Exception e) {

            System.out.println("Error al registrar la venta: "
                    + e.getMessage());
        }
    }
}
