package vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import modelo.*;
import controlador.NewProductoControlador;
import persistencia.ReporteExcel;

public class FormularioProducto extends JInternalFrame {
    private JTextField txtFolio, txtNombre, txtCantidad, txtPrecio, txtImagen;
    private JComboBox<String> cbCategoria;
    private JLabel lblVisorImagen; 
    private JPanel pnlVisor;
    private JButton btnGuardar, btnSeleccionar, btnVerHistorial;
    private NewProductoControlador controlador;
    private List<NewProductos> listaTemporal; 

    public FormularioProducto() {
        super("Registro de Inventario con Imagen", true, true, true, true);
        controlador = new NewProductoControlador();
        listaTemporal = new ArrayList<>();
        
        setSize(850, 550); 
        setLayout(new BorderLayout(15, 15));

        // --- PANEL IZQUIERDO: DATOS ---
        JPanel pnlDatos = new JPanel(new GridLayout(9, 2, 8, 8)); 
        pnlDatos.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));

        pnlDatos.add(new JLabel(" Categoría:"));
        cbCategoria = new JComboBox<>(new String[]{"Abarrotes", "Lácteos", "Bebidas", "Frutas y Verduras", "Carnes", "Limpieza"});
        pnlDatos.add(cbCategoria);

        pnlDatos.add(new JLabel(" Folio:"));
        txtFolio = new JTextField();
        pnlDatos.add(txtFolio);

        pnlDatos.add(new JLabel(" Nombre:"));
        txtNombre = new JTextField();
        pnlDatos.add(txtNombre);

        pnlDatos.add(new JLabel(" Cantidad:"));
        txtCantidad = new JTextField();
        pnlDatos.add(txtCantidad);

        pnlDatos.add(new JLabel(" Precio:"));
        txtPrecio = new JTextField();
        pnlDatos.add(txtPrecio);

        pnlDatos.add(new JLabel(" Imagen:"));
        txtImagen = new JTextField("Sin imagen");
        txtImagen.setEditable(false);
        pnlDatos.add(txtImagen);

        btnSeleccionar = new JButton("Buscar Foto...");
        pnlDatos.add(btnSeleccionar);

        btnGuardar = new JButton("Guardar Todo");
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setForeground(Color.WHITE);
        pnlDatos.add(btnGuardar);
        
        btnVerHistorial = new JButton("Ver Resumen");
        btnVerHistorial.setBackground(new Color(52, 152, 219));
        btnVerHistorial.setForeground(Color.WHITE);
        pnlDatos.add(new JLabel(" Reporte Sesión:"));
        pnlDatos.add(btnVerHistorial);

        // --- PANEL DERECHO: VISOR (ESTE ES EL QUE FALLABA) ---
        pnlVisor = new JPanel(new BorderLayout());
        pnlVisor.setBorder(BorderFactory.createTitledBorder("Vista Previa"));
        pnlVisor.setBackground(Color.WHITE);
        
        lblVisorImagen = new JLabel("No hay imagen", SwingConstants.CENTER);
        pnlVisor.add(lblVisorImagen, BorderLayout.CENTER);
        
        add(pnlDatos, BorderLayout.WEST);
        add(pnlVisor, BorderLayout.CENTER);
        
        btnSeleccionar.addActionListener(e -> seleccionarImagen());
        btnGuardar.addActionListener(e -> guardarProceso());
        btnVerHistorial.addActionListener(e -> mostrarHistorial());
    }
    
    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File origen = fileChooser.getSelectedFile();
            try {
                File carpeta = new File("imagenes");
                if (!carpeta.exists()) carpeta.mkdirs();
                
                File destino = new File(carpeta, origen.getName());
                java.nio.file.Files.copy(origen.toPath(), destino.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                
                ImageIcon icono = new ImageIcon(destino.getAbsolutePath());
                
                Image img = icono.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                
                pnlVisor.removeAll();
                lblVisorImagen = new JLabel(new ImageIcon(img));
                pnlVisor.add(lblVisorImagen, BorderLayout.CENTER);
                
                // GOLPE DE GRACIA AL MOTOR GRÁFICO
                pnlVisor.revalidate();
                pnlVisor.repaint();
                pnlVisor.updateUI();
                
                txtImagen.setText("imagenes/" + destino.getName());
                System.out.println("PINTADO FORZADO Y REFRESCADO UI: " + destino.getName());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
            }
        }
    }

    private void guardarProceso() {
        try {
            ReporteExcel reporte = new ReporteExcel();
            String cat = (String) cbCategoria.getSelectedItem();
            int cant = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());
            
            NewProductos nuevo = null;
            switch (cat) {
                case "Abarrotes" -> nuevo = new Abarrotes(txtFolio.getText(), txtNombre.getText(), cant, precio, txtImagen.getText());
                case "Lácteos" -> nuevo = new Lacteos(txtFolio.getText(), txtNombre.getText(), cant, precio, txtImagen.getText());
                case "Bebidas" -> nuevo = new Bebidas(txtFolio.getText(), txtNombre.getText(), cant, precio, txtImagen.getText());
                case "Frutas y Verduras" -> nuevo = new FrutasVerduras(txtFolio.getText(), txtNombre.getText(), cant, precio, txtImagen.getText());
                case "Carnes" -> nuevo = new CarnesPescados(txtFolio.getText(), txtNombre.getText(), cant, precio, txtImagen.getText());
                case "Limpieza" -> nuevo = new LimpiezaHogar(txtFolio.getText(), txtNombre.getText(), cant, precio, txtImagen.getText());
            }

            if (nuevo != null) {
                controlador.guardarProductoComoJSON(nuevo);
                listaTemporal.add(nuevo);
                reporte.generarReporte(listaTemporal, "Reporte_Inventario");

                double total = 0;
                for (NewProductos p : listaTemporal) {
                    total += (p.getPrecio() * p.getCantidad());
                }

                JOptionPane.showMessageDialog(this, "Producto guardado. Total: $" + total);
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void mostrarHistorial() {
        if (listaTemporal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos registrados.");
            return;
        }
        System.out.println("Productos en sesión: " + listaTemporal.size());
    }

    private void limpiarCampos() {
        txtFolio.setText("");
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtImagen.setText("Sin imagen");
        pnlVisor.removeAll();
        lblVisorImagen = new JLabel("No hay imagen");
        pnlVisor.add(lblVisorImagen);
        pnlVisor.revalidate();
        pnlVisor.repaint();
    }
}