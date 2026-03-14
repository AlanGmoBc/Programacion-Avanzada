package Parte3;

import javax.swing.*;
import java.awt.*;
import Modelo.*;

public class DialogoInsumo extends JDialog {
    private JTextField Tid, Tnom;
    private JComboBox<Categoria> comboCat;
    private JButton btnGuardar, btnCancelar;
    private boolean guardado = false;
    private Insumo insumoResultado;

    public DialogoInsumo(Frame padre, ListaCategorias lc) {
        super(padre, "Datos del Insumo", true);
        setLayout(new GridLayout(4, 2, 10, 10));
        setSize(300, 200);
        setLocationRelativeTo(padre);

        add(new JLabel(" ID:"));
        Tid = new JTextField(); add(Tid);

        add(new JLabel(" Nombre:"));
        Tnom = new JTextField(); add(Tnom);

        add(new JLabel(" Categoría:"));
        comboCat = new JComboBox<>();
        // Llenar combo con el modelo de la lista de categorías
        DefaultListModel<Categoria> mod = lc.generarModeloCategorias();
        for(int i=0; i<mod.size(); i++) comboCat.addItem(mod.getElementAt(i));
        add(comboCat);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            insumoResultado = new Insumo(Tid.getText(), Tnom.getText(), 
                               ((Categoria)comboCat.getSelectedItem()).getIdcategoria());
            guardado = true;
            dispose();
        });
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    public boolean isGuardado() { return guardado; }
    public Insumo getInsumo() { return insumoResultado; }
}