package Parte1;
import javax.swing.*;
import java.awt.event.*;
import Modelo.*;
import Libreria.Archivotxt;

public class Practica01_a extends JFrame {
    private JList<Categoria> ListaCategoria;
    private DefaultListModel<Categoria> modelocategoria;
    private ListaCategorias listacategorias;
    private ListaInsumos listainsumos;
    private JTextField Tid, Tinsumo;
    private JTextArea areaTexto;

    public Practica01_a() {
        // Inicializar datos
        listacategorias = new ListaCategorias();
        listainsumos = new ListaInsumos();
        Archivotxt arch = new Archivotxt("Categoria");
        if(arch.existe()) listacategorias.cargarCategorias(arch.cargar());
        modelocategoria = listacategorias.generarModeloCategorias();

        // GUI
        setTitle("Practica 2 - Insumos");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("ID:"); l1.setBounds(20, 20, 50, 20); add(l1);
        Tid = new JTextField(); Tid.setBounds(80, 20, 100, 20); add(Tid);

        JLabel l2 = new JLabel("Insumo:"); l2.setBounds(20, 50, 50, 20); add(l2);
        Tinsumo = new JTextField(); Tinsumo.setBounds(80, 50, 100, 20); add(Tinsumo);

        JLabel l3 = new JLabel("Categoria:"); l3.setBounds(20, 80, 80, 20); add(l3);
        
        ListaCategoria = new JList<>(modelocategoria);
        JScrollPane scrollList = new JScrollPane(ListaCategoria);
        scrollList.setBounds(20, 110, 160, 80);
        add(scrollList);

        JButton btnAdd = new JButton("Agregar");
        btnAdd.setBounds(20, 200, 100, 30);
        add(btnAdd);

        areaTexto = new JTextArea();
        JScrollPane scrollArea = new JScrollPane(areaTexto);
        scrollArea.setBounds(20, 250, 440, 180);
        add(scrollArea);

        btnAdd.addActionListener(e -> {
            if (ListaCategoria.getSelectedIndex() != -1 && !Tid.getText().isEmpty()) {
                Categoria sel = ListaCategoria.getSelectedValue();
                Insumo nuevo = new Insumo(Tid.getText(), Tinsumo.getText(), sel.getIdcategoria());
                listainsumos.agregarInsumo(nuevo);
                areaTexto.append("ID: " + nuevo.getIdProducto() + " | Insumo: " + nuevo.getProducto() + " | Cat: " + sel.toString() + "\n");
            } else {
                JOptionPane.showMessageDialog(null, "Completa los campos y selecciona categoría");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new Practica01_a(); }
}