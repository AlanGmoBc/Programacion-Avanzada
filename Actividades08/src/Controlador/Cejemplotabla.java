package Controlador;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Librerias.Libreria;
import Modelo.ModeloTabla;
import Vista.EjemploTabla;

public class Cejemplotabla implements ListSelectionListener {
    ModeloTabla modelo;
    EjemploTabla vista;
    String archivo = "ReporteInscripcionCalificaciones.csv";

    public Cejemplotabla() {
        ArrayList<String[]> datos = Libreria.LeerDatosCSV(archivo);
        modelo = new ModeloTabla(datos);
        vista = new EjemploTabla();

        // Cargar las carreras en la lista lateral
        vista.getListaCarreras().setModel(modelo.getModeloCarreras());
        vista.getTable().setModel(modelo.getModelo());
        
        vista.conectarEventos(this, e -> guardar());
        vista.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String carrera = vista.getListaCarreras().getSelectedValue();
            if (carrera != null) {
                modelo.filtrarPorCarrera(carrera);
            }
        }
    }

    private void guardar() {
    }
}