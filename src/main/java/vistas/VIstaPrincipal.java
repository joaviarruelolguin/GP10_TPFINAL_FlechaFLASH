
package vistas;


public class VistaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaPrincipal.class.getName());

   
    public VistaPrincipal() {
        initComponents();
        configurarNavegacion();
        mostrarPantalla("INICIO");
    }

    // Se crea un objeto de cada clase/vista y se los agrega como contenedor al pnlContenido para luego poder hacerlos visibles.
    private void configurarNavegacion() {
    
    // Registrar paneles agregando las instancias y asignar identificadores.
    pnlContenido.add(new VistaInicio(), "INICIO");
    pnlContenido.add(new VistaRecorridos(), "RECORRIDOS");
    pnlContenido.add(new VistaColectivos(), "COLECTIVOS");
    pnlContenido.add(new VistaAsientos(), "ASIENTOS");
    pnlContenido.add(new VistaSalidasHorarios(), "SALIDASHORARIOS");
    pnlContenido.add(new VistaVentaPasajes(), "VENTAPASAJES");
    pnlContenido.add(new VistaPasajeros(), "PASAJEROS");
    pnlContenido.add(new VistaVentasRealizadas(), "VENTASREALIZADAS");
    
    }
    
    // Método para indicar a Java que al abrir el sistema muestre VistaInicio
    private void mostrarPantalla(String nombre) {
        
        java.awt.CardLayout cl = (java.awt.CardLayout) pnlContenido.getLayout();
        // CardLayout busca de forma automática el panel registrado con determinado identificador y lo muestra.
        cl.show(pnlContenido, nombre);
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPrincipal = new javax.swing.JPanel();
        pnlContenido = new javax.swing.JPanel();
        pnlMenuLateral = new javax.swing.JPanel();
        lblNombreSistema = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        btnPasajeros = new javax.swing.JButton();
        btnColectivos = new javax.swing.JButton();
        btnRecorridos = new javax.swing.JButton();
        btnVentasRealizadas = new javax.swing.JButton();
        btnVentasPasajes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        btnSalidasHorarios = new javax.swing.JButton();
        btnAsientos = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FlechaFLASH");
        setBackground(new java.awt.Color(204, 255, 204));
        setResizable(false);

        pnPrincipal.setLayout(new java.awt.BorderLayout());

        pnlContenido.setBackground(new java.awt.Color(241, 245, 249));
        pnlContenido.setLayout(new java.awt.CardLayout());
        pnPrincipal.add(pnlContenido, java.awt.BorderLayout.CENTER);

        pnlMenuLateral.setBackground(new java.awt.Color(20, 43, 73));
        pnlMenuLateral.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pnlMenuLateral.setPreferredSize(new java.awt.Dimension(270, 723));

        lblNombreSistema.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lblNombreSistema.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreSistema.setText("FlechaFLASH");

        lblSubtitulo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(184, 199, 217));
        lblSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitulo.setText("Gestión de transporte");

        btnInicio.setBackground(new java.awt.Color(30, 58, 95));
        btnInicio.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/inicio.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorderPainted(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.setFocusPainted(false);
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInicio.addActionListener(this::btnInicioActionPerformed);

        btnPasajeros.setBackground(new java.awt.Color(30, 58, 95));
        btnPasajeros.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPasajeros.setForeground(new java.awt.Color(255, 255, 255));
        btnPasajeros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pasajeros.png"))); // NOI18N
        btnPasajeros.setText("Pasajeros");
        btnPasajeros.setBorderPainted(false);
        btnPasajeros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPasajeros.setFocusPainted(false);
        btnPasajeros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPasajeros.addActionListener(this::btnPasajerosActionPerformed);

        btnColectivos.setBackground(new java.awt.Color(30, 58, 95));
        btnColectivos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnColectivos.setForeground(new java.awt.Color(255, 255, 255));
        btnColectivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/colectivos.png"))); // NOI18N
        btnColectivos.setText("Colectivos ");
        btnColectivos.setBorderPainted(false);
        btnColectivos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnColectivos.setFocusPainted(false);
        btnColectivos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnColectivos.addActionListener(this::btnColectivosActionPerformed);

        btnRecorridos.setBackground(new java.awt.Color(30, 58, 95));
        btnRecorridos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRecorridos.setForeground(new java.awt.Color(255, 255, 255));
        btnRecorridos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/recorridos.png"))); // NOI18N
        btnRecorridos.setText("Recorridos");
        btnRecorridos.setBorderPainted(false);
        btnRecorridos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecorridos.setFocusPainted(false);
        btnRecorridos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRecorridos.addActionListener(this::btnRecorridosActionPerformed);

        btnVentasRealizadas.setBackground(new java.awt.Color(30, 58, 95));
        btnVentasRealizadas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnVentasRealizadas.setForeground(new java.awt.Color(255, 255, 255));
        btnVentasRealizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ventas_realizadas.png"))); // NOI18N
        btnVentasRealizadas.setText("Ventas realizadas");
        btnVentasRealizadas.setBorderPainted(false);
        btnVentasRealizadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentasRealizadas.setFocusPainted(false);
        btnVentasRealizadas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentasRealizadas.addActionListener(this::btnVentasRealizadasActionPerformed);

        btnVentasPasajes.setBackground(new java.awt.Color(30, 58, 95));
        btnVentasPasajes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnVentasPasajes.setForeground(new java.awt.Color(255, 255, 255));
        btnVentasPasajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta_pasajes.png"))); // NOI18N
        btnVentasPasajes.setText("Ventas de pasajes");
        btnVentasPasajes.setBorderPainted(false);
        btnVentasPasajes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentasPasajes.setFocusPainted(false);
        btnVentasPasajes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentasPasajes.addActionListener(this::btnVentasPasajesActionPerformed);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/autobus.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(204, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GENERAL");

        jLabel4.setBackground(new java.awt.Color(204, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VENTAS");

        btnSalidasHorarios.setBackground(new java.awt.Color(30, 58, 95));
        btnSalidasHorarios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalidasHorarios.setForeground(new java.awt.Color(255, 255, 255));
        btnSalidasHorarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salidas_horarios.png"))); // NOI18N
        btnSalidasHorarios.setText("Salidas y horarios");
        btnSalidasHorarios.setBorderPainted(false);
        btnSalidasHorarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalidasHorarios.setFocusPainted(false);
        btnSalidasHorarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSalidasHorarios.addActionListener(this::btnSalidasHorariosActionPerformed);

        btnAsientos.setBackground(new java.awt.Color(30, 58, 95));
        btnAsientos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAsientos.setForeground(new java.awt.Color(255, 255, 255));
        btnAsientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/asientos.png"))); // NOI18N
        btnAsientos.setText("Asientos ");
        btnAsientos.setBorderPainted(false);
        btnAsientos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsientos.setFocusPainted(false);
        btnAsientos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAsientos.addActionListener(this::btnAsientosActionPerformed);

        jLabel5.setBackground(new java.awt.Color(204, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ADMINISTRACIÓN");

        javax.swing.GroupLayout pnlMenuLateralLayout = new javax.swing.GroupLayout(pnlMenuLateral);
        pnlMenuLateral.setLayout(pnlMenuLateralLayout);
        pnlMenuLateralLayout.setHorizontalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVentasPasajes)
                            .addComponent(jLabel4)
                            .addComponent(btnPasajeros)
                            .addComponent(btnVentasRealizadas))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(15, 15, 15))))
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(btnRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnColectivos)
                            .addComponent(btnAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalidasHorarios)
                            .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombreSistema, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(lblSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pnlMenuLateralLayout.setVerticalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreSistema)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnColectivos, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalidasHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(btnVentasPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPasajeros, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVentasRealizadas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pnPrincipal.add(pnlMenuLateral, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Cuando el usuario hace clic en botón, inicio en este caso, se ejecuta el evento y mostrarPantalla visualiza vista
    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        mostrarPantalla("INICIO");
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnSalidasHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidasHorariosActionPerformed
        mostrarPantalla("SALIDASHORARIOS");
    }//GEN-LAST:event_btnSalidasHorariosActionPerformed

    private void btnRecorridosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecorridosActionPerformed
        mostrarPantalla("RECORRIDOS");
    }//GEN-LAST:event_btnRecorridosActionPerformed

    private void btnColectivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColectivosActionPerformed
        mostrarPantalla("COLECTIVOS");
    }//GEN-LAST:event_btnColectivosActionPerformed

    private void btnAsientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsientosActionPerformed
        mostrarPantalla("ASIENTOS");
    }//GEN-LAST:event_btnAsientosActionPerformed

    private void btnVentasPasajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasPasajesActionPerformed
        mostrarPantalla("VENTAPASAJES");
    }//GEN-LAST:event_btnVentasPasajesActionPerformed

    private void btnPasajerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasajerosActionPerformed
        mostrarPantalla("PASAJEROS");
    }//GEN-LAST:event_btnPasajerosActionPerformed

    private void btnVentasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasRealizadasActionPerformed
        mostrarPantalla("VENTASREALIZADAS");
    }//GEN-LAST:event_btnVentasRealizadasActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new VistaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsientos;
    private javax.swing.JButton btnColectivos;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnPasajeros;
    private javax.swing.JButton btnRecorridos;
    private javax.swing.JButton btnSalidasHorarios;
    private javax.swing.JButton btnVentasPasajes;
    private javax.swing.JButton btnVentasRealizadas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblNombreSistema;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JPanel pnPrincipal;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlMenuLateral;
    // End of variables declaration//GEN-END:variables
}
