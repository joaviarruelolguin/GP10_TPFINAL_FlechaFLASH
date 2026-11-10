package vistas;

import controlador.NotificadorCambios;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Pasajero;
import modelo.TipoPasajero;
import persistencia.PasajeroData;

public class VistaPasajeros extends javax.swing.JPanel {

    private PasajeroData pasajeroData = new PasajeroData();

    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public VistaPasajeros() {
        initComponents();
        armarCabecera();
        txtIdOR.setEditable(false);
        cargarPasajeros();

        tipoPasajeroComboBox.setModel(new DefaultComboBoxModel<>(TipoPasajero.values()));
        modoNuevo();
    }

    public void armarCabecera() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");
        modelo.addColumn("Tipo");

        pasajerosTabla.setModel(modelo);
    }

    public void cargarPasajeros() {
        modelo.setRowCount(0);

        ArrayList<Pasajero> listaPasajeros = pasajeroData.listarPasajeros();

        for (Pasajero lista : listaPasajeros) {
            modelo.addRow(new Object[]{
                lista.getIdPasajero(),
                lista.getNombre(),
                lista.getApellido(),
                lista.getDni(),
                lista.getCorreo(),
                lista.getTelefono(),
                lista.getTipoPasajero()
            }
            );
        }
    }

    public void sincronizarPasajeros() {

        // Evitar actualizar si el formulario contiene datos.
        if (!nombreTextField.getText().trim().isEmpty()
                || !apellidoTextField.getText().trim().isEmpty()
                || !dniTextField.getText().trim().isEmpty()
                || !correoTextField.getText().trim().isEmpty()
                || !telefonoTextField.getText().trim().isEmpty()
                || !txtIdOR.getText().trim().isEmpty()) {
            return;
        }

        // Conservar el texto de búsqueda.
        String idBuscado = txtBusquedaID.getText().trim();
        String dniBuscado = txtBusquedaDNI.getText().trim();
        String apellidoBuscado = txtBusquedaApellido.getText().trim();

        // Actualizar la tabla.
        cargarPasajeros();

        // Restaurar el texto de búsqueda.
        txtBusquedaID.setText(idBuscado);
        txtBusquedaDNI.setText(dniBuscado);
        txtBusquedaApellido.setText(apellidoBuscado);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerVista = new javax.swing.JPanel();
        tituloVista = new javax.swing.JLabel();
        iconoVista = new javax.swing.JLabel();
        crudPanel = new javax.swing.JPanel();
        tituloCrudPanel = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        nombreTextField = new javax.swing.JTextField();
        labelApellido = new javax.swing.JLabel();
        apellidoTextField = new javax.swing.JTextField();
        labelCorreo = new javax.swing.JLabel();
        tipoPasajeroComboBox = new javax.swing.JComboBox<>();
        correoTextField = new javax.swing.JTextField();
        labelTelefono = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        dniTextField = new javax.swing.JTextField();
        labelDNI = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        labelTipoPasajero = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        txtIdOR = new javax.swing.JTextField();
        consultaPanel = new javax.swing.JPanel();
        tituloConsultaPanel = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        pasajerosTablaScroll = new javax.swing.JScrollPane();
        pasajerosTabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBusquedaID = new javax.swing.JTextField();
        txtBusquedaDNI = new javax.swing.JTextField();
        txtBusquedaApellido = new javax.swing.JTextField();

        headerVista.setBackground(new java.awt.Color(20, 43, 73));
        headerVista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(20, 43, 73)));
        headerVista.setForeground(new java.awt.Color(20, 43, 73));

        tituloVista.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloVista.setForeground(new java.awt.Color(242, 242, 242));
        tituloVista.setText("Pasajeros");

        iconoVista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pasajeros.png"))); // NOI18N

        javax.swing.GroupLayout headerVistaLayout = new javax.swing.GroupLayout(headerVista);
        headerVista.setLayout(headerVistaLayout);
        headerVistaLayout.setHorizontalGroup(
            headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerVistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconoVista)
                .addGap(25, 25, 25))
        );
        headerVistaLayout.setVerticalGroup(
            headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerVistaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloVista)
                    .addComponent(iconoVista))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        crudPanel.setBackground(new java.awt.Color(242, 239, 239));
        crudPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        tituloCrudPanel.setBackground(new java.awt.Color(20, 43, 73));
        tituloCrudPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloCrudPanel.setForeground(new java.awt.Color(23, 47, 77));
        tituloCrudPanel.setText("Gestión");

        labelNombre.setBackground(new java.awt.Color(20, 43, 73));
        labelNombre.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(51, 51, 51));
        labelNombre.setText("NOMBRE");

        labelApellido.setBackground(new java.awt.Color(20, 43, 73));
        labelApellido.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelApellido.setForeground(new java.awt.Color(51, 51, 51));
        labelApellido.setText("APELLIDO");

        labelCorreo.setBackground(new java.awt.Color(20, 43, 73));
        labelCorreo.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(51, 51, 51));
        labelCorreo.setText("CORREO");

        labelTelefono.setBackground(new java.awt.Color(20, 43, 73));
        labelTelefono.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelTelefono.setForeground(new java.awt.Color(51, 51, 51));
        labelTelefono.setText("TELEFONO");

        labelDNI.setBackground(new java.awt.Color(20, 43, 73));
        labelDNI.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelDNI.setForeground(new java.awt.Color(51, 51, 51));
        labelDNI.setText("DNI");

        btnGuardar.setBackground(new java.awt.Color(0, 108, 158));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 108, 158));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 108, 158)));
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnBorrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 0, 0));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar.png"))); // NOI18N
        btnBorrar.setText("Eliminar");
        btnBorrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnBorrar.addActionListener(this::btnBorrarActionPerformed);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clean.png"))); // NOI18N
        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        labelTipoPasajero.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelTipoPasajero.setForeground(new java.awt.Color(51, 51, 51));
        labelTipoPasajero.setText("TIPO PASAJERO");

        idLabel.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        idLabel.setForeground(new java.awt.Color(50, 50, 50));
        idLabel.setText("ID");

        javax.swing.GroupLayout crudPanelLayout = new javax.swing.GroupLayout(crudPanel);
        crudPanel.setLayout(crudPanelLayout);
        crudPanelLayout.setHorizontalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addComponent(idLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crudPanelLayout.createSequentialGroup()
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtIdOR)
                                    .addComponent(labelCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(correoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                                .addGap(0, 27, Short.MAX_VALUE)))
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crudPanelLayout.createSequentialGroup()
                                .addComponent(labelApellido)
                                .addGap(397, 397, 397))
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tipoPasajeroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(crudPanelLayout.createSequentialGroup()
                                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(labelTelefono))
                                        .addGap(25, 25, 25)
                                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelTipoPasajero)
                                            .addComponent(labelDNI)))
                                    .addGroup(crudPanelLayout.createSequentialGroup()
                                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnGuardar)))
                                .addGap(35, 35, 35))))
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addComponent(tituloCrudPanel)
                        .addContainerGap())))
        );
        crudPanelLayout.setVerticalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(tituloCrudPanel)
                .addGap(18, 18, 18)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(labelApellido)
                    .addComponent(labelDNI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCorreo)
                    .addComponent(labelTelefono)
                    .addComponent(labelTipoPasajero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoPasajeroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIdOR, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        consultaPanel.setBackground(new java.awt.Color(242, 239, 239));
        consultaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        tituloConsultaPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloConsultaPanel.setForeground(new java.awt.Color(23, 47, 77));
        tituloConsultaPanel.setText("Lista de pasajeros");

        labelID.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelID.setForeground(new java.awt.Color(50, 50, 50));
        labelID.setText("ID");

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(0, 108, 158));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 108, 158)));
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        pasajerosTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        pasajerosTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pasajerosTablaMouseClicked(evt);
            }
        });
        pasajerosTablaScroll.setViewportView(pasajerosTabla);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 50, 50));
        jLabel1.setText("DNI");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 50, 50));
        jLabel2.setText("APELLIDO");

        txtBusquedaID.addActionListener(this::txtBusquedaIDActionPerformed);

        txtBusquedaDNI.addActionListener(this::txtBusquedaDNIActionPerformed);

        txtBusquedaApellido.addActionListener(this::txtBusquedaApellidoActionPerformed);

        javax.swing.GroupLayout consultaPanelLayout = new javax.swing.GroupLayout(consultaPanel);
        consultaPanel.setLayout(consultaPanelLayout);
        consultaPanelLayout.setHorizontalGroup(
            consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaPanelLayout.createSequentialGroup()
                        .addComponent(tituloConsultaPanel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaPanelLayout.createSequentialGroup()
                        .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(consultaPanelLayout.createSequentialGroup()
                                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelID)
                                    .addComponent(txtBusquedaID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtBusquedaDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(consultaPanelLayout.createSequentialGroup()
                                        .addComponent(txtBusquedaApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2)))
                            .addComponent(pasajerosTablaScroll))
                        .addGap(30, 30, 30))))
        );
        consultaPanelLayout.setVerticalGroup(
            consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(tituloConsultaPanel)
                .addGap(34, 34, 34)
                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(consultaPanelLayout.createSequentialGroup()
                        .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelID)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusquedaID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBusquedaDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBusquedaApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(pasajerosTablaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consultaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(consultaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pasajerosTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasajerosTablaMouseClicked
        int fila = pasajerosTabla.getSelectedRow();

        if (fila != -1) {
            cargarFormulario(fila);
        }
    }//GEN-LAST:event_pasajerosTablaMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String id = txtBusquedaID.getText().trim();
        String dni = txtBusquedaDNI.getText().trim();
        String apellido = txtBusquedaApellido.getText().trim().toLowerCase();

        if (id.isEmpty() && dni.isEmpty() && apellido.isEmpty()) {
            cargarPasajeros();
            return;
        }

        if (!id.isEmpty() && !id.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El ID debe ser numérico.");
            return;
        }

        cargarPasajeros();

        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            boolean coincide = (id.isEmpty() || modelo.getValueAt(i, 0).toString().equals(id))
                    && (dni.isEmpty() || modelo.getValueAt(i, 3).toString().equals(dni))
                    && (apellido.isEmpty() || modelo.getValueAt(i, 2).toString().toLowerCase().contains(apellido));

            if (!coincide) {
                modelo.removeRow(i);
            }
        }

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron pasajeros.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        String idPasajero = txtIdOR.getText().trim();
        if (idPasajero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione o busque un pasajero.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea borrar al pasajero?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idPasajero);

            if (pasajeroData.borrarPasajero(id)) {
                JOptionPane.showMessageDialog(this, "Pasajero borrado correctamente.");

                cargarPasajeros();
                modoNuevo();
                
                NotificadorCambios.notificarCambio(NotificadorCambios.PASAJEROS);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo borrar el pasajero. Puede tener ventas o pasajes asociados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validarCamposVacios(crudPanel)) {
            JOptionPane.showMessageDialog(this, "Campos incompletos.");
            return;
        }

        String dni = dniTextField.getText().trim();
        if (!dni.matches("\\d{7,8}")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener 7 u 8 dígitos numéricos.");
            return;
        }

        String correo = correoTextField.getText().trim();
        if (!correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            JOptionPane.showMessageDialog(this, "El correo no tiene un formato válido.");
            return;
        }

        if (!telefonoTextField.getText().trim().matches("[\\d\\s+()-]{6,30}")) {
            JOptionPane.showMessageDialog(this, "El teléfono solo puede tener números, espacios, +, - y paréntesis.");
            return;
        }

        int idPasajero = Integer.parseInt(txtIdOR.getText());
        String nombre = nombreTextField.getText().trim();
        String apellido = apellidoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        TipoPasajero tipoPasajero = (TipoPasajero) tipoPasajeroComboBox.getSelectedItem();

        Pasajero p = new Pasajero();
        p.setIdPasajero(idPasajero);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setDni(dni);
        p.setCorreo(correo);
        p.setTelefono(telefono);
        p.setTipoPasajero(tipoPasajero);

        if (pasajeroData.actualizarPasajero(p)) {
            JOptionPane.showMessageDialog(this, "Pasajero actualizado correctamente.");

            cargarPasajeros();
            modoNuevo();
            
            NotificadorCambios.notificarCambio(NotificadorCambios.PASAJEROS);

        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el pasajero. Verifique que el DNI no esté repetido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarCamposVacios(crudPanel)) {
            JOptionPane.showMessageDialog(this, "Campos incompletos.");
            return;
        }

        String dni = dniTextField.getText().trim();
        if (!dni.matches("\\d{7,8}")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener 7 u 8 dígitos numéricos.");
            return;
        }

        String correo = correoTextField.getText().trim();
        if (!correo.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            JOptionPane.showMessageDialog(this, "El correo no tiene un formato válido.");
            return;
        }

        if (!telefonoTextField.getText().trim().matches("[\\d\\s+()-]{6,30}")) {
            JOptionPane.showMessageDialog(this, "El teléfono solo puede tener números, espacios, +, - y paréntesis.");
            return;
        }

        String nombre = nombreTextField.getText().trim();
        String apellido = apellidoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        TipoPasajero tipoPasajero = (TipoPasajero) tipoPasajeroComboBox.getSelectedItem();

        Pasajero pasajero = new Pasajero(dni, nombre, apellido, correo, telefono, tipoPasajero);

        if (pasajeroData.guardarPasajero(pasajero)) {
            JOptionPane.showMessageDialog(this, "El pasajero se ha guardado correctamente.");

            cargarPasajeros();

            modoNuevo();
            
            NotificadorCambios.notificarCambio(NotificadorCambios.PASAJEROS);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el pasajero. Verifique que el DNI no esté repetido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        modoNuevo();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtBusquedaIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaIDActionPerformed
        btnBuscar.doClick();
    }//GEN-LAST:event_txtBusquedaIDActionPerformed

    private void txtBusquedaDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaDNIActionPerformed
        btnBuscar.doClick();
    }//GEN-LAST:event_txtBusquedaDNIActionPerformed

    private void txtBusquedaApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaApellidoActionPerformed
        btnBuscar.doClick();
    }//GEN-LAST:event_txtBusquedaApellidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTextField;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JPanel consultaPanel;
    private javax.swing.JTextField correoTextField;
    private javax.swing.JPanel crudPanel;
    private javax.swing.JTextField dniTextField;
    private javax.swing.JPanel headerVista;
    private javax.swing.JLabel iconoVista;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelDNI;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelTipoPasajero;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTable pasajerosTabla;
    private javax.swing.JScrollPane pasajerosTablaScroll;
    private javax.swing.JTextField telefonoTextField;
    private javax.swing.JComboBox<TipoPasajero> tipoPasajeroComboBox;
    private javax.swing.JLabel tituloConsultaPanel;
    private javax.swing.JLabel tituloCrudPanel;
    private javax.swing.JLabel tituloVista;
    private javax.swing.JTextField txtBusquedaApellido;
    private javax.swing.JTextField txtBusquedaDNI;
    private javax.swing.JTextField txtBusquedaID;
    private javax.swing.JTextField txtIdOR;
    // End of variables declaration//GEN-END:variables

    public boolean validarCamposVacios(JPanel jPanel) {

        for (Component c : jPanel.getComponents()) {
            if (c instanceof JTextField && c != txtIdOR) {
                JTextField caja = (JTextField) c;

                if (caja.getText().trim().isEmpty()) {
                    return true;
                }
            }
        }

        for (Component c : jPanel.getComponents()) {
            if (c instanceof JComboBox) {
                JComboBox combo = (JComboBox) c;
                if (combo.getSelectedIndex() == -1) {
                    return true;
                }
            }

        }
        return false;
    }

    public void vaciarCampos(JPanel jPanel) {
        for (Component c : jPanel.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JComboBox) {
                ((JComboBox<?>) c).setSelectedIndex(-1);
            }
        }
    }

    private void modoNuevo() {
        vaciarCampos(crudPanel);
        vaciarCampos(consultaPanel);
        pasajerosTabla.clearSelection();
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
    }

    private void modoSeleccion() {
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }

    private void cargarFormulario(int fila) {
        //idTextField.setText(pasajerosTabla.getValueAt(fila, 0).toString());
        txtIdOR.setText(pasajerosTabla.getValueAt(fila, 0).toString());
        nombreTextField.setText(pasajerosTabla.getValueAt(fila, 1).toString());
        apellidoTextField.setText(pasajerosTabla.getValueAt(fila, 2).toString());
        dniTextField.setText(pasajerosTabla.getValueAt(fila, 3).toString());
        correoTextField.setText(pasajerosTabla.getValueAt(fila, 4).toString());
        telefonoTextField.setText(pasajerosTabla.getValueAt(fila, 5).toString());
        tipoPasajeroComboBox.setSelectedItem(pasajerosTabla.getValueAt(fila, 6));

        modoSeleccion();
    }
}
