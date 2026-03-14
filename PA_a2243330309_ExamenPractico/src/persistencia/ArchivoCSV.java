package persistencia;

import modelo.Producto;
import java.io.*;
import java.util.ArrayList;

public class ArchivoCSV {
    private final String RUTA = "productos.csv";
    
    public void exportarCSV(ArrayList<Producto> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {
            for (Producto p : lista) {
                pw.println(p.getId() + "," + 
                           p.getNombre() + "," + 
                           p.getCategoria() + "," + 
                           p.getStock() + "," + 
                           p.getPrecio() + "," + 
                           p.getEstado());
            }
            System.out.println("Datos guardados en " + RUTA);
        } catch (IOException e) {
            System.err.println("Error al exportar: " + e.getMessage());
        }
    }

    public ArrayList<Producto> importarCSV() {
        ArrayList<Producto> lista = new ArrayList<>();
        File archivo = new File(RUTA);
        
        if (!archivo.exists()) {
            System.out.println("Archivo no encontrado, iniciando lista vacía.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos.length == 6) {
                    try {
                        lista.add(new Producto(
                            datos[0], // ID
                            datos[1], // Nombre
                            datos[2], // Categoría
                            Integer.parseInt(datos[3].trim()), // Stock
                            Double.parseDouble(datos[4].trim()), // Precio
                            datos[5]  // Estado
                        ));
                    } catch (NumberFormatException e) {
                        System.err.println("Error en formato de número en línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}