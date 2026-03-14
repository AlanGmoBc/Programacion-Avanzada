package modelo;

public class Pregunta {
    private String enunciado;
    private String respuestaCorrecta;
    private String[] opciones;

    public Pregunta(String enunciado, String respuestaCorrecta, String[] opciones) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.opciones = opciones;
    }

    public String getEnunciado() { return enunciado; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public String[] getOpciones() { return opciones; }
}