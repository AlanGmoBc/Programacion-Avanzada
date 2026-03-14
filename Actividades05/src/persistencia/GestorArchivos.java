package persistencia;

import modelo.Pregunta;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {
    public static List<Pregunta> cargarDesdeCSV(File archivo) throws Exception {
        List<Pregunta> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 6) continue;

                String enunciado = datos[0];
                String correcta = datos[1];
                String[] opciones = {datos[2], datos[3], datos[4], datos[5]};
                lista.add(new Pregunta(enunciado, correcta, opciones));
            }
        }
        if (lista.size() < 5) {
            throw new Exception("El archivo debe tener al menos 5 preguntas.");
        }
        return lista;
    }
}