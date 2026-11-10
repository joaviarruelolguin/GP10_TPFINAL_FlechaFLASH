package servicios;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openpdf.text.Image;

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

            documento.add(new Paragraph("FLECHAFLASH"));
            documento.add(new Paragraph("BOLETO DE VIAJE"));
            documento.add(new Paragraph("Prueba de generacion de PDF"));

            documento.close();

            System.out.println("PDF generado correctamente.");

        } catch (Exception e) {

            System.out.println("Error al generar PDF: " + e.getMessage());

        }
    }
}
