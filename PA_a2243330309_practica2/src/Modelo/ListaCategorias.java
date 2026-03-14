package Modelo;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class ListaCategorias {
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public void cargarCategorias(ArrayList<String[]> datos) {
        categorias.clear();
        for (String[] fila : datos) {
            categorias.add(new Categoria(fila[0], fila[1]));
        }
    }

    public String buscarCategoriaPorId(String id) {
        for (Categoria c : categorias) {
            if (c.getIdcategoria().equals(id)) return c.toString();
        }
        return "Desconocido";
    }

    public DefaultListModel<Categoria> generarModeloCategorias() {
        DefaultListModel<Categoria> modelo = new DefaultListModel<>();
        for (Categoria c : categorias) {
            modelo.addElement(c);
        }
        return modelo;
    }
    
    public Categoria buscarObjetoCategoria(String id) {
        for (Categoria c : categorias) {
            if (c.getIdcategoria().equals(id)) return c;
        }
        return null;
    }
    
    public void eliminarCategoria(int indice) {
        if (indice >= 0 && indice < categorias.size()) {
            categorias.remove(indice);
        }
    }

    public void agregarCategoria(Categoria c) {
        categorias.add(c);
    }
}