package Parte2;

import javax.swing.*;
import java.awt.event.*;

public class Practica04 extends JFrame {
    public Practica04() {
        setTitle("Sistema de Gestión - Menú Principal");
        setSize(800, 600);

        JMenuBar barra = new JMenuBar();
        JMenu mConfig = new JMenu("Configuración");
        JMenuItem itemCat = new JMenuItem("Categorías");
        JMenuItem itemIns = new JMenuItem("Insumos");
        JMenuItem itemSalir = new JMenuItem("Salir");

        itemCat.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abriendo Categorías...");
        });

        itemIns.addActionListener(e -> {
            new Practica02_a();
        });

        itemSalir.addActionListener(e -> System.exit(0));

        mConfig.add(itemCat);
        mConfig.add(itemIns);
        mConfig.addSeparator();
        mConfig.add(itemSalir);
        barra.add(mConfig);

        setJMenuBar(barra);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}