package vista;

import javax.swing.*;
import modelo.*;
import persistencia.GestorArchivos;
import java.awt.event.*;
import java.io.File;
import java.util.List;

public class ExamenVista extends JFrame {
    private Examen examen;
    private JLabel lblPregunta;
    private JRadioButton[] rbOpciones = new JRadioButton[4];
    private ButtonGroup grupoOpciones = new ButtonGroup();
    private JButton btnSiguiente, btnStart;
    private JMenu menuArchivo; // Lo declaramos aquí para poder habilitarlo/deshabilitarlo

    public ExamenVista() {
        setTitle("Examen de Opción Múltiple");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblPregunta = new JLabel("Cargue un archivo para iniciar");
        lblPregunta.setBounds(20, 30, 450, 25);
        add(lblPregunta);

        for (int i = 0; i < 4; i++) {
            rbOpciones[i] = new JRadioButton();
            rbOpciones[i].setBounds(20, 70 + (i * 40), 400, 25);
            rbOpciones[i].setVisible(false);
            grupoOpciones.add(rbOpciones[i]);
            add(rbOpciones[i]);
        }

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(20, 300, 120, 30);
        btnSiguiente.setEnabled(false);
        add(btnSiguiente);

        btnStart = new JButton("Start Exam");
        btnStart.setBounds(160, 300, 120, 30);
        btnStart.setEnabled(false);
        add(btnStart);

        // Menú Archivo
        JMenuBar menuBar = new JMenuBar();
        menuArchivo = new JMenu("Archivo");
        JMenuItem itemAbrir = new JMenuItem("Abrir (.csv)");
        itemAbrir.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<Pregunta> p = GestorArchivos.cargarDesdeCSV(fc.getSelectedFile());
                    examen = new Examen(p);
                    btnStart.setEnabled(true);
                    lblPregunta.setText("Archivo cargado con éxito.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        });
        menuArchivo.add(itemAbrir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        // Evento del botón Start
        btnStart.addActionListener(e -> {
            if (examen != null) {
                examen.barajar();
                btnStart.setEnabled(false);
                btnSiguiente.setEnabled(true);
                menuArchivo.setEnabled(false); // Bloqueamos menú según instrucciones
                for(JRadioButton rb : rbOpciones) rb.setVisible(true);
                actualizarInterfaz();
            }
        });

        // Evento del botón Siguiente (llama al nuevo método responder)
        btnSiguiente.addActionListener(e -> responder());
    }

    private void responder() {
        if (grupoOpciones.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una opción.");
            return;
        }

        String respuestaUsuario = "";
        for (JRadioButton rb : rbOpciones) {
            if (rb.isSelected()) {
                respuestaUsuario = rb.getText();
                break;
            }
        }

        Pregunta actual = examen.obtenerPreguntaActual();
        if (respuestaUsuario.equals(actual.getRespuestaCorrecta())) {
            examen.registrarAcierto(); // Suma un punto en el objeto examen
        }

        if (!examen.siguiente()) {
            // Calcular resultados finales
            double porcentaje = ((double) examen.getAciertos() / examen.getTotalPreguntas()) * 100;
            
            JOptionPane.showMessageDialog(this, 
                "Examen Finalizado\n" +
                "Aciertos: " + examen.getAciertos() + " de " + examen.getTotalPreguntas() + "\n" +
                "Calificación: " + porcentaje + "%");
            
            btnSiguiente.setEnabled(false);
            menuArchivo.setEnabled(true);
            lblPregunta.setText("Examen terminado. Cargue otro archivo si desea.");
        } else {
            actualizarInterfaz();
        }
    }

    private void actualizarInterfaz() {
        Pregunta p = examen.obtenerPreguntaActual();
        if (p != null) {
            lblPregunta.setText(p.getEnunciado());
            String[] opts = p.getOpciones();
            grupoOpciones.clearSelection();
            for (int i = 0; i < 4; i++) {
                rbOpciones[i].setText(opts[i]);
            }
        }
    }

    public static void main(String[] args) {
        // Ejecución de la interfaz
        SwingUtilities.invokeLater(() -> {
            new ExamenVista().setVisible(true);
        });
    }
}