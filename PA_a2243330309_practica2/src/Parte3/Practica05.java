package Parte3;

import javax.swing.*;
import java.awt.*;

public class Practica05 extends JFrame {
    private JDesktopPane escritorio;

    public Practica05() {
        setTitle("Contenedor Principal MDI");
        setSize(1000, 700);
        
        escritorio = new JDesktopPane();
        escritorio.setBackground(Color.GRAY);
        add(escritorio, BorderLayout.CENTER);

        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem itemInterna = new JMenuItem("Nueva Ventana Insumos");
        
        itemInterna.addActionListener(e -> {
            // Creamos un JInternalFrame
            JInternalFrame interna = new JInternalFrame("Insumos Interno", true, true, true, true);
            interna.setSize(400, 300);
            interna.add(new JLabel("Contenido de la práctica aquí"));
            interna.setVisible(true);
            escritorio.add(interna);
        });

        menu.add(itemInterna);
        barra.add(menu);
        setJMenuBar(barra);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Practica05();
    }
}