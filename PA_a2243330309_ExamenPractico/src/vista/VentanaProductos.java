package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaProductos extends JInternalFrame {
    public JTextField txtId, txtNombre, txtStock, txtPrecio;
    public JRadioButton rbActivo, rbInactivo;
    public JComboBox<String> cbCategoria; 
    public JButton btnGuardar, btnEliminar, btnLimpiar, btnBuscar, btnProcesarPago;
    public JLabel lblTotal;
    public JTable tablaProductos;
    public DefaultTableModel modeloTabla;
    public ButtonGroup grupoEstado;

    public VentanaProductos() {
        setTitle("Sistema de Gestión y Punto de Venta");
        setClosable(true);
        setMaximizable(true);
        setSize(1000, 600);
        
        inicializarComponentesBasicos();
        
        JTabbedPane pestañas = new JTabbedPane();
        
        pestañas.addTab("Gestión de Productos", crearPanelGestion());
        
        pestañas.addTab("Inventario Actual", crearPanelInventario());
        
        pestañas.addTab("Punto de Venta", crearPanelVentas());

        getContentPane().add(pestañas);
    }

    private void inicializarComponentesBasicos() {
        txtId = new JTextField(10);
        txtNombre = new JTextField(15);
        txtPrecio = new JTextField(10);
        txtStock = new JTextField(10);
        cbCategoria = new JComboBox<>(new String[]{"General", "Electrónica", "Alimentos", "Limpieza"});
        rbActivo = new JRadioButton("Activo", true);
        rbInactivo = new JRadioButton("Inactivo");
        grupoEstado = new ButtonGroup();
        grupoEstado.add(rbActivo); grupoEstado.add(rbInactivo);
        
        btnGuardar = new JButton("Guardar / Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar");
        btnProcesarPago = new JButton("PROCESAR PAGO");
        lblTotal = new JLabel("TOTAL: $0.00");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Categoría", "Stock", "Precio", "Estado"}, 0);
        tablaProductos = new JTable(modeloTabla);
    }

    private JPanel crearPanelGestion() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; panel.add(txtId, gbc);
        gbc.gridx = 2; panel.add(btnBuscar, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; panel.add(txtNombre, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; panel.add(cbCategoria, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; panel.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Stock:"), gbc);
        gbc.gridx = 1; panel.add(txtStock, gbc);

        JPanel pEstado = new JPanel(); pEstado.add(rbActivo); pEstado.add(rbInactivo);
        gbc.gridx = 1; gbc.gridy = 5; panel.add(pEstado, gbc);

        JPanel pBotones = new JPanel(); pBotones.add(btnGuardar); pBotones.add(btnEliminar); pBotones.add(btnLimpiar);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3; panel.add(pBotones, gbc);

        return panel;
    }

    private JPanel crearPanelInventario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelVentas() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblTotal, gbc);
        
        btnProcesarPago.setPreferredSize(new Dimension(200, 50));
        btnProcesarPago.setBackground(new Color(0, 153, 76));
        btnProcesarPago.setForeground(Color.WHITE);
        gbc.gridy = 1; panel.add(btnProcesarPago, gbc);

        return panel;
    }
}