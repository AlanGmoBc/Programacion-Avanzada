package vista;

import controlador.ProductoControlador;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    private JDesktopPane desktopPane;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Inventario - Professional Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.GRAY);
        add(desktopPane, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenu menuCatalogos = new JMenu("Catálogos");

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(e -> System.exit(0));

        JMenuItem itemProductos = new JMenuItem("Gestión de Productos");
        itemProductos.addActionListener(e -> abrirVentanaProductos());

        menuArchivo.add(itemSalir);
        menuCatalogos.add(itemProductos);
        menuBar.add(menuArchivo);
        menuBar.add(menuCatalogos);
        setJMenuBar(menuBar);
    }

    private void abrirVentanaProductos() {
        VentanaProductos vista = new VentanaProductos();
        
        new ProductoControlador(vista);
        
        desktopPane.add(vista);
        vista.setVisible(true);
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