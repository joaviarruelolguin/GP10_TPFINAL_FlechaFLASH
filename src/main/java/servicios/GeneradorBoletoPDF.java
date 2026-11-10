package servicios;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openpdf.text.Image;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfPCell;

public class GeneradorBoletoPDF {

    public void generarPDFPrueba() {

        Document documento = new Document(
                org.openpdf.text.PageSize.A4.rotate(),
                25, 25, 25, 25
        );

        try {

            // Crear la carpeta donde guardaremos los boletos
            Path carpetaBoletos = Paths.get("boletos");

            Files.createDirectories(carpetaBoletos);

            // Definir el nombre y la ubicación del PDF
            Path archivoPDF = carpetaBoletos.resolve("BoletoPrueba.pdf");

            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(archivoPDF.toFile())
            );

            documento.open();
            // Buscar la imagen dentro de los recursos del proyecto
            java.net.URL rutaImagen = getClass().getResource(
                    "/encabezado_flechaflash.png"
            );

// Comprobar que la imagen exista
            if (rutaImagen == null) {
                throw new IllegalStateException(
                        "No se encontró la imagen del encabezado."
                );
            }

// Cargar y ajustar la imagen al documento
            Image encabezado = Image.getInstance(rutaImagen);

            encabezado.scaleToFit(790, 170);
            encabezado.setAlignment(Image.ALIGN_CENTER);

// Agregar el encabezado al PDF
            documento.add(encabezado);

            // Título del boleto
            Paragraph titulo = new Paragraph("BOLETO DE VIAJE");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);

// Crear una tabla con dos columnas
            PdfPTable tablaPasajero = new PdfPTable(2);

// Establecer el ancho de la tabla
            tablaPasajero.setWidthPercentage(100);

// Definir la proporción de las columnas
            tablaPasajero.setWidths(new float[]{70, 30});

// Crear la celda del pasajero
            PdfPCell celdaPasajero = new PdfPCell(
                    new Paragraph("PASAJERO\nJuan Perez")
            );

            celdaPasajero.setPadding(12);

// Crear la celda del asiento
            PdfPCell celdaAsiento = new PdfPCell(
                    new Paragraph("ASIENTO\n12")
            );

            celdaAsiento.setPadding(12);

// Agregar las celdas a la tabla
            tablaPasajero.addCell(celdaPasajero);
            tablaPasajero.addCell(celdaAsiento);

// Agregar la tabla al documento
            documento.add(tablaPasajero);
            // Crear la tabla de origen y destino
            PdfPTable tablaRecorrido = new PdfPTable(2);

// La tabla ocupará todo el ancho disponible
            tablaRecorrido.setWidthPercentage(100);

// Distribuir el espacio entre las dos columnas
            tablaRecorrido.setWidths(new float[]{50, 50});

// Crear la celda del origen
            PdfPCell celdaOrigen = new PdfPCell(
                    new Paragraph("ORIGEN\nSanta Rosa del Conlara")
            );
            celdaOrigen.setPadding(12);

// Crear la celda del destino
            PdfPCell celdaDestino = new PdfPCell(
                    new Paragraph("DESTINO\nSan Luis")
            );
            celdaDestino.setPadding(12);

// Agregar las celdas a la tabla
            tablaRecorrido.addCell(celdaOrigen);
            tablaRecorrido.addCell(celdaDestino);

// Agregar la tabla al documento
            documento.add(tablaRecorrido);
            // Crear la tabla de fecha y hora del viaje
            PdfPTable tablaFechaHora = new PdfPTable(2);

// Ocupar todo el ancho disponible
            tablaFechaHora.setWidthPercentage(100);

// Distribuir el espacio entre las columnas
            tablaFechaHora.setWidths(new float[]{50, 50});

// Crear la celda de fecha
            PdfPCell celdaFecha = new PdfPCell(
                    new Paragraph("FECHA DEL VIAJE\n15/11/2026")
            );
            celdaFecha.setPadding(12);

// Crear la celda de hora
            PdfPCell celdaHora = new PdfPCell(
                    new Paragraph("HORA DE SALIDA\n08:30 hs")
            );
            celdaHora.setPadding(12);

// Agregar las celdas a la tabla
            tablaFechaHora.addCell(celdaFecha);
            tablaFechaHora.addCell(celdaHora);

// Agregar la tabla al documento
            documento.add(tablaFechaHora);
            // Crear la tabla de identificación de la venta y del boleto
            PdfPTable tablaIdentificacion = new PdfPTable(2);

            tablaIdentificacion.setWidthPercentage(100);
            tablaIdentificacion.setWidths(new float[]{50, 50});

// Celda con el número de venta
            PdfPCell celdaVenta = new PdfPCell(
                    new Paragraph("N.º DE VENTA\n000125")
            );
            celdaVenta.setPadding(12);

// Celda con el número de boleto
            PdfPCell celdaBoleto = new PdfPCell(
                    new Paragraph("N.º DE BOLETO\n000348")
            );
            celdaBoleto.setPadding(12);

// Agregar las celdas
            tablaIdentificacion.addCell(celdaVenta);
            tablaIdentificacion.addCell(celdaBoleto);

// Agregar la tabla al documento
            documento.add(tablaIdentificacion);
            // Mostrar el precio individual del pasaje
            Paragraph precio = new Paragraph("IMPORTE DEL PASAJE: $ 25.000,00");
            precio.setAlignment(Paragraph.ALIGN_RIGHT);

            documento.add(precio);

            documento.close();

            System.out.println("PDF generado correctamente.");

        } catch (Exception e) {

            System.out.println("Error al generar PDF: " + e.getMessage());

        }
    }
}
