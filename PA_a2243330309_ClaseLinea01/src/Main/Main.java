package Main;

import Libreria.ConexionBD;
import Modelo.BaseDatos;
import Modelo.Configurador;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE MATERIAS ===");

        // 1. Cargar configuración desde XML
        Map<String, String> conf = Configurador.cargarConfiguracion();
        Connection con = null;

        try {
            // 2. Intentar conexión según el motor activo del XML
            String motor = conf.get("motor");
            System.out.println("Conectando a: " + motor.toUpperCase() + "...");

            String[] params = {
                conf.get("host"), 
                conf.get("db"), 
                conf.get("user"), 
                conf.get("pass")
            };

            if (motor.equalsIgnoreCase("mysql")) {
                con = ConexionBD.conectarMySQL(params);
            } else {
                con = ConexionBD.conectarSQLServer(params);
            }

            if (con != null) {
                // 3. Consultar datos de la tabla ASIGNATURA
                BaseDatos bd = new BaseDatos(con);
                // null en columnas y condiciones para traer todo (*)
                ArrayList<Object[]> listaMaterias = bd.consultar("ASIGNATURA", null, null);

                // 4. Mostrar resultados
                System.out.println("\nLISTADO DE MATERIAS:");
                System.out.println("CARRERA | PLAN | ID | MATERIA");
                System.out.println("--------------------------------------------------");

                for (Object[] fila : listaMaterias) {
                    for (Object campo : fila) {
                        System.out.print(campo + "\t| ");
                    }
                    System.out.println();
                }
                System.out.println("--------------------------------------------------");
            }

        } catch (Exception e) {
            System.err.println("Fallo en la ejecución: " + e.getMessage());
        } finally {
            // 5. Cerrar conexión
            ConexionBD.cerrarConexion();
        }
    }
}