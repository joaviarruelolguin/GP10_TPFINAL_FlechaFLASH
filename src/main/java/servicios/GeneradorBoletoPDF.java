
package servicios;

import modelo.Venta;
import modelo.Pasaje;
import modelo.HorarioParadaViaje;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Image;
import org.openpdf.text.Rectangle;
import org.openpdf.text.Font;
import org.openpdf.text.Chunk;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfPCell;

import java.awt.Color;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GeneradorBoletoPDF {

    public boolean generarBoleto(Venta venta, Pasaje pasaje) {

        Document documento = null;

        try {
            // Validar los datos necesarios
            if (venta == null || pasaje == null
                    || pasaje.getPasajero() == null
                    || pasaje.getAsiento() == null
                    || pasaje.getRutaViaje() == null
                    || pasaje.getParadaOrigen() == null
                    || pasaje.getParadaDestino() == null) {

                throw new IllegalArgumentException(
                        "Faltan datos necesarios para generar el boleto.");
            }

            // Obtener la hora de salida desde la parada de origen
            LocalDateTime horaSalida = null;

            for (HorarioParadaViaje horario
                    : pasaje.getRutaViaje().getHorarios()) {

                if (horario.getParadaRecorrido()
                        .getIdParadaRecorrido()
                        == pasaje.getParadaOrigen().getIdParadaRecorrido()) {

                    horaSalida = horario.getFechaHoraSalida();
                    break;
                }
            }

            if (horaSalida == null) {
                throw new IllegalStateException(
                        "No se encontró el horario de salida de la parada de origen.");
            }

            // Recuperar los datos reales del boleto
            String nombrePasajero
                    = pasaje.getPasajero().getNombre() + " "
                    + pasaje.getPasajero().getApellido();

            String asiento = String.valueOf(
                    pasaje.getAsiento().getNumeroAsiento());

            String origen = pasaje.getParadaOrigen().getNombreLugar();
            String destino = pasaje.getParadaDestino().getNombreLugar();

            String fecha = horaSalida.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String hora = horaSalida.format(
                    DateTimeFormatter.ofPattern("HH:mm")) + " hs";

            String numeroVenta = String.valueOf(venta.getIdVenta());
            String numeroBoleto = String.valueOf(pasaje.getIdPasaje());

            String importe = NumberFormat.getCurrencyInstance(
                    new Locale("es", "AR"))
                    .format(pasaje.getPrecioFinal());

            // Crear la carpeta de boletos
            Path carpetaBoletos = Paths.get("boletos");
            Files.createDirectories(carpetaBoletos);

            // Crear un archivo diferente para cada pasaje
            Path archivoPDF = carpetaBoletos.resolve(
                    "Boleto_Venta_" + venta.getIdVenta()
                    + "_Pasaje_" + pasaje.getIdPasaje() + ".pdf");

            // Configurar el tamaño del boleto: 16 x 10 cm
            Rectangle tamañoBoleto = new Rectangle(
                    16 * 28.34646f,
                    10 * 28.34646f
            );

            documento = new Document(
                    tamañoBoleto, 12, 12, 105, 12);

            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(archivoPDF.toFile()));

            documento.open();

            // Cargar el encabezado institucional
            java.net.URL rutaImagen = getClass().getResource(
                    "/encabezado_flechaflash.png");

            if (rutaImagen == null) {
                throw new IllegalStateException(
                        "No se encontró la imagen del encabezado.");
            }

            Image encabezado = Image.getInstance(rutaImagen);

            float anchoOriginal = encabezado.getWidth();
            float altoOriginal = encabezado.getHeight();

            float nuevoAncho = tamañoBoleto.getWidth();
            float nuevoAlto = nuevoAncho * altoOriginal / anchoOriginal;

            encabezado.scaleAbsolute(nuevoAncho, nuevoAlto);

            encabezado.setAbsolutePosition(
                    0, tamañoBoleto.getHeight() - nuevoAlto);

            documento.add(encabezado);

            // Colores institucionales
            Color azulFlecha = new Color(18, 63, 120);
            Color naranjaFlecha = new Color(245, 155, 35);

            Font fuenteEtiqueta = new Font(
                    Font.HELVETICA, 8, Font.BOLD, azulFlecha);

            Font fuenteValor = new Font(
                    Font.HELVETICA, 9, Font.NORMAL, Color.BLACK);

            // Título
            Font fuenteTitulo = new Font(
                    Font.HELVETICA, 10, Font.BOLD);

            Paragraph titulo = new Paragraph(
                    "BOLETO DE VIAJE", fuenteTitulo);

            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setSpacingBefore(2);
            titulo.setSpacingAfter(4);

            documento.add(titulo);

            // Tabla de pasajero y asiento
            PdfPTable tablaPasajero = new PdfPTable(2);
            tablaPasajero.setWidthPercentage(100);
            tablaPasajero.setWidths(new float[]{70, 30});

            agregarCelda(tablaPasajero, "PASAJERO", nombrePasajero,
                    fuenteEtiqueta, fuenteValor);

            agregarCelda(tablaPasajero, "ASIENTO", asiento,
                    fuenteEtiqueta, fuenteValor);

            documento.add(tablaPasajero);

            // Tabla de origen y destino
            PdfPTable tablaRecorrido = new PdfPTable(2);
            tablaRecorrido.setWidthPercentage(100);
            tablaRecorrido.setWidths(new float[]{50, 50});

            agregarCelda(tablaRecorrido, "ORIGEN", origen,
                    fuenteEtiqueta, fuenteValor);

            agregarCelda(tablaRecorrido, "DESTINO", destino,
                    fuenteEtiqueta, fuenteValor);

            documento.add(tablaRecorrido);

            // Tabla de fecha y hora
            PdfPTable tablaFechaHora = new PdfPTable(2);
            tablaFechaHora.setWidthPercentage(100);
            tablaFechaHora.setWidths(new float[]{50, 50});

            agregarCelda(tablaFechaHora, "FECHA DEL VIAJE", fecha,
                    fuenteEtiqueta, fuenteValor);

            agregarCelda(tablaFechaHora, "HORA DE SALIDA", hora,
                    fuenteEtiqueta, fuenteValor);

            documento.add(tablaFechaHora);

            // Tabla de identificación
            PdfPTable tablaIdentificacion = new PdfPTable(2);
            tablaIdentificacion.setWidthPercentage(100);
            tablaIdentificacion.setWidths(new float[]{50, 50});

            agregarCelda(tablaIdentificacion, "N.º DE VENTA", numeroVenta,
                    fuenteEtiqueta, fuenteValor);

            agregarCelda(tablaIdentificacion, "N.º DE BOLETO", numeroBoleto,
                    fuenteEtiqueta, fuenteValor);

            documento.add(tablaIdentificacion);

            // Importe individual del pasaje
            Font fuenteImporte = new Font(
                    Font.HELVETICA, 9, Font.BOLD, naranjaFlecha);

            Paragraph precio = new Paragraph(
                    "IMPORTE DEL PASAJE: " + importe,
                    fuenteImporte);

            precio.setAlignment(Paragraph.ALIGN_RIGHT);

            documento.add(precio);

            documento.close();
            documento = null;

            System.out.println(
                    "Boleto generado correctamente: " + archivoPDF);

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Error al generar el boleto: " + e.getMessage());

            return false;

        } finally {

            if (documento != null && documento.isOpen()) {
                documento.close();
            }
        }
    }

    // Método auxiliar para crear las celdas del boleto
    private void agregarCelda(
            PdfPTable tabla,
            String etiqueta,
            String valor,
            Font fuenteEtiqueta,
            Font fuenteValor) {

        Paragraph contenido = new Paragraph();

        contenido.add(new Chunk(etiqueta + "\n", fuenteEtiqueta));
        contenido.add(new Chunk(valor, fuenteValor));

        PdfPCell celda = new PdfPCell(contenido);
        celda.setPadding(5);

        tabla.addCell(celda);
    }
}
