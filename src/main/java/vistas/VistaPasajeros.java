package vistas;

import javax.swing.table.DefaultTableModel;

public class VistaPasajeros extends javax.swing.JPanel {
    
        DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public VistaPasajeros() {
        initComponents();
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
                .addGap(47, 47, 47)
                .addComponent(iconoVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tituloVista)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        nombreTextField.addActionListener(this::nombreTextFieldActionPerformed);

        labelApellido.setBackground(new java.awt.Color(20, 43, 73));
        labelApellido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelApellido.setText("Apellido");

        apellidoTextField.addActionListener(this::apellidoTextFieldActionPerformed);

        labelCorreo.setBackground(new java.awt.Color(20, 43, 73));
        labelCorreo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelCorreo.setText("Correo");

        correoTextField.addActionListener(this::correoTextFieldActionPerformed);

        labelTelefono.setBackground(new java.awt.Color(20, 43, 73));
        labelTelefono.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTelefono.setText("Teléfono");

        telefonoTextField.addActionListener(this::telefonoTextFieldActionPerformed);

        dniTextField.addActionListener(this::dniTextFieldActionPerformed);

        labelDNI.setBackground(new java.awt.Color(20, 43, 73));
        labelDNI.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelDNI.setText("DNI");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setText("Actualizar");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(this::btnBorrarActionPerformed);

        javax.swing.GroupLayout crudPanelLayout = new javax.swing.GroupLayout(crudPanel);
        crudPanel.setLayout(crudPanelLayout);
        crudPanelLayout.setHorizontalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addComponent(tituloCrudPanel)
                        .addContainerGap())
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelCorreo)
                                .addGroup(crudPanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(correoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(crudPanelLayout.createSequentialGroup()
                                            .addComponent(btnGuardar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnActualizar)))))
                            .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(crudPanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelNombre)))
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelApellido)
                                    .addComponent(labelTelefono)
                                    .addGroup(crudPanelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(crudPanelLayout.createSequentialGroup()
                                                .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelDNI)
                                                    .addGroup(crudPanelLayout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crudPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                                .addComponent(btnBorrar)
                                .addGap(23, 23, 23))))))
        );
        crudPanelLayout.setVerticalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addComponent(labelTelefono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addComponent(labelDNI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addComponent(tituloCrudPanel)
                                .addGap(18, 18, 18)
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(crudPanelLayout.createSequentialGroup()
                                        .addComponent(labelNombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(crudPanelLayout.createSequentialGroup()
                                        .addComponent(labelApellido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCorreo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(correoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnBorrar))
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
                            .addComponent(pasajerosTablaScroll)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(consultaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consultaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dniTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniTextFieldActionPerformed

    private void telefonoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoTextFieldActionPerformed

    private void correoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoTextFieldActionPerformed

    private void apellidoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoTextFieldActionPerformed

    private void nombreTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTextFieldActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTextField;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
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
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTable pasajerosTabla;
    private javax.swing.JScrollPane pasajerosTablaScroll;
    private javax.swing.JTextField telefonoTextField;
    private javax.swing.JLabel tituloConsultaPanel;
    private javax.swing.JLabel tituloCrudPanel;
    private javax.swing.JLabel tituloVista;
    // End of variables declaration//GEN-END:variables
}
