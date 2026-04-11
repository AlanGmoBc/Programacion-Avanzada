package Modelo;

import java.sql.*;
import java.util.*;

public class BaseDatos {

    private Connection conexion;

    public BaseDatos(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta genérica para una o más tablas.
     * Centraliza la lógica de lectura para evitar repetir código.
     */
    private ArrayList<String[]> ejecutarConsulta(String sql) {
        ArrayList<String[]> resultados = new ArrayList<>();
        
        // try-with-resources: Cierra automáticamente el Statement y ResultSet
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                String[] fila = new String[numColumnas];
                for (int i = 1; i <= numColumnas; i++) {
                    fila[i - 1] = rs.getString(i);
                }
                resultados.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error en consulta SQL: " + e.getMessage());
            System.err.println("SQL fallido: " + sql);
        }
        return resultados;
    }

    public ArrayList<String[]> consultar(String tabla, String campos, String condicion) {
        String select = (campos == null || campos.isEmpty()) ? "*" : campos;
        String sql = "SELECT " + select + " FROM " + tabla;
        if (condicion != null && !condicion.isEmpty()) {
            sql += " WHERE " + condicion;
        }
        return ejecutarConsulta(sql);
    }

    public ArrayList<String[]> consultar(String tabla1, String tabla2, String campos, String condicion) {
        String select = (campos == null || campos.isEmpty()) ? "*" : campos;
        // Se asume que condicion contiene la clausula ON del Join
        String sql = "SELECT " + select + " FROM " + tabla1 + " INNER JOIN " + tabla2 + " ON " + condicion;
        return ejecutarConsulta(sql);
    }

    public boolean existe(String tabla, String condicion) {
        String sql = "SELECT 1 FROM " + tabla + " WHERE " + condicion;
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next(); // true si hay al menos una fila
        } catch (SQLException e) {
            System.err.println("Error en existe(): " + e.getMessage());
            return false;
        }
    }

    /**
     * INSERTAR SEGURO: Utiliza PreparedStatement con parámetros '?'
     * Evita inyección SQL y errores por comillas en los textos.
     */
    public void insertar(String tabla, String campos, String[] valores) {
        // Genera los '?' dinámicamente según la cantidad de valores
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            placeholders.append("?");
            if (i < valores.length - 1) placeholders.append(",");
        }

        String sql = "INSERT INTO " + tabla + " (" + campos + ") VALUES (" + placeholders.toString() + ")";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            for (int i = 0; i < valores.length; i++) {
                ps.setString(i + 1, valores[i]);
            }
            ps.executeUpdate();
            System.out.println("Registro insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }

    public void eliminar(String tabla, String campo, String valor) {
        String sql = "DELETE FROM " + tabla + " WHERE " + campo + " = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, valor);
            int filas = pstmt.executeUpdate();
            System.out.println("Registros eliminados: " + filas);
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    public void modificar(String tabla, String campoSet, String nuevoValor, String campoDonde, String valorDonde) {
        String sql = "UPDATE " + tabla + " SET " + campoSet + " = ? WHERE " + campoDonde + " = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevoValor);
            ps.setString(2, valorDonde);
            int filas = ps.executeUpdate();
            System.out.println("Registros actualizados: " + filas);
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        }
    }
}