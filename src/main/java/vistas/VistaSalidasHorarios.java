package vistas;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import persistencia.RecorridoData;
import persistencia.ColectivoData;
import modelo.Recorrido;
import modelo.Colectivo;
import modelo.ParadaRecorrido;


public class VistaSalidasHorarios extends javax.swing.JPanel {

      private RecorridoData recorridoData = new RecorridoData();
    private ColectivoData colectivoData = new ColectivoData();
    
    public VistaSalidasHorarios() {
        initComponents();
        configurarHoraSalida();
        configurarTablaHorarios();
        cargarComboBox();
        habilitarFormulario(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerVista = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblParadasHorarios = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHorariosEstimados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSalidasRegistradas = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblDatosSalidas = new javax.swing.JLabel();
        pnlDatosSalida = new javax.swing.JPanel();
        lblRecorrido = new javax.swing.JLabel();
        cmbRecorrido = new javax.swing.JComboBox<>();
        lblHoraSalida = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblColectivo1 = new javax.swing.JLabel();
        lblPrecioBase = new javax.swing.JLabel();
        cmbColectivo = new javax.swing.JComboBox<>();
        dtChFecha = new com.toedter.calendar.JDateChooser();
        spnHoraSalida = new javax.swing.JSpinner();
        txtPrecioBase = new javax.swing.JTextField();
        lblSalidasRegistradas1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(238, 238, 238));

        headerVista.setBackground(new java.awt.Color(20, 43, 73));
        headerVista.setPreferredSize(new java.awt.Dimension(181, 85));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel1.setText("Salidas y Horarios");
        jLabel1.setToolTipText("");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salidas_horarios.png"))); // NOI18N

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
                .addGroup(headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28))
        );

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(23, 47, 77));
        lblTitulo.setText("Gestión de Salidas y Horarios");

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(82, 100, 125));
        lblSubtitulo.setText("Registrá las salidas de los viajes y calculá los horarios estimados de cada parada");

        lblParadasHorarios.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblParadasHorarios.setForeground(new java.awt.Color(23, 47, 77));
        lblParadasHorarios.setText("Paradas y horarios estimados");

        tblHorariosEstimados.setBackground(new java.awt.Color(230, 233, 237));
        tblHorariosEstimados.setForeground(new java.awt.Color(82, 100, 125));
        tblHorariosEstimados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Orden", "Parada", "Km acumulados", "Hora de llegada (est.)", "Hora de salida (est.)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHorariosEstimados.setRowHeight(42);
        tblHorariosEstimados.setSelectionBackground(new java.awt.Color(210, 225, 238));
        jScrollPane1.setViewportView(tblHorariosEstimados);

        tblSalidasRegistradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Salida", "Recorrido", "Colectivo", "Fecha", "Hora", "Precio base", "Estado"
            }
        ));
        jScrollPane2.setViewportView(tblSalidasRegistradas);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Editar.png"))); // NOI18N
        btnEditar.setText("Editar");

        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Desactivar.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");

        lblDatosSalidas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDatosSalidas.setForeground(new java.awt.Color(23, 47, 77));
        lblDatosSalidas.setText("Datos de la salida");

        pnlDatosSalida.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosSalida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        pnlDatosSalida.setForeground(new java.awt.Color(200, 200, 200));
        pnlDatosSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlDatosSalidaMouseClicked(evt);
            }
        });

        lblRecorrido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblRecorrido.setForeground(new java.awt.Color(82, 100, 125));
        lblRecorrido.setText("RECORRIDO");

        cmbRecorrido.addItemListener(this::cmbRecorridoItemStateChanged);
        cmbRecorrido.addActionListener(this::cmbRecorridoActionPerformed);

        lblHoraSalida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHoraSalida.setForeground(new java.awt.Color(82, 100, 125));
        lblHoraSalida.setText("HORA DE SALIDA");

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(82, 100, 125));
        lblFecha.setText("FECHA");

        lblColectivo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblColectivo1.setForeground(new java.awt.Color(82, 100, 125));
        lblColectivo1.setText("COLECTIVO");

        lblPrecioBase.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPrecioBase.setForeground(new java.awt.Color(82, 100, 125));
        lblPrecioBase.setText("PRECIO BASE");

        dtChFecha.setDateFormatString("dd/MM/yyyy");

        spnHoraSalida.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        spnHoraSalida.setEditor(new javax.swing.JSpinner.DateEditor(spnHoraSalida, "HH:mm"));

        javax.swing.GroupLayout pnlDatosSalidaLayout = new javax.swing.GroupLayout(pnlDatosSalida);
        pnlDatosSalida.setLayout(pnlDatosSalidaLayout);
        pnlDatosSalidaLayout.setHorizontalGroup(
            pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosSalidaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRecorrido))
                .addGap(47, 47, 47)
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblColectivo1)
                    .addComponent(cmbColectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosSalidaLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(lblFecha))
                    .addGroup(pnlDatosSalidaLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(dtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnHoraSalida)
                    .addComponent(lblHoraSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58)
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecioBase)
                    .addComponent(txtPrecioBase, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        pnlDatosSalidaLayout.setVerticalGroup(
            pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosSalidaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecorrido)
                    .addComponent(lblColectivo1)
                    .addComponent(lblFecha)
                    .addComponent(lblHoraSalida)
                    .addComponent(lblPrecioBase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbRecorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbColectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrecioBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtChFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        lblSalidasRegistradas1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSalidasRegistradas1.setForeground(new java.awt.Color(23, 47, 77));
        lblSalidasRegistradas1.setText("Salidas registradas");

        btnGuardar.setBackground(new java.awt.Color(0, 108, 158));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setOpaque(true);

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(82, 100, 125));
        btnCancelar.setText("Cancelar");
        btnCancelar.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubtitulo)
                            .addComponent(lblTitulo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlDatosSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblDatosSalidas)
                                            .addComponent(btnNuevo))
                                        .addGap(35, 35, 35)
                                        .addComponent(btnEditar)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnDesactivar)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnEliminar))
                                    .addComponent(lblParadasHorarios))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(lblSalidasRegistradas1)
                    .addContainerGap(615, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo)
                .addGap(83, 83, 83)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(lblDatosSalidas)
                .addGap(18, 18, 18)
                .addComponent(pnlDatosSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(lblParadasHorarios)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(238, 238, 238)
                    .addComponent(lblSalidasRegistradas1)
                    .addContainerGap(712, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void configurarHoraSalida() {
         spnHoraSalida.setEditor(new JSpinner.DateEditor(spnHoraSalida, "HH:mm"));
    }
    
    
    private void configurarTablaHorarios() {
        javax.swing.table.DefaultTableCellRenderer automatico = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setBackground(new java.awt.Color(230, 233, 237));
                setForeground(new java.awt.Color(100, 100, 100));
                setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                return this;
            }
        };

        
        javax.swing.table.TableCellRenderer editable = new javax.swing.table.TableCellRenderer() {
            private final javax.swing.JTextField campo = new javax.swing.JTextField();

            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                campo.setText(value == null ? "" : value.toString());
                campo.setBackground(java.awt.Color.WHITE);
                campo.setForeground(new java.awt.Color(23, 47, 77));
                campo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
                campo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                campo.setBorder(javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(180, 190, 200)
                ));

                campo.setPreferredSize(new java.awt.Dimension(90, 28));
            
                javax.swing.JPanel contenedor = new javax.swing.JPanel(new java.awt.GridBagLayout());
                contenedor.setBackground(new java.awt.Color(190, 228, 248));
                contenedor.add(campo);
                return contenedor;
            }
        };

        for (int i = 0; i < 5; i++) {
            tblHorariosEstimados.getColumnModel().getColumn(i).setCellRenderer(
                    i == 2 ? editable : automatico
            );
        }
         
        tblHorariosEstimados.setGridColor(new java.awt.Color(215, 220, 225));
        tblHorariosEstimados.setIntercellSpacing(new java.awt.Dimension(1, 1));
        tblHorariosEstimados.setShowGrid(true);
        
         javax.swing.JTextField campoKm = new javax.swing.JTextField();

        campoKm.setBackground(new java.awt.Color(255, 255, 255));
        campoKm.setForeground(new java.awt.Color(23, 47, 77));
        campoKm.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        campoKm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoKm.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(180, 190, 200)
        ));

        tblHorariosEstimados.getColumnModel().getColumn(2).setCellEditor(new javax.swing.DefaultCellEditor(campoKm));

    }

    private void cargarComboBox() {
        cmbRecorrido.removeAllItems();
        cmbColectivo.removeAllItems();

        for (Recorrido recorrido : recorridoData.listarRecorridosActivos()) {
            cmbRecorrido.addItem(recorrido);
        }

        for (Colectivo colectivo : colectivoData.listarColectivosActivos()) {
            cmbColectivo.addItem(colectivo);
        }
    }

    
    private void habilitarFormulario(boolean habilitar) {
        cmbRecorrido.setEnabled(habilitar);
        cmbColectivo.setEnabled(habilitar);
        dtChFecha.setEnabled(habilitar);
        spnHoraSalida.setEnabled(habilitar);
        txtPrecioBase.setEnabled(habilitar);
        tblHorariosEstimados.setEnabled(habilitar);

        btnGuardar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);

        btnNuevo.setEnabled(!habilitar);
        btnEditar.setEnabled(!habilitar);
        btnDesactivar.setEnabled(!habilitar);
        btnEliminar.setEnabled(!habilitar);
    }
    
    

    private void pnlDatosSalidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDatosSalidaMouseClicked

    }//GEN-LAST:event_pnlDatosSalidaMouseClicked

    private void cmbRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRecorridoActionPerformed
        
        
        
    }//GEN-LAST:event_cmbRecorridoActionPerformed

    private void limpiarFormulario() {
        cmbRecorrido.setSelectedIndex(-1);
        cmbColectivo.setSelectedIndex(-1);

        dtChFecha.setDate(null);
        spnHoraSalida.setValue(new java.util.Date());

        txtPrecioBase.setText("");

        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) tblHorariosEstimados.getModel();

        modelo.setRowCount(0);
    }
    
    
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        limpiarFormulario();
        habilitarFormulario(true);

    }//GEN-LAST:event_btnNuevoActionPerformed

    
    
    
    private void cargarParadas(Recorrido recorrido) {
        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) tblHorariosEstimados.getModel();

        modelo.setRowCount(0);

        for (ParadaRecorrido parada : recorrido.getParadas()) {
            modelo.addRow(new Object[]{
                parada.getOrden(),
                parada.getNombreLugar(),
                parada.getDistanciaAcumuladaKm(),
                "",
                ""
            });
        }
    }

    
    private void cmbRecorridoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRecorridoItemStateChanged

        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Recorrido recorrido = (Recorrido) cmbRecorrido.getSelectedItem();

            if (recorrido != null) {
                cargarParadas(recorrido);
            }
        }

    }//GEN-LAST:event_cmbRecorridoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<Colectivo> cmbColectivo;
    private javax.swing.JComboBox<Recorrido> cmbRecorrido;
    private com.toedter.calendar.JDateChooser dtChFecha;
    private java.awt.Panel headerVista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblColectivo1;
    private javax.swing.JLabel lblDatosSalidas;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHoraSalida;
    private javax.swing.JLabel lblParadasHorarios;
    private javax.swing.JLabel lblPrecioBase;
    private javax.swing.JLabel lblRecorrido;
    private javax.swing.JLabel lblSalidasRegistradas1;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlDatosSalida;
    private javax.swing.JSpinner spnHoraSalida;
    private javax.swing.JTable tblHorariosEstimados;
    private javax.swing.JTable tblSalidasRegistradas;
    private javax.swing.JTextField txtPrecioBase;
    // End of variables declaration//GEN-END:variables
}
