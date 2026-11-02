package vistas;

import java.awt.Component;
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
        cargarPasajeros();

        tipoPasajeroComboBox.setModel(new DefaultComboBoxModel<>(TipoPasajero.values()));

    }

    public void armarCabecera() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DNI");
        modelo.addColumn("Correo");
        modelo.addColumn("Telefono");

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
                lista.getTelefono()
            });
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
        correoTextField = new javax.swing.JTextField();
        labelTelefono = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        dniTextField = new javax.swing.JTextField();
        labelDNI = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        tipoPasajeroComboBox = new javax.swing.JComboBox<>();
        labelTipoPasajero = new javax.swing.JLabel();
        consultaPanel = new javax.swing.JPanel();
        tituloConsultaPanel = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        pasajerosTablaScroll = new javax.swing.JScrollPane();
        pasajerosTabla = new javax.swing.JTable();

        headerVista.setBackground(new java.awt.Color(20, 43, 73));

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
                .addGap(31, 31, 31)
                .addGroup(headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloVista)
                    .addComponent(iconoVista))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        crudPanel.setBackground(new java.awt.Color(242, 239, 239));
        crudPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tituloCrudPanel.setBackground(new java.awt.Color(20, 43, 73));
        tituloCrudPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloCrudPanel.setText("Gestión");

        labelNombre.setBackground(new java.awt.Color(20, 43, 73));
        labelNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelNombre.setText("Nombre");

        labelApellido.setBackground(new java.awt.Color(20, 43, 73));
        labelApellido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelApellido.setText("Apellido");

        labelCorreo.setBackground(new java.awt.Color(20, 43, 73));
        labelCorreo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelCorreo.setText("Correo");

        labelTelefono.setBackground(new java.awt.Color(20, 43, 73));
        labelTelefono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTelefono.setText("Teléfono");

        labelDNI.setBackground(new java.awt.Color(20, 43, 73));
        labelDNI.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelDNI.setText("DNI");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setText("Actualizar");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(this::btnBorrarActionPerformed);

        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        labelTipoPasajero.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTipoPasajero.setText("Tipo pasajero");

        javax.swing.GroupLayout crudPanelLayout = new javax.swing.GroupLayout(crudPanel);
        crudPanel.setLayout(crudPanelLayout);
        crudPanelLayout.setHorizontalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloCrudPanel)
                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(crudPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(btnGuardar)
                            .addGap(18, 18, 18)
                            .addComponent(btnActualizar)
                            .addGap(18, 18, 18)
                            .addComponent(btnBorrar)
                            .addGap(100, 100, 100)
                            .addComponent(btnLimpiar))
                        .addGroup(crudPanelLayout.createSequentialGroup()
                            .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, crudPanelLayout.createSequentialGroup()
                                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNombre)
                                        .addGroup(crudPanelLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelApellido)
                                        .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(crudPanelLayout.createSequentialGroup()
                                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crudPanelLayout.createSequentialGroup()
                                            .addComponent(correoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(crudPanelLayout.createSequentialGroup()
                                            .addComponent(labelCorreo)
                                            .addGap(133, 133, 133)))
                                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelTelefono)
                                        .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(18, 18, 18)
                            .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dniTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addComponent(labelDNI)
                                .addComponent(tipoPasajeroComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTipoPasajero)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        crudPanelLayout.setVerticalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloCrudPanel)
                .addGap(18, 18, 18)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(labelApellido)
                    .addComponent(labelDNI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCorreo)
                    .addComponent(labelTelefono)
                    .addComponent(labelTipoPasajero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipoPasajeroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(correoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnBorrar)
                    .addComponent(btnLimpiar))
                .addGap(24, 24, 24))
        );

        consultaPanel.setBackground(new java.awt.Color(242, 239, 239));
        consultaPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tituloConsultaPanel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tituloConsultaPanel.setText("Lista de pasajeros");

        labelID.setText("ID");

        btnBuscar.setText("Buscar");

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
            .addGroup(consultaPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloConsultaPanel)
                    .addGroup(consultaPanelLayout.createSequentialGroup()
                        .addComponent(labelID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(consultaPanelLayout.createSequentialGroup()
                                .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))
                            .addComponent(pasajerosTablaScroll))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        consultaPanelLayout.setVerticalGroup(
            consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloConsultaPanel)
                .addGap(18, 18, 18)
                .addGroup(consultaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelID)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pasajerosTablaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consultaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        vaciarCampos(crudPanel);
        modoNuevo();
        pasajerosTabla.clearSelection();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void pasajerosTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasajerosTablaMouseClicked
        int fila = pasajerosTabla.getSelectedRow();

        if (fila != -1) {
            idTextField.setText(pasajerosTabla.getValueAt(fila, 0).toString());
            nombreTextField.setText(pasajerosTabla.getValueAt(fila, 1).toString());
            apellidoTextField.setText(pasajerosTabla.getValueAt(fila, 2).toString());
            dniTextField.setText(pasajerosTabla.getValueAt(fila, 3).toString());
            correoTextField.setText(pasajerosTabla.getValueAt(fila, 4).toString());
            telefonoTextField.setText(pasajerosTabla.getValueAt(fila, 5).toString());
        }
    }//GEN-LAST:event_pasajerosTablaMouseClicked


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
            if (c instanceof JTextField) {
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
        btnBorrar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }

    private void modoSeleccion(boolean activa) {
        btnBorrar.setEnabled(true);
        btnActualizar.setEnabled(true);

    }
}
