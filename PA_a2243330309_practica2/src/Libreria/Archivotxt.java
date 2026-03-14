package Libreria;
import java.io.*;
import java.util.ArrayList;

public class Archivotxt {
    private String nombreArchivo;

    public Archivotxt(String nombre) {
        this.nombreArchivo = nombre + ".txt";
    }

    public boolean existe() {
        File archivo = new File(this.nombreArchivo);
        return archivo.exists();
    }

    public ArrayList<String[]> cargar() {
        ArrayList<String[]> lineas = new ArrayList<>();
        if (this.existe()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (!linea.trim().isEmpty()) {
                        lineas.add(linea.split(","));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineas;
    }
}