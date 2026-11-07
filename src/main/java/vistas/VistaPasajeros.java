package vistas;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
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
        consultaPanel = new javax.swing.JPanel();
        tituloConsultaPanel = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pasajerosTablaScroll = new javax.swing.JScrollPane();
        pasajerosTabla = new javax.swing.JTable();

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
        labelNombre.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(51, 51, 51));
        labelNombre.setText("NOMBRE");

        labelApellido.setBackground(new java.awt.Color(20, 43, 73));
        labelApellido.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        labelApellido.setForeground(new java.awt.Color(51, 51, 51));
        labelApellido.setText("APELLIDO");

        labelCorreo.setBackground(new java.awt.Color(20, 43, 73));
        labelCorreo.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(51, 51, 51));
        labelCorreo.setText("CORREO");

        tipoPasajeroComboBox.addActionListener(this::tipoPasajeroComboBoxActionPerformed);

        labelTelefono.setBackground(new java.awt.Color(20, 43, 73));
        labelTelefono.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        labelTelefono.setForeground(new java.awt.Color(51, 51, 51));
        labelTelefono.setText("TELEFONO");

        labelDNI.setBackground(new java.awt.Color(20, 43, 73));
        labelDNI.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
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

        labelTipoPasajero.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        labelTipoPasajero.setForeground(new java.awt.Color(51, 51, 51));
        labelTipoPasajero.setText("TIPO PASAJERO");

        javax.swing.GroupLayout crudPanelLayout = new javax.swing.GroupLayout(crudPanel);
        crudPanel.setLayout(crudPanelLayout);
        crudPanelLayout.setHorizontalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crudPanelLayout.createSequentialGroup()
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCorreo)
                                    .addComponent(labelNombre)
                                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(correoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 27, Short.MAX_VALUE))
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        consultaPanel.setBackground(new java.awt.Color(242, 239, 239));
        consultaPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        tituloConsultaPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloConsultaPanel.setForeground(new java.awt.Color(23, 47, 77));
        tituloConsultaPanel.setText("Lista de pasajeros");

        labelID.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelID.setForeground(new java.awt.Color(50, 50, 50));
        labelID.setText("ID");

        idTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idTextFieldKeyPressed(evt);
            }
        });

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

        javax.swing.GroupLayout consultaPanelLayout = new javax.swing.GroupLayout(consultaPanel);
        consultaPanel.setLayout(consultaPanelLayout);
        consultaPanelLayout.setHorizontalGroup(
            consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, consultaPanelLayout.createSequentialGroup()
                        .addComponent(tituloConsultaPanel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, consultaPanelLayout.createSequentialGroup()
                        .addComponent(labelID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pasajerosTablaScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                            .addGroup(consultaPanelLayout.createSequentialGroup()
                                .addComponent(idTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 29, 29))
        );
        consultaPanelLayout.setVerticalGroup(
            consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(tituloConsultaPanel)
                .addGap(18, 18, 18)
                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(labelID)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pasajerosTablaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(consultaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pasajerosTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasajerosTablaMouseClicked
        int fila = pasajerosTabla.getSelectedRow();

        if (fila != -1) {
            idTextField.setText(pasajerosTabla.getValueAt(fila, 0).toString());
            nombreTextField.setText(pasajerosTabla.getValueAt(fila, 1).toString());
            apellidoTextField.setText(pasajerosTabla.getValueAt(fila, 2).toString());
            dniTextField.setText(pasajerosTabla.getValueAt(fila, 3).toString());
            correoTextField.setText(pasajerosTabla.getValueAt(fila, 4).toString());
            telefonoTextField.setText(pasajerosTabla.getValueAt(fila, 5).toString());
            tipoPasajeroComboBox.setSelectedItem(pasajerosTabla.getValueAt(fila, 6));

            modoSeleccion();
        }
    }//GEN-LAST:event_pasajerosTablaMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String idIngresado = idTextField.getText().trim();

        if (idIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar el campo ID.");
            return;
        }

        try {
            int idPasajero = Integer.parseInt(idIngresado);
            Pasajero p = pasajeroData.buscarPasajero(idPasajero);

            if (p == null) {
                JOptionPane.showMessageDialog(this, "Pasajero no encontrado.");
                return;
            }

            for (int i = 0; i < modelo.getRowCount(); i++) {
                int idTabla = Integer.parseInt(modelo.getValueAt(i, 0).toString());

                if (idTabla == p.getIdPasajero()) {
                    pasajerosTabla.setRowSelectionInterval(i, i);

                    pasajerosTabla.scrollRectToVisible(pasajerosTabla.getCellRect(i, 0, true));

                    break;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El id debe ser un número.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void idTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBuscarActionPerformed(null);
        }
    }//GEN-LAST:event_idTextFieldKeyPressed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        String idPasajero = idTextField.getText().trim();
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
                vaciarCampos(crudPanel);
                modoNuevo();
                pasajerosTabla.clearSelection();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo borrar el pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (validarCamposVacios(crudPanel)) {
            JOptionPane.showMessageDialog(this, "Campos incompletos.");
            return;
        }

        int idPasajero = Integer.parseInt(idTextField.getText());
        String nombre = nombreTextField.getText().trim();
        String apellido = apellidoTextField.getText().trim();
        String dni = dniTextField.getText().trim();
        String correo = correoTextField.getText().trim();
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
            vaciarCampos(crudPanel);
            modoNuevo();
            pasajerosTabla.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (validarCamposVacios(crudPanel)) {
            JOptionPane.showMessageDialog(this, "Campos incompletos.");
            return;
        }

        String nombre = nombreTextField.getText().trim();
        String apellido = apellidoTextField.getText().trim();
        String dni = dniTextField.getText().trim();
        String correo = correoTextField.getText().trim();
        String telefono = telefonoTextField.getText().trim();
        TipoPasajero tipoPasajero = (TipoPasajero) tipoPasajeroComboBox.getSelectedItem();

        Pasajero pasajero = new Pasajero(dni, nombre, apellido, correo, telefono, tipoPasajero);

        if (pasajeroData.guardarPasajero(pasajero)) {
            JOptionPane.showMessageDialog(this, "El pasajero se ha guardado correctamente.");

            cargarPasajeros();

            vaciarCampos(crudPanel);

            modoNuevo();

            pasajerosTabla.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el pasajero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        vaciarCampos(crudPanel);
        modoNuevo();
        pasajerosTabla.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tipoPasajeroComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoPasajeroComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoPasajeroComboBoxActionPerformed


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
    private javax.swing.JTextField idTextField;
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
    // End of variables declaration//GEN-END:variables

    public boolean validarCamposVacios(JPanel jPanel) {

        for (Component c : jPanel.getComponents()) {
            if (c instanceof JTextField) {
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
        JComboBox combo = null;
        for (Component c : jPanel.getComponents()) {
            if (c instanceof JTextField && c == idTextField) {
                JTextField caja = (JTextField) c;
                caja.setText("");
            }
        }
        for (int i = 0; i < jPanel.getComponents().length; i++) {
            if (jPanel.getComponents()[i] instanceof JTextField) {
                JTextField caja = (JTextField) jPanel.getComponents()[i];
                caja.setText("");
            }
            if (jPanel.getComponents()[i] instanceof JComboBox) {
                combo = (JComboBox) jPanel.getComponents()[i];
                combo.setSelectedIndex(-1);
            }

        }
    }

    private void modoNuevo() {
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnBorrar.setEnabled(false);
        tipoPasajeroComboBox.setSelectedIndex(-1);
    }

    private void modoSeleccion() {
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }
}
