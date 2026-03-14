package Parte3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Modelo.*;

public class IfrmInsumo extends JInternalFrame {
    private JTable tabla;
    private JButton btnAgregar, btnEliminar, btnModificar;
    private ListaInsumos li;
    private ListaCategorias lc;

    public IfrmInsumo(ListaInsumos li, ListaCategorias lc) {
        super("Administración de Insumos", true, true, true, true);
        this.li = li;
        this.lc = lc;
        
        setSize(600, 400);
        setLayout(new BorderLayout());

        tabla = new JTable(li.getmodelo(lc));
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Nuevo Insumo");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            Frame padre = (Frame) SwingUtilities.getWindowAncestor(this);
            DlgInsumo diag = new DlgInsumo(padre, lc); 
            diag.setVisible(true);
            
            if(diag.isGuardado()) {
                li.agregarInsumo(diag.getInsumo());
                actualizarTabla();
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if(fila != -1) {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres borrarlo?");
                if(resp == JOptionPane.YES_OPTION) {
                    actualizarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un insumo de la tabla");
            }
        });
    }

    public void actualizarTabla() {
        tabla.setModel(li.getmodelo(lc));
    }
}