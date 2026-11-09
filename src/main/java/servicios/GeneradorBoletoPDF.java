package servicios;

import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeneradorBoletoPDF {

    public void generarPDFPrueba() {

        Document documento = new Document();

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