package principal;

import controlador.ProductoControlador;
import vista.VentanaProductos;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
    	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        // Ejecutar la interfaz
        SwingUtilities.invokeLater(() -> {
            // 1. Creamos el marco principal (La ventana de Windows)
            JFrame framePrincipal = new JFrame("Sistema de Gestión Integral");
            framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            framePrincipal.setSize(1100, 700);
            framePrincipal.setLocationRelativeTo(null); // Centra la ventana

            // 2. Creamos la vista (donde están las 3 pestañas)
            VentanaProductos vistaPestañas = new VentanaProductos();
            
            // IMPORTANTE: Como VentanaProductos es un JInternalFrame, 
            // le quitamos el borde para que parezca un panel normal dentro del JFrame
            vistaPestañas.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)vistaPestañas.getUI()).setNorthPane(null);
            vistaPestañas.setVisible(true);

            // 3. Inicializamos el controlador pasándole la vista
            new ProductoControlador(vistaPestañas);

            // 4. Agregamos la vista al marco y mostramos
            framePrincipal.add(vistaPestañas);
            framePrincipal.setVisible(true);
        });
    }
}