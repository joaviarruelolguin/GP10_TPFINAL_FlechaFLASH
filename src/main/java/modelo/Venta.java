
package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venta {

    private int idVenta;
    private Pasajero pasajeroComprador;
    private LocalDateTime fechaHoraVenta;
    private MedioPago medioPago;
    private int cantidadCuotas;
    private int cantidadPasajes;
    private BigDecimal importeTotal;
    private String tipoComprobante;
    private String numeroComprobante;
    private boolean estado;

    // Constructor sin ID: para registrar una venta nueva
    public Venta(Pasajero pasajeroComprador, LocalDateTime fechaHoraVenta,
            MedioPago medioPago, int cantidadCuotas, int cantidadPasajes,
            BigDecimal importeTotal, String tipoComprobante,
            String numeroComprobante, boolean estado) {

        this.pasajeroComprador = pasajeroComprador;
        this.fechaHoraVenta = fechaHoraVenta;
        this.medioPago = medioPago;
        this.cantidadCuotas = cantidadCuotas;
        this.cantidadPasajes = cantidadPasajes;
        this.importeTotal = importeTotal;
        this.tipoComprobante = tipoComprobante;
        this.numeroComprobante = numeroComprobante;
        this.estado = estado;
    }

    // Constructor con ID: para recuperar una venta de la BD
    public Venta(int idVenta, Pasajero pasajeroComprador,
            LocalDateTime fechaHoraVenta, MedioPago medioPago,
            int cantidadCuotas, int cantidadPasajes,
            BigDecimal importeTotal, String tipoComprobante,
            String numeroComprobante, boolean estado) {

        this.idVenta = idVenta;
        this.pasajeroComprador = pasajeroComprador;
        this.fechaHoraVenta = fechaHoraVenta;
        this.medioPago = medioPago;
        this.cantidadCuotas = cantidadCuotas;
        this.cantidadPasajes = cantidadPasajes;
        this.importeTotal = importeTotal;
        this.tipoComprobante = tipoComprobante;
        this.numeroComprobante = numeroComprobante;
        this.estado = estado;
    }
    
    // constructor vacio

    public Venta() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Pasajero getPasajeroComprador() {
        return pasajeroComprador;
    }

    public void setPasajeroComprador(Pasajero pasajeroComprador) {
        this.pasajeroComprador = pasajeroComprador;
    }

    public LocalDateTime getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(LocalDateTime fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public int getCantidadPasajes() {
        return cantidadPasajes;
    }

    public void setCantidadPasajes(int cantidadPasajes) {
        this.cantidadPasajes = cantidadPasajes;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Venta N° " + idVenta
                + " - Comprador: " + pasajeroComprador
                + " - Total: $" + importeTotal;
    }
}
