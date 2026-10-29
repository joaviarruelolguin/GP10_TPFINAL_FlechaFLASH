package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RutaViaje {

    private int idRutaViaje;
    private Recorrido recorrido;
    private Colectivo colectivo;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private BigDecimal precioBase;
    private boolean estado;

    public RutaViaje(Recorrido recorrido, Colectivo colectivo, LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, BigDecimal precioBase, boolean estado) {
        this.recorrido = recorrido;
        this.colectivo = colectivo;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.precioBase = precioBase;
        this.estado = estado;
    }

    public RutaViaje(int idRutaViaje, Recorrido recorrido, Colectivo colectivo, LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, BigDecimal precioBase, boolean estado) {
        this.idRutaViaje = idRutaViaje;
        this.recorrido = recorrido;
        this.colectivo = colectivo;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.precioBase = precioBase;
        this.estado = estado;
    }

    public RutaViaje() {
    }

    public int getIdRutaViaje() {
        return idRutaViaje;
    }

    public void setIdRutaViaje(int idRutaViaje) {
        this.idRutaViaje = idRutaViaje;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return " Ruta del viaje " + idRutaViaje
                + " Recorrido:  " + recorrido
                + " Colectivo: " + colectivo
                + " Fecha y hora de salida" + fechaHoraSalida
                + " Fecha y hora de llegada: " + fechaHoraLlegada
                + " Precio: $ " + precioBase;

    }

}
