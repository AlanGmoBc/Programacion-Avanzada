package Parte3;

import javax.swing.*;
import java.awt.*;
import Modelo.*;

public class DlgInsumo extends JDialog {
    private JTextField Tid, Tnombre;
    private JComboBox<Categoria> comboCat;
    private JButton btnGuardar, btnCancelar;
    private Insumo insumoNuevo;
    private boolean guardado = false;

    // El constructor recibe el "Frame padre" para bloquear la ventana de atrás (Modal)
    public DlgInsumo(Frame padre, ListaCategorias lc) {
        super(padre, "Datos del Insumo", true); // "true" lo hace modal
        setSize(350, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        setLocationRelativeTo(padre);

        // Componentes
        add(new JLabel(" ID del Producto:"));
        Tid = new JTextField();
        add(Tid);

        add(new JLabel(" Nombre Insumo:"));
        Tnombre = new JTextField();
        add(Tnombre);

        add(new JLabel(" Categoría:"));
        comboCat = new JComboBox<>();
        
        // Llenamos el combo con las categorías que ya existen
        DefaultListModel<Categoria> modeloCategorias = lc.generarModeloCategorias();
        for (int i = 0; i < modeloCategorias.getSize(); i++) {
            comboCat.addItem(modeloCategorias.getElementAt(i));
        }
        add(comboCat);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);

        // Evento Guardar
        btnGuardar.addActionListener(e -> {
            if (!Tid.getText().isEmpty() && !Tnombre.getText().isEmpty()) {
                // Obtenemos el objeto categoría seleccionado
                Categoria catSel = (Categoria) comboCat.getSelectedItem();
                
                // Creamos el objeto Insumo
                insumoNuevo = new Insumo(Tid.getText(), Tnombre.getText(), catSel.getIdcategoria());
                guardado = true;
                dispose(); // Cierra el diálogo
            } else {
                JOptionPane.showMessageDialog(this, "Llena todos los campos, cabrón");
            }
        });

        // Evento Cancelar
        btnCancelar.addActionListener(e -> dispose());
    }

    public boolean isGuardado() {
        return guardado;
    }

    public Insumo getInsumo() {
        return insumoNuevo;
    }
}