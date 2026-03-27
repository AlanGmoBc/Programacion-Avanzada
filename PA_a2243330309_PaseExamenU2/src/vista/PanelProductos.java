package vista;
import controlador.InventarioControlador;
import modelo.Producto;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelProductos extends JPanel {
    JTextField txtSku = new JTextField(), txtNom = new JTextField(), txtPre = new JTextField(), 
               txtGan = new JTextField(), txtStk = new JTextField(), txtProv = new JTextField(); // Nuevo: Proveedor
    
    JComboBox<String> cbCat = new JComboBox<>(new String[]{
        "Abarrotes", "Lácteos", "Bebidas", "Carnes y Pescados", "Frutas y Verduras", "Limpieza", "Higiene Personal"
    });
    
    JComboBox<String> cbUni = new JComboBox<>(new String[]{"Kg", "Pieza", "Litro", "Paquete", "Caja"});
    
    // NUEVO: Tipo de Transporte
    JComboBox<String> cbTrans = new JComboBox<>(new String[]{
        "Camión de Reparto", "Camioneta", "Camión Semi", "Tráiler", "Motocicleta"
    });

    JLabel lblFoto = new JLabel("VISTA PREVIA", 0);
    File archivoFoto;
    InventarioControlador ctrl = new InventarioControlador();

    public PanelProductos() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Formulario extendido a 9 filas para los nuevos campos
        JPanel pForm = new JPanel(new GridLayout(9, 2, 10, 10));
        pForm.add(new JLabel("SKU:")); pForm.add(txtSku);
        pForm.add(new JLabel("Nombre:")); pForm.add(txtNom);
        pForm.add(new JLabel("Categoría:")); pForm.add(cbCat);
        pForm.add(new JLabel("Precio Compra ($):")); pForm.add(txtPre);
        pForm.add(new JLabel("Ganancia (%):")); pForm.add(txtGan);
        pForm.add(new JLabel("Stock:")); pForm.add(txtStk);
        pForm.add(new JLabel("Unidad:")); pForm.add(cbUni);
        pForm.add(new JLabel("Proveedor:")); pForm.add(txtProv); // Nuevo
        pForm.add(new JLabel("Tipo Transporte:")); pForm.add(cbTrans); // Nuevo

        JPanel pImg = new JPanel(new BorderLayout(5, 5));
        lblFoto.setPreferredSize(new Dimension(250, 250));
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JButton btnFoto = new JButton("Seleccionar Imagen");
        pImg.add(lblFoto, BorderLayout.CENTER); pImg.add(btnFoto, BorderLayout.SOUTH);

        JPanel pCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        pCentro.add(pForm); pCentro.add(pImg);

        // BOTONES GRANDES ABAJO
        JPanel pBtns = new JPanel(new GridLayout(1, 6, 10, 10));
        JButton btnLimpiar = new JButton("NUEVO / LIMPIAR");
        JButton btnReporte = new JButton("GANANCIAS");
        JButton btnVerInv = new JButton("INVENTARIO");
        JButton btnModificar = new JButton("MODIFICAR");
        JButton btnFinalizar = new JButton("FIN DÍA");
        JButton btnGuardar = new JButton("GUARDAR PRODUCTO");

        pBtns.add(btnLimpiar); pBtns.add(btnReporte); pBtns.add(btnVerInv);
        pBtns.add(btnModificar); pBtns.add(btnFinalizar); pBtns.add(btnGuardar);

        add(pCentro, BorderLayout.CENTER);
        add(pBtns, BorderLayout.SOUTH);

        // EVENTOS
        btnGuardar.addActionListener(e -> {
            try {
                Producto p = obtenerProducto();
                ctrl.guardar(p, archivoFoto);
                JOptionPane.showMessageDialog(this, "Guardado con Proveedor.");
                limpiar();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Datos inválidos"); }
        });

        btnModificar.addActionListener(e -> {
            try {
                ctrl.modificar(obtenerProducto());
                limpiar();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error al modificar"); }
        });

        btnFoto.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == 0) {
                archivoFoto = fc.getSelectedFile();
                lblFoto.setIcon(new ImageIcon(new ImageIcon(archivoFoto.getPath()).getImage().getScaledInstance(250, 250, 4)));
                lblFoto.setText("");
            }
        });

        btnLimpiar.addActionListener(e -> limpiar());
        btnVerInv.addActionListener(e -> ctrl.mostrarInventario());
    }

    private Producto obtenerProducto() {
        return new Producto(txtSku.getText(), txtNom.getText(), cbCat.getSelectedItem().toString(), 
               Double.parseDouble(txtPre.getText()), Double.parseDouble(txtGan.getText()), 
               Integer.parseInt(txtStk.getText()), cbUni.getSelectedItem().toString(),
               txtProv.getText(), cbTrans.getSelectedItem().toString()); // Nuevos parámetros
    }

    private void limpiar() {
        txtSku.setText(""); txtNom.setText(""); txtPre.setText(""); txtGan.setText(""); 
        txtStk.setText(""); txtProv.setText(""); cbCat.setSelectedIndex(0);
        cbTrans.setSelectedIndex(0); lblFoto.setIcon(null); archivoFoto = null;
    }
}