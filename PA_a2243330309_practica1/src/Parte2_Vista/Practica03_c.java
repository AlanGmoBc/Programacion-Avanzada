package Parte2_Vista;

import javax.swing.*;
import java.awt.*;
import Libreria.Librerias;

public class Practica03_c extends JInternalFrame {
    public Practica03_c(Practica03_a principal) {
        super("Mantenimiento de Categorias", true, true, true, true);
        Librerias lib = new Librerias();
        lib.menuspadre(principal.barra, false);
        
        setSize(300, 450);
        JList listaGrafica = new JList(principal.listaC.generarModeloCategorias());
        add(new JScrollPane(listaGrafica), BorderLayout.CENTER);

        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent e) {
                lib.menuspadre(principal.barra, true);
            }
        });
    }
}