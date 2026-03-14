package Parte2_Vista;
import javax.swing.*;
import java.awt.*;
import Libreria.Librerias;

public class Practica03_b extends JInternalFrame {
    public Practica03_b(Practica03_a principal) {
        super("Mantenimiento de Insumos", true, true, true, true);
        Librerias lib = new Librerias();
        lib.menuspadre(principal.barra, false);

        setSize(600, 400);
        JTable tabla = new JTable(principal.listaI.getmodelo(principal.listaC));
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        JButton btnNuevo = new JButton("Agregar Nuevo Insumo");
        btnNuevo.addActionListener(e -> {
            new Practica03_d(principal, true).setVisible(true);
            tabla.setModel(principal.listaI.getmodelo(principal.listaC));
        });
        add(btnNuevo, BorderLayout.SOUTH);

        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
                lib.menuspadre(principal.barra, true);
            }
        });
    }
}