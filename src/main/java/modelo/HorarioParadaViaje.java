
package modelo;

import java.time.LocalDateTime;

public class HorarioParadaViaje {

    private int idHorarioParadaViaje;
    private RutaViaje rutaViaje;
    private ParadaRecorrido paradaRecorrido;
    private LocalDateTime fechaHoraLlegada;
    private LocalDateTime fechaHoraSalida;

    public HorarioParadaViaje(RutaViaje rutaViaje, ParadaRecorrido paradaRecorrido, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida) {
        this.rutaViaje = rutaViaje;
        this.paradaRecorrido = paradaRecorrido;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public HorarioParadaViaje(int idHorarioParadaViaje, RutaViaje rutaViaje, ParadaRecorrido paradaRecorrido, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida) {
        this.idHorarioParadaViaje = idHorarioParadaViaje;
        this.rutaViaje = rutaViaje;
        this.paradaRecorrido = paradaRecorrido;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public HorarioParadaViaje() {
    }

    public int getIdHorarioParadaViaje() {
        return idHorarioParadaViaje;
    }

    public void setIdHorarioParadaViaje(int idHorarioParadaViaje) {
        this.idHorarioParadaViaje = idHorarioParadaViaje;
    }

    public RutaViaje getRutaViaje() {
        return rutaViaje;
    }

    public void setRutaViaje(RutaViaje rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    public ParadaRecorrido getParadaRecorrido() {
        return paradaRecorrido;
    }

    public void setParadaRecorrido(ParadaRecorrido paradaRecorrido) {
        this.paradaRecorrido = paradaRecorrido;
    }

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    @Override
    public String toString() {
        return "Horario de parada: " + paradaRecorrido
                + " Llegada: " + fechaHoraLlegada
                + " Salida: " + fechaHoraSalida;
    }
    
}