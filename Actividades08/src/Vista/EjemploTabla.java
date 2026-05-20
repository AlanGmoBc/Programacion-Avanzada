package Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

public class EjemploTabla extends JFrame {
    private JTable table;
    private JList<String> listaCarreras;
    private JButton btnGuardar;

    public EjemploTabla() {
        setTitle("Filtro por Carrera - Sistema Escolar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Lista de Carreras a la izquierda
        listaCarreras = new JList<>();
        JScrollPane scrollLista = new JScrollPane(listaCarreras);
        scrollLista.setPreferredSize(new Dimension(250, 0));
        scrollLista.setBorder(BorderFactory.createTitledBorder("Seleccione Carrera"));
        contentPane.add(scrollLista, BorderLayout.WEST);

        // Tabla en el centro
        table = new JTable();
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

        // Botón Guardar abajo
        btnGuardar = new JButton("Guardar Cambios");
        contentPane.add(btnGuardar, BorderLayout.SOUTH);
    }

    public JTable getTable() { return table; }
    public JList<String> getListaCarreras() { return listaCarreras; }
    
    public void conectarEventos(ListSelectionListener sel, java.awt.event.ActionListener save) {
        listaCarreras.addListSelectionListener(sel);
        btnGuardar.addActionListener(save);
    }
}