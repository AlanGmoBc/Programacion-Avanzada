package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

public class VentanaPrincipal extends JFrame {
    public JTextField txtAsignatura, txtProfesor, txtGrupo;
    public JTable tablaRubrica;
    public DefaultTableModel modeloTabla;
    public JButton btnGuardar, btnSeleccionarCarpeta;
    public JPanel pnlSemaforo;
    public JLabel lblRutaSeleccionada;

    public VentanaPrincipal() {
        FlatLightLaf.setup();
        
        setTitle("SAE-AE - Sistema de Evaluación de Atributos");
        setSize(850, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- PANEL NORTE: Datos generales ---
        JPanel pnlNorte = new JPanel(new GridLayout(4, 2, 5, 5));
        pnlNorte.setBorder(BorderFactory.createTitledBorder("Datos del Instrumento"));
        
        txtAsignatura = new JTextField();
        txtProfesor = new JTextField();
        txtGrupo = new JTextField();
        btnSeleccionarCarpeta = new JButton("Elegir Carpeta de Guardado...");
        lblRutaSeleccionada = new JLabel("Carpeta: (Raíz del proyecto)");

        pnlNorte.add(new JLabel(" Asignatura:")); pnlNorte.add(txtAsignatura);
        pnlNorte.add(new JLabel(" Profesor:")); pnlNorte.add(txtProfesor);
        pnlNorte.add(new JLabel(" Grupo:")); pnlNorte.add(txtGrupo);
        pnlNorte.add(btnSeleccionarCarpeta); pnlNorte.add(lblRutaSeleccionada);
        add(pnlNorte, BorderLayout.NORTH);

        // --- PANEL CENTRO: JTable Dinámica ---
        String[] columnas = {"Nombre del Alumno", "Criterio 1", "Criterio 2", "Criterio 3", "Promedio"};
        
        // CORRECCIÓN: Inicialización limpia del modelo
        modeloTabla = new DefaultTableModel(columnas, 4) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 4; 
            }
        };
        
        tablaRubrica = new JTable(modeloTabla);
        
        DefaultTableCellRenderer renderizadorPromedio = new DefaultTableCellRenderer();
        renderizadorPromedio.setHorizontalAlignment(JLabel.CENTER);
        renderizadorPromedio.setBackground(new Color(240, 240, 240)); 
        tablaRubrica.getColumnModel().getColumn(4).setCellRenderer(renderizadorPromedio);
        
        add(new JScrollPane(tablaRubrica), BorderLayout.CENTER);

        // --- PANEL SUR: Botones y Semáforo ---
        JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar y Exportar Excel");
        pnlSemaforo = new JPanel();
        pnlSemaforo.setPreferredSize(new Dimension(25, 25));
        pnlSemaforo.setBackground(Color.RED); 

        pnlSur.add(new JLabel("Estado: "));
        pnlSur.add(pnlSemaforo);
        pnlSur.add(btnGuardar);
        add(pnlSur, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        
     // Panel para la Lista de Cotejo (Módulo 3)
        JPanel pnlCotejo = new JPanel(new GridLayout(0, 1));
        pnlCotejo.setBorder(BorderFactory.createTitledBorder("Lista de Cotejo"));
        JCheckBox chk1 = new JCheckBox("Indicador 1: Cumple formato");
        JCheckBox chk2 = new JCheckBox("Indicador 2: Código limpio");
        JCheckBox chk3 = new JCheckBox("Indicador 3: Funciona Excel");
        JButton btnMarcarTodo = new JButton("Marcar Todos");

        btnMarcarTodo.addActionListener(e -> {
            chk1.setSelected(true);
            chk2.setSelected(true);
            chk3.setSelected(true);
        });

        pnlCotejo.add(chk1); pnlCotejo.add(chk2); pnlCotejo.add(chk3); pnlCotejo.add(btnMarcarTodo);
        add(pnlCotejo, BorderLayout.WEST); // Ponlo a la izquierda
    }
}