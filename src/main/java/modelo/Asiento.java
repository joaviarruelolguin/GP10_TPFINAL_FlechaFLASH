
package modelo;


public class Asiento {
    private int idAsiento;
    private Colectivo colectivo;
    private int numeroAsiento;

    public Asiento(Colectivo colectivo, int numeroAsiento) {
        this.colectivo = colectivo;
        this.numeroAsiento = numeroAsiento;
    }

    public Asiento(int idAsiento, Colectivo colectivo, int numeroAsiento) {
        this.idAsiento = idAsiento;
        this.colectivo = colectivo;
        this.numeroAsiento = numeroAsiento;
    }

    public Asiento() {
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    @Override
    public String toString() {
        return "Asiento N°" + numeroAsiento;
    }
    

    
    
}
