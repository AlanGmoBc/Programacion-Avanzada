package controlador;

import modelo.Producto;
import persistencia.ArchivoCSV;
import vista.VentanaProductos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductoControlador {
    private ArrayList<Producto> listaProductos;
    private VentanaProductos vista;
    private ArchivoCSV persistencia;

    public ProductoControlador(VentanaProductos vista) {
        this.vista = vista;
        this.persistencia = new ArchivoCSV();
        this.listaProductos = persistencia.importarCSV();
        
        initEvents();
        actualizarTabla();
    }
    
    private void initEvents() {
        // Pestaña Gestión
        vista.btnGuardar.addActionListener(e -> guardarProducto());
        vista.btnEliminar.addActionListener(e -> eliminarProducto());
        vista.btnBuscar.addActionListener(e -> consultarProducto());
        vista.btnLimpiar.addActionListener(e -> limpiarCampos());

        // Conexión del botón de pago
        vista.btnProcesarPago.addActionListener(e -> finalizarVenta());
    }

    // MÉTODO ACTUALIZADO CON GENERACIÓN DE TICKET
    private void finalizarVenta() {
        String id = vista.txtId.getText().trim();
        Producto p = buscarPorId(id);

        if (p != null && p.getStock() > 0) {
            // 1. Restar stock
            p.setStock(p.getStock() - 1); 
            
            // 2. Guardar cambio en el CSV
            persistencia.exportarCSV(listaProductos); 
            
            // 3. GENERAR EL TICKET
            modelo.Ticket.generarTicket(p, p.getPrecio());
            
            // 4. Actualizar interfaz
            actualizarTabla();
            JOptionPane.showMessageDialog(vista, "Venta realizada. Ticket generado en la carpeta del proyecto.");
        } else {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado o sin stock.");
        }
    }

    private void consultarProducto() {
        String idBusqueda = vista.txtId.getText().trim();
        if (idBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese un ID para buscar.");
            return;
        }

        Producto encontrado = buscarPorId(idBusqueda);
        if (encontrado != null) {
            vista.txtNombre.setText(encontrado.getNombre());
            vista.txtPrecio.setText(String.valueOf(encontrado.getPrecio()));
            vista.txtStock.setText(String.valueOf(encontrado.getStock()));
            vista.cbCategoria.setSelectedItem(encontrado.getCategoria());
            
            if (encontrado.getEstado().equals("Activo")) vista.rbActivo.setSelected(true);
            else vista.rbInactivo.setSelected(true);
        } else {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado.");
        }
    }

    private void guardarProducto() {
        try {
            String id = vista.txtId.getText().trim();
            String nom = vista.txtNombre.getText().trim();
            String cat = vista.cbCategoria.getSelectedItem().toString(); 
            int stock = Integer.parseInt(vista.txtStock.getText());
            double pre = Double.parseDouble(vista.txtPrecio.getText());
            String estado = vista.rbActivo.isSelected() ? "Activo" : "Inactivo";

            if (id.isEmpty() || nom.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "ID y Nombre son obligatorios.");
                return;
            }

            Producto existente = buscarPorId(id);
            if (existente != null) {
                existente.setNombre(nom);
                existente.setPrecio(pre);
                existente.setStock(stock);
                existente.setCategoria(cat);
                existente.setEstado(estado);
            } else {
                listaProductos.add(new Producto(id, nom, cat, stock, pre, estado));
            }
            
            persistencia.exportarCSV(listaProductos); 
            
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Guardado exitosamente en CSV.");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio y Stock deben ser números.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        String id = vista.txtId.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el ID del producto a eliminar.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, "¿Eliminar producto " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            listaProductos.removeIf(p -> p.getId().equals(id));
            persistencia.exportarCSV(listaProductos);
            actualizarTabla();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.cbCategoria.setSelectedIndex(0);
        vista.rbActivo.setSelected(true);
    }

    private void actualizarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (Producto p : listaProductos) {
            vista.modeloTabla.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getCategoria(), p.getStock(), p.getPrecio(), p.getEstado()
            });
        }
    }

    private Producto buscarPorId(String id) {
        for (Producto p : listaProductos) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }
}