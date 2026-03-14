package Parte2_Modelo;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class ListaCategorias {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public void agregarCategoria(Categoria c) {
        categorias.add(c);
    }

    public void cargarCategorias(ArrayList<String[]> categoriasString) {
        for (String[] fila : categoriasString) {
            String id = fila[0];
            String nombre = fila[1];
            agregarCategoria(new Categoria(id, nombre));
        }
    }

    public DefaultListModel<Categoria> generarModeloCategorias() {
        DefaultListModel<Categoria> modelo = new DefaultListModel<>();
        for (Categoria categoria : categorias) {
            modelo.addElement(categoria);
        }
        return modelo;
    }
    
    public String buscarCategoria(String id) {
        for(Categoria c : categorias) {
            if(c.getIdcategoria().trim().equals(id.trim())) return c.getNombre();
        }
        return "No encontrada";
    }
}