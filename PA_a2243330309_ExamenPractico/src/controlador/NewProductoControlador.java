package controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelo.NewProductos;
import java.io.FileWriter;
import java.io.IOException;

public class NewProductoControlador {

    // 1. Configuramos Gson para que el archivo JSON se vea ordenado (Pretty Printing)
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public void guardarProductoComoJSON(NewProductos producto) {
        // Obtenemos el folio para el nombre del archivo
        String nombreArchivo = producto.getFolio() + ".json";

        // Intentamos escribir el archivo en la carpeta del proyecto
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            
            // Convertimos el objeto 'producto' a formato JSON y lo escribimos
            gson.toJson(producto, writer);
            
            System.out.println("Éxito: Se ha generado el archivo " + nombreArchivo);
            
        } catch (IOException e) {
            System.err.println("Error al guardar el JSON: " + e.getMessage());
        }
    }
}