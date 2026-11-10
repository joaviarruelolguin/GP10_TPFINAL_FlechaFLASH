
package vistas;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Asiento;
import modelo.Colectivo;
import persistencia.AsientoData;
import persistencia.ColectivoData;

import controlador.NotificadorCambios;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class VistaAsientos extends javax.swing.JPanel implements PropertyChangeListener {

    
    private AsientoData asientoData = new AsientoData();
    private ColectivoData colectivoData = new ColectivoData();
    
    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public VistaAsientos() {
        initComponents();
        NotificadorCambios.agregarListener(this);
        armarTabla();
        configurarTablaAsientos();
        cargarColectivos();
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (NotificadorCambios.COLECTIVOS.equals(evt.getNewValue())
                || NotificadorCambios.ASIENTOS.equals(evt.getNewValue())) {
            sincronizarColectivos();
        }
    }

    private void sincronizarColectivos() {
        Colectivo seleccionado = (Colectivo) cbColectivo.getSelectedItem();
        Integer idSeleccionado = seleccionado != null ? seleccionado.getIdColectivo() : null;

        cargarColectivos();

        if (idSeleccionado != null) {
            for (int i = 0; i < cbColectivo.getItemCount(); i++) {
                Colectivo colectivo = cbColectivo.getItemAt(i);

                if (colectivo.getIdColectivo() == idSeleccionado) {
                    cbColectivo.setSelectedIndex(i);
                    cargarAsientos(colectivo);
                    return;
                }
            }
        }

        limpiarConsulta();
    }

    private ArrayList<Asiento> cargarAsientos(Colectivo colectivo) {
        ArrayList<Asiento> asientos = asientoData.listarAsientosPorColectivo(colectivo);

        modelo.setRowCount(0);

        for (Asiento asiento : asientos) {
            String estado = colectivo.isEstado() ? "Activo" : "Inactivo";
            modelo.addRow(new Object[]{asiento.getNumeroAsiento(), asiento.getIdAsiento(), estado});
        }

        lblCantidadAsientos.setText(String.valueOf(colectivo.getCapacidad()));
        lblMatricula.setText(colectivo.getMatricula());

        return asientos;
    }

    private void limpiarConsulta() {
        modelo.setRowCount(0);
        lblCantidadAsientos.setText("—");
        lblMatricula.setText("—");
    }

    private void armarTabla() {
        modelo.setColumnIdentifiers(new String[]{"N.º de asiento", "ID del asiento", "Estado"});
        jTable1.setModel(modelo);
    }
    
    
    private void configurarTablaAsientos() {

        jTable1.setRowHeight(40);
        jTable1.setBackground(java.awt.Color.WHITE);
        jTable1.setForeground(new java.awt.Color(23, 47, 77));
        jTable1.setShowGrid(true);
        jTable1.setGridColor(new java.awt.Color(220, 220, 220));
        jTable1.setIntercellSpacing(new java.awt.Dimension(1, 3));
        jTable1.setFillsViewportHeight(true);
        jTable1.setSelectionBackground(new java.awt.Color(190, 225, 242));

        javax.swing.table.DefaultTableCellRenderer centrado = new javax.swing.table.DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        ((javax.swing.table.DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTable1.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 28));

        jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);
    }

    

    private void cargarColectivos() {
        List<Colectivo> colectivos = colectivoData.listarColectivos();

        cbColectivo.removeAllItems();

        for (Colectivo colectivo : colectivos) {
            cbColectivo.addItem(colectivo);
        }

        cbColectivo.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> lista, Object valor, int indice, boolean seleccionado, boolean foco) {
                super.getListCellRendererComponent(lista, valor, indice, seleccionado, foco);

                if (valor instanceof Colectivo) {
                    Colectivo colectivo = (Colectivo) valor;
                    setText(colectivo.getMarca() + " – " + colectivo.getModelo());
                }

                return this;
            }
        });
        
        cbColectivo.setSelectedIndex(-1);
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerVista = new javax.swing.JPanel();
        tituloVista = new javax.swing.JLabel();
        iconoVista = new javax.swing.JLabel();
        GestionPanel = new javax.swing.JPanel();
        lbidAsiento = new javax.swing.JLabel();
        btConsultar = new javax.swing.JButton();
        cbColectivo = new javax.swing.JComboBox<>();
        lblMatricula = new javax.swing.JLabel();
        lblCantidadAsientos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();

        headerVista.setBackground(new java.awt.Color(20, 43, 73));
        headerVista.setPreferredSize(new java.awt.Dimension(179, 85));

        tituloVista.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloVista.setForeground(new java.awt.Color(242, 242, 242));
        tituloVista.setText("Asientos");

        iconoVista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/asientos.png"))); // NOI18N

        javax.swing.GroupLayout headerVistaLayout = new javax.swing.GroupLayout(headerVista);
        headerVista.setLayout(headerVistaLayout);
        headerVistaLayout.setHorizontalGroup(
            headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerVistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iconoVista)
                .addGap(30, 30, 30))
        );
        headerVistaLayout.setVerticalGroup(
            headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerVistaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVista)
                    .addComponent(iconoVista))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        GestionPanel.setBackground(new java.awt.Color(242, 239, 239));
        GestionPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        lbidAsiento.setText("Seleccioná un colectivo");

        btConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btConsultar.setText("Consultar");
        btConsultar.addActionListener(this::btConsultarActionPerformed);

        cbColectivo.addActionListener(this::cbColectivoActionPerformed);

        lblMatricula.setBackground(new java.awt.Color(255, 255, 255));
        lblMatricula.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblMatricula.setForeground(new java.awt.Color(23, 47, 77));
        lblMatricula.setText("—");

        lblCantidadAsientos.setBackground(new java.awt.Color(255, 255, 255));
        lblCantidadAsientos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCantidadAsientos.setForeground(new java.awt.Color(23, 47, 77));
        lblCantidadAsientos.setText("—");

        jLabel1.setText("Asientos");

        jLabel3.setText("Matrícula");

        javax.swing.GroupLayout GestionPanelLayout = new javax.swing.GroupLayout(GestionPanel);
        GestionPanel.setLayout(GestionPanelLayout);
        GestionPanelLayout.setHorizontalGroup(
            GestionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GestionPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(GestionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbColectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btConsultar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbidAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addGroup(GestionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(lblMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidadAsientos, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        GestionPanelLayout.setVerticalGroup(
            GestionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GestionPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(GestionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GestionPanelLayout.createSequentialGroup()
                        .addComponent(lbidAsiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbColectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GestionPanelLayout.createSequentialGroup()
                        .addComponent(lblCantidadAsientos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(GestionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GestionPanelLayout.createSequentialGroup()
                        .addComponent(lblMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "N.º de asiento", "ID del asiento", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(20, 43, 73));
        jLabel2.setText("Lista de Asientos por Colectivo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(20, 43, 73));
        lblTitulo.setText("Gestión de asientos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerVista, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GestionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(GestionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        Colectivo colectivo = (Colectivo) cbColectivo.getSelectedItem();

        if (colectivo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un colectivo.");
            return;
        }

        ArrayList<Asiento> asientos = cargarAsientos(colectivo);

        if (asientos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El colectivo no tiene asientos registrados.");
        }
    }//GEN-LAST:event_btConsultarActionPerformed

    private void cbColectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbColectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbColectivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GestionPanel;
    private javax.swing.JButton btConsultar;
    private javax.swing.JComboBox<Colectivo> cbColectivo;
    private javax.swing.JPanel headerVista;
    private javax.swing.JLabel iconoVista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbidAsiento;
    private javax.swing.JLabel lblCantidadAsientos;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel tituloVista;
    // End of variables declaration//GEN-END:variables
}
