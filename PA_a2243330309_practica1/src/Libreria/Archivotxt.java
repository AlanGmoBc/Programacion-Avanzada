package Libreria;
import java.io.*;
import java.util.ArrayList;

public class Archivotxt {
    private String nombre;
    public Archivotxt(String nombre) { this.nombre = nombre + ".txt"; }
    public boolean existe() { return new File(nombre).exists(); }
    public ArrayList<String[]> cargar() {
        ArrayList<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombre))) {
            String linea;
            while ((linea = br.readLine()) != null) { datos.add(linea.split(",")); }
        } catch (Exception e) { return null; }
        return datos;
    }
    public void guardar(String contenido) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombre))) {
            pw.print(contenido);
        } catch (Exception e) { e.printStackTrace(); }
    }
}