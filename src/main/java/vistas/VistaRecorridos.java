package vistas;

import modelo.Recorrido;
import java.util.List;
import java.util.ArrayList;
import modelo.ParadaRecorrido;

public class VistaRecorridos extends javax.swing.JPanel {

    private persistencia.RecorridoData recorridoData = new persistencia.RecorridoData();
    private java.util.List<modelo.ParadaRecorrido> paradasFormulario = new java.util.ArrayList<>();
    private boolean modoEdicion = false;
    private int idRecorridoSeleccionado = -1;

    private List<Recorrido> recorridosCargados = new ArrayList<>();

    private List<javax.swing.JTextField> camposNombreParada = new ArrayList<>();
    private List<javax.swing.JTextField> camposDistanciaParada = new ArrayList<>();

    public VistaRecorridos() {
        initComponents();

        pnlParadas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 8));
        pnlParadas.setBackground(java.awt.Color.WHITE);

        configurarTablaRecorridos();
        configurarEventos();
        habilitarFormulario(false);
        cargarRecorridos();

    }

    private void configurarTablaRecorridos() {
        String[] columnas = {"Descripción", "Cantidad de paradas", "Estado"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tblRecorridos.setModel(modelo);
    }

    private void cargarRecorridos() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblRecorridos.getModel();
        modelo.setRowCount(0);
        recorridosCargados = recorridoData.listarRecorridos();

        for (Recorrido recorrido : recorridosCargados) {
            int cantidadParadas = recorrido.getParadas() == null ? 0 : recorrido.getParadas().size();
            String estado = recorrido.isEstado() ? "Activo" : "Inactivo";

            modelo.addRow(new Object[]{recorrido.getDescripcion(), cantidadParadas, estado});
        }
    }

    private Recorrido obtenerRecorridoSeleccionado() {
        int fila = tblRecorridos.getSelectedRow();

        if (fila == -1) {
            return null;
        }

        fila = tblRecorridos.convertRowIndexToModel(fila);
        return recorridosCargados.get(fila);
    }

    private void habilitarFormulario(boolean habilitar) {
        pnlFormularioRecorrido.setVisible(habilitar);
        txtDescripcion.setEnabled(habilitar);
        pnlParadas.setEnabled(habilitar);
        btnAgregarParada.setEnabled(habilitar);
        btnGuardar.setEnabled(habilitar);
        btnCancelar.setEnabled(habilitar);

        btnNuevo.setEnabled(!habilitar);
        btnEditar.setEnabled(!habilitar);
        btnDesactivar.setEnabled(!habilitar);
        btnEliminar.setEnabled(!habilitar);
        tblRecorridos.setEnabled(!habilitar);
    }

    private void configurarEventos() {
        btnNuevo.addActionListener(e -> nuevoRecorrido());
        btnEditar.addActionListener(e -> editarRecorrido());
        btnDesactivar.addActionListener(e -> cambiarEstadoRecorrido());
        btnEliminar.addActionListener(e -> eliminarRecorrido());
        btnCancelar.addActionListener(e -> cancelarFormulario());
        btnGuardar.addActionListener(e -> guardar());
        btnAgregarParada.addActionListener(e -> agregarFilaParada());

        tblRecorridos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Recorrido seleccionado = obtenerRecorridoSeleccionado();
                idRecorridoSeleccionado = seleccionado != null ? seleccionado.getIdRecorrido() : -1;

                if (seleccionado != null && !seleccionado.isEstado()) {
                    btnDesactivar.setText("Reactivar");
                } else {
                    btnDesactivar.setText("Desactivar");
                }
            }
        });
    }

    private void nuevoRecorrido() {
        modoEdicion = false;
        idRecorridoSeleccionado = -1;

        txtDescripcion.setText("");
        paradasFormulario.clear();
        camposNombreParada.clear();
        camposDistanciaParada.clear();
        pnlParadas.removeAll();
        pnlParadas.revalidate();
        pnlParadas.repaint();

        tblRecorridos.clearSelection();
        habilitarFormulario(true);
        agregarFilaParada();
        agregarFilaParada();
        txtDescripcion.requestFocusInWindow();
    }

    private void editarRecorrido() {
        Recorrido seleccionado = obtenerRecorridoSeleccionado();

        if (seleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccioná un recorrido para editar.");
            return;
        }

        if (recorridoData.tieneSalidasAsociadas(seleccionado.getIdRecorrido())) {
            javax.swing.JOptionPane.showMessageDialog(this, "Este recorrido tiene salidas asociadas. No se pueden modificar su descripción ni sus paradas.");
            return;
        }

        modoEdicion = true;
        idRecorridoSeleccionado = seleccionado.getIdRecorrido();

        txtDescripcion.setText(seleccionado.getDescripcion());
        camposNombreParada.clear();
        camposDistanciaParada.clear();
        pnlParadas.removeAll();

        habilitarFormulario(true);

        for (ParadaRecorrido parada : seleccionado.getParadas()) {
            agregarFilaParada();

            int indice = camposNombreParada.size() - 1;
            camposNombreParada.get(indice).setText(parada.getNombreLugar());
            camposDistanciaParada.get(indice).setText(String.valueOf(parada.getDistanciaAcumuladaKm()));
        }

        actualizarOrdenesParadas();
    }

    private void cambiarEstadoRecorrido() {
        Recorrido seleccionado = obtenerRecorridoSeleccionado();

        if (seleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccioná un recorrido.");
            return;
        }

        boolean nuevoEstado = !seleccionado.isEstado();

        String mensaje = nuevoEstado
                ? "¿Deseás reactivar este recorrido?"
                : "¿Deseás desactivar este recorrido?";

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmar operación",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        Recorrido recorrido = new Recorrido(
                seleccionado.getIdRecorrido(),
                seleccionado.getDescripcion(),
                nuevoEstado
        );

        boolean resultado = recorridoData.actualizarRecorrido(recorrido);

        if (resultado) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    nuevoEstado ? "Recorrido reactivado correctamente." : "Recorrido desactivado correctamente.");

            cargarRecorridos();
            btnDesactivar.setText("Desactivar");
            idRecorridoSeleccionado = -1;
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo cambiar el estado del recorrido.");
        }
    }
    
    private void cancelarFormulario() {
        modoEdicion = false;
        idRecorridoSeleccionado = -1;

        txtDescripcion.setText("");
        paradasFormulario.clear();
        camposNombreParada.clear();
        camposDistanciaParada.clear();

        pnlParadas.removeAll();
        pnlParadas.revalidate();
        pnlParadas.repaint();

        habilitarFormulario(false);
        btnDesactivar.setText("Desactivar");
        cargarRecorridos();
    }

    private void agregarFilaParada() {
        int orden = camposNombreParada.size() + 1;

        javax.swing.JPanel fila = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 5));
        fila.setBackground(java.awt.Color.WHITE);

        javax.swing.JLabel lblOrden = new javax.swing.JLabel(String.valueOf(orden));
        lblOrden.setPreferredSize(new java.awt.Dimension(20, 25));

        javax.swing.JTextField txtNombre = new javax.swing.JTextField(14);
        txtNombre.setToolTipText("Nombre de la parada");

        javax.swing.JTextField txtDistancia = new javax.swing.JTextField(6);
        txtDistancia.setToolTipText("Distancia acumulada en km");

        if (orden == 1) {
            txtDistancia.setText("0");
            txtDistancia.setEnabled(false);
        }

        javax.swing.JButton btnEliminarFila = new javax.swing.JButton("X");

        btnEliminarFila.addActionListener(e -> {
            int indice = camposNombreParada.indexOf(txtNombre);

            if (indice == 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se puede eliminar la primera parada porque representa el origen del recorrido.");
                return;
            }

            if (camposNombreParada.size() <= 2) {
                javax.swing.JOptionPane.showMessageDialog(this, "El recorrido debe tener al menos dos paradas.");
                return;
            }

            camposNombreParada.remove(txtNombre);
            camposDistanciaParada.remove(txtDistancia);
            pnlParadas.remove(fila);
            actualizarOrdenesParadas();
        });

        fila.add(lblOrden);
        fila.add(txtNombre);
        fila.add(txtDistancia);
        fila.add(btnEliminarFila);

        camposNombreParada.add(txtNombre);
        camposDistanciaParada.add(txtDistancia);

        pnlParadas.add(fila);
        pnlParadas.revalidate();
        pnlParadas.repaint();
    }

    private void actualizarOrdenesParadas() {
        int orden = 1;

        for (java.awt.Component componente : pnlParadas.getComponents()) {
            if (componente instanceof javax.swing.JPanel fila) {
                for (java.awt.Component elemento : fila.getComponents()) {
                    if (elemento instanceof javax.swing.JLabel lblOrden) {
                        lblOrden.setText(String.valueOf(orden));
                    }
                }
                orden++;
            }
        }

        if (!camposDistanciaParada.isEmpty()) {
            camposDistanciaParada.get(0).setText("0");
            camposDistanciaParada.get(0).setEnabled(false);
        }

        pnlParadas.revalidate();
        pnlParadas.repaint();
    }

    private List<ParadaRecorrido> obtenerParadasFormulario() {

        List<ParadaRecorrido> paradas = new ArrayList<>();

        if (camposNombreParada.size() < 2) {
            javax.swing.JOptionPane.showMessageDialog(this, "El recorrido debe tener al menos dos paradas.");
            return null;
        }

        double distanciaAnterior = -1;

        for (int i = 0; i < camposNombreParada.size(); i++) {
            String nombre = camposNombreParada.get(i).getText().trim();
            String textoDistancia = camposDistanciaParada.get(i).getText().trim();

            if (nombre.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Completá el nombre de todas las paradas.");
                return null;
            }

            double distancia;

            try {
                distancia = Double.parseDouble(textoDistancia.replace(",", "."));
            } catch (NumberFormatException e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ingresá una distancia válida en la parada " + (i + 1) + ".");
                return null;
            }

            if (distancia < 0 || distancia > 20000) {
                javax.swing.JOptionPane.showMessageDialog(this, "La distancia debe estar entre 0 y 20.000 km.");
                return null;
            }

            if (i == 0 && distancia != 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "La primera parada debe tener una distancia de 0 km.");
                return null;
            }

            if (i > 0 && distancia <= distanciaAnterior) {
                javax.swing.JOptionPane.showMessageDialog(this, "Las distancias deben ser crecientes. Revisá la parada " + (i + 1) + ".");
                return null;
            }

            paradas.add(new ParadaRecorrido(null, nombre, i + 1, distancia));
            distanciaAnterior = distancia;
        }

        return paradas;
    }

    private boolean validarDescripcion() {
        String descripcion = txtDescripcion.getText().trim();

        if (descripcion.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingresá la descripción del recorrido.");
            txtDescripcion.requestFocusInWindow();
            return false;
        }

        return true;
    }

    private void guardar() {
        if (!validarDescripcion()) {
            return;
        }

        List<ParadaRecorrido> paradas = obtenerParadasFormulario();

        if (paradas == null) {
            return;
        }

        boolean resultado;

        if (modoEdicion) {
            Recorrido actual = recorridoData.buscarRecorrido(idRecorridoSeleccionado);

            if (actual == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontró el recorrido seleccionado.");
                return;
            }

            Recorrido recorrido = new Recorrido(idRecorridoSeleccionado, txtDescripcion.getText().trim(), actual.isEstado());
            recorrido.setParadas(paradas);

            resultado = recorridoData.actualizarRecorridoCompleto(recorrido);

        } else {
            Recorrido recorrido = new Recorrido(txtDescripcion.getText().trim(), true, paradas);
            resultado = recorridoData.guardarRecorrido(recorrido);
        }

        if (resultado) {
            javax.swing.JOptionPane.showMessageDialog(this, modoEdicion ? "Recorrido actualizado correctamente." : "Recorrido guardado correctamente.");
            cancelarFormulario();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo guardar el recorrido.");
        }
    }

    
    private void eliminarRecorrido() {
        Recorrido seleccionado = obtenerRecorridoSeleccionado();

        if (seleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccioná un recorrido para eliminar.");
            return;
        }

        if (recorridoData.tieneSalidasAsociadas(seleccionado.getIdRecorrido())) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se puede eliminar este recorrido porque tiene salidas asociadas. Podés desactivarlo.");
            return;
        }

        int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que querés eliminar el recorrido \"" + seleccionado.getDescripcion() + "\" y todas sus paradas?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado = recorridoData.eliminarRecorrido(seleccionado.getIdRecorrido());

        if (resultado) {
            javax.swing.JOptionPane.showMessageDialog(this, "Recorrido eliminado correctamente.");
            cargarRecorridos();
            idRecorridoSeleccionado = -1;
            btnDesactivar.setText("Desactivar");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo eliminar el recorrido.");
        }
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
                .addGroup(headerVistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28))
        );

        lblRecorridosRegistrados.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRecorridosRegistrados.setForeground(new java.awt.Color(20, 43, 73));
        lblRecorridosRegistrados.setText("Recorridos registrados");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(20, 43, 73));
        lblTitulo.setText("Gestión de Recorridos");

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        javax.swing.GroupLayout pnlDescripcionLayout = new javax.swing.GroupLayout(pnlDescripcion);
        pnlDescripcion.setLayout(pnlDescripcionLayout);
        pnlDescripcionLayout.setHorizontalGroup(
            pnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDescripcionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        lblParadasRecorrido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblParadasRecorrido.setForeground(new java.awt.Color(23, 47, 77));
        lblParadasRecorrido.setText("Paradas del recorrido");

        javax.swing.GroupLayout pnlFormularioRecorridoLayout = new javax.swing.GroupLayout(pnlFormularioRecorrido);
        pnlFormularioRecorrido.setLayout(pnlFormularioRecorridoLayout);
        pnlFormularioRecorridoLayout.setHorizontalGroup(
            pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioRecorridoLayout.createSequentialGroup()
                .addGroup(pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioRecorridoLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioRecorridoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblParadasRecorrido)
                            .addComponent(lblDatosRecorrido)
                            .addGroup(pnlFormularioRecorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnAgregarParada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                .addComponent(pnlParadas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblRecorridosRegistrados)
                            .addComponent(lblSubtitulo)
                            .addComponent(scrollRecorridos, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                            .addComponent(pnlFormularioRecorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
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
