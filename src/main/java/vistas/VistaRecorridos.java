
package vistas;


public class VistaRecorridos extends javax.swing.JPanel {

    
    public VistaRecorridos() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerVista = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblRecorridosRegistrados = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        scrollRecorridos = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecorridos = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlFormularioRecorrido = new java.awt.Panel();
        pnlDescripcion = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAgregarParada = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblDatosRecorrido = new javax.swing.JLabel();
        pnlParadas = new javax.swing.JPanel();
        lblParadasRecorrido = new javax.swing.JLabel();

        headerVista.setBackground(new java.awt.Color(20, 43, 73));
        headerVista.setPreferredSize(new java.awt.Dimension(181, 85));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Recorridos");
        jLabel1.setToolTipText("");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/recorridos.png"))); // NOI18N

        javax.swing.GroupLayout headerVistaLayout = new javax.swing.GroupLayout(headerVista);
        headerVista.setLayout(headerVistaLayout);
        headerVistaLayout.setHorizontalGroup(
            headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerVistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27))
        );
        headerVistaLayout.setVerticalGroup(
            headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerVistaLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        lblRecorridosRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRecorridosRegistrados.setForeground(new java.awt.Color(20, 43, 73));
        lblRecorridosRegistrados.setText("Recorridos registrados");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(20, 43, 73));
        lblTitulo.setText("Gestión de Recorridos");

        lblSubtitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitulo.setText("Administrá los recorridos y sus paradas");

        scrollRecorridos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        tblRecorridos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblRecorridos.setForeground(new java.awt.Color(23, 47, 77));
        tblRecorridos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Descripción", "Paradas", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRecorridos.setRowHeight(30);
        tblRecorridos.setSelectionBackground(new java.awt.Color(190, 215, 245));
        tblRecorridos.setSelectionForeground(new java.awt.Color(23, 47, 77));
        tblRecorridos.setShowHorizontalLines(true);
        tblRecorridos.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(tblRecorridos);

        scrollRecorridos.setViewportView(jScrollPane1);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Editar.png"))); // NOI18N
        btnEditar.setText("Editar");

        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Desactivar.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");

        pnlFormularioRecorrido.setBackground(new java.awt.Color(238, 238, 238));

        pnlDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        pnlDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        pnlDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDescripcionMouseClicked(evt);
            }
        });

        lblDescripcion.setForeground(new java.awt.Color(82, 100, 125));
        lblDescripcion.setText("DESCRIPCIÓN");

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(23, 47, 77));
        txtDescripcion.setText("jTextField1");

        javax.swing.GroupLayout pnlDescripcionLayout = new javax.swing.GroupLayout(pnlDescripcion);
        pnlDescripcion.setLayout(pnlDescripcionLayout);
        pnlDescripcionLayout.setHorizontalGroup(
            pnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescripcionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDescripcionLayout.setVerticalGroup(
            pnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescripcionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnAgregarParada.setBackground(new java.awt.Color(238, 238, 238));
        btnAgregarParada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarParada.setForeground(new java.awt.Color(0, 108, 158));
        btnAgregarParada.setText("+ Agregar parada");
        btnAgregarParada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 108, 158)));
        btnAgregarParada.setFocusable(false);

        btnGuardar.setBackground(new java.awt.Color(0, 108, 158));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setOpaque(true);

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(82, 100, 125));
        btnCancelar.setText("Cancelar");
        btnCancelar.setOpaque(true);

        lblDatosRecorrido.setBackground(new java.awt.Color(238, 238, 238));
        lblDatosRecorrido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDatosRecorrido.setForeground(new java.awt.Color(23, 47, 77));
        lblDatosRecorrido.setText("Datos del recorrido");

        pnlParadas.setBackground(new java.awt.Color(238, 238, 238));
        pnlParadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlParadasMouseClicked(evt);
            }
        });
        pnlParadas.setLayout(new javax.swing.BoxLayout(pnlParadas, javax.swing.BoxLayout.Y_AXIS));

        lblParadasRecorrido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblParadasRecorrido.setForeground(new java.awt.Color(23, 47, 77));
        lblParadasRecorrido.setText("Paradas del recorrido");

        javax.swing.GroupLayout pnlFormularioRecorridoLayout = new javax.swing.GroupLayout(pnlFormularioRecorrido);
        pnlFormularioRecorrido.setLayout(pnlFormularioRecorridoLayout);
        pnlFormularioRecorridoLayout.setHorizontalGroup(
            pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioRecorridoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlParadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblParadasRecorrido)
                    .addComponent(lblDatosRecorrido)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioRecorridoLayout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregarParada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnlFormularioRecorridoLayout.setVerticalGroup(
            pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioRecorridoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDatosRecorrido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblParadasRecorrido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlParadas, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarParada, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRecorridosRegistrados)
                            .addComponent(scrollRecorridos)
                            .addComponent(lblSubtitulo)
                            .addComponent(pnlFormularioRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(35, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(btnNuevo)
                .addGap(35, 35, 35)
                .addComponent(btnEditar)
                .addGap(35, 35, 35)
                .addComponent(btnDesactivar)
                .addGap(35, 35, 35)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblRecorridosRegistrados)
                .addGap(18, 18, 18)
                .addComponent(scrollRecorridos, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addComponent(pnlFormularioRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlDescripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDescripcionMouseClicked

    }//GEN-LAST:event_pnlDescripcionMouseClicked

    private void pnlParadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlParadasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlParadasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarParada;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private java.awt.Panel headerVista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatosRecorrido;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblParadasRecorrido;
    private javax.swing.JLabel lblRecorridosRegistrados;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlDescripcion;
    private java.awt.Panel pnlFormularioRecorrido;
    private javax.swing.JPanel pnlParadas;
    private javax.swing.JScrollPane scrollRecorridos;
    private javax.swing.JTable tblRecorridos;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
