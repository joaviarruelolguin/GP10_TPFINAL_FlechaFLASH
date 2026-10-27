
package modelo;

import java.time.LocalDate;

public class Colectivo {
    private int idColectivo;
    private String matricula;
    private String marca;
    private String modelo;
    private int capacidad;
    private LocalDate caducidad;
    private boolean estado;

    
    // utilizamos este contructor para crear un nuevo colectivo para guardarlo en la base de datos
    // la base de datos le genera el id
    public Colectivo(String matricula, String marca, String modelo, 
            int capacidad, LocalDate caducidad, boolean estado) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.caducidad = caducidad;
        this.estado = estado;
    }
    
    // lo utilizamos cuando ya tenemos un colectivo y queremos recuperar la informacion

    public Colectivo(int idColectivo, String matricula, String marca, String modelo, int capacidad, LocalDate caducidad, boolean estado) {
        this.idColectivo = idColectivo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.caducidad = caducidad;
        this.estado = estado;
    }
    
    
   // lo creamos sin atributos y los completamos con los setters

    public Colectivo() {
    }

    public int getIdColectivo() {
        return idColectivo;
    }

    public void setIdColectivo(int idColectivo) {
        this.idColectivo = idColectivo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return matricula + "-" + marca +"-" + modelo;
    }
    
    
    
    
    
    
    
    
    
    
}
