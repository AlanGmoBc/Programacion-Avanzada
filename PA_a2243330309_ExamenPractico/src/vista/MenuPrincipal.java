package vista;

import controlador.ProductoControlador;
import controlador.NewProductoControlador;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    private JDesktopPane desktopPane;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Inventario - Professional Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("logo.png").getImage());
        
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.GRAY);
        add(desktopPane, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuCatalogos = new JMenu("Catálogos");
        // --- NUEVA SECCIÓN DE MENÚ ---
        JMenu menuReportes = new JMenu("Reportes"); 

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));

        JMenuItem itemProductos = new JMenuItem("Gestión de Productos");
        itemProductos.addActionListener(e -> abrirVentanaProductos());

        // --- NUEVO ÍTEM PARA EL FORMULARIO DE EXCEL/JSON ---
        JMenuItem itemNuevoRegistro = new JMenuItem("Nuevo Registro (JSON/Excel)");
        itemNuevoRegistro.addActionListener(e -> abrirNuevoFormulario());

        menuArchivo.add(itemSalir);
        menuCatalogos.add(itemProductos);
        // --- AÑADIMOS LAS NUEVAS OPCIONES ---
        menuReportes.add(itemNuevoRegistro);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuCatalogos);
        menuBar.add(menuReportes);
        
        setJMenuBar(menuBar);
    }
    
    private void abrirVentanaProductos() {
        VentanaProductos vista = new VentanaProductos();
        new ProductoControlador(vista);
        desktopPane.add(vista);
        vista.setVisible(true);
    }

    // --- NUEVO MÉTODO PARA ABRIR EL FORMULARIO QUE CREAMOS ---
    private void abrirNuevoFormulario() {
        FormularioProducto nuevaVista = new FormularioProducto();
        desktopPane.add(nuevaVista);
        nuevaVista.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}