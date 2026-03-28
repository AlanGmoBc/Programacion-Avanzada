package modelo;

import java.util.List;

public class Evaluacion {
    private String id; // Ejemplo: Calculo_JuanC_4A
    private String asignatura;
    private String profesor;
    private String grupo;
    private List<String> alumnos;
    private double calificacionRubrica;
    private String observaciones;
    private String estado; // "Rojo", "Amarillo", "Verde"

    // Constructor, Getters y Setters
    public Evaluacion() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public List<String> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<String> alumnos) {
		this.alumnos = alumnos;
	}

	public double getCalificacionRubrica() {
		return calificacionRubrica;
	}

	public void setCalificacionRubrica(double calificacionRubrica) {
		this.calificacionRubrica = calificacionRubrica;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}