package Parte2_Vista;
import javax.swing.*;
import java.awt.*;
import Parte2_Modelo.Insumo;
import Parte2_Modelo.Categoria;
import Libreria.Archivotxt;

public class Practica03_d extends JDialog {
    private JTextField txtId = new JTextField();
    private JTextField txtNom = new JTextField();
    private JComboBox<Categoria> comboCat = new JComboBox<>();
    private JButton btnGuardar = new JButton("Guardar Insumo");

    public Practica03_d(Practica03_a padre, boolean modal) {
        super(padre, modal);
        setTitle("Nuevo Insumo");
        setLayout(new GridLayout(4, 2, 10, 10));

        DefaultListModel<Categoria> datos = padre.listaC.generarModeloCategorias();
        for(int i = 0; i < datos.getSize(); i++) {
            comboCat.addItem(datos.getElementAt(i));
        }

        add(new JLabel("  ID:")); add(txtId);
        add(new JLabel("  Nombre:")); add(txtNom);
        add(new JLabel("  Categoría:")); add(comboCat);
        add(new JLabel("")); add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            Categoria seleccionada = (Categoria) comboCat.getSelectedItem();
            if(seleccionada != null && !txtId.getText().isEmpty()) {
                Insumo nuevo = new Insumo(txtId.getText(), txtNom.getText(), seleccionada.getIdcategoria());
                padre.listaI.agregarInsumo(nuevo);

                new Archivotxt("Insumos").guardar(padre.listaI.toArchivo());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Llena los campos y selecciona categoría.");
            }
        });

        pack();
        setLocationRelativeTo(padre);
    }
}