package modelo;

import java.util.ArrayList;
import java.util.List;

public class Recorrido {

    private int idRecorrido;
    private String descripcion;
    private boolean estado;
    private List<ParadaRecorrido> paradas = new ArrayList<>();

    // Constructor para crear un recorrido nuevo recibiendo sus paradas. (COMPOSICIÓN)
    public Recorrido(String descripcion, boolean estado, List<ParadaRecorrido> paradas) {
        this.descripcion = descripcion;
        this.estado = estado;

        for (ParadaRecorrido parada : paradas) {
            agregarParada(parada);
        }
    }

    // Constructor para recuperar un recorrido de la BD. Después, el DAO consulta la tabla ParadaRecorrido y completa la lista.
    public Recorrido(int idRecorrido, String descripcion, boolean estado) {
        this.idRecorrido = idRecorrido;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Recorrido() {
    }

    public int getIdRecorrido() {
        return idRecorrido;
    }

    public void setIdRecorrido(int idRecorrido) {
        this.idRecorrido = idRecorrido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<ParadaRecorrido> getParadas() {
        return paradas;
    }

    public void setParadas(List<ParadaRecorrido> paradas) {
        
        this.paradas = new ArrayList<>();
        for (ParadaRecorrido parada : paradas) {
            agregarParada(parada);
        }
    }

    @Override
    public String toString() {
        return descripcion;
    }

    public void agregarParada(ParadaRecorrido parada) {
        if (parada != null) {
            parada.setRecorrido(this); // Cada parada queda asociada al recorrido.
            paradas.add(parada);
        }
    }

}
