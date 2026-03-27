package controlador;
import modelo.Producto;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InventarioControlador {
    
    public void guardar(Producto p, File foto) {
        try (FileWriter fw = new FileWriter("productos.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            // Añadimos proveedor y transporte al final de la línea
            pw.println(p.sku + "," + p.nombre + "," + p.categoria + "," + p.precio + "," + 
                       p.ganancia + "," + p.stock + "," + p.unidad + "," + p.proveedor + "," + p.transporte);
        } catch (Exception e) { e.printStackTrace(); }

        if (foto != null) {
            try {
                File carpeta = new File("imagenes");
                if (!carpeta.exists()) carpeta.mkdir();
                Files.copy(foto.toPath(), Paths.get("imagenes", p.sku + ".png"), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void modificar(Producto p) {
        File archivo = new File("productos.csv");
        StringBuilder contenidoNuevo = new StringBuilder();
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(p.sku)) {
                    contenidoNuevo.append(p.sku).append(",").append(p.nombre).append(",")
                                  .append(p.categoria).append(",").append(p.precio).append(",")
                                  .append(p.ganancia).append(",").append(p.stock).append(",")
                                  .append(p.unidad).append(",").append(p.proveedor).append(",")
                                  .append(p.transporte).append("\n");
                    encontrado = true;
                } else {
                    contenidoNuevo.append(linea).append("\n");
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        if (encontrado) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
                pw.print(contenidoNuevo.toString());
                JOptionPane.showMessageDialog(null, "Producto y Proveedor actualizados.");
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public void mostrarInventario() {
        String[] col = {"SKU", "Nombre", "Cat", "Precio", "Gan", "Stock", "Unidad", "Proveedor", "Transporte"};
        DefaultTableModel mod = new DefaultTableModel(col, 0);
        try (BufferedReader br = new BufferedReader(new FileReader("productos.csv"))) {
            String l;
            while ((l = br.readLine()) != null) mod.addRow(l.split(","));
            JTable t = new JTable(mod);
            JFrame f = new JFrame("Inventario Completo");
            f.add(new JScrollPane(t)); f.setSize(900, 400); f.setLocationRelativeTo(null); f.setVisible(true);
        } catch (Exception e) { JOptionPane.showMessageDialog(null, "Error al leer."); }
    }
}