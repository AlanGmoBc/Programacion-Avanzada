package controlador;

import modelo.Evaluacion;
import vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EvaluacionControlador {
    private VentanaPrincipal vista;
    private Evaluacion modelo;

    public EvaluacionControlador(VentanaPrincipal vista) {
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(e -> guardarDatos());
    }

    private void guardarDatos() {
        // 1. Recoger datos de la vista
        // 2. Lógica para guardar en JSON (usa GSON aquí)
        JOptionPane.showMessageDialog(vista, "Datos actualizados en el JSON correctamente.");
        vista.panelEstado.setBackground(java.awt.Color.GREEN);
    }
}