package Librerias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Libreria {

    public static boolean ExisteArchivo(String narchivo) {
        File archivo = new File(narchivo);
        return archivo.exists();
    }
    
    public static ArrayList<String[]> LeerDatosCSV(String narchivo) {
        ArrayList<String[]> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(narchivo))) {
            String linea;
            boolean esPrimeraLinea = true; 
            while ((linea = lector.readLine()) != null) {
                if (esPrimeraLinea) { 
                    esPrimeraLinea = false;
                    continue;
                }
                
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                    for (int i = 0; i < datos.length; i++) {
                        datos[i] = datos[i].replace("\"", "").trim();
                    }
                    
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return lista;
    }

    public static void GuardarCSV(String narchivo, String contenido) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(narchivo, false))) {
            escritor.write(contenido);
            System.out.println("Datos guardados exitosamente en: " + narchivo);
        } catch (IOException e) {
            System.out.println("Error crítico al guardar el archivo: " + e.getMessage());
        }
    }
}