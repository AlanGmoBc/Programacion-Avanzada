package Modelo;

public class Asignatura {
    private String carrera;
    private String planEstudio;
    private String materia;
    private String profesor;
    private String alumno;
    private String grupo;

    public Asignatura(String[] fila) {
        if (fila.length >= 15) {
            this.carrera = fila[1];
            this.grupo = fila[2];
            this.profesor = fila[3];
            this.materia = fila[4];
            this.alumno = fila[9];
            this.planEstudio = fila[14];
        }
    }

    public String getInfoAgrupada() {
        return "Materia: " + materia + " | Prof: " + profesor + " | Alumno: " + alumno + " | Grupo: " + grupo;
    }
}