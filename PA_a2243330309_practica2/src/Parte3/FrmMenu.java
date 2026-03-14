package Parte3;

import javax.swing.*;
import java.awt.*;
import Modelo.*;
import Libreria.*;
import ParteFinal2.Administrador;

public class FrmMenu extends JFrame {
    private JDesktopPane escritorio;
    private ListaCategorias lc;
    private ListaInsumos li;
    private ListaObras lo;

    public FrmMenu() {
        lc = new ListaCategorias();
        li = new ListaInsumos();
        lo = new ListaObras();
        
        cargarDatosGenerales();

        setTitle("Sistema de Gestión - PA_a2243330309_practica2");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        escritorio = new JDesktopPane();
        escritorio.setBackground(Color.DARK_GRAY);
        setContentPane(escritorio);

        JMenuBar barraMenu = new JMenuBar();

        JMenu mCatalogos = new JMenu("Configuración");
        JMenuItem itemCat = new JMenuItem("Categorías");
        JMenuItem itemIns = new JMenuItem("Insumos");
        JMenuItem itemObras = new JMenuItem("Obras");

        mCatalogos.add(itemCat);
        mCatalogos.add(itemIns);
        mCatalogos.add(itemObras);

        JMenu mAdmin = new JMenu("Administración");
        JMenuItem itemDB = new JMenuItem("Establecer Base de Datos (TXT/XML)");
        mAdmin.add(itemDB);

        itemDB.addActionListener(e -> {
            Administrador.configurarModo();
            cargarDatosGenerales();
            verificarModoActual();
        });

        // NUEVA IMPLEMENTACIÓN PARA VER FOTOS
        itemIns.addActionListener(e -> {
            Practica03_a ventanaFotos = new Practica03_a(li, lc);
            escritorio.add(ventanaFotos);
            ventanaFotos.setVisible(true);
        });

        itemCat.addActionListener(e -> {
            IfrmCategoria ifrm = new IfrmCategoria(lc);
            escritorio.add(ifrm);
            ifrm.setVisible(true);
        });

        itemObras.addActionListener(e -> {
            IfrmObra ifrm = new IfrmObra(lo);
            escritorio.add(ifrm);
            ifrm.setVisible(true);
        });

        barraMenu.add(mCatalogos);
        barraMenu.add(mAdmin);
        setJMenuBar(barraMenu);
    }

    // --- MÉTODO DE PERSISTENCIA TXT/XML ---
    public void cargarDatosGenerales() {
        if (Administrador.modoPersistencia == 0) {
            // MODO TXT
            System.out.println("Cargando datos desde archivos TXT...");
            
            Archivotxt fTxtCat = new Archivotxt("Categoria");
            lc.cargarCategorias(fTxtCat.cargar());
            
            Archivotxt fTxtIns = new Archivotxt("Insumos");
            li.cargarInsumos(fTxtIns.cargar());
            
        } else {
            // MODO XML
            System.out.println("Cargando datos desde archivos XML...");
            
            ArchivoXML fXmlCat = new ArchivoXML("Categoria");
            String[] tagsCat = {"id", "nombre"};
            lc.cargarCategorias(fXmlCat.cargar("Categoria", tagsCat));
            
            ArchivoXML fXmlIns = new ArchivoXML("Insumos");
            String[] tagsIns = {"id", "nombre", "idCategoria"};
            li.cargarInsumos(fXmlIns.cargar("Insumo", tagsIns));
        }
    }

    private void verificarModoActual() {
        String modo = (Administrador.modoPersistencia == 0) ? "TXT" : "XML";
        JOptionPane.showMessageDialog(this, "El sistema ahora lee y guarda en: " + modo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmMenu().setVisible(true);
        });
    }
}