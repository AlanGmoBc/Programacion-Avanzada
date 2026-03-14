package modelo;

import java.util.Collections;
import java.util.List;

public class Examen {
    private List<Pregunta> preguntas;
    private int indiceActual;
    private int aciertos; // <--- Nueva variable

    public Examen(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
        this.indiceActual = 0;
        this.aciertos = 0;
    }

    public void barajar() {
        Collections.shuffle(preguntas);
    }

    public Pregunta obtenerPreguntaActual() {
        return (indiceActual < preguntas.size()) ? preguntas.get(indiceActual) : null;
    }

    public void registrarAcierto() {
        this.aciertos++;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getTotalPreguntas() {
        return preguntas.size();
    }

    public boolean siguiente() {
        if (indiceActual < preguntas.size() - 1) {
            indiceActual++;
            return true;
        }
        return false;
    }
}