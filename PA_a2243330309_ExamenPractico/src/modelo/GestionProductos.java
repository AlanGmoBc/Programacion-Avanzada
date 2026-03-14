package modelo;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionProductos {
    private ArrayList<Producto> lista;

    public GestionProductos() {
        this.lista = new ArrayList<>();
    }

    public void agregar(Producto p) { lista.add(p); }

    public boolean existe(String id) {
        return lista.stream().anyMatch(p -> p.getId().equals(id));
    }

    public void eliminar(String id) {
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                break;
            }
        }
    }

    public ArrayList<Producto> getLista() { return lista; }
    public void setLista(ArrayList<Producto> lista) { this.lista = lista; }
}