package Parte3;
import javax.swing.*;
import java.awt.*;
import Modelo.ListaObras;

public class IfrmObra extends JInternalFrame {
    private JTable tabla;
    private ListaObras lo;

    public IfrmObra(ListaObras lo) {
        super("Manejo de Obras", true, true, true, true);
        this.lo = lo;
        setSize(500, 300);
        
        tabla = new JTable(lo.getModelo());
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnNuevo = new JButton("Nueva Obra");
        btnNuevo.addActionListener(e -> {
            Frame f = (Frame) SwingUtilities.getWindowAncestor(this);
            DlgObra dlg = new DlgObra(f);
            dlg.setVisible(true);
            if(dlg.isGuardado()) {
                lo.agregar(dlg.getObra());
                tabla.setModel(lo.getModelo());
            }
        });
        add(btnNuevo, BorderLayout.SOUTH);
    }
}