package Parte2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import Modelo.*;
import Libreria.Archivotxt;

public class Practica02_a extends JFrame {
    private JTable tablaProductos;
    private JList<Categoria> listaCat;
    private DefaultListModel<Categoria> modeloCat;
    private ListaCategorias datosCat;
    private ListaInsumos datosInsumos;
    private JTextField Tid, Tinsumo;

    public Practica02_a() {
        datosCat = new ListaCategorias();
        datosInsumos = new ListaInsumos();

        Archivotxt arch = new Archivotxt("Categoria");
        if(arch.existe()) datosCat.cargarCategorias(arch.cargar());
        modeloCat = datosCat.generarModeloCategorias();

        setTitle("Practica 02 - JTable");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel panelNorte = new JPanel(new GridLayout(3, 2));
        panelNorte.add(new JLabel("ID:"));
        Tid = new JTextField(); panelNorte.add(Tid);
        panelNorte.add(new JLabel("Insumo:"));
        Tinsumo = new JTextField(); panelNorte.add(Tinsumo);
        
        listaCat = new JList<>(modeloCat);
        panelNorte.add(new JLabel("Categoría:"));
        panelNorte.add(new JScrollPane(listaCat));
        add(panelNorte, BorderLayout.NORTH);

        tablaProductos = new JTable();
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JButton btnAgregar = new JButton("Agregar a Tabla");
        btnAgregar.addActionListener(e -> {
            if(listaCat.getSelectedIndex() != -1) {
                Categoria c = listaCat.getSelectedValue();
                Insumo nuevo = new Insumo(Tid.getText(), Tinsumo.getText(), c.getIdcategoria());
                datosInsumos.agregarInsumo(nuevo);
                tablaProductos.setModel(datosInsumos.getmodelo(datosCat));
            }
        });
        add(btnAgregar, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}