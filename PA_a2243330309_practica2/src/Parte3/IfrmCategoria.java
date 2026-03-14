package Parte3;
import javax.swing.*;
import java.awt.*;
import Modelo.ListaCategorias;

public class IfrmCategoria extends JInternalFrame {
    private JList<Modelo.Categoria> lista;
    private ListaCategorias lc;

    public IfrmCategoria(ListaCategorias lc) {
        super("Manejo de Categorías", true, true, true, true);
        this.lc = lc;
        setSize(400, 300);
        setLayout(new BorderLayout());

        lista = new JList<>(lc.generarModeloCategorias());
        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel pBotones = new JPanel();
        JButton btnAdd = new JButton("Nueva");
        JButton btnDel = new JButton("Eliminar");

        btnAdd.addActionListener(e -> {
            Frame f = (Frame) SwingUtilities.getWindowAncestor(this);
            DlgCategoria dlg = new DlgCategoria(f);
            dlg.setVisible(true);
            if(dlg.isGuardado()){
                lc.agregarCategoria(dlg.getCategoria());
                lista.setModel(lc.generarModeloCategorias());
            }
        });

        btnDel.addActionListener(e -> {
            int sel = lista.getSelectedIndex();
            if(sel != -1){
                int confirm = JOptionPane.showConfirmDialog(this, "¿Borrar categoría?");
                if(confirm == JOptionPane.YES_OPTION){
                    lc.eliminarCategoria(sel);
                    lista.setModel(lc.generarModeloCategorias());
                }
            }
        });

        pBotones.add(btnAdd); pBotones.add(btnDel);
        add(pBotones, BorderLayout.SOUTH);
    }
}