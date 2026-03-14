package Parte2_Modelo;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListaInsumos {
    private ArrayList<Insumo> insumos = new ArrayList<>();

    public boolean agregarInsumo(Insumo nodo) {
        for(Insumo i : insumos) {
            if(i.getIdProducto().equals(nodo.getIdProducto())) return false;
        }
        insumos.add(nodo);
        return true;
    }

    public String toArchivo() {
        String resultado = "";
        for (Insumo insumo : insumos) {
            resultado += insumo.toLinea() + "\n";
        }
        return resultado;
    }

    public void cargarinsumo(ArrayList<String[]> insumosString) {
        for (String[] fila : insumosString) {
            String id = fila[0];
            String nombre = fila[1];
            String idCat = fila[2];
            insumos.add(new Insumo(id, nombre, idCat));
        }
    }

    public DefaultTableModel getmodelo(ListaCategorias listac) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        modelo.addColumn("id");
        modelo.addColumn("Insumo");
        modelo.addColumn("Categoria");

        for (Insumo nodo : insumos) {
            String[] info = new String[3];
            info[0] = nodo.getIdProducto().trim();
            info[1] = nodo.getProducto().trim();
            info[2] = listac.buscarCategoria(nodo.getIdCategoria().trim());
            modelo.addRow(info);
        }
        return modelo;
    }
}