//package vistas;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import modelo.Recorrido;
//import modelo.Colectivo;
//import modelo.RutaViaje;
//import persistencia.RutaViajeData;
//
//public class TestRutaViaje {
//
//    public static void main(String[] args) {
//
//        // 1. Creamos el objeto de persistencia
//        RutaViajeData rutaData = new RutaViajeData();
//
//        // 2. Creamos el recorrido existente
//        Recorrido recorrido = new Recorrido(
//                1,
//                "Santa Rosa del Conlara - Merlo",
//                true
//        );
//
//        // 3. Creamos el colectivo existente
//        Colectivo colectivo = new Colectivo(
//                1,
//                "AB123CD",
//                "Mercedes Benz",
//                "OF 1721",
//                45,
//                null,
//                true
//        );
//
//        // 4. Creamos el nuevo viaje
//        RutaViaje ruta = new RutaViaje(
//                recorrido,
//                colectivo,
//                LocalDateTime.of(2026, 11, 1, 8, 0),
//                LocalDateTime.of(2026, 11, 1, 9, 0),
//                new BigDecimal("3500.00"),
//                true
//        );
//
////        // 5. Guardamos el viaje
////        boolean guardado = rutaData.guardarRutaViaje(ruta);
////
////        if (guardado) {
////            System.out.println("¡Ruta guardada correctamente!");
////            System.out.println("ID generado: " + ruta.getIdRutaViaje());
////        } else {
////            System.out.println("No se pudo guardar la ruta.");
////        }
//        // 6. Listamos las rutas
//        System.out.println("LISTADO DE RUTAS:");
//
//        for (RutaViaje r : rutaData.listarRutasViaje()) {
//            System.out.println(r);
//        }
//
//        System.out.println("BUSCANDO RUTA POR ID:");
//
//        RutaViaje rutaEncontrada = rutaData.buscarRutaViaje(1);
//
//        if (rutaEncontrada != null) {
//            System.out.println(rutaEncontrada);
//        } else {
//            System.out.println("No se encontró la ruta.");
//        }
//
////        System.out.println("ACTUALIZANDO RUTA:");
////
////        RutaViaje rutaModificar = rutaData.buscarRutaViaje(1);
////
////        if (rutaModificar != null) {
////
////            rutaModificar.setPrecioBase(new BigDecimal("4000.00"));
////
////            boolean actualizado = rutaData.actualizarRutaViaje(rutaModificar);
////
////            if (actualizado) {
////                System.out.println("Ruta actualizada correctamente.");
////
////                RutaViaje rutaActualizada = rutaData.buscarRutaViaje(1);
////
////                System.out.println(rutaActualizada);
////            } else {
////                System.out.println("No se pudo actualizar la ruta.");
////            }
////
////        } else {
////            System.out.println("No se encontró la ruta.");
////        }
////
////        System.out.println("DESACTIVANDO RUTA:");
////
////        boolean desactivada = rutaData.desactivarRutaViaje(1);
////
////        if (desactivada) {
////
////            System.out.println("Ruta desactivada correctamente.");
////
////            RutaViaje rutaDesactivada = rutaData.buscarRutaViaje(1);
////
////            if (rutaDesactivada != null) {
////                System.out.println("Estado de la ruta: "
////                        + rutaDesactivada.isEstado());
////            }
////
////        } else {
////            System.out.println("No se pudo desactivar la ruta.");
////        }
//
//        System.out.println("ELIMINANDO RUTA:");
//
//        boolean eliminada = rutaData.eliminarRutaViaje(1);
//
//        if (eliminada) {
//
//            System.out.println("Ruta eliminada correctamente.");
//
//            RutaViaje rutaEliminada = rutaData.buscarRutaViaje(1);
//
//            if (rutaEliminada == null) {
//                System.out.println("Comprobación: la ruta ya no existe.");
//            }
//
//        } else {
//            System.out.println("No se pudo eliminar la ruta.");
//        }
//
//    }
//}

/*
 * ============================================================
 * GUIA DE PRUEBAS - CLASE RUTAVIAJEDATA
 * ============================================================
 *
 * IMPORTANTE:
 * Ejecutar estas pruebas desde TestRutaViaje.java utilizando
 * la opción "Run File" de NetBeans.
 *
 * ANTES DE COMENZAR:
 * 1. Verificar que el servidor MariaDB esté iniciado.
 * 2. Verificar que exista la base de datos "flechaflash".
 * 3. Comprobar que existan registros en las tablas recorrido
 *    y colectivo, necesarios para crear una ruta de viaje.
 * 4. Verificar los ID de esos registros en la base de datos.
 *
 * ORDEN DE EJECUCION DE LAS PRUEBAS:
 *
 * 1. GUARDAR RUTA:
 *    Ejecutar guardarRutaViaje() para insertar una nueva ruta.
 *    La prueba utiliza un recorrido y un colectivo existentes.
 *    El ID generado se recupera automáticamente.
 *
 * 2. LISTAR RUTAS:
 *    Ejecutar listarRutasViaje() para comprobar que la ruta
 *    se haya guardado y que se recuperen sus datos relacionados.
 *
 * 3. BUSCAR RUTA:
 *    Ejecutar buscarRutaViaje(id) utilizando el ID generado
 *    al guardar la ruta.
 *
 * 4. ACTUALIZAR RUTA:
 *    Buscar primero la ruta y modificar alguno de sus datos.
 *    Luego ejecutar actualizarRutaViaje().
 *    Volver a buscarla para verificar el cambio.
 *
 * 5. BAJA LOGICA:
 *    Ejecutar desactivarRutaViaje(id).
 *    Esto cambia el estado de la ruta a false, pero NO elimina
 *    el registro de la base de datos.
 *    Se puede comprobar buscando nuevamente la ruta.
 *
 * 6. BAJA FISICA:
 *    Ejecutar eliminarRutaViaje(id).
 *    Esta operación elimina definitivamente el registro.
 *
 *    IMPORTANTE: Antes de eliminar, comprobar que no existan
 *    pasajes asociados a esa ruta:
 *
 *    SELECT * FROM pasaje WHERE idRutaViaje = ID;
 *
 *    Si existen pasajes relacionados, la clave foránea puede
 *    impedir la eliminación.
 *
 * PRECAUCIONES PARA EVITAR ERRORES Y DUPLICADOS:
 *
 * - No ejecutar varias veces el bloque de guardado sin revisar
 *   antes la base de datos: cada ejecución inserta una ruta nueva.
 *
 * - No asumir que los ID siempre serán 1. Consultar los ID
 *   reales de recorrido, colectivo y ruta.
 *
 * - Ejecutar las pruebas de actualización, baja lógica y baja
 *   física sobre una ruta existente.
 *
 * - La baja lógica debe probarse antes de la baja física.
 *
 * - Si se elimina físicamente una ruta, ya no podrá utilizarse
 *   ese ID para las pruebas posteriores.
 *
 * - Para repetir el conjunto completo de pruebas, preparar
 *   nuevamente los datos necesarios y verificar sus ID.
 *
 * ============================================================
 */

