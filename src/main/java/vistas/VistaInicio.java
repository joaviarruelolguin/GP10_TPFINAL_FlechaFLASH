
package vistas;

import controlador.NotificadorCambios;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import modelo.RutaViaje;
import persistencia.RutaViajeData;
import persistencia.PasajeData;
import persistencia.ColectivoData;
import persistencia.PasajeroData;

public class VistaInicio extends javax.swing.JPanel implements PropertyChangeListener {

    private RutaViajeData rutaData = new RutaViajeData();
    private PasajeData pasajeData = new PasajeData();
    private ColectivoData colectivoData = new ColectivoData();
    private PasajeroData pasajeroData = new PasajeroData();

    public VistaInicio() {
        initComponents();
        configurarAccesosRapidos();
        configurarDisenoAdaptable();
        configurarTarjetasSalidas();
        NotificadorCambios.agregarListener(this);
        sincronizarDatos();
    }

    private void configurarAccesosRapidos() {
        configurarTarjeta(pnlNuevaVenta, icono1, lblVenta, jLabel2, jLabel4);
        configurarTarjeta(pnlNuevaSalida, icono2, jLabel1, jLabel3, jLabel5);
    }

    private void configurarTarjeta(javax.swing.JPanel tarjeta, javax.swing.JLabel icono, javax.swing.JLabel titulo, javax.swing.JLabel descripcion, javax.swing.JLabel flecha) {
        tarjeta.removeAll();
        tarjeta.setLayout(new java.awt.GridBagLayout());

        javax.swing.JPanel textos = new javax.swing.JPanel();
        textos.setOpaque(false);
        textos.setLayout(new javax.swing.BoxLayout(textos, javax.swing.BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(descripcion);

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(10, 12, 10, 12);

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        tarjeta.add(icono, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        tarjeta.add(textos, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        tarjeta.add(flecha, gbc);

        tarjeta.revalidate();
        tarjeta.repaint();
    }

    private void configurarTarjetasSalidas() {
        configurarTarjetaSalida(pnlSalida1, lblHorario1, lblDestino1, lblColectivo1, lblTiempo1);
        configurarTarjetaSalida(pnlSalida2, lblHorario2, lblDestino2, lblColectivo2, lblTiempo2);
        configurarTarjetaSalida(pnlSalida3, lblHorario3, lblDestino3, lblColectivo3, lblTiempo3);
    }

    private void configurarTarjetaSalida(javax.swing.JPanel tarjeta, javax.swing.JLabel horario, javax.swing.JLabel destino, javax.swing.JLabel colectivo, javax.swing.JLabel estado) {
        tarjeta.removeAll();
        tarjeta.setLayout(new java.awt.GridBagLayout());

        javax.swing.JPanel informacion = new javax.swing.JPanel();
        informacion.setOpaque(false);
        informacion.setLayout(new javax.swing.BoxLayout(informacion, javax.swing.BoxLayout.Y_AXIS));
        informacion.add(destino);
        informacion.add(colectivo);

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(10, 12, 10, 12);

        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        tarjeta.add(horario, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        tarjeta.add(informacion, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        tarjeta.add(estado, gbc);
    }


    
    private void configurarDisenoAdaptable() {
        removeAll();
        setLayout(new java.awt.GridBagLayout());

        agregarFila(lblTituloPrincipal, 0, 22);
        agregarFila(lblSubtitulo, 1, 5);
        agregarFila(pnlEstadisticas, 2, 55);
        agregarFila(jSeparator1, 3, 10);
        agregarFila(lblAccesosRapidos1, 4, 5);
        agregarFila(pnlAccesosRapidos, 5, 12);
        agregarFila(jSeparator2, 6, 50);
        agregarFila(lblProximasSalidas, 7, 5);
        agregarFila(pnlListadoSalidas, 8, 12);

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.weighty = 1;
        gbc.fill = java.awt.GridBagConstraints.VERTICAL;
        add(javax.swing.Box.createVerticalGlue(), gbc);
    }

    private void agregarFila(java.awt.Component componente, int fila, int espacioSuperior) {
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.anchor = java.awt.GridBagConstraints.WEST;
        gbc.insets = new java.awt.Insets(espacioSuperior, 50, 30, 50);
        add(componente, gbc);
    }


    private void sincronizarDatos() {
        cargarEstadisticas();
        cargarProximasSalidas();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (NotificadorCambios.PASAJEROS.equals(evt.getNewValue())
                || NotificadorCambios.VENTAS.equals(evt.getNewValue())
                || NotificadorCambios.SALIDAS.equals(evt.getNewValue())
                || NotificadorCambios.RECORRIDOS.equals(evt.getNewValue())
                || NotificadorCambios.COLECTIVOS.equals(evt.getNewValue())) {
            sincronizarDatos();
        }
    }

    private void cargarEstadisticas() {
        List<RutaViaje> rutas = rutaData.listarRutasViaje();
        LocalDate hoy = LocalDate.now();

        long salidasHoy = rutas.stream()
                .filter(r -> r.isEstado())
                .filter(r -> r.getFechaHoraSalida().toLocalDate().equals(hoy))
                .count();

        lblCantidadSalidas.setText(String.valueOf(salidasHoy));
        lblCantidadPasajesV.setText(String.valueOf(pasajeData.contarPasajesEmitidos()));
        lblCantidadColectivosA.setText(String.valueOf(colectivoData.listarColectivosActivos().size()));
        lblCantidadPasajerosA.setText(String.valueOf(pasajeroData.listarPasajeros().size()));
    }

    
    private void cargarProximasSalidas() {
        List<RutaViaje> rutas = rutaData.listarRutasViaje();

        List<RutaViaje> proximas = rutas.stream()
                .filter(r -> r.isEstado())
                .filter(r -> r.getFechaHoraSalida().isAfter(LocalDateTime.now()))
                .filter(r -> r.getColectivo().isEstado())
                .filter(r -> r.getRecorrido().isEstado())
                .limit(3)
                .toList();

        javax.swing.JLabel[] horarios = {lblHorario1, lblHorario2, lblHorario3};
        javax.swing.JLabel[] destinos = {lblDestino1, lblDestino2, lblDestino3};
        javax.swing.JLabel[] colectivos = {lblColectivo1, lblColectivo2, lblColectivo3};
        javax.swing.JLabel[] tiempos = {lblTiempo1, lblTiempo2, lblTiempo3};
        javax.swing.JPanel[] paneles = {pnlSalida1, pnlSalida2, pnlSalida3};

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        for (int i = 0; i < 3; i++) {
            if (i < proximas.size()) {
                RutaViaje ruta = proximas.get(i);

                horarios[i].setText(ruta.getFechaHoraSalida().format(formatoHora));
                destinos[i].setText(ruta.getRecorrido().getDescripcion());

                colectivos[i].setText(
                        ruta.getColectivo().getMarca() + " — "
                        + ruta.getColectivo().getModelo()
                );

                tiempos[i].setText("Programada");
                paneles[i].setVisible(true);
            } else {
                paneles[i].setVisible(false);
            }
        }

        pnlListadoSalidas.revalidate();
        pnlListadoSalidas.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloPrincipal = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlEstadisticas = new javax.swing.JPanel();
        pnlSalidasHoy = new javax.swing.JPanel();
        lblCantidadSalidas = new javax.swing.JLabel();
        lblDescripcionSalidas = new javax.swing.JLabel();
        lblIcono1 = new javax.swing.JLabel();
        pnlPasajesVendidos = new javax.swing.JPanel();
        lblDescripcionVendidos = new javax.swing.JLabel();
        lblCantidadPasajesV = new javax.swing.JLabel();
        lblIcono2 = new javax.swing.JLabel();
        pnlColectivosActivos = new javax.swing.JPanel();
        lblDescripcionColectivos = new javax.swing.JLabel();
        lblCantidadColectivosA = new javax.swing.JLabel();
        lblIcono3 = new javax.swing.JLabel();
        pnlPasajerosRegistrados = new javax.swing.JPanel();
        lblDescripcionPasajeros = new javax.swing.JLabel();
        lblCantidadPasajerosA = new javax.swing.JLabel();
        lblIcono4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblAccesosRapidos1 = new javax.swing.JLabel();
        pnlAccesosRapidos = new javax.swing.JPanel();
        pnlNuevaVenta = new javax.swing.JPanel();
        icono1 = new javax.swing.JLabel();
        lblVenta = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlNuevaSalida = new javax.swing.JPanel();
        icono2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblProximasSalidas = new javax.swing.JLabel();
        pnlListadoSalidas = new javax.swing.JPanel();
        pnlSalida1 = new javax.swing.JPanel();
        lblHorario1 = new javax.swing.JLabel();
        lblDestino1 = new javax.swing.JLabel();
        lblColectivo1 = new javax.swing.JLabel();
        lblTiempo1 = new javax.swing.JLabel();
        pnlSalida2 = new javax.swing.JPanel();
        lblHorario2 = new javax.swing.JLabel();
        lblDestino2 = new javax.swing.JLabel();
        lblTiempo2 = new javax.swing.JLabel();
        lblColectivo2 = new javax.swing.JLabel();
        pnlSalida3 = new javax.swing.JPanel();
        lblHorario3 = new javax.swing.JLabel();
        lblDestino3 = new javax.swing.JLabel();
        lblTiempo3 = new javax.swing.JLabel();
        lblColectivo3 = new javax.swing.JLabel();

        lblTituloPrincipal.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloPrincipal.setForeground(new java.awt.Color(20, 43, 73));
        lblTituloPrincipal.setText("Panel principal");

        lblSubtitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(82, 100, 125));
        lblSubtitulo.setText("Bienvenido a FlechaFLASH");

        pnlEstadisticas.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        lblCantidadSalidas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCantidadSalidas.setText("12");

        lblDescripcionSalidas.setText("Salidas de hoy");

        lblIcono1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/SalidasI.png"))); // NOI18N

        javax.swing.GroupLayout pnlSalidasHoyLayout = new javax.swing.GroupLayout(pnlSalidasHoy);
        pnlSalidasHoy.setLayout(pnlSalidasHoyLayout);
        pnlSalidasHoyLayout.setHorizontalGroup(
            pnlSalidasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalidasHoyLayout.createSequentialGroup()
                .addGroup(pnlSalidasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSalidasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCantidadSalidas)
                        .addComponent(lblIcono1))
                    .addComponent(lblDescripcionSalidas))
                .addGap(0, 123, Short.MAX_VALUE))
        );
        pnlSalidasHoyLayout.setVerticalGroup(
            pnlSalidasHoyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalidasHoyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcono1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantidadSalidas)
                .addGap(18, 18, 18)
                .addComponent(lblDescripcionSalidas)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pnlEstadisticas.add(pnlSalidasHoy);

        lblDescripcionVendidos.setText("Pasajes vendidos");

        lblCantidadPasajesV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCantidadPasajesV.setText("85");

        lblIcono2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/VentasI.png"))); // NOI18N

        javax.swing.GroupLayout pnlPasajesVendidosLayout = new javax.swing.GroupLayout(pnlPasajesVendidos);
        pnlPasajesVendidos.setLayout(pnlPasajesVendidosLayout);
        pnlPasajesVendidosLayout.setHorizontalGroup(
            pnlPasajesVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasajesVendidosLayout.createSequentialGroup()
                .addGroup(pnlPasajesVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionVendidos)
                    .addGroup(pnlPasajesVendidosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCantidadPasajesV))
                    .addComponent(lblIcono2))
                .addGap(0, 110, Short.MAX_VALUE))
        );
        pnlPasajesVendidosLayout.setVerticalGroup(
            pnlPasajesVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPasajesVendidosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblIcono2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadPasajesV)
                .addGap(18, 18, 18)
                .addComponent(lblDescripcionVendidos)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pnlEstadisticas.add(pnlPasajesVendidos);

        lblDescripcionColectivos.setText("Colectivos activos");

        lblCantidadColectivosA.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCantidadColectivosA.setText("8");

        lblIcono3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ColectivosI.png"))); // NOI18N

        javax.swing.GroupLayout pnlColectivosActivosLayout = new javax.swing.GroupLayout(pnlColectivosActivos);
        pnlColectivosActivos.setLayout(pnlColectivosActivosLayout);
        pnlColectivosActivosLayout.setHorizontalGroup(
            pnlColectivosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColectivosActivosLayout.createSequentialGroup()
                .addGroup(pnlColectivosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIcono3)
                    .addComponent(lblDescripcionColectivos)
                    .addGroup(pnlColectivosActivosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCantidadColectivosA)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        pnlColectivosActivosLayout.setVerticalGroup(
            pnlColectivosActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlColectivosActivosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcono3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCantidadColectivosA)
                .addGap(18, 18, 18)
                .addComponent(lblDescripcionColectivos)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pnlEstadisticas.add(pnlColectivosActivos);

        lblDescripcionPasajeros.setText("Pasajeros registrados");

        lblCantidadPasajerosA.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCantidadPasajerosA.setText("64");

        lblIcono4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/PasajerosI.png"))); // NOI18N

        javax.swing.GroupLayout pnlPasajerosRegistradosLayout = new javax.swing.GroupLayout(pnlPasajerosRegistrados);
        pnlPasajerosRegistrados.setLayout(pnlPasajerosRegistradosLayout);
        pnlPasajerosRegistradosLayout.setHorizontalGroup(
            pnlPasajerosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasajerosRegistradosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCantidadPasajerosA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPasajerosRegistradosLayout.createSequentialGroup()
                .addGroup(pnlPasajerosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIcono4)
                    .addComponent(lblDescripcionPasajeros))
                .addGap(0, 88, Short.MAX_VALUE))
        );
        pnlPasajerosRegistradosLayout.setVerticalGroup(
            pnlPasajerosRegistradosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPasajerosRegistradosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcono4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCantidadPasajerosA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcionPasajeros)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pnlEstadisticas.add(pnlPasajerosRegistrados);

        lblAccesosRapidos1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAccesosRapidos1.setForeground(new java.awt.Color(20, 43, 73));
        lblAccesosRapidos1.setText("Accesos rápidos");

        pnlAccesosRapidos.setLayout(new java.awt.GridLayout(2, 1, 20, 12));

        pnlNuevaVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlNuevaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNuevaVentaMouseClicked(evt);
            }
        });

        icono1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/NuevaVentaI.png"))); // NOI18N

        lblVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblVenta.setText("Nueva venta");

        jLabel2.setText("Vender pasajes");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText(">");

        javax.swing.GroupLayout pnlNuevaVentaLayout = new javax.swing.GroupLayout(pnlNuevaVenta);
        pnlNuevaVenta.setLayout(pnlNuevaVentaLayout);
        pnlNuevaVentaLayout.setHorizontalGroup(
            pnlNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icono1)
                .addGap(18, 18, 18)
                .addGroup(pnlNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lblVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        pnlNuevaVentaLayout.setVerticalGroup(
            pnlNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevaVentaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(pnlNuevaVentaLayout.createSequentialGroup()
                        .addComponent(lblVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(icono1))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnlAccesosRapidos.add(pnlNuevaVenta);

        pnlNuevaSalida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlNuevaSalida.setForeground(new java.awt.Color(242, 242, 242));
        pnlNuevaSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlNuevaSalidaMouseClicked(evt);
            }
        });

        icono2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/NuevaSalidaI.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Programar salida");

        jLabel3.setText("Crear una salida de transporte");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText(">");

        javax.swing.GroupLayout pnlNuevaSalidaLayout = new javax.swing.GroupLayout(pnlNuevaSalida);
        pnlNuevaSalida.setLayout(pnlNuevaSalidaLayout);
        pnlNuevaSalidaLayout.setHorizontalGroup(
            pnlNuevaSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevaSalidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(icono2)
                .addGap(18, 18, 18)
                .addGroup(pnlNuevaSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        pnlNuevaSalidaLayout.setVerticalGroup(
            pnlNuevaSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNuevaSalidaLayout.createSequentialGroup()
                .addGroup(pnlNuevaSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNuevaSalidaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(pnlNuevaSalidaLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(pnlNuevaSalidaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(icono2)))
                .addGap(28, 28, 28))
        );

        pnlAccesosRapidos.add(pnlNuevaSalida);

        lblProximasSalidas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblProximasSalidas.setForeground(new java.awt.Color(20, 43, 73));
        lblProximasSalidas.setText("Próximas salidas");

        pnlListadoSalidas.setLayout(new java.awt.GridLayout(3, 1, 0, 10));

        pnlSalida1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));
        pnlSalida1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSalida1MouseClicked(evt);
            }
        });

        lblHorario1.setBackground(new java.awt.Color(229, 239, 251));
        lblHorario1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblHorario1.setText("08:30");
        lblHorario1.setOpaque(true);

        lblDestino1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDestino1.setText("San Luis → Mendoza");

        lblColectivo1.setText("Mercedes-Benz  —  OH 1621");

        lblTiempo1.setBackground(new java.awt.Color(221, 243, 227));
        lblTiempo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTiempo1.setForeground(new java.awt.Color(22, 128, 60));
        lblTiempo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempo1.setText("  A tiempo");
        lblTiempo1.setOpaque(true);

        javax.swing.GroupLayout pnlSalida1Layout = new javax.swing.GroupLayout(pnlSalida1);
        pnlSalida1.setLayout(pnlSalida1Layout);
        pnlSalida1Layout.setHorizontalGroup(
            pnlSalida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalida1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblHorario1)
                .addGap(29, 29, 29)
                .addGroup(pnlSalida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDestino1)
                    .addComponent(lblColectivo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(lblTiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlSalida1Layout.setVerticalGroup(
            pnlSalida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalida1Layout.createSequentialGroup()
                .addGroup(pnlSalida1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlSalida1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblHorario1))
                    .addGroup(pnlSalida1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDestino1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblColectivo1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSalida1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pnlListadoSalidas.add(pnlSalida1);

        pnlSalida2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));
        pnlSalida2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSalida2MouseClicked(evt);
            }
        });

        lblHorario2.setBackground(new java.awt.Color(229, 239, 251));
        lblHorario2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblHorario2.setText("09:15");
        lblHorario2.setOpaque(true);

        lblDestino2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDestino2.setText("San Luis → San Juan");

        lblTiempo2.setBackground(new java.awt.Color(221, 243, 227));
        lblTiempo2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTiempo2.setForeground(new java.awt.Color(22, 128, 60));
        lblTiempo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempo2.setText("  A tiempo");
        lblTiempo2.setOpaque(true);

        lblColectivo2.setText("Scania  —  AC 456 EF");

        javax.swing.GroupLayout pnlSalida2Layout = new javax.swing.GroupLayout(pnlSalida2);
        pnlSalida2.setLayout(pnlSalida2Layout);
        pnlSalida2Layout.setHorizontalGroup(
            pnlSalida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalida2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblHorario2)
                .addGap(27, 27, 27)
                .addGroup(pnlSalida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDestino2)
                    .addComponent(lblColectivo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(lblTiempo2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        pnlSalida2Layout.setVerticalGroup(
            pnlSalida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalida2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblDestino2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(lblColectivo2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSalida2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSalida2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTiempo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHorario2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(16, 16, 16))
        );

        pnlListadoSalidas.add(pnlSalida2);

        pnlSalida3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(206, 206, 206)));
        pnlSalida3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSalida3MouseClicked(evt);
            }
        });

        lblHorario3.setBackground(new java.awt.Color(229, 239, 251));
        lblHorario3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblHorario3.setText("10:00");
        lblHorario3.setOpaque(true);

        lblDestino3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDestino3.setText("San Luis → Córdoba");

        lblTiempo3.setBackground(new java.awt.Color(221, 243, 227));
        lblTiempo3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTiempo3.setForeground(new java.awt.Color(22, 128, 60));
        lblTiempo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTiempo3.setText("  A tiempo");
        lblTiempo3.setOpaque(true);

        lblColectivo3.setText("Volvo  —  AD 789 GH");

        javax.swing.GroupLayout pnlSalida3Layout = new javax.swing.GroupLayout(pnlSalida3);
        pnlSalida3.setLayout(pnlSalida3Layout);
        pnlSalida3Layout.setHorizontalGroup(
            pnlSalida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalida3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblHorario3)
                .addGap(26, 26, 26)
                .addGroup(pnlSalida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDestino3)
                    .addComponent(lblColectivo3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(lblTiempo3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlSalida3Layout.setVerticalGroup(
            pnlSalida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSalida3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(pnlSalida3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorario3)
                    .addComponent(lblTiempo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(pnlSalida3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblDestino3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblColectivo3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pnlListadoSalidas.add(pnlSalida3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAccesosRapidos1)
                        .addGap(0, 471, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlEstadisticas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlAccesosRapidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(51, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProximasSalidas)
                    .addComponent(pnlListadoSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(pnlEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAccesosRapidos1)
                .addGap(18, 18, 18)
                .addComponent(pnlAccesosRapidos, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblProximasSalidas)
                .addGap(18, 18, 18)
                .addComponent(pnlListadoSalidas, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlNuevaVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNuevaVentaMouseClicked
       
         firePropertyChange("navegar", null, "VENTAPASAJES");        
    }//GEN-LAST:event_pnlNuevaVentaMouseClicked

    private void pnlNuevaSalidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlNuevaSalidaMouseClicked

        firePropertyChange("navegar", null, "SALIDASHORARIOS");
    }//GEN-LAST:event_pnlNuevaSalidaMouseClicked

    private void pnlSalida1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSalida1MouseClicked
       
        
        
    }//GEN-LAST:event_pnlSalida1MouseClicked

    private void pnlSalida2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSalida2MouseClicked
        
        
        
    }//GEN-LAST:event_pnlSalida2MouseClicked

    private void pnlSalida3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSalida3MouseClicked
        
        
        
    }//GEN-LAST:event_pnlSalida3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icono1;
    private javax.swing.JLabel icono2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAccesosRapidos1;
    private javax.swing.JLabel lblCantidadColectivosA;
    private javax.swing.JLabel lblCantidadPasajerosA;
    private javax.swing.JLabel lblCantidadPasajesV;
    private javax.swing.JLabel lblCantidadSalidas;
    private javax.swing.JLabel lblColectivo1;
    private javax.swing.JLabel lblColectivo2;
    private javax.swing.JLabel lblColectivo3;
    private javax.swing.JLabel lblDescripcionColectivos;
    private javax.swing.JLabel lblDescripcionPasajeros;
    private javax.swing.JLabel lblDescripcionSalidas;
    private javax.swing.JLabel lblDescripcionVendidos;
    private javax.swing.JLabel lblDestino1;
    private javax.swing.JLabel lblDestino2;
    private javax.swing.JLabel lblDestino3;
    private javax.swing.JLabel lblHorario1;
    private javax.swing.JLabel lblHorario2;
    private javax.swing.JLabel lblHorario3;
    private javax.swing.JLabel lblIcono1;
    private javax.swing.JLabel lblIcono2;
    private javax.swing.JLabel lblIcono3;
    private javax.swing.JLabel lblIcono4;
    private javax.swing.JLabel lblProximasSalidas;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTiempo1;
    private javax.swing.JLabel lblTiempo2;
    private javax.swing.JLabel lblTiempo3;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblVenta;
    private javax.swing.JPanel pnlAccesosRapidos;
    private javax.swing.JPanel pnlColectivosActivos;
    private javax.swing.JPanel pnlEstadisticas;
    private javax.swing.JPanel pnlListadoSalidas;
    private javax.swing.JPanel pnlNuevaSalida;
    private javax.swing.JPanel pnlNuevaVenta;
    private javax.swing.JPanel pnlPasajerosRegistrados;
    private javax.swing.JPanel pnlPasajesVendidos;
    private javax.swing.JPanel pnlSalida1;
    private javax.swing.JPanel pnlSalida2;
    private javax.swing.JPanel pnlSalida3;
    private javax.swing.JPanel pnlSalidasHoy;
    // End of variables declaration//GEN-END:variables
}
