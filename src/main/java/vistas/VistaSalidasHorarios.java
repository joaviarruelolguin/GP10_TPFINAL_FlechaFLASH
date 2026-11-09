package vistas;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import logica.CalculadorTiempos;
import persistencia.RecorridoData;
import persistencia.ColectivoData;
import modelo.Recorrido;
import modelo.Colectivo;
import modelo.ParadaRecorrido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.RutaViaje;
import modelo.HorarioParadaViaje;
import persistencia.RutaViajeData;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import controlador.NotificadorCambios;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VistaSalidasHorarios extends javax.swing.JPanel implements PropertyChangeListener {

    private RecorridoData recorridoData = new RecorridoData();
    private ColectivoData colectivoData = new ColectivoData();
    private RutaViajeData rutaViajeData = new RutaViajeData();

    private CalculadorTiempos calculadorTiempos = new CalculadorTiempos();

    private List<RutaViaje> listaRutas;

    private RutaViaje rutaEnEdicion = null;

    public VistaSalidasHorarios() {
        initComponents();
        NotificadorCambios.agregarListener(this);
        tblSalidasRegistradas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        tblSalidasRegistradas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarBotonEstado();
            }
        });

        tblSalidasRegistradas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !btnGuardar.isEnabled()) {
                mostrarHorariosSalidaSeleccionada();
            }
        });

        configurarHoraSalida();

        spnHoraSalida.addChangeListener(e -> {
            Recorrido recorrido = (Recorrido) cmbRecorrido.getSelectedItem();
            if (recorrido != null) {
                cargarParadas(recorrido);
            }
        });

        configurarTablaHorarios();
        cargarComboBox();
        cargarTablaSalidas();
        habilitarFormulario(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String entidad = (String) evt.getNewValue();

        if (NotificadorCambios.RECORRIDOS.equals(entidad)
                || NotificadorCambios.COLECTIVOS.equals(entidad)) {
            sincronizarCombos();
        }
    }

    private void sincronizarCombos() {
        Recorrido recorridoSeleccionado = (Recorrido) cmbRecorrido.getSelectedItem();
        Colectivo colectivoSeleccionado = (Colectivo) cmbColectivo.getSelectedItem();

        Integer idRecorrido = recorridoSeleccionado == null ? null : recorridoSeleccionado.getIdRecorrido();
        Integer idColectivo = colectivoSeleccionado == null ? null : colectivoSeleccionado.getIdColectivo();

        cargarComboBox();

        cmbRecorrido.setSelectedIndex(-1);
        cmbColectivo.setSelectedIndex(-1);

        for (int i = 0; i < cmbRecorrido.getItemCount(); i++) {
            if (idRecorrido != null && cmbRecorrido.getItemAt(i).getIdRecorrido() == idRecorrido) {
                cmbRecorrido.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cmbColectivo.getItemCount(); i++) {
            if (idColectivo != null && cmbColectivo.getItemAt(i).getIdColectivo() == idColectivo) {
                cmbColectivo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void actualizarBotonEstado() {
        int fila = tblSalidasRegistradas.getSelectedRow();

        if (fila == -1) {
            btnDesactivar.setText("Desactivar");
            return;
        }

        int filaModelo = tblSalidasRegistradas.convertRowIndexToModel(fila);
        String estado = tblSalidasRegistradas.getModel().getValueAt(filaModelo, 6).toString();

        btnDesactivar.setText(estado.equalsIgnoreCase("Activa") ? "Desactivar" : "Activar");
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
                false, false, false, false, false
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
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Desactivar.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(this::btnDesactivarActionPerformed);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        lblDatosSalidas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDatosSalidas.setForeground(new java.awt.Color(23, 47, 77));
        lblDatosSalidas.setText("Datos de la salida");

        pnlDatosSalida.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosSalida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        pnlDatosSalida.setForeground(new java.awt.Color(200, 200, 200));

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

        txtPrecioBase.addActionListener(this::txtPrecioBaseActionPerformed);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
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
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(82, 100, 125));
        btnCancelar.setText("Cancelar");
        btnCancelar.setOpaque(true);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlDatosSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDatosSalidas)
                                    .addComponent(btnNuevo))
                                .addGap(35, 35, 35)
                                .addComponent(btnEditar)
                                .addGap(35, 35, 35)
                                .addComponent(btnDesactivar)
                                .addGap(35, 35, 35)
                                .addComponent(btnEliminar)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSubtitulo)
                                    .addComponent(lblTitulo)
                                    .addComponent(lblParadasHorarios))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
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
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(lblParadasHorarios)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(238, 238, 238)
                    .addComponent(lblSalidasRegistradas1)
                    .addContainerGap(749, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void configurarHoraSalida() {
        spnHoraSalida.setEditor(new JSpinner.DateEditor(spnHoraSalida, "HH:mm"));
    }

    private void configurarTablaHorarios() {

        javax.swing.table.DefaultTableCellRenderer automatico = new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(new java.awt.Color(230, 233, 237));
                setForeground(new java.awt.Color(100, 100, 100));
                setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                return this;
            }
        };

        javax.swing.table.DefaultTableCellRenderer kilometros = new javax.swing.table.DefaultTableCellRenderer();
        kilometros.setBackground(java.awt.Color.WHITE);
        kilometros.setForeground(new java.awt.Color(23, 47, 77));
        kilometros.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        kilometros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.table.DefaultTableCellRenderer horarios = new javax.swing.table.DefaultTableCellRenderer();
        horarios.setBackground(new java.awt.Color(190, 228, 248));
        horarios.setForeground(new java.awt.Color(23, 47, 77));
        horarios.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        horarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        tblHorariosEstimados.getColumnModel().getColumn(0).setCellRenderer(automatico);
        tblHorariosEstimados.getColumnModel().getColumn(1).setCellRenderer(automatico);
        tblHorariosEstimados.getColumnModel().getColumn(2).setCellRenderer(kilometros);
        tblHorariosEstimados.getColumnModel().getColumn(3).setCellRenderer(horarios);
        tblHorariosEstimados.getColumnModel().getColumn(4).setCellRenderer(horarios);

        tblHorariosEstimados.setGridColor(new java.awt.Color(215, 220, 225));
        tblHorariosEstimados.setIntercellSpacing(new java.awt.Dimension(1, 1));
        tblHorariosEstimados.setShowGrid(true);

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

        btnGuardar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);

        btnNuevo.setEnabled(!habilitar);
        btnEditar.setEnabled(!habilitar);
        btnDesactivar.setEnabled(!habilitar);
        btnEliminar.setEnabled(!habilitar);
    }

    private void cargarTablaSalidas() {

        DefaultTableModel modelo = (DefaultTableModel) tblSalidasRegistradas.getModel();
        modelo.setRowCount(0);

        listaRutas = rutaViajeData.listarRutasViaje();

        for (RutaViaje ruta : listaRutas) {
            modelo.addRow(new Object[]{
                ruta.getIdRutaViaje(),
                ruta.getRecorrido().getDescripcion(),
                ruta.getColectivo().getMatricula(),
                ruta.getFechaHoraSalida().toLocalDate(),
                ruta.getFechaHoraSalida().toLocalTime(),
                ruta.getPrecioBase(),
                ruta.isEstado() ? "Activa" : "Inactiva"
            });
        }
    }


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

        rutaEnEdicion = null;
        limpiarFormulario();
        habilitarFormulario(true);

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cargarParadas(Recorrido recorrido) {

        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblHorariosEstimados.getModel();
        modelo.setRowCount(0);
        java.util.List<ParadaRecorrido> paradas = recorrido.getParadas();
        java.util.Date horaSalida = (java.util.Date) spnHoraSalida.getValue();
        java.util.Calendar hora = java.util.Calendar.getInstance();
        hora.setTime(horaSalida);

        for (int i = 0; i < paradas.size(); i++) {
            ParadaRecorrido parada = paradas.get(i);
            String llegada = "";
            String salida = "";

            if (i == 0) {
                salida = new java.text.SimpleDateFormat("HH:mm").format(hora.getTime());
            } else {
                ParadaRecorrido anterior = paradas.get(i - 1);
                double minutos = calculadorTiempos.calcularTiempoViaje(anterior, parada);
                hora.add(java.util.Calendar.MINUTE, (int) Math.round(minutos));
                llegada = new java.text.SimpleDateFormat("HH:mm").format(hora.getTime());
                int detencion = calculadorTiempos.calcularTiempoDetencion(parada, paradas.size());
                hora.add(java.util.Calendar.MINUTE, detencion);
                salida = new java.text.SimpleDateFormat("HH:mm").format(hora.getTime());
            }

            modelo.addRow(new Object[]{parada.getOrden(), parada.getNombreLugar(), parada.getDistanciaAcumuladaKm(), llegada, salida});
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        rutaEnEdicion = null;
        limpiarFormulario();
        habilitarFormulario(false);

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        Recorrido recorrido = (Recorrido) cmbRecorrido.getSelectedItem();
        Colectivo colectivo = (Colectivo) cmbColectivo.getSelectedItem();

        if (recorrido == null || colectivo == null || dtChFecha.getDate() == null
                || txtPrecioBase.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completá todos los campos.");
            return;
        }

        BigDecimal precioBase;

        try {
            precioBase = new BigDecimal(txtPrecioBase.getText().trim().replace(",", "."));
            if (precioBase.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresá un precio válido.");
            return;
        }

        List<ParadaRecorrido> paradas = recorrido.getParadas();

        if (paradas == null || paradas.size() < 2) {
            JOptionPane.showMessageDialog(this, "El recorrido debe tener al menos dos paradas.");
            return;
        }

        LocalDate fecha = dtChFecha.getDate().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();

        LocalTime hora = LocalTime.ofInstant(
                ((java.util.Date) spnHoraSalida.getValue()).toInstant(),
                ZoneId.systemDefault()
        );

        LocalDateTime fechaHoraSalida = LocalDateTime.of(fecha, hora);

        if (rutaEnEdicion == null && fechaHoraSalida.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this, "No se pueden registrar salidas con fecha y hora pasadas.");
            return;
        }

        LocalDateTime horaActual = fechaHoraSalida;
        boolean estado = rutaEnEdicion == null || rutaEnEdicion.isEstado();

        RutaViaje ruta = new RutaViaje(
                recorrido,
                colectivo,
                fechaHoraSalida,
                null,
                precioBase,
                estado
        );

        if (rutaEnEdicion != null) {
            ruta.setIdRutaViaje(rutaEnEdicion.getIdRutaViaje());
        }

        for (int i = 0; i < paradas.size(); i++) {
            ParadaRecorrido parada = paradas.get(i);

            LocalDateTime llegada;
            LocalDateTime salida;

            if (i == 0) {
                llegada = fechaHoraSalida;
                salida = fechaHoraSalida;
            } else {
                ParadaRecorrido anterior = paradas.get(i - 1);

                double minutos = calculadorTiempos.calcularTiempoViaje(anterior, parada);

                llegada = horaActual.plusMinutes(Math.round(minutos));

                int detencion = calculadorTiempos.calcularTiempoDetencion(parada, paradas.size());

                salida = llegada.plusMinutes(detencion);
            }

            HorarioParadaViaje horario = new HorarioParadaViaje(
                    ruta,
                    parada,
                    llegada,
                    salida
            );

            ruta.agregarHorario(horario);
            horaActual = salida;
        }

        ruta.setFechaHoraLlegada(
                ruta.getHorarios().get(ruta.getHorarios().size() - 1).getFechaHoraLlegada()
        );

        boolean resultado;

        if (rutaEnEdicion == null) {
            resultado = rutaViajeData.guardarRutaViaje(ruta);
        } else {
            // Recuperar los ID de los horarios existentes para que el DAO pueda actualizarlos.
            for (HorarioParadaViaje horarioNuevo : ruta.getHorarios()) {
                for (HorarioParadaViaje horarioAnterior : rutaEnEdicion.getHorarios()) {
                    if (horarioNuevo.getParadaRecorrido().getIdParadaRecorrido()
                            == horarioAnterior.getParadaRecorrido().getIdParadaRecorrido()) {
                        horarioNuevo.setIdHorarioParadaViaje(
                                horarioAnterior.getIdHorarioParadaViaje()
                        );
                        break;
                    }
                }
            }

            resultado = rutaViajeData.actualizarRutaViaje(ruta);
        }

        if (resultado) {
            JOptionPane.showMessageDialog(this, rutaEnEdicion == null
                    ? "La salida se guardó correctamente."
                    : "La salida se actualizó correctamente.");

            rutaEnEdicion = null;
            cargarTablaSalidas();
            NotificadorCambios.notificarCambio(NotificadorCambios.SALIDAS);
            limpiarFormulario();
            habilitarFormulario(false);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la salida. Revisá los datos.");
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private RutaViaje obtenerRutaSeleccionada() {
        int fila = tblSalidasRegistradas.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccioná una salida de la tabla.");
            return null;
        }

        int filaModelo = tblSalidasRegistradas.convertRowIndexToModel(fila);
        int idRuta = (int) tblSalidasRegistradas.getModel().getValueAt(filaModelo, 0);

        return rutaViajeData.buscarRutaViaje(idRuta);
    }

    private void cargarRutaEnFormulario(RutaViaje ruta) {
        habilitarFormulario(true);

        // Seleccionar el recorrido correspondiente.
        for (int i = 0; i < cmbRecorrido.getItemCount(); i++) {
            Recorrido item = cmbRecorrido.getItemAt(i);
            if (item.getIdRecorrido() == ruta.getRecorrido().getIdRecorrido()) {
                cmbRecorrido.setSelectedIndex(i);
                break;
            }
        }

        // Seleccionar el colectivo correspondiente.
        for (int i = 0; i < cmbColectivo.getItemCount(); i++) {
            Colectivo item = cmbColectivo.getItemAt(i);
            if (item.getIdColectivo() == ruta.getColectivo().getIdColectivo()) {
                cmbColectivo.setSelectedIndex(i);
                break;
            }
        }

        // Cargar fecha y hora de salida.
        LocalDateTime fechaHora = ruta.getFechaHoraSalida();

        dtChFecha.setDate(java.util.Date.from(
                fechaHora.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()
        ));

        spnHoraSalida.setValue(java.util.Date.from(
                fechaHora.atZone(ZoneId.systemDefault()).toInstant()
        ));

        txtPrecioBase.setText(ruta.getPrecioBase().toString());

        // Cargar los horarios de las paradas.
        DefaultTableModel modelo = (DefaultTableModel) tblHorariosEstimados.getModel();
        modelo.setRowCount(0);

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        for (HorarioParadaViaje horario : ruta.getHorarios()) {
            ParadaRecorrido parada = horario.getParadaRecorrido();

            String llegada = parada.getOrden() == 1
                    ? ""
                    : horario.getFechaHoraLlegada().format(formatoHora);

            String salida = horario.getFechaHoraSalida().format(formatoHora);

            modelo.addRow(new Object[]{
                parada.getOrden(),
                parada.getNombreLugar(),
                parada.getDistanciaAcumuladaKm(),
                llegada,
                salida
            });
        }
    }


    private void txtPrecioBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioBaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioBaseActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        RutaViaje ruta = obtenerRutaSeleccionada();

        if (ruta == null) {
            return;
        }

        rutaEnEdicion = ruta;
        cargarRutaEnFormulario(ruta);

    }//GEN-LAST:event_btnEditarActionPerformed


    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed

        RutaViaje ruta = obtenerRutaSeleccionada();

        if (ruta == null) {
            return;
        }

        boolean activar = !ruta.isEstado();
        String accion = activar ? "activar" : "desactivar";

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que querés " + accion + " esta salida?",
                activar ? "Confirmar activación" : "Confirmar desactivación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean resultado = activar
                    ? rutaViajeData.activarRutaViaje(ruta.getIdRutaViaje())
                    : rutaViajeData.desactivarRutaViaje(ruta.getIdRutaViaje());

            if (resultado) {
                JOptionPane.showMessageDialog(this,
                        activar ? "La salida se activó correctamente." : "La salida se desactivó correctamente.");

                rutaEnEdicion = null;
                limpiarFormulario();
                habilitarFormulario(false);
                cargarTablaSalidas();
                NotificadorCambios.notificarCambio(NotificadorCambios.SALIDAS);
            } else {
                JOptionPane.showMessageDialog(this,
                        activar ? "No se pudo activar la salida." : "No se pudo desactivar la salida.");
            }
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        RutaViaje ruta = obtenerRutaSeleccionada();

        if (ruta == null) {
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que querés eliminar definitivamente esta salida?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (rutaViajeData.eliminarRutaViaje(ruta.getIdRutaViaje())) {
                JOptionPane.showMessageDialog(this, "La salida se eliminó correctamente.");
                rutaEnEdicion = null;
                limpiarFormulario();
                habilitarFormulario(false);
                cargarTablaSalidas();
                NotificadorCambios.notificarCambio(NotificadorCambios.SALIDAS);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la salida.");
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void mostrarHorariosSalidaSeleccionada() {
        int fila = tblSalidasRegistradas.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) tblHorariosEstimados.getModel();
        modelo.setRowCount(0);

        if (fila == -1) {
            return;
        }

        int filaModelo = tblSalidasRegistradas.convertRowIndexToModel(fila);
        int idRuta = (int) tblSalidasRegistradas.getModel().getValueAt(filaModelo, 0);
        RutaViaje ruta = rutaViajeData.buscarRutaViaje(idRuta);

        if (ruta == null || ruta.getHorarios() == null) {
            return;
        }

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        for (HorarioParadaViaje horario : ruta.getHorarios()) {
            ParadaRecorrido parada = horario.getParadaRecorrido();
            String llegada = parada.getOrden() == 1 ? "" : horario.getFechaHoraLlegada().format(formatoHora);
            String salida = horario.getFechaHoraSalida().format(formatoHora);

            modelo.addRow(new Object[]{parada.getOrden(), parada.getNombreLugar(), parada.getDistanciaAcumuladaKm(), llegada, salida});
        }
    }


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
