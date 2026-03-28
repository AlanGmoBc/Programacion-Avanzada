package sistema;

import vista.VentanaPrincipal;
import controlador.Controlador;

public class Main {
    public static void main(String[] args) {
        System.setProperty("log4j2.loggerContextFactory", "org.apache.logging.log4j.simple.SimpleLoggerContextFactory");
        
        VentanaPrincipal vista = new VentanaPrincipal();
        new Controlador(vista);
        vista.setVisible(true);
    }
}