package vista;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

public class VentanaPrincipal extends JFrame {
    public JTextField txtAsignatura, txtProfesor, txtGrupo;
    public JTextArea txtObservaciones;
    public JButton btnGuardar, btnGenerarExcel;
    public JPanel panelEstado; // El semáforo

    public VentanaPrincipal() {
        // Configurar FlatLaf
        try { UIManager.setLookAndFeel(new FlatLightLaf()); } catch (Exception e) {}
        
        setTitle("SAE-AE - Evaluación de Atributos");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de Datos (Módulo 1)
        JPanel pnlDatos = new JPanel(new GridLayout(4, 2));
        pnlDatos.add(new JLabel("Asignatura:")); txtAsignatura = new JTextField(); pnlDatos.add(txtAsignatura);
        pnlDatos.add(new JLabel("Profesor:")); txtProfesor = new JTextField(); pnlDatos.add(txtProfesor);
        pnlDatos.add(new JLabel("Grupo:")); txtGrupo = new JTextField(); pnlDatos.add(txtGrupo);
        
        // Semáforo (Indicador visual)
        panelEstado = new JPanel();
        panelEstado.setBackground(Color.RED); 
        panelEstado.setPreferredSize(new Dimension(20, 20));
        pnlDatos.add(new JLabel("Estado:")); pnlDatos.add(panelEstado);

        add(pnlDatos, BorderLayout.NORTH);
        
        // Módulo 2: JTable Dinámica (Simulación)
        String[] columnas = {"Criterio", "Excelente", "Bueno", "Regular", "Insuficiente"};
        Object[][] datos = { {"Indicador 1", false, false, false, false} };
        JTable tablaRubrica = new JTable(datos, columnas);
        add(new JScrollPane(tablaRubrica), BorderLayout.CENTER);

        // Botones
        JPanel pnlBotones = new JPanel();
        btnGuardar = new JButton("Guardar JSON");
        btnGenerarExcel = new JButton("Exportar Excel");
        pnlBotones.add(btnGuardar); pnlBotones.add(btnGenerarExcel);
        add(pnlBotones, BorderLayout.SOUTH);
    }
}