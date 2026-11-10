package vistas;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Colectivo;
import persistencia.ColectivoData;

import controlador.NotificadorCambios;

public class VistaColectivos extends javax.swing.JPanel {

    private static final int CAPACIDAD_MAXIMA = 60;

    private ColectivoData colectivoData = new ColectivoData();
    private List<Colectivo> listaColectivos = new ArrayList<>();

    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public VistaColectivos() {
        initComponents();
        armarCabecera();
        configurarTablaColectivos();
        txtIdOR.setEditable(false);
        modoNuevo();
        cargarColectivos();
    }

    public void armarCabecera() {
        modelo.addColumn("ID");
        modelo.addColumn("Matricula");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Capacidad");
        modelo.addColumn("Chofer");
        modelo.addColumn("Caducidad");
        modelo.addColumn("Estado");

        tblColectivos.setModel(modelo);
    }

    
    
    private void configurarTablaColectivos() {

        tblColectivos.setRowHeight(40);
        tblColectivos.setBackground(java.awt.Color.WHITE);
        tblColectivos.setForeground(new java.awt.Color(23, 47, 77));
        tblColectivos.setShowGrid(true);
        tblColectivos.setGridColor(new java.awt.Color(220, 220, 220));
        tblColectivos.setIntercellSpacing(new java.awt.Dimension(1, 3));
        tblColectivos.setFillsViewportHeight(true);
        tblColectivos.setSelectionBackground(new java.awt.Color(190, 225, 242));

        javax.swing.table.DefaultTableCellRenderer centrado = new javax.swing.table.DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        for (int i = 0; i < tblColectivos.getColumnCount(); i++) {
            tblColectivos.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        ((javax.swing.table.DefaultTableCellRenderer) tblColectivos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tblColectivos.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 28));

        jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);
     
    }

   
    
    public void cargarColectivos() {
        modelo.setRowCount(0);

        listaColectivos = colectivoData.listarColectivos();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Colectivo lista : listaColectivos) {
            modelo.addRow(new Object[]{
                lista.getIdColectivo(),
                lista.getMatricula(),
                lista.getMarca(),
                lista.getModelo(),
                lista.getCapacidad(),
                lista.getChofer(),
                lista.getCaducidad() == null ? "-" : lista.getCaducidad().format(formato),
                lista.isEstado() ? "Activo" : "Inactivo"
            }
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloGestion = new javax.swing.JLabel();
        subtituloGestion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblColectivos = new javax.swing.JTable();
        panelCrud = new javax.swing.JPanel();
        tituloSeccionCrud = new javax.swing.JLabel();
        labelMatricula = new javax.swing.JLabel();
        labelMarca = new javax.swing.JLabel();
        labelModelo = new javax.swing.JLabel();
        labelChofer = new javax.swing.JLabel();
        labelCapacidad = new javax.swing.JLabel();
        labelCaducidad = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtChofer = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        dcCaducidad = new com.toedter.calendar.JDateChooser();
        btnLimpiar = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();
        txtIdOR = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        encabezadoVista = new javax.swing.JPanel();
        tituloVista = new javax.swing.JLabel();
        iconoVista = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        idLabelBusqueda = new javax.swing.JLabel();
        txtIdBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtBusquedaMarca = new javax.swing.JTextField();
        txtBusquedaMatricula1 = new javax.swing.JTextField();
        idLabelBusqueda1 = new javax.swing.JLabel();
        idLabelBusqueda2 = new javax.swing.JLabel();

        tituloGestion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tituloGestion.setForeground(new java.awt.Color(23, 47, 77));
        tituloGestion.setText("Gestión de colectivos");

        subtituloGestion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        subtituloGestion.setForeground(new java.awt.Color(82, 100, 125));
        subtituloGestion.setText("Administrá la flota y su documentación  ");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        tblColectivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblColectivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblColectivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblColectivos);

        panelCrud.setBackground(new java.awt.Color(242, 239, 239));
        panelCrud.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));

        tituloSeccionCrud.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloSeccionCrud.setForeground(new java.awt.Color(23, 47, 77));
        tituloSeccionCrud.setText("Datos del colectivo");

        labelMatricula.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelMatricula.setForeground(new java.awt.Color(51, 51, 51));
        labelMatricula.setText("MATRICULA");

        labelMarca.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelMarca.setForeground(new java.awt.Color(51, 51, 51));
        labelMarca.setText("MARCA");

        labelModelo.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelModelo.setForeground(new java.awt.Color(51, 51, 51));
        labelModelo.setText("MODELO");

        labelChofer.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelChofer.setForeground(new java.awt.Color(51, 51, 51));
        labelChofer.setText("CHOFER");

        labelCapacidad.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelCapacidad.setForeground(new java.awt.Color(51, 51, 51));
        labelCapacidad.setText("CAPACIDAD");

        labelCaducidad.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelCaducidad.setForeground(new java.awt.Color(51, 51, 51));
        labelCaducidad.setText("CADUCIDAD");

        btnGuardar.setBackground(new java.awt.Color(0, 108, 158));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Nuevo.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(50, 50, 50));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/clean.png"))); // NOI18N
        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        labelID.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelID.setForeground(new java.awt.Color(51, 51, 51));
        labelID.setText("ID");

        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 108, 158));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 108, 158)));
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        javax.swing.GroupLayout panelCrudLayout = new javax.swing.GroupLayout(panelCrud);
        panelCrud.setLayout(panelCrudLayout);
        panelCrudLayout.setHorizontalGroup(
            panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrudLayout.createSequentialGroup()
                        .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloSeccionCrud)
                            .addGroup(panelCrudLayout.createSequentialGroup()
                                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMatricula)
                                    .addComponent(labelCapacidad)
                                    .addComponent(txtCapacidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelChofer)
                                    .addComponent(labelMarca)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelCaducidad)
                                    .addComponent(labelModelo)
                                    .addComponent(txtModelo)
                                    .addComponent(dcCaducidad, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(panelCrudLayout.createSequentialGroup()
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(panelCrudLayout.createSequentialGroup()
                        .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelID)
                            .addComponent(txtIdOR, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelCrudLayout.setVerticalGroup(
            panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCrudLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tituloSeccionCrud)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelMarca, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelMatricula))
                    .addComponent(labelModelo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCrudLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCapacidad)
                            .addComponent(labelChofer)))
                    .addGroup(panelCrudLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCaducidad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcCaducidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdOR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCrudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        encabezadoVista.setBackground(new java.awt.Color(20, 43, 73));

        tituloVista.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloVista.setForeground(new java.awt.Color(255, 255, 255));
        tituloVista.setText("Colectivos");

        iconoVista.setForeground(new java.awt.Color(255, 255, 255));
        iconoVista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/colectivos.png"))); // NOI18N

        javax.swing.GroupLayout encabezadoVistaLayout = new javax.swing.GroupLayout(encabezadoVista);
        encabezadoVista.setLayout(encabezadoVistaLayout);
        encabezadoVistaLayout.setHorizontalGroup(
            encabezadoVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encabezadoVistaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloVista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoVista)
                .addGap(25, 25, 25))
        );
        encabezadoVistaLayout.setVerticalGroup(
            encabezadoVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encabezadoVistaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(encabezadoVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloVista)
                    .addComponent(iconoVista))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/borrar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnDesactivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDesactivar.setForeground(new java.awt.Color(51, 51, 51));
        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Desactivar.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(this::btnDesactivarActionPerformed);

        btnActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActivar.setForeground(new java.awt.Color(51, 51, 51));
        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/actualizar.png"))); // NOI18N
        btnActivar.setText("Activar");
        btnActivar.addActionListener(this::btnActivarActionPerformed);

        idLabelBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        idLabelBusqueda.setForeground(new java.awt.Color(51, 51, 51));
        idLabelBusqueda.setText("ID");

        txtIdBusqueda.addActionListener(this::txtIdBusquedaActionPerformed);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(0, 108, 158));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 108, 158)));
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        txtBusquedaMarca.addActionListener(this::txtBusquedaMarcaActionPerformed);

        txtBusquedaMatricula1.addActionListener(this::txtBusquedaMatricula1ActionPerformed);

        idLabelBusqueda1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        idLabelBusqueda1.setForeground(new java.awt.Color(51, 51, 51));
        idLabelBusqueda1.setText("MATRICULA");

        idLabelBusqueda2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        idLabelBusqueda2.setForeground(new java.awt.Color(51, 51, 51));
        idLabelBusqueda2.setText("MARCA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(encabezadoVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelCrud, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subtituloGestion)
                            .addComponent(tituloGestion))
                        .addGap(473, 473, 473))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(idLabelBusqueda)
                                .addComponent(txtIdBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(idLabelBusqueda1)
                                .addComponent(txtBusquedaMatricula1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(56, 56, 56)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(idLabelBusqueda2)
                                    .addGap(173, 173, 173))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtBusquedaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(encabezadoVista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(tituloGestion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtituloGestion)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabelBusqueda)
                    .addComponent(idLabelBusqueda1)
                    .addComponent(idLabelBusqueda2))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusquedaMatricula1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusquedaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(panelCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        String idColectivo = txtIdOR.getText().trim();

        if (idColectivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un colectivo.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro desea desactivar el colectivo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idColectivo);

            if (colectivoData.desactivarColectivo(id)) {
                JOptionPane.showMessageDialog(this, "Se ha desactivado el colectivo.");

                cargarColectivos();
                modoNuevo();
                NotificadorCambios.notificarCambio(NotificadorCambios.COLECTIVOS);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo desactivar el colectivo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!txtIdOR.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hay un colectivo seleccionado. Use Actualizar, o presione Limpiar campos para cargar uno nuevo.");
            return;
        }

        if (validarCamposVacios(panelCrud)) {
            JOptionPane.showMessageDialog(this, "Complete matrícula, marca, modelo, capacidad y chofer.");
            return;
        }

        int capacidad;
        try {
            capacidad = Integer.parseInt(txtCapacidad.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser un número entero.");
            return;
        }

        if (capacidad <= 0) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser mayor a cero.");
            return;
        }

        if (capacidad > CAPACIDAD_MAXIMA) {
            JOptionPane.showMessageDialog(this, "La capacidad no puede superar los " + CAPACIDAD_MAXIMA + " asientos.");
            return;
        }

        Colectivo nuevo = new Colectivo(
                txtMatricula.getText().trim().toUpperCase(),
                txtMarca.getText().trim(),
                txtModelo.getText().trim(),
                capacidad,
                leerCaducidad(),
                true,
                txtChofer.getText().trim());

        if (colectivoData.guardarColectivo(nuevo)) {
            JOptionPane.showMessageDialog(this, "Se ha guardado el colectivo con sus " + capacidad + " asientos.");
            cargarColectivos();
            modoNuevo();
            NotificadorCambios.notificarCambio(NotificadorCambios.COLECTIVOS);
            NotificadorCambios.notificarCambio(NotificadorCambios.ASIENTOS);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el colectivo. Verifique que la matrícula no esté repetida.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String idColectivo = txtIdOR.getText().trim();
        if (idColectivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un colectivo.");
            return;
        }

        if (validarCamposVacios(panelCrud)) {
            JOptionPane.showMessageDialog(this, "Complete matrícula, marca, modelo, capacidad y chofer.");
            return;
        }

        int id = Integer.parseInt(idColectivo);

        Colectivo original = colectivoData.buscarColectivo(id);
        if (original == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el colectivo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!txtCapacidad.getText().trim().equals(String.valueOf(original.getCapacidad()))) {
            JOptionPane.showMessageDialog(this, "La capacidad no se puede modificar porque los asientos ya fueron generados.");
            return;
        }

        Colectivo editado = new Colectivo(
                id,
                txtMatricula.getText().trim().toUpperCase(),
                txtMarca.getText().trim(),
                txtModelo.getText().trim(),
                original.getCapacidad(),
                leerCaducidad(),
                original.isEstado(),
                txtChofer.getText().trim());

        if (colectivoData.actualizarColectivo(editado)) {
            JOptionPane.showMessageDialog(this, "Se ha actualizado el colectivo.");
            cargarColectivos();
            modoNuevo();
            NotificadorCambios.notificarCambio(NotificadorCambios.COLECTIVOS);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el colectivo. Verifique que la matrícula no esté repetida.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String idColectivo = txtIdOR.getText().trim();

        if (idColectivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un colectivo.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Seguro desea eliminar el colectivo y todos sus asientos?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idColectivo);

            if (colectivoData.eliminarColectivo(id)) {
                JOptionPane.showMessageDialog(this, "Se ha eliminado el colectivo.");
                cargarColectivos();
                modoNuevo();
                NotificadorCambios.notificarCambio(NotificadorCambios.COLECTIVOS);
                NotificadorCambios.notificarCambio(NotificadorCambios.ASIENTOS);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo eliminar el colectivo. Puede tener salidas o pasajes asociados.\n"
                        + "Si ya no se usa, puede desactivarlo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        modoNuevo();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        String idColectivo = txtIdOR.getText().trim();

        if (idColectivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un colectivo.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que desea activar el colectivo?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idColectivo);

            if (colectivoData.activarColectivo(id)) {
                JOptionPane.showMessageDialog(this, "Se ha activado el colectivo.");

                cargarColectivos();
                modoNuevo();
                NotificadorCambios.notificarCambio(NotificadorCambios.COLECTIVOS);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo activar el colectivo",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void mostrarCaducidad(LocalDate caducidad) {
        if (caducidad == null) {
            dcCaducidad.setDate(null);
        } else {
            dcCaducidad.setDate(Date.from(caducidad.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
    }

    private LocalDate leerCaducidad() {
        Date fecha = dcCaducidad.getDate();
        if (fecha == null) {
            return null;
        }
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void tblColectivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblColectivosMouseClicked
        int fila = tblColectivos.getSelectedRow();
        if (fila != -1) {
            cargarFormulario(fila);
        }
    }//GEN-LAST:event_tblColectivosMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        Integer id = null;
        String idIngresado = txtIdBusqueda.getText().trim();

        if (!idIngresado.isEmpty()) {
            try {
                id = Integer.parseInt(idIngresado);

                if (id <= 0) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número mayor a cero.");
                    return;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.");
                return;
            }
        }

        String matricula = txtBusquedaMatricula1.getText().trim();
        String marca = txtBusquedaMarca.getText().trim();

        listaColectivos = colectivoData.buscarColectivos(id, matricula, marca);

        modelo.setRowCount(0);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Colectivo colectivo : listaColectivos) {
            modelo.addRow(new Object[]{
                colectivo.getIdColectivo(),
                colectivo.getMatricula(),
                colectivo.getMarca(),
                colectivo.getModelo(),
                colectivo.getCapacidad(),
                colectivo.getChofer(),
                colectivo.getCaducidad() == null ? "-" : colectivo.getCaducidad().format(formato),
                colectivo.isEstado() ? "Activo" : "Inactivo"
            });
        }

        tblColectivos.clearSelection();
        vaciarCampos(panelCrud);
        txtIdOR.setText("");

        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnActivar.setEnabled(false);
        btnDesactivar.setEnabled(false);

        if (listaColectivos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No se encontraron colectivos con los criterios ingresados.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaMarcaActionPerformed

    private void txtBusquedaMatricula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaMatricula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaMatricula1ActionPerformed

    private void txtIdBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private com.toedter.calendar.JDateChooser dcCaducidad;
    private javax.swing.JPanel encabezadoVista;
    private javax.swing.JLabel iconoVista;
    private javax.swing.JLabel idLabelBusqueda;
    private javax.swing.JLabel idLabelBusqueda1;
    private javax.swing.JLabel idLabelBusqueda2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCaducidad;
    private javax.swing.JLabel labelCapacidad;
    private javax.swing.JLabel labelChofer;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelModelo;
    private javax.swing.JPanel panelCrud;
    private javax.swing.JLabel subtituloGestion;
    private javax.swing.JTable tblColectivos;
    private javax.swing.JLabel tituloGestion;
    private javax.swing.JLabel tituloSeccionCrud;
    private javax.swing.JLabel tituloVista;
    private javax.swing.JTextField txtBusquedaMarca;
    private javax.swing.JTextField txtBusquedaMatricula1;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtChofer;
    private javax.swing.JTextField txtIdBusqueda;
    private javax.swing.JTextField txtIdOR;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
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

        return false;
    }

    public void vaciarCampos(JPanel jPanel) {

        for (Component c : jPanel.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            } else if (c instanceof JDateChooser) {
                ((JDateChooser) c).setDate(null);
            }
        }

    }

    private void modoNuevo() {
        vaciarCampos(panelCrud);
        txtIdBusqueda.setText("");
        tblColectivos.clearSelection();
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnActivar.setEnabled(false);
        btnDesactivar.setEnabled(false);
    }

    private void modoSeleccion(boolean activa) {
        btnGuardar.setEnabled(false);
        btnEliminar.setEnabled(true);
        btnActualizar.setEnabled(true);
        btnActivar.setEnabled(!activa);
        btnDesactivar.setEnabled(activa);
    }

    private void cargarFormulario(int fila) {
        txtIdBusqueda.setText(tblColectivos.getValueAt(fila, 0).toString());
        txtIdOR.setText(tblColectivos.getValueAt(fila, 0).toString());
        txtMatricula.setText(tblColectivos.getValueAt(fila, 1).toString());
        txtMarca.setText(tblColectivos.getValueAt(fila, 2).toString());
        txtModelo.setText(tblColectivos.getValueAt(fila, 3).toString());
        txtCapacidad.setText(tblColectivos.getValueAt(fila, 4).toString());
        txtChofer.setText(tblColectivos.getValueAt(fila, 5).toString());
        mostrarCaducidad(listaColectivos.get(fila).getCaducidad());
        modoSeleccion(listaColectivos.get(fila).isEstado());
    }
}
