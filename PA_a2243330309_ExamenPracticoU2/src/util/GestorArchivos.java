package util;

import com.google.gson.Gson; // Debes agregar el JAR de GSON
import java.io.FileWriter;

public class GestorArchivos {
    public static void guardarJSON(Object data, String ruta) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}