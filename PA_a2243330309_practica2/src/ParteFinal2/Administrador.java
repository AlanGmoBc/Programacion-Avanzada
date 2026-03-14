package ParteFinal2;

import javax.swing.JOptionPane;

public class Administrador {
    public static int modoPersistencia = 0; 

    public static void configurarModo() {
        String[] options = {"Archivos de Texto (TXT)", "Archivos XML"};
        int seleccion = JOptionPane.showOptionDialog(null, 
            "Seleccione el motor de base de datos para el sistema:",
            "Configuración de Administrador",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, options, options[0]);
        
        if (seleccion != -1) {
            modoPersistencia = seleccion;
            String modo = (seleccion == 0) ? "TXT" : "XML";
            JOptionPane.showMessageDialog(null, "Sistema configurado en modo: " + modo);
        }
    }
}