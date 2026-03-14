package Modelo;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ListaObras {
    private ArrayList<Obra> lista = new ArrayList<>();

    public void agregar(Obra o) { lista.add(o); }

    public DefaultTableModel getModelo() {
        String[] col = {"ID", "Nombre Obra", "Ubicación"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0);
        for (Obra o : lista) {
            Object[] fila = {o.getIdObra(), o.getNombreObra(), o.getUbicacion()};
            modelo.addRow(fila);
        }
        return modelo;
    }
}