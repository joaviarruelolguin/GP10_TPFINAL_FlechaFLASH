
package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pasaje {

    private int idPasaje;
    private Pasajero pasajero;
    private RutaViaje rutaViaje;
    private Asiento asiento;
    private ParadaRecorrido paradaOrigen;
    private ParadaRecorrido paradaDestino;
    private LocalDateTime fechaHoraVenta;
    private BigDecimal precioFinal;
    private boolean estado;
    private Venta venta;

    // Constructor sin ID
    public Pasaje(Pasajero pasajero, RutaViaje rutaViaje,
            Asiento asiento, ParadaRecorrido paradaOrigen,
            ParadaRecorrido paradaDestino, LocalDateTime fechaHoraVenta,
            BigDecimal precioFinal, boolean estado, Venta venta) {

        this.pasajero = pasajero;
        this.rutaViaje = rutaViaje;
        this.asiento = asiento;
        this.paradaOrigen = paradaOrigen;
        this.paradaDestino = paradaDestino;
        this.fechaHoraVenta = fechaHoraVenta;
        this.precioFinal = precioFinal;
        this.estado = estado;
        this.venta = venta;
    }

    // Constructor con ID
    public Pasaje(int idPasaje, Pasajero pasajero,
            RutaViaje rutaViaje, Asiento asiento,
            ParadaRecorrido paradaOrigen, ParadaRecorrido paradaDestino,
            LocalDateTime fechaHoraVenta, BigDecimal precioFinal,
            boolean estado, Venta venta) {

        this.idPasaje = idPasaje;
        this.pasajero = pasajero;
        this.rutaViaje = rutaViaje;
        this.asiento = asiento;
        this.paradaOrigen = paradaOrigen;
        this.paradaDestino = paradaDestino;
        this.fechaHoraVenta = fechaHoraVenta;
        this.precioFinal = precioFinal;
        this.estado = estado;
        this.venta = venta;
    }

    public Pasaje() {
    }

    public int getIdPasaje() {
        return idPasaje;
    }

    public void setIdPasaje(int idPasaje) {
        this.idPasaje = idPasaje;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public RutaViaje getRutaViaje() {
        return rutaViaje;
    }

    public void setRutaViaje(RutaViaje rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public ParadaRecorrido getParadaOrigen() {
        return paradaOrigen;
    }

    public void setParadaOrigen(ParadaRecorrido paradaOrigen) {
        this.paradaOrigen = paradaOrigen;
    }

    public ParadaRecorrido getParadaDestino() {
        return paradaDestino;
    }

    public void setParadaDestino(ParadaRecorrido paradaDestino) {
        this.paradaDestino = paradaDestino;
    }

    public LocalDateTime getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(LocalDateTime fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "Pasaje N° " + idPasaje
                + " - Pasajero: " + pasajero
                + " - Asiento: " + asiento
                + " - Origen: " + paradaOrigen
                + " - Destino: " + paradaDestino
                + " - Precio: $" + precioFinal;
    }
}
