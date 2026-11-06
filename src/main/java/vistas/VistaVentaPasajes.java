package vistas;

public class VistaVentaPasajes extends javax.swing.JPanel {

    /**
     * Creates new form VistaVentaPasajes
     */
    public VistaVentaPasajes() {
        initComponents();
        aplicarEstilos();
        System.out.println("ESTILOS APLICADOS");
    }

    private void aplicarEstilos() {

        // Colores institucionales FlechaFLASH
        java.awt.Color azulMarino = new java.awt.Color(20, 43, 73);
        java.awt.Color blanco = java.awt.Color.WHITE;
        java.awt.Color grisTexto = new java.awt.Color(45, 55, 70);
        java.awt.Color grisClaro = new java.awt.Color(217, 222, 229);

        // Campos de texto
        javax.swing.JTextField[] campos = {
            txtFechaVenta,
            txtEstadoVenta,
            txtPrecioPasaje,
            txtNumeroComprobante,
            txtImporteTotal
        };

        for (javax.swing.JTextField campo : campos) {
            campo.setBackground(blanco);
            campo.setForeground(grisTexto);
            campo.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        }

        // Campos que no deben editarse manualmente
        txtFechaVenta.setEditable(false);
        txtEstadoVenta.setEditable(false);
        txtPrecioPasaje.setEditable(false);
        txtImporteTotal.setEditable(false);

        // Botones
        btnAgregarPasaje.setBackground(azulMarino);
        btnAgregarPasaje.setForeground(blanco);
        btnAgregarPasaje.setOpaque(true);
        btnAgregarPasaje.setContentAreaFilled(true);
        btnAgregarPasaje.setBorderPainted(false);

        btnRegistrarVenta.setBackground(azulMarino);
        btnRegistrarVenta.setForeground(blanco);
        btnRegistrarVenta.setOpaque(true);
        btnRegistrarVenta.setContentAreaFilled(true);
        btnRegistrarVenta.setBorderPainted(false);

        btnCancelarVenta.setBackground(grisClaro);
        btnCancelarVenta.setForeground(grisTexto);
        btnCancelarVenta.setOpaque(true);
        btnCancelarVenta.setContentAreaFilled(true);
        btnCancelarVenta.setBorderPainted(false);

        txtFechaVenta.setText("");
        txtEstadoVenta.setText("");
        txtPrecioPasaje.setText("");
        txtNumeroComprobante.setText("");
        txtImporteTotal.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelContenido = new javax.swing.JPanel();
        panelDatosventa = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbComprador = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFechaVenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEstadoVenta = new javax.swing.JTextField();
        panelSeccionPasajes = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbRutaViaje = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbOrigen = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbPasajero = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbAsiento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnAgregarPasaje = new javax.swing.JButton();
        txtPrecioPasaje = new javax.swing.JTextField();
        panelDetallePasajes = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPasajes = new javax.swing.JTable();
        panelPago = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbMedioPago = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbCuotas = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbTipoComprobante = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtNumeroComprobante = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        btnCancelarVenta = new javax.swing.JButton();
        btnRegistrarVenta = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(20, 43, 73));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Venta de Pasajes ");
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        panelContenido.setBackground(new java.awt.Color(204, 204, 204));

        panelDatosventa.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(20, 43, 73));
        jLabel2.setText("DATOS DE LA VENTA");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(65, 75, 90));
        jLabel3.setText("Comprador ");

        cbComprador.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbComprador.setForeground(new java.awt.Color(45, 55, 70));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(65, 75, 90));
        jLabel4.setText("Fecha y Hora ");

        txtFechaVenta.setEditable(false);
        txtFechaVenta.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtFechaVenta.setForeground(new java.awt.Color(45, 55, 70));
        txtFechaVenta.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(65, 75, 90));
        jLabel5.setText("Estado ");

        txtEstadoVenta.setEditable(false);
        txtEstadoVenta.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtEstadoVenta.setForeground(new java.awt.Color(45, 55, 70));
        txtEstadoVenta.setText("jTextField1");

        javax.swing.GroupLayout panelDatosventaLayout = new javax.swing.GroupLayout(panelDatosventa);
        panelDatosventa.setLayout(panelDatosventaLayout);
        panelDatosventaLayout.setHorizontalGroup(
            panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosventaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosventaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(34, 34, 34)
                        .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(panelDatosventaLayout.createSequentialGroup()
                        .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(45, 45, 45)
                        .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDatosventaLayout.createSequentialGroup()
                                .addComponent(txtEstadoVenta)
                                .addGap(167, 167, 167)))))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        panelDatosventaLayout.setVerticalGroup(
            panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosventaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEstadoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        panelSeccionPasajes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(20, 43, 73));
        jLabel6.setText("SELECCION DE PASAJES ");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(65, 75, 90));
        jLabel7.setText("Ruta de Viaje ");

        cbRutaViaje.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbRutaViaje.setForeground(new java.awt.Color(45, 55, 70));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(65, 75, 90));
        jLabel8.setText("Origen :");

        cbOrigen.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbOrigen.setForeground(new java.awt.Color(45, 55, 70));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(65, 75, 90));
        jLabel9.setText("Destino:");

        cbDestino.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbDestino.setForeground(new java.awt.Color(45, 55, 70));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(65, 75, 90));
        jLabel10.setText("Pasajero");

        cbPasajero.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbPasajero.setForeground(new java.awt.Color(45, 55, 70));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(65, 75, 90));
        jLabel11.setText("Asiento");

        cbAsiento.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbAsiento.setForeground(new java.awt.Color(45, 55, 70));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(65, 75, 90));
        jLabel12.setText("Precio: ");

        btnAgregarPasaje.setBackground(new java.awt.Color(20, 43, 73));
        btnAgregarPasaje.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAgregarPasaje.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPasaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Nuevo.png"))); // NOI18N
        btnAgregarPasaje.setText("AGREGAR PASAJE");

        txtPrecioPasaje.setEditable(false);
        txtPrecioPasaje.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtPrecioPasaje.setForeground(new java.awt.Color(45, 55, 70));
        txtPrecioPasaje.setText("jTextField1");

        javax.swing.GroupLayout panelSeccionPasajesLayout = new javax.swing.GroupLayout(panelSeccionPasajes);
        panelSeccionPasajes.setLayout(panelSeccionPasajesLayout);
        panelSeccionPasajesLayout.setHorizontalGroup(
            panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                        .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel8))
                                .addGap(41, 41, 41)
                                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbRutaViaje, 0, 208, Short.MAX_VALUE)
                                    .addComponent(cbOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSeccionPasajesLayout.setVerticalGroup(
            panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbRutaViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cbAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9))
                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtPrecioPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnAgregarPasaje)
                .addGap(23, 23, 23))
        );

        panelDetallePasajes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(20, 43, 73));
        jLabel13.setText("DETALLE DE PASAJES ");

        tablaPasajes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tablaPasajes.setForeground(new java.awt.Color(45, 55, 70));
        tablaPasajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pasajero", "Ruta", "Origen", "Destino", "Asiento", "Precio"
            }
        ));
        tablaPasajes.setRowHeight(28);
        tablaPasajes.setSelectionBackground(new java.awt.Color(210, 222, 238));
        tablaPasajes.setSelectionForeground(new java.awt.Color(20, 43, 73));
        jScrollPane1.setViewportView(tablaPasajes);

        javax.swing.GroupLayout panelDetallePasajesLayout = new javax.swing.GroupLayout(panelDetallePasajes);
        panelDetallePasajes.setLayout(panelDetallePasajesLayout);
        panelDetallePasajesLayout.setHorizontalGroup(
            panelDetallePasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallePasajesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelDetallePasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDetallePasajesLayout.setVerticalGroup(
            panelDetallePasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallePasajesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        panelPago.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(20, 43, 73));
        jLabel14.setText("PAGO Y COMPROBANTES");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(65, 75, 90));
        jLabel15.setText("Medio de pago");

        cbMedioPago.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbMedioPago.setForeground(new java.awt.Color(45, 55, 70));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(65, 75, 90));
        jLabel16.setText("Cuotas");

        cbCuotas.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbCuotas.setForeground(new java.awt.Color(45, 55, 70));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(65, 75, 90));
        jLabel17.setText("Tipo de comprobante");

        cbTipoComprobante.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbTipoComprobante.setForeground(new java.awt.Color(45, 55, 70));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(65, 75, 90));
        jLabel18.setText("Número de comprobante");

        txtNumeroComprobante.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNumeroComprobante.setForeground(new java.awt.Color(45, 55, 70));
        txtNumeroComprobante.setText("jTextField1");
        txtNumeroComprobante.addActionListener(this::txtNumeroComprobanteActionPerformed);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(65, 75, 90));
        jLabel19.setText("Importe total: ");

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtImporteTotal.setForeground(new java.awt.Color(45, 55, 70));
        txtImporteTotal.setText("jTextField1");

        btnCancelarVenta.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelarVenta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCancelarVenta.setForeground(new java.awt.Color(38, 54, 74));
        btnCancelarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Desactivar.png"))); // NOI18N
        btnCancelarVenta.setText("CANCELAR ");
        btnCancelarVenta.setOpaque(false);
        btnCancelarVenta.addActionListener(this::btnCancelarVentaActionPerformed);

        btnRegistrarVenta.setBackground(new java.awt.Color(20, 43, 73));
        btnRegistrarVenta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnRegistrarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/NuevaVentaI.png"))); // NOI18N
        btnRegistrarVenta.setText("REGISTRAR VENTA ");

        javax.swing.GroupLayout panelPagoLayout = new javax.swing.GroupLayout(panelPago);
        panelPago.setLayout(panelPagoLayout);
        panelPagoLayout.setHorizontalGroup(
            panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPagoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbMedioPago, 0, 156, Short.MAX_VALUE)
                            .addComponent(cbCuotas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(txtImporteTotal)))
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPagoLayout.setVerticalGroup(
            panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPagoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cbMedioPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarVenta))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout panelContenidoLayout = new javax.swing.GroupLayout(panelContenido);
        panelContenido.setLayout(panelContenidoLayout);
        panelContenidoLayout.setHorizontalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSeccionPasajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDatosventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDetallePasajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        panelContenidoLayout.setVerticalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(panelDatosventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSeccionPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDetallePasajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(panelContenido);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroComprobanteActionPerformed

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarVentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPasaje;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JComboBox<String> cbAsiento;
    private javax.swing.JComboBox<String> cbComprador;
    private javax.swing.JComboBox<String> cbCuotas;
    private javax.swing.JComboBox<String> cbDestino;
    private javax.swing.JComboBox<String> cbMedioPago;
    private javax.swing.JComboBox<String> cbOrigen;
    private javax.swing.JComboBox<String> cbPasajero;
    private javax.swing.JComboBox<String> cbRutaViaje;
    private javax.swing.JComboBox<String> cbTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatosventa;
    private javax.swing.JPanel panelDetallePasajes;
    private javax.swing.JPanel panelPago;
    private javax.swing.JPanel panelSeccionPasajes;
    private javax.swing.JTable tablaPasajes;
    private javax.swing.JTextField txtEstadoVenta;
    private javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtNumeroComprobante;
    private javax.swing.JTextField txtPrecioPasaje;
    // End of variables declaration//GEN-END:variables
}
