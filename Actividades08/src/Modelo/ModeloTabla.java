package Modelo;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla {
    private DefaultTableModel modelo;
    private ArrayList<String[]> datos;
    private String columnasVisuales[] = {"NOMBRE", "MATRICULA", "ESPECIALIDAD", "CALIFICACION"};

    public ModeloTabla(ArrayList<String[]> datos) {
        this.datos = datos;
        this.modelo = new DefaultTableModel(columnasVisuales, 0);
    }

    public DefaultListModel<String> getModeloCarreras() {
        DefaultListModel<String> lista = new DefaultListModel<>();
        ArrayList<String> tmp = new ArrayList<>();
        for (String[] fila : datos) {
            if (fila.length > 1 && !tmp.contains(fila[1])) {
                tmp.add(fila[1]);
                lista.addElement(fila[1]);
            }
        }
        return lista;
    }

    public void filtrarPorCarrera(String carreraSeleccionada) {
        modelo.setRowCount(0); // Limpiar tabla
        for (String[] fila : datos) {
            if (fila.length >= 10 && fila[1].equals(carreraSeleccionada)) {
                String[] filaReducida = {
                    fila[8],  // NOMBRE
                    fila[7],  // MATRICULA
                    fila[1],  // ESPECIALIDAD
                    fila[9]   // CALIFICACION
                };
                modelo.addRow(filaReducida);
            }
        }
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}