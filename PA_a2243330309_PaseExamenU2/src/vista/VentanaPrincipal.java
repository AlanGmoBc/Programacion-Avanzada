package vista;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Sistema Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new PanelProductos()); // Aqui cargamos el panel rústico
        setSize(400, 500);
        setLocationRelativeTo(null);
    }
}