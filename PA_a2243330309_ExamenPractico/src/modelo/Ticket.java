package modelo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    public static void generarTicket(Producto p, double total) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        
        String nombreArchivo = "ticket_" + p.getId() + ".txt";

        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println("======= TICKET DE VENTA =======");
            pw.println("Fecha: " + fecha);
            pw.println("-------------------------------");
            pw.println("Producto: " + p.getNombre());
            pw.println("ID:       " + p.getId());
            pw.println("Precio:   $" + p.getPrecio());
            pw.println("Cantidad: 1");
            pw.println("-------------------------------");
            pw.println("TOTAL:    $" + total);
            pw.println("===============================");
            pw.println("   Gracias por su compra");
            System.out.println("Ticket generado: " + nombreArchivo);
        } catch (Exception e) {
            System.err.println("Error al crear ticket: " + e.getMessage());
        }
    }
}