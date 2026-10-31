
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Colectivo {
    private int idColectivo;
    private String matricula;
    private String marca;
    private String modelo;
    private int capacidad;
    private LocalDate caducidad;
    private boolean estado;
    private String chofer;
    
    private List<Asiento> asientos;
    
    // utilizamos este contructor para crear un nuevo colectivo para guardarlo en la base de datos
    // la base de datos le genera el id
    public Colectivo(String matricula, String marca, String modelo, 
            int capacidad, LocalDate caducidad, boolean estado, String chofer) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.caducidad = caducidad;
        this.estado = estado;
        this.chofer = chofer;
        
        // COMPOSICIÓN. Lista incializada al crearse Colectivo
        this.asientos = new ArrayList<>();
        
        for (int i = 1; i <= capacidad; i++) {
            asientos.add(new Asiento(this, i));
        }
        
    }
    
    // lo utilizamos cuando ya tenemos un colectivo y queremos recuperar la informacion

    public Colectivo(int idColectivo, String matricula, String marca, String modelo, int capacidad, LocalDate caducidad, boolean estado, String chofer) {
        this.idColectivo = idColectivo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.caducidad = caducidad;
        this.estado = estado;
        this.chofer = chofer;
     
        // COMPOSICIÓN. Lista inicializada para recuperar los asientos existentes.
        this.asientos = new ArrayList<>();

    }
    
    
   // lo creamos sin atributos y los completamos con los setters. 

    public Colectivo() {
        
        // COMPOSICIÓN. Lista incializada al crearse Colectivo
        this.asientos = new ArrayList<>();

    }

    public void agregarAsiento(Asiento asiento) {
        asientos.add(asiento);
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

    
    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    
    @Override
    public String toString() {
        return matricula + "-" + marca +"-" + modelo;
    }
    
    public List<Asiento> getAsientos() {
        return asientos;
    }
}