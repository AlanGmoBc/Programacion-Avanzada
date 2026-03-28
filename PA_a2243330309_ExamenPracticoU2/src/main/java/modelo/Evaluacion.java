package modelo;

import java.util.ArrayList;
import java.util.List;

public class Evaluacion {
    public String id;
    public String asignatura;
    public String profesor;
    public String grupo;
    public String observaciones;
    public double promedio;
    public List<String> alumnos = new ArrayList<>();
    
    public Evaluacion(String id, String asignatura, String profesor, String grupo) {
        this.id = id;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.grupo = grupo;
    }
    
    public Evaluacion() {}
}