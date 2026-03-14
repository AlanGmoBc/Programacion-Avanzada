package Modelo;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListaInsumos {
    private ArrayList<Insumo> insumos = new ArrayList<>();

    public void agregarInsumo(Insumo i) { insumos.add(i); }

    public void cargarInsumos(ArrayList<String[]> datos) {
        this.insumos.clear();
        for (String[] fila : datos) {
            this.insumos.add(new Insumo(fila[0], fila[1], fila[2]));
        }
    }

    public DefaultTableModel getmodelo(ListaCategorias listac) {
        String[] col = {"ID", "Insumo", "Categoria"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Insumo i : insumos) {
            String nombreCat = listac.buscarCategoriaPorId(i.getIdCategoria());
            Object[] fila = {i.getIdProducto(), i.getProducto(), nombreCat};
            modelo.addRow(fila);
        }
        return modelo;
    }
}