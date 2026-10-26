package modelo;

public class ParadaRecorrido {

    private int idParadaRecorrido;
    private Recorrido recorrido;
    private String nombreLugar;
    private int orden;
    private double distanciaAcumuladaKm;

    public ParadaRecorrido(Recorrido recorrido, String nombreLugar, int orden, double distanciaAcumuladaKm) {
        this.recorrido = recorrido;
        this.nombreLugar = nombreLugar;
        this.orden = orden;
        this.distanciaAcumuladaKm = distanciaAcumuladaKm;
    }

    public ParadaRecorrido(int idParadaRecorrido, Recorrido recorrido, String nombreLugar, int orden, double distanciaAcumuladaKm) {
        this.idParadaRecorrido = idParadaRecorrido;
        this.recorrido = recorrido;
        this.nombreLugar = nombreLugar;
        this.orden = orden;
        this.distanciaAcumuladaKm = distanciaAcumuladaKm;
    }

    public ParadaRecorrido() {
    }

    public int getIdParadaRecorrido() {
        return idParadaRecorrido;
    }

    public void setIdParadaRecorrido(int idParadaRecorrido) {
        this.idParadaRecorrido = idParadaRecorrido;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public double getDistanciaAcumuladaKm() {
        return distanciaAcumuladaKm;
    }

    public void setDistanciaAcumuladaKm(double distanciaAcumuladaKm) {
        this.distanciaAcumuladaKm = distanciaAcumuladaKm;
    }

    @Override
    public String toString() {
        return nombreLugar;
    }

}
