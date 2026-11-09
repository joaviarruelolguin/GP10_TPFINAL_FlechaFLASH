package vistas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import modelo.Asiento;
import modelo.MedioPago;
import modelo.ParadaRecorrido;
import modelo.Pasajero;
import modelo.RutaViaje;

public class VistaVentaPasajes extends javax.swing.JPanel {

    private java.util.List<modelo.Pasaje> listaPasajes
            = new java.util.ArrayList<>();

    private boolean cargandoParadas = false;

    public VistaVentaPasajes() {
        initComponents();
        aplicarEstilos();
        actualizarDatosVenta();
        estilizarTabla();
        cargarCompradores();
        cargarRutas();
        cargarPasajeros();
        actualizarAsientosDisponibles();
        cargarMediosPago();
        actualizarCuotas();
        cbTipoComprobante.removeAllItems();
        cbTipoComprobante.addItem("TICKET");
        cbTipoComprobante.setSelectedIndex(0);
        cbTipoComprobante.setEnabled(false);
        configurarColumnasTabla();

    }
    
     private void configurarColumnasTabla() {
        javax.swing.table.DefaultTableModel modeloTabla
                = (javax.swing.table.DefaultTableModel) tablaPasajes.getModel();

        modeloTabla.setColumnIdentifiers(new Object[]{
            "Pasajero", "DNI", "Ruta", "Fecha y hora de salida",
            "Origen", "Destino", "Asiento", "Precio"
        });
    }


    private void aplicarEstilos() {

        // Colores institucionales FlechaFLASH
        java.awt.Color azulMarino = new java.awt.Color(20, 43, 73);
        java.awt.Color blanco = java.awt.Color.WHITE;
        java.awt.Color grisTexto = new java.awt.Color(45, 55, 70);
        java.awt.Color grisClaro = new java.awt.Color(217, 222, 229);

        // Campos de texto
        javax.swing.JTextField[] campos = {
            txtFechaVenta,
            txtEstadoVenta,
            txtPrecioPasaje,
            txtNumeroComprobante,
            txtImporteTotal
        };

        for (javax.swing.JTextField campo : campos) {
            campo.setBackground(blanco);
            campo.setForeground(grisTexto);
            campo.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        }

        // Campos que no deben editarse manualmente
        txtFechaVenta.setEditable(false);
        txtEstadoVenta.setEditable(false);
        txtPrecioPasaje.setEditable(false);
        txtNumeroComprobante.setEditable(false);
        txtImporteTotal.setEditable(false);

        // Botones
        btnAgregarPasaje.setBackground(azulMarino);
        btnAgregarPasaje.setForeground(blanco);
        btnAgregarPasaje.setOpaque(true);
        btnAgregarPasaje.setContentAreaFilled(true);
        btnAgregarPasaje.setBorderPainted(false);

        btnRegistrarVenta.setBackground(azulMarino);
        btnRegistrarVenta.setForeground(blanco);
        btnRegistrarVenta.setOpaque(true);
        btnRegistrarVenta.setContentAreaFilled(true);
        btnRegistrarVenta.setBorderPainted(false);

        btnCancelarVenta.setBackground(grisClaro);
        btnCancelarVenta.setForeground(grisTexto);
        btnCancelarVenta.setOpaque(true);
        btnCancelarVenta.setContentAreaFilled(true);
        btnCancelarVenta.setBorderPainted(false);

        txtFechaVenta.setText("");
        txtEstadoVenta.setText("");
        txtPrecioPasaje.setText("");
        txtNumeroComprobante.setText("");
        txtImporteTotal.setText("");
        txtImporteTotal.setVisible(false);
        txtImporteTotal.setVisible(false);
        jLabel19.setVisible(false);

    }

    private void cargarCompradores() {

        try {
            persistencia.PasajeroData pasajeroData
                    = new persistencia.PasajeroData();

            cbComprador.removeAllItems();

            for (modelo.Pasajero pasajero
                    : pasajeroData.listarPasajeros()) {

                cbComprador.addItem(pasajero);
                cbComprador.setSelectedIndex(-1);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar los compradores: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarDatosVenta() {

        java.time.LocalDateTime ahora
                = java.time.LocalDateTime.now();

        java.time.format.DateTimeFormatter formato
                = java.time.format.DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm"
                );

        txtFechaVenta.setText(ahora.format(formato));

        txtEstadoVenta.setText("EN PREPARACIÓN");
    }

    private void cargarPasajeros() {

        try {
            persistencia.PasajeroData pasajeroData
                    = new persistencia.PasajeroData();

            cbPasajero.removeAllItems();

            for (modelo.Pasajero pasajero
                    : pasajeroData.listarPasajeros()) {

                cbPasajero.addItem(pasajero);
                cbPasajero.setSelectedIndex(-1);

            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar los pasajeros: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cargarAsientos() {

        cbAsiento.removeAllItems();

        modelo.RutaViaje rutaSeleccionada
                = (modelo.RutaViaje) cbRutaViaje.getSelectedItem();

        if (rutaSeleccionada == null
                || rutaSeleccionada.getColectivo() == null) {
            return;
        }

        try {
            persistencia.AsientoData asientoData
                    = new persistencia.AsientoData();

            java.util.List<modelo.Asiento> asientos
                    = asientoData.listarAsientosPorColectivo(
                            rutaSeleccionada.getColectivo()
                    );

            for (modelo.Asiento asiento : asientos) {
                cbAsiento.addItem(asiento);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar los asientos: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarAsientosDisponibles() {

        cbAsiento.removeAllItems();

        modelo.RutaViaje ruta
                = (modelo.RutaViaje) cbRutaViaje.getSelectedItem();

        modelo.ParadaRecorrido origen
                = (modelo.ParadaRecorrido) cbOrigen.getSelectedItem();

        modelo.ParadaRecorrido destino
                = (modelo.ParadaRecorrido) cbDestino.getSelectedItem();

        if (ruta == null || origen == null || destino == null) {
            return;
        }

        if (origen.getOrden() >= destino.getOrden()) {
            return;
        }

        try {
            persistencia.AsientoData asientoData
                    = new persistencia.AsientoData();

            persistencia.PasajeData pasajeData
                    = new persistencia.PasajeData();

            java.util.List<modelo.Asiento> asientos
                    = asientoData.listarAsientosPorColectivo(
                            ruta.getColectivo()
                    );

            for (modelo.Asiento asiento : asientos) {

                boolean disponible = pasajeData.consultarDisponibilidad(
                        ruta.getIdRutaViaje(),
                        asiento.getIdAsiento(),
                        origen.getOrden(),
                        destino.getOrden()
                );

                if (disponible) {
                    cbAsiento.addItem(asiento);
                }
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al consultar los asientos disponibles: "
                    + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarPrecioPasaje() {

        modelo.RutaViaje ruta
                = (modelo.RutaViaje) cbRutaViaje.getSelectedItem();

        modelo.ParadaRecorrido origen
                = (modelo.ParadaRecorrido) cbOrigen.getSelectedItem();

        modelo.ParadaRecorrido destino
                = (modelo.ParadaRecorrido) cbDestino.getSelectedItem();

        txtPrecioPasaje.setText("");

        if (ruta == null || origen == null || destino == null) {
            return;
        }

        if (origen.getOrden() >= destino.getOrden()) {
            return;
        }

        try {
            persistencia.PasajeData pasajeData
                    = new persistencia.PasajeData();

            java.math.BigDecimal precio
                    = pasajeData.calcularPrecioPasaje(
                            ruta,
                            origen,
                            destino
                    );

            txtPrecioPasaje.setText(precio.toPlainString());

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al calcular el precio: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void actualizarImporteTotal() {

        if (listaPasajes.isEmpty()) {
            lblImporteDestacado.setText("$ 0.00");
            return;
        }

        modelo.MedioPago medio
                = (modelo.MedioPago) cbMedioPago.getSelectedItem();

        if (medio == null) {
            return;
        }

        int cuotas = 0;

        if (medio == modelo.MedioPago.CREDITO) {

            Object seleccion = cbCuotas.getSelectedItem();

            if (seleccion == null) {
                return;
            }

            cuotas = (Integer) seleccion;
        }

        try {
            persistencia.VentaData ventaData
                    = new persistencia.VentaData();

            BigDecimal total = ventaData.calcularImporteTotal(
                    listaPasajes, cuotas, medio
            );

            lblImporteDestacado.setText(
                    "$ " + total.setScale(2, RoundingMode.HALF_UP)
                            .toPlainString()
            );

        } catch (Exception e) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al calcular el importe: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void agregarPasaje() {

        // Recuperar los objetos seleccionados
        modelo.Pasajero pasajero
                = (modelo.Pasajero) cbPasajero.getSelectedItem();

        modelo.RutaViaje ruta
                = (modelo.RutaViaje) cbRutaViaje.getSelectedItem();

        modelo.ParadaRecorrido origen
                = (modelo.ParadaRecorrido) cbOrigen.getSelectedItem();

        modelo.ParadaRecorrido destino
                = (modelo.ParadaRecorrido) cbDestino.getSelectedItem();

        modelo.Asiento asiento
                = (modelo.Asiento) cbAsiento.getSelectedItem();

        // Validar que todos los datos estén seleccionados
        if (pasajero == null || ruta == null || origen == null
                || destino == null || asiento == null) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Completá todos los datos del pasaje.",
                    "Datos incompletos",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar el orden de las paradas
        if (origen.getOrden() >= destino.getOrden()) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "El origen debe ser anterior al destino.",
                    "Tramo inválido",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar que el asiento no esté repetido en un tramo superpuesto
        for (modelo.Pasaje pasaje : listaPasajes) {

            if (pasaje.getRutaViaje().getIdRutaViaje()
                    == ruta.getIdRutaViaje()
                    && pasaje.getAsiento().getIdAsiento()
                    == asiento.getIdAsiento()) {

                boolean seSuperponen
                        = pasaje.getParadaOrigen().getOrden()
                        < destino.getOrden()
                        && pasaje.getParadaDestino().getOrden()
                        > origen.getOrden();

                if (seSuperponen) {

                    javax.swing.JOptionPane.showMessageDialog(
                            this,
                            "Ese asiento ya fue agregado para un tramo superpuesto.",
                            "Asiento repetido",
                            javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
            }
        }

        // Recuperar el precio
        String textoPrecio = txtPrecioPasaje.getText().trim();

        if (textoPrecio.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo obtener el precio del pasaje."
            );
            return;
        }

        try {
            java.math.BigDecimal precio
                    = new java.math.BigDecimal(textoPrecio);

            // Crear el objeto Pasaje
            modelo.Pasaje nuevoPasaje = new modelo.Pasaje(
                    pasajero,
                    ruta,
                    asiento,
                    origen,
                    destino,
                    java.time.LocalDateTime.now(),
                    precio,
                    true,
                    null
            );

            // Agregar a la lista temporal
            listaPasajes.add(nuevoPasaje);
            actualizarImporteTotal();

            // Agregar a la tabla
            javax.swing.table.DefaultTableModel modeloTabla
                    = (javax.swing.table.DefaultTableModel) tablaPasajes.getModel();

            modeloTabla.addRow(new Object[]{
                pasajero.getApellido() + ", " + pasajero.getNombre(),
                pasajero.getDni(),
                ruta.getIdRutaViaje(),
                ruta.getFechaHoraSalida().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                ),
                origen.getNombreLugar(),
                destino.getNombreLugar(),
                asiento.toString(),
                precio
            });

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Pasaje agregado correctamente."
            );

        } catch (NumberFormatException e) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "El precio ingresado no tiene un formato válido."
            );
        }
    }

    private void cargarRutas() {

        try {
            persistencia.RutaViajeData rutaData
                    = new persistencia.RutaViajeData();

            cbRutaViaje.removeAllItems();

            for (modelo.RutaViaje ruta
                    : rutaData.listarRutasViaje()) {

                cbRutaViaje.addItem(ruta);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar las rutas: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cargarParadas() {

        cargandoParadas = true;

        try {
            cbOrigen.removeAllItems();
            cbDestino.removeAllItems();

            modelo.RutaViaje rutaSeleccionada
                    = (modelo.RutaViaje) cbRutaViaje.getSelectedItem();

            if (rutaSeleccionada == null) {
                return;
            }

            persistencia.ParadaRecorridoData paradaData
                    = new persistencia.ParadaRecorridoData();

            java.util.List<modelo.ParadaRecorrido> paradas
                    = paradaData.listarParadasPorRecorrido(
                            rutaSeleccionada.getRecorrido().getIdRecorrido()
                    );

            for (modelo.ParadaRecorrido parada : paradas) {
                cbOrigen.addItem(parada);
                cbDestino.addItem(parada);
            }

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar las paradas: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        } finally {
            cargandoParadas = false;
        }
    }

    private void estilizarTabla() {

        javax.swing.table.JTableHeader encabezado
                = tablaPasajes.getTableHeader();

        encabezado.setBackground(new java.awt.Color(20, 43, 73));
        encabezado.setForeground(java.awt.Color.WHITE);
        encabezado.setFont(
                new java.awt.Font("Arial", java.awt.Font.BOLD, 12)
        );

        encabezado.setPreferredSize(
                new java.awt.Dimension(0, 30)
        );
    }

    private void cargarMediosPago() {

        cbMedioPago.removeAllItems();

        for (modelo.MedioPago medio : modelo.MedioPago.values()) {
            cbMedioPago.addItem(medio);
        }
    }

    private void actualizarCuotas() {

        modelo.MedioPago medio
                = (modelo.MedioPago) cbMedioPago.getSelectedItem();

        cbCuotas.removeAllItems();

        if (medio == modelo.MedioPago.CREDITO) {

            cbCuotas.setEnabled(true);

            for (int i = 1; i <= 6; i++) {
                cbCuotas.addItem(i);
            }

            cbCuotas.setSelectedIndex(0);

        } else {

            cbCuotas.setEnabled(false);

            cbCuotas.addItem(0);
            cbCuotas.setSelectedIndex(0);
        }
    }

    private void registrarVentaDesdeFormulario() {

        // Recuperar el comprador
        modelo.Pasajero comprador
                = (modelo.Pasajero) cbComprador.getSelectedItem();

        // Recuperar el medio de pago
        modelo.MedioPago medio
                = (modelo.MedioPago) cbMedioPago.getSelectedItem();

        // Validar que exista un comprador
        if (comprador == null) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná un comprador.",
                    "Datos incompletos",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar que haya pasajes agregados
        if (listaPasajes.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Agregá al menos un pasaje a la venta.",
                    "Venta vacía",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Validar el medio de pago
        if (medio == null) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná un medio de pago.",
                    "Datos incompletos",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Recuperar la cantidad de cuotas
        int cuotas = 0;

        if (medio == modelo.MedioPago.CREDITO) {

            Object seleccion = cbCuotas.getSelectedItem();

            if (seleccion == null) {
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Seleccioná la cantidad de cuotas.",
                        "Datos incompletos",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            cuotas = (Integer) seleccion;
        }

        try {

            // Actualizar las rutas desde la base de datos antes de validar la venta.
            sincronizarRutasPendientes();

            // Crear el objeto Venta
            modelo.Venta venta = new modelo.Venta(
                    comprador,
                    java.time.LocalDateTime.now(),
                    medio,
                    cuotas,
                    listaPasajes.size(),
                    null,
                    null,
                    null,
                    true
            );

            // Registrar la venta y sus pasajes
            persistencia.VentaData ventaData
                    = new persistencia.VentaData();

            boolean registrada = ventaData.registrarVenta(
                    venta,
                    listaPasajes
            );

            if (registrada) {

                // Agrego esta línea para que cuando VentaData termine de registrar la venta correctamente, 
                // el número generado se coloque automáticamente en el campo en vez de forma manual.
                txtNumeroComprobante.setText(venta.getNumeroComprobante());
                
                javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Venta registrada correctamente.\n"
                        + "Número de venta: " + venta.getIdVenta()
                        + "\nComprobante interno: "
                        + venta.getNumeroComprobante()
                        + "\nImporte total: $"
                        + venta.getImporteTotal(),
                        "Venta exitosa",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                );

                // Preparar el formulario para una nueva venta
                limpiarFormularioVenta();
            }
        } catch (Exception e) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar la venta:\n"
                    + e.getMessage(),
                    "Error al registrar",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarFormularioVenta() {

        // Limpiar la lista temporal de pasajes
        listaPasajes.clear();

        // Limpiar la tabla
        javax.swing.table.DefaultTableModel modeloTabla
                = (javax.swing.table.DefaultTableModel) tablaPasajes.getModel();

        modeloTabla.setRowCount(0);

        // Limpiar las selecciones
        cbComprador.setSelectedIndex(-1);
        cbPasajero.setSelectedIndex(-1);
        cbRutaViaje.setSelectedIndex(-1);

        // Limpiar las paradas y los asientos
        cbOrigen.removeAllItems();
        cbDestino.removeAllItems();
        cbAsiento.removeAllItems();

        // Limpiar los campos de texto
        txtPrecioPasaje.setText("");
        txtNumeroComprobante.setText("");
        
        // Restablecer el medio de pago y las cuotas
        cbMedioPago.setSelectedIndex(0);
        actualizarCuotas();
        
        // Mantener el tipo de comprobante predeterminado
        cbTipoComprobante.setSelectedIndex(0);

        // Limpiar el tipo de comprobante
        // cbTipoComprobante.setSelectedIndex(0);

        // Restablecer el importe total
        actualizarImporteTotal();
        
        actualizarDatosVenta();
    }

    private void sincronizarRutasPendientes() {

        persistencia.RutaViajeData rutaData
                = new persistencia.RutaViajeData();

        javax.swing.table.DefaultTableModel modeloTabla
                = (javax.swing.table.DefaultTableModel) tablaPasajes.getModel();

        for (int i = 0; i < listaPasajes.size(); i++) {

            modelo.Pasaje pasaje = listaPasajes.get(i);

            modelo.RutaViaje rutaAnterior = pasaje.getRutaViaje();

            modelo.RutaViaje rutaActualizada
                    = rutaData.buscarRutaViaje(rutaAnterior.getIdRutaViaje());

            if (rutaActualizada == null) {
                throw new IllegalArgumentException(
                        "No se pudo recuperar la ruta "
                        + rutaAnterior.getIdRutaViaje()
                        + " desde la base de datos."
                );
            }

            // Comprobar que el recorrido no haya cambiado.
            if (rutaActualizada.getRecorrido().getIdRecorrido()
                    != rutaAnterior.getRecorrido().getIdRecorrido()) {

                throw new IllegalArgumentException(
                        "La ruta " + rutaAnterior.getIdRutaViaje()
                        + " cambió de recorrido. Quitá y volvé a agregar ese pasaje."
                );
            }

            // Comprobar que el colectivo no haya cambiado.
            if (rutaActualizada.getColectivo().getIdColectivo()
                    != rutaAnterior.getColectivo().getIdColectivo()) {

                throw new IllegalArgumentException(
                        "La ruta " + rutaAnterior.getIdRutaViaje()
                        + " cambió de colectivo. Quitá y volvé a agregar ese pasaje."
                );
            }

            // Actualizar la referencia del pasaje.
            pasaje.setRutaViaje(rutaActualizada);

            // Actualizar la fecha visible en la tabla.
            modeloTabla.setValueAt(
                    rutaActualizada.getFechaHoraSalida().format(
                            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    ),
                    i,
                    3
            );
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelContenido = new javax.swing.JPanel();
        panelDatosventa = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbComprador = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFechaVenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEstadoVenta = new javax.swing.JTextField();
        panelSeccionPasajes = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbRutaViaje = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbOrigen = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbPasajero = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cbAsiento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnAgregarPasaje = new javax.swing.JButton();
        txtPrecioPasaje = new javax.swing.JTextField();
        panelDetallePasajes = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPasajes = new javax.swing.JTable();
        panelPago = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbMedioPago = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbCuotas = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbTipoComprobante = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtNumeroComprobante = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtImporteTotal = new javax.swing.JTextField();
        btnCancelarVenta = new javax.swing.JButton();
        btnRegistrarVenta = new javax.swing.JButton();
        panelImporteTotal = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblImporteDestacado = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(20, 43, 73));
        jPanel2.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(20, 43, 73));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/venta_pasajes.png"))); // NOI18N
        jLabel1.setText("Venta de Pasajes ");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 27));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel1.setIconTextGap(12);
        jLabel1.setMaximumSize(new java.awt.Dimension(32667, 32667));
        jLabel1.setPreferredSize(new java.awt.Dimension(0, 85));
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        panelContenido.setBackground(new java.awt.Color(204, 204, 204));

        panelDatosventa.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(20, 43, 73));
        jLabel2.setText("DATOS DE LA VENTA");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(65, 75, 90));
        jLabel3.setText("Comprador ");

        cbComprador.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbComprador.setForeground(new java.awt.Color(45, 55, 70));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(65, 75, 90));
        jLabel4.setText("Fecha y Hora ");

        txtFechaVenta.setEditable(false);
        txtFechaVenta.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtFechaVenta.setForeground(new java.awt.Color(45, 55, 70));
        txtFechaVenta.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(65, 75, 90));
        jLabel5.setText("Estado ");

        txtEstadoVenta.setEditable(false);
        txtEstadoVenta.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtEstadoVenta.setForeground(new java.awt.Color(45, 55, 70));
        txtEstadoVenta.setText("jTextField1");

        javax.swing.GroupLayout panelDatosventaLayout = new javax.swing.GroupLayout(panelDatosventa);
        panelDatosventa.setLayout(panelDatosventaLayout);
        panelDatosventaLayout.setHorizontalGroup(
            panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosventaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosventaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(34, 34, 34)
                        .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addGroup(panelDatosventaLayout.createSequentialGroup()
                        .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(45, 45, 45)
                        .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelDatosventaLayout.createSequentialGroup()
                                .addComponent(txtEstadoVenta)
                                .addGap(167, 167, 167)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDatosventaLayout.setVerticalGroup(
            panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosventaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panelDatosventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEstadoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        panelSeccionPasajes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(20, 43, 73));
        jLabel6.setText("SELECCION DE PASAJES ");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(65, 75, 90));
        jLabel7.setText("Ruta de Viaje ");

        cbRutaViaje.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbRutaViaje.setForeground(new java.awt.Color(45, 55, 70));
        cbRutaViaje.addItemListener(this::cbRutaViajeItemStateChanged);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(65, 75, 90));
        jLabel8.setText("Origen :");

        cbOrigen.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbOrigen.setForeground(new java.awt.Color(45, 55, 70));
        cbOrigen.addItemListener(this::cbOrigenItemStateChanged);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(65, 75, 90));
        jLabel9.setText("Destino:");

        cbDestino.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbDestino.setForeground(new java.awt.Color(45, 55, 70));
        cbDestino.addItemListener(this::cbDestinoItemStateChanged);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(65, 75, 90));
        jLabel10.setText("Pasajero");

        cbPasajero.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbPasajero.setForeground(new java.awt.Color(45, 55, 70));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(65, 75, 90));
        jLabel11.setText("Asiento");

        cbAsiento.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbAsiento.setForeground(new java.awt.Color(45, 55, 70));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(65, 75, 90));
        jLabel12.setText("Precio: ");

        btnAgregarPasaje.setBackground(new java.awt.Color(20, 43, 73));
        btnAgregarPasaje.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnAgregarPasaje.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPasaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Nuevo.png"))); // NOI18N
        btnAgregarPasaje.setText("AGREGAR PASAJE");
        btnAgregarPasaje.addActionListener(this::btnAgregarPasajeActionPerformed);

        txtPrecioPasaje.setEditable(false);
        txtPrecioPasaje.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtPrecioPasaje.setForeground(new java.awt.Color(45, 55, 70));
        txtPrecioPasaje.setText("jTextField1");

        javax.swing.GroupLayout panelSeccionPasajesLayout = new javax.swing.GroupLayout(panelSeccionPasajes);
        panelSeccionPasajes.setLayout(panelSeccionPasajesLayout);
        panelSeccionPasajesLayout.setHorizontalGroup(
            panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                        .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel8))
                                .addGap(41, 41, 41)
                                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbRutaViaje, 0, 208, Short.MAX_VALUE)
                                    .addComponent(cbOrigen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSeccionPasajesLayout.setVerticalGroup(
            panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbRutaViaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cbAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(cbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9))
                    .addGroup(panelSeccionPasajesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSeccionPasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtPrecioPasaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnAgregarPasaje)
                .addGap(23, 23, 23))
        );

        panelDetallePasajes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(20, 43, 73));
        jLabel13.setText("DETALLE DE PASAJES ");

        tablaPasajes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tablaPasajes.setForeground(new java.awt.Color(45, 55, 70));
        tablaPasajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pasajero", "Ruta", "Origen", "Destino", "Asiento", "Precio"
            }
        ));
        tablaPasajes.setRowHeight(28);
        tablaPasajes.setSelectionBackground(new java.awt.Color(210, 222, 238));
        tablaPasajes.setSelectionForeground(new java.awt.Color(20, 43, 73));
        jScrollPane1.setViewportView(tablaPasajes);

        javax.swing.GroupLayout panelDetallePasajesLayout = new javax.swing.GroupLayout(panelDetallePasajes);
        panelDetallePasajes.setLayout(panelDetallePasajesLayout);
        panelDetallePasajesLayout.setHorizontalGroup(
            panelDetallePasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallePasajesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelDetallePasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDetallePasajesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelDetallePasajesLayout.setVerticalGroup(
            panelDetallePasajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDetallePasajesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        panelPago.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(20, 43, 73));
        jLabel14.setText("PAGO Y COMPROBANTES");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(65, 75, 90));
        jLabel15.setText("Medio de pago");

        cbMedioPago.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbMedioPago.setForeground(new java.awt.Color(45, 55, 70));
        cbMedioPago.addActionListener(this::cbMedioPagoActionPerformed);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(65, 75, 90));
        jLabel16.setText("Cuotas");

        cbCuotas.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbCuotas.setForeground(new java.awt.Color(45, 55, 70));
        cbCuotas.addActionListener(this::cbCuotasActionPerformed);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(65, 75, 90));
        jLabel17.setText("Tipo de comprobante");

        cbTipoComprobante.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cbTipoComprobante.setForeground(new java.awt.Color(45, 55, 70));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(65, 75, 90));
        jLabel18.setText("Número de comprobante");

        txtNumeroComprobante.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNumeroComprobante.setForeground(new java.awt.Color(45, 55, 70));
        txtNumeroComprobante.setText("jTextField1");
        txtNumeroComprobante.addActionListener(this::txtNumeroComprobanteActionPerformed);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(65, 75, 90));
        jLabel19.setText("Importe total: ");

        txtImporteTotal.setEditable(false);
        txtImporteTotal.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtImporteTotal.setForeground(new java.awt.Color(45, 55, 70));
        txtImporteTotal.setText("jTextField1");

        btnCancelarVenta.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelarVenta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCancelarVenta.setForeground(new java.awt.Color(38, 54, 74));
        btnCancelarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Desactivar.png"))); // NOI18N
        btnCancelarVenta.setText("CANCELAR ");
        btnCancelarVenta.addActionListener(this::btnCancelarVentaActionPerformed);

        btnRegistrarVenta.setBackground(new java.awt.Color(20, 43, 73));
        btnRegistrarVenta.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnRegistrarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/NuevaVentaI.png"))); // NOI18N
        btnRegistrarVenta.setText("REGISTRAR VENTA ");
        btnRegistrarVenta.addActionListener(this::btnRegistrarVentaActionPerformed);

        panelImporteTotal.setBackground(new java.awt.Color(20, 43, 73));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("IMPORTE TOTAL DE LA VENTA");

        lblImporteDestacado.setFont(new java.awt.Font("Arial", 1, 26)); // NOI18N
        lblImporteDestacado.setForeground(new java.awt.Color(255, 255, 255));
        lblImporteDestacado.setText("$ 0,00");

        javax.swing.GroupLayout panelImporteTotalLayout = new javax.swing.GroupLayout(panelImporteTotal);
        panelImporteTotal.setLayout(panelImporteTotalLayout);
        panelImporteTotalLayout.setHorizontalGroup(
            panelImporteTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImporteTotalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelImporteTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImporteDestacado, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelImporteTotalLayout.setVerticalGroup(
            panelImporteTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImporteTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImporteDestacado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPagoLayout = new javax.swing.GroupLayout(panelPago);
        panelPago.setLayout(panelPagoLayout);
        panelPagoLayout.setHorizontalGroup(
            panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPagoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRegistrarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbMedioPago, 0, 156, Short.MAX_VALUE)
                            .addComponent(cbCuotas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPagoLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addGap(48, 48, 48)
                                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(txtImporteTotal)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPagoLayout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addComponent(panelImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPagoLayout.setVerticalGroup(
            panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPagoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtNumeroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cbMedioPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(cbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelPagoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(panelImporteTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(panelPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarVenta))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout panelContenidoLayout = new javax.swing.GroupLayout(panelContenido);
        panelContenido.setLayout(panelContenidoLayout);
        panelContenidoLayout.setHorizontalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidoLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSeccionPasajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDatosventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDetallePasajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        panelContenidoLayout.setVerticalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenidoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(panelDatosventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelSeccionPasajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDetallePasajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(panelContenido);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroComprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroComprobanteActionPerformed

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        // Verificar que haya una fila seleccionada
        int filaSeleccionada = tablaPasajes.getSelectedRow();

        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná el pasaje que querés quitar de la venta.",
                    "Ningún pasaje seleccionado",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Confirmar la eliminación del pasaje
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que querés quitar este pasaje de la venta?",
                "Confirmar eliminación",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        // Convertir el índice de la fila al índice del modelo
        int indiceModelo = tablaPasajes.convertRowIndexToModel(filaSeleccionada);

        // Quitar el pasaje de la lista temporal
        listaPasajes.remove(indiceModelo);

        // Quitar la fila de la tabla
        javax.swing.table.DefaultTableModel modeloTabla
                = (javax.swing.table.DefaultTableModel) tablaPasajes.getModel();

        modeloTabla.removeRow(indiceModelo);

        // Actualizar el importe total
        actualizarImporteTotal();

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "El pasaje fue quitado de la venta.",
                "Operación realizada",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );

    }//GEN-LAST:event_btnCancelarVentaActionPerformed

    private void cbRutaViajeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbRutaViajeItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            cargarParadas();
            cargarAsientos();
        }
    }//GEN-LAST:event_cbRutaViajeItemStateChanged

    private void cbOrigenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrigenItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED
                && !cargandoParadas) {

            actualizarAsientosDisponibles();
            actualizarPrecioPasaje();
        }
    }//GEN-LAST:event_cbOrigenItemStateChanged

    private void cbDestinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDestinoItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED
                && !cargandoParadas) {

            actualizarAsientosDisponibles();
            actualizarPrecioPasaje();
        }
    }//GEN-LAST:event_cbDestinoItemStateChanged

    private void btnAgregarPasajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPasajeActionPerformed
        agregarPasaje();
    }//GEN-LAST:event_btnAgregarPasajeActionPerformed

    private void cbMedioPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMedioPagoActionPerformed
        actualizarCuotas();
        actualizarImporteTotal();
    }//GEN-LAST:event_cbMedioPagoActionPerformed

    private void cbCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCuotasActionPerformed
        actualizarImporteTotal();
    }//GEN-LAST:event_cbCuotasActionPerformed

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        registrarVentaDesdeFormulario();
    }//GEN-LAST:event_btnRegistrarVentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarPasaje;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JComboBox<Asiento> cbAsiento;
    private javax.swing.JComboBox<Pasajero> cbComprador;
    private javax.swing.JComboBox<Integer> cbCuotas;
    private javax.swing.JComboBox<ParadaRecorrido> cbDestino;
    private javax.swing.JComboBox<MedioPago> cbMedioPago;
    private javax.swing.JComboBox<ParadaRecorrido> cbOrigen;
    private javax.swing.JComboBox<Pasajero> cbPasajero;
    private javax.swing.JComboBox<RutaViaje> cbRutaViaje;
    private javax.swing.JComboBox<String> cbTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImporteDestacado;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatosventa;
    private javax.swing.JPanel panelDetallePasajes;
    private javax.swing.JPanel panelImporteTotal;
    private javax.swing.JPanel panelPago;
    private javax.swing.JPanel panelSeccionPasajes;
    private javax.swing.JTable tablaPasajes;
    private javax.swing.JTextField txtEstadoVenta;
    private javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextField txtImporteTotal;
    private javax.swing.JTextField txtNumeroComprobante;
    private javax.swing.JTextField txtPrecioPasaje;
    // End of variables declaration//GEN-END:variables
}
