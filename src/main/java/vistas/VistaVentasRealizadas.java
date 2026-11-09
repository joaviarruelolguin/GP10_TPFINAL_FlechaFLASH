package vistas;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import modelo.MedioPago;
import modelo.Pasajero;
import modelo.Venta;
import persistencia.VentaData;

public class VistaVentasRealizadas extends javax.swing.JPanel {

    private VentaData ventaData = new VentaData();
    private List<Venta> listaVentas = new ArrayList<>();

    // Formato argentino
    private final DecimalFormat formatoImporte = new DecimalFormat("$#,##0.00",
            DecimalFormatSymbols.getInstance(Locale.forLanguageTag("es-AR")));

    private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public VistaVentasRealizadas() {
        initComponents();
        txtNumeroComprobante.addActionListener(e -> filtrarVentas());
        armarCabecera();
        cargarVentas();
    }

    public void armarCabecera() {
        modelo.addColumn("Comprobante");
        modelo.addColumn("Fecha");
        modelo.addColumn("Comprador");
        modelo.addColumn("DNI");
        modelo.addColumn("Medio de pago");
        modelo.addColumn("Cuotas");
        modelo.addColumn("Pasajes");
        modelo.addColumn("Importe");

        tblVentas.setModel(modelo);
    }

    public void cargarVentas() {
        listaVentas = ventaData.listarVentas();
        llenarTabla(listaVentas);
    }

    private void filtrarVentas() {
        String comprobante = txtNumeroComprobante.getText().trim();

        List<Venta> filtradas = new ArrayList<>();

        for (Venta v : listaVentas) {

            if (!comprobante.isEmpty()
                    && (v.getNumeroComprobante() == null
                    || !v.getNumeroComprobante().contains(comprobante))) {
                continue;
            }

            filtradas.add(v);
        }

        llenarTabla(filtradas);
    }

    private void llenarTabla(List<Venta> ventas) {
        modelo.setRowCount(0);

        BigDecimal total = BigDecimal.ZERO;

        for (Venta v : ventas) {
            modelo.addRow(new Object[]{
                v.getNumeroComprobante() == null ? "-" : v.getNumeroComprobante(),
                v.getFechaHoraVenta().format(formatoFecha),
                nombreComprador(v.getPasajeroComprador()),
                v.getPasajeroComprador().getDni(),
                textoMedioPago(v.getMedioPago()),
                v.getCantidadCuotas() > 0 ? v.getCantidadCuotas() : "-",
                v.getCantidadPasajes(),
                formatoImporte.format(v.getImporteTotal())
            });

            total = total.add(v.getImporteTotal());
        }

        lblCantidadVentas.setText(String.valueOf(ventas.size()));
        lblImporteAcumulado.setText(formatoImporte.format(total));
    }

    private String textoComprador(Pasajero p) {
        return p.getNombre() + " " + p.getApellido() + " (DNI " + p.getDni() + ")";
    }

    private String nombreComprador(Pasajero p) {
        return p.getNombre() + " " + p.getApellido();
    }

    private String textoMedioPago(MedioPago medio) {
        return switch (medio) {
            case CREDITO ->
                "Crédito";
            case DEBITO ->
                "Débito";
            case EFECTIVO ->
                "Efectivo";
            case BILLETERA_VIRTUAL ->
                "Billetera virtual";
        };
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        encabezadoVista = new javax.swing.JPanel();
        iconoVista = new javax.swing.JLabel();
        tituloVista = new javax.swing.JLabel();
        tituloGestion = new javax.swing.JLabel();
        subtituloGestion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tituloGestion1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtComprador = new javax.swing.JTextField();
        txtNumeroComprobante = new javax.swing.JTextField();
        dcHasta = new com.toedter.calendar.JDateChooser();
        dcDesde = new com.toedter.calendar.JDateChooser();
        tituloGestion2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbMedioPago = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnLimpiarFiltros1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblCantidadVentas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblImporteAcumulado = new javax.swing.JLabel();

        jTextField3.setText("jTextField1");

        encabezadoVista.setBackground(new java.awt.Color(20, 43, 73));

        iconoVista.setForeground(new java.awt.Color(255, 255, 255));
        iconoVista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta_pasajes.png"))); // NOI18N

        tituloVista.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloVista.setForeground(new java.awt.Color(255, 255, 255));
        tituloVista.setText("Ventas ");

        javax.swing.GroupLayout encabezadoVistaLayout = new javax.swing.GroupLayout(encabezadoVista);
        encabezadoVista.setLayout(encabezadoVistaLayout);
        encabezadoVistaLayout.setHorizontalGroup(
            encabezadoVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encabezadoVistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconoVista)
                .addGap(25, 25, 25))
        );
        encabezadoVistaLayout.setVerticalGroup(
            encabezadoVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encabezadoVistaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(encabezadoVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloVista)
                    .addComponent(iconoVista))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tituloGestion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tituloGestion.setForeground(new java.awt.Color(23, 47, 77));
        tituloGestion.setText("Ventas realizadas");

        subtituloGestion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        subtituloGestion.setForeground(new java.awt.Color(102, 102, 102));
        subtituloGestion.setText("Historial y consulta de operaciones ");

        jLabel1.setText("Cantidad de ventas");

        jLabel3.setText("Importe acumulado");

        tituloGestion1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tituloGestion1.setForeground(new java.awt.Color(23, 47, 77));
        tituloGestion1.setText("Filtros");

        jLabel5.setText("Número Comprobante");

        jLabel6.setText("Comprador");

        jLabel7.setText("Desde");

        jLabel8.setText("Hasta");

        tituloGestion2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tituloGestion2.setForeground(new java.awt.Color(23, 47, 77));
        tituloGestion2.setText("Listado");

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblVentas);

        jLabel2.setText("Medios de pago");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnLimpiarFiltros1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clean.png"))); // NOI18N
        btnLimpiarFiltros1.setText("Limpiar");

        jPanel2.setBackground(new java.awt.Color(20, 43, 73));

        lblCantidadVentas.setBackground(new java.awt.Color(255, 255, 255));
        lblCantidadVentas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCantidadVentas.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadVentas.setText("125");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantidadVentas)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblCantidadVentas)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(20, 43, 73));

        lblImporteAcumulado.setBackground(new java.awt.Color(255, 255, 255));
        lblImporteAcumulado.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblImporteAcumulado.setForeground(new java.awt.Color(255, 255, 255));
        lblImporteAcumulado.setText("$850.000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImporteAcumulado)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblImporteAcumulado)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(encabezadoVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tituloGestion2)
                    .addComponent(jScrollPane1)
                    .addComponent(tituloGestion)
                    .addComponent(subtituloGestion)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tituloGestion1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnLimpiarFiltros1)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnBuscar))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(txtComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(cmbMedioPago, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(encabezadoVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(tituloGestion)
                .addGap(18, 18, 18)
                .addComponent(subtituloGestion)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(tituloGestion1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMedioPago))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarFiltros1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloGestion2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        filtrarVentas();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnLimpiarFiltros1;
    private javax.swing.JComboBox<String> cmbMedioPago;
    private com.toedter.calendar.JDateChooser dcDesde;
    private com.toedter.calendar.JDateChooser dcHasta;
    private javax.swing.JPanel encabezadoVista;
    private javax.swing.JLabel iconoVista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblCantidadVentas;
    private javax.swing.JLabel lblImporteAcumulado;
    private javax.swing.JLabel subtituloGestion;
    private javax.swing.JTable tblVentas;
    private javax.swing.JLabel tituloGestion;
    private javax.swing.JLabel tituloGestion1;
    private javax.swing.JLabel tituloGestion2;
    private javax.swing.JLabel tituloVista;
    private javax.swing.JTextField txtComprador;
    private javax.swing.JTextField txtNumeroComprobante;
    // End of variables declaration//GEN-END:variables
}
