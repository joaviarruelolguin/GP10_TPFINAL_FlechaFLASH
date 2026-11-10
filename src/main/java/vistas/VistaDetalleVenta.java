
package vistas;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Pasaje;
import modelo.Venta;

public class VistaDetalleVenta extends JDialog {

    private final Venta venta;
    private final List<Pasaje> pasajes;
    private final JTable tablaPasajes;

    public VistaDetalleVenta(
            Window propietario,
            Venta venta,
            List<Pasaje> pasajes) {

        super(propietario, "Detalle de venta", ModalityType.APPLICATION_MODAL);

        this.venta = venta;
        this.pasajes = pasajes;

        setSize(900, 420);
        setLocationRelativeTo(propietario);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel(
                "   Venta: " + venta.getNumeroComprobante()
                + "  |  Pasajes: " + pasajes.size());

        add(titulo, BorderLayout.NORTH);

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new String[]{
                    "N.º Pasaje", "Pasajero", "DNI",
                    "Origen", "Destino", "Asiento", "Precio"
                }, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        for (Pasaje p : pasajes) {
            modeloTabla.addRow(new Object[]{
                p.getIdPasaje(),
                p.getPasajero().getNombre() + " "
                + p.getPasajero().getApellido(),
                p.getPasajero().getDni(),
                p.getParadaOrigen().getNombreLugar(),
                p.getParadaDestino().getNombreLugar(),
                p.getAsiento().getNumeroAsiento(),
                p.getPrecioFinal()
            });
        }

        tablaPasajes = new JTable(modeloTabla);
        tablaPasajes.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        tablaPasajes.setRowHeight(28);
        tablaPasajes.setAutoCreateRowSorter(true);

        add(new JScrollPane(tablaPasajes), BorderLayout.CENTER);

        JButton btnAbrir = new JButton("VER / IMPRIMIR BOLETO");
        JButton btnCerrar = new JButton("CERRAR");

        btnAbrir.addActionListener(e -> abrirBoleto());
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnAbrir);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void abrirBoleto() {

        int fila = tablaPasajes.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccioná un pasaje de la tabla.");
            return;
        }

        int filaModelo = tablaPasajes.convertRowIndexToModel(fila);
        Pasaje pasaje = pasajes.get(filaModelo);

        String nombreArchivo = "Boleto_Venta_"
                + venta.getIdVenta()
                + "_Pasaje_" + pasaje.getIdPasaje() + ".pdf";

        Path ruta = Paths.get("boletos", nombreArchivo);

        if (!Files.isRegularFile(ruta)) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró el boleto PDF:\n"
                    + ruta.toAbsolutePath(),
                    "Archivo no encontrado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!Desktop.isDesktopSupported()
                || !Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            JOptionPane.showMessageDialog(this,
                    "Este equipo no permite abrir archivos PDF automáticamente.");
            return;
        }

        try {
            File archivo = ruta.toFile();
            Desktop.getDesktop().open(archivo);

        } catch (IOException | SecurityException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo abrir el boleto: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
