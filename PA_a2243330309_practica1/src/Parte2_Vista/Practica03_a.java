package Parte2_Vista;

import javax.swing.*;
import java.awt.event.*;
import Libreria.Archivotxt;
import Parte2_Modelo.ListaCategorias;
import Parte2_Modelo.ListaInsumos;

public class Practica03_a extends JFrame implements ActionListener {
    public JDesktopPane escritorio;
    public JMenuBar barra;

    public ListaCategorias listaC = new ListaCategorias();
    public ListaInsumos listaI = new ListaInsumos();

    public Practica03_a() {
        setTitle("Sistema de Inventario");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        escritorio = new JDesktopPane();
        setContentPane(escritorio);

        Archivotxt arcC = new Archivotxt("Categoria");
        if(arcC.existe()) {
            listaC.cargarCategorias(arcC.cargar());
        } else {
            precargarCategorias();
        }

        Archivotxt arcI = new Archivotxt("Insumos");
        if(arcI.existe()) listaI.cargarinsumo(arcI.cargar());

        crearMenu();
    }

    private void precargarCategorias() {
        listaC.agregarCategoria(new Parte2_Modelo.Categoria("1", "Materiales"));
        listaC.agregarCategoria(new Parte2_Modelo.Categoria("2", "Mano de Obra"));
        listaC.agregarCategoria(new Parte2_Modelo.Categoria("3", "Maquinaria"));
        new Archivotxt("Categoria").guardar("1,Materiales\n2,Mano de Obra\n3,Maquinaria");
    }

    private void crearMenu() {
        barra = new JMenuBar();
        JMenu menu = new JMenu("Configuracion");
        JMenuItem itemI = new JMenuItem("Insumos");
        JMenuItem itemC = new JMenuItem("Categorias");
        itemI.addActionListener(this);
        itemC.addActionListener(this);
        menu.add(itemC); menu.add(itemI);
        barra.add(menu); setJMenuBar(barra);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Insumos")) {
            Practica03_b venB = new Practica03_b(this);
            escritorio.add(venB);
            venB.setVisible(true);
        } else if(e.getActionCommand().equals("Categorias")) {
            Practica03_c venC = new Practica03_c(this);
            escritorio.add(venC);
            venC.setVisible(true);
        }
    }
    public static void main(String[] args) { new Practica03_a().setVisible(true); }
}