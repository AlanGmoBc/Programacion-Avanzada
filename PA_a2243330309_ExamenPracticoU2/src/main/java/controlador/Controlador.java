package controlador;

import modelo.Evaluacion;
import vista.VentanaPrincipal;
import utilidad.GestorArchivos;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.io.File;
import java.util.*;

public class Controlador {
    private String rutaDestino = "."; 
    private List<String[]> todosLosDatos = new ArrayList<>();
    private boolean isCargando = false;

    public Controlador(VentanaPrincipal vista) {
        
        vista.btnSeleccionarCarpeta.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(".");
            // FILTRO PARA EVITAR EL ERROR DE OFFICE XML:
            chooser.setFileFilter(new FileNameExtensionFilter("Archivos Excel (.xlsx)", "xlsx"));
            chooser.setAcceptAllFileFilterUsed(false);

            if(chooser.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
                File seleccion = chooser.getSelectedFile();
                rutaDestino = seleccion.getParent();
                
                List<String[]> nuevosDatos = GestorArchivos.leerDatosDesdeExcel(seleccion.getAbsolutePath());
                
                if (!nuevosDatos.isEmpty()) {
                    this.todosLosDatos = nuevosDatos;
                    menuSeleccionProfesor(vista);
                } else {
                    JOptionPane.showMessageDialog(vista, "Archivo no válido o sin datos de alumnos.");
                }
            }
        });

        vista.modeloTabla.addTableModelListener(e -> {
            if (!isCargando && e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int fila = e.getFirstRow();
                int col = e.getColumn();
                if (col >= 1 && col <= 3) {
                    actualizarPromedioRapido(vista, fila);
                }
            }
        });

        vista.btnGuardar.addActionListener(e -> {
            ejecutarGuardadoYContinuar(vista);
        });
    }

    private void menuSeleccionProfesor(VentanaPrincipal vista) {
        Set<String> profes = new LinkedHashSet<>();
        for(String[] d : todosLosDatos) profes.add(d[2]);

        String seleccion = (String) JOptionPane.showInputDialog(vista, 
                "Seleccione Maestro/Grupo:", "Cambiando Evaluación",
                JOptionPane.QUESTION_MESSAGE, null, profes.toArray(), profes.toArray()[0]);

        if(seleccion != null) {
            isCargando = true;
            cargarFiltroEnTabla(vista, seleccion);
            isCargando = false;
        }
    }

    private void cargarFiltroEnTabla(VentanaPrincipal vista, String profe) {
        vista.modeloTabla.setRowCount(0);
        boolean cabeceraLista = false;
        for (String[] d : todosLosDatos) {
            if (d[2].equals(profe)) {
                if(!cabeceraLista) {
                    vista.txtAsignatura.setText(d[1]);
                    vista.txtProfesor.setText(d[2]);
                    vista.txtGrupo.setText(d[3]);
                    cabeceraLista = true;
                }
                vista.modeloTabla.addRow(new Object[]{d[0], "0", "0", "0", "0.00"});
            }
        }
        vista.pnlSemaforo.setBackground(Color.YELLOW);
    }

    private void ejecutarGuardadoYContinuar(VentanaPrincipal vista) {
        try {
            // 1. Crear el objeto de evaluación
            String id = vista.txtAsignatura.getText().replace(" ", "") + "_" + vista.txtGrupo.getText();
            Evaluacion eval = new Evaluacion(id, vista.txtAsignatura.getText(), vista.txtProfesor.getText(), vista.txtGrupo.getText());

            // 2. Procesar tabla y promedios
            double sumaGrupal = 0;
            for (int i = 0; i < vista.modeloTabla.getRowCount(); i++) {
                String nombre = vista.modeloTabla.getValueAt(i, 0).toString();
                String prom = vista.modeloTabla.getValueAt(i, 4).toString();
                eval.alumnos.add(nombre + " | Promedio: " + prom);
                sumaGrupal += Double.parseDouble(prom);
            }
            eval.promedio = sumaGrupal / vista.modeloTabla.getRowCount();

            // 3. Guardar archivos físicamente
            GestorArchivos.guardarJSON(eval, rutaDestino + File.separator + id + ".json");
            GestorArchivos.generarExcel(eval, rutaDestino + File.separator + id + "_Final.xlsx");

            // 4. Mostrar Evaluación Final
            String reporte = "--- RESULTADO GRUPAL ---\n" +
                             "Asignatura: " + eval.asignatura + "\n" +
                             "Promedio Final: " + String.format("%.2f", eval.promedio);
            JOptionPane.showMessageDialog(vista, reporte);
            
            vista.pnlSemaforo.setBackground(Color.GREEN);

            // 5. Ciclo para otra evaluación
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Deseas evaluar a otro profesor o grupo ahora?");
            if(opcion == JOptionPane.YES_OPTION) {
                menuSeleccionProfesor(vista);
            }
        } catch (Exception ex) {
            vista.pnlSemaforo.setBackground(Color.RED);
            JOptionPane.showMessageDialog(vista, "Error al guardar: " + ex.getMessage());
        }
    }

    private void actualizarPromedioRapido(VentanaPrincipal v, int f) {
        try {
            double c1 = Double.parseDouble(v.modeloTabla.getValueAt(f, 1).toString());
            double c2 = Double.parseDouble(v.modeloTabla.getValueAt(f, 2).toString());
            double c3 = Double.parseDouble(v.modeloTabla.getValueAt(f, 3).toString());
            String prom = String.format("%.2f", (c1 + c2 + c3) / 3.0);
            SwingUtilities.invokeLater(() -> v.modeloTabla.setValueAt(prom, f, 4));
        } catch (Exception ex) {}
    }
}