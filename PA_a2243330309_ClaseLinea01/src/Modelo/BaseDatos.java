package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class BaseDatos {
    private Connection conexion;

    public BaseDatos(Connection conexion) {
        this.conexion = conexion;
    }

    public ArrayList<Object[]> consultar(String tabla, String[] columnas, String condicion) throws SQLException {
        ArrayList<Object[]> datos = new ArrayList<>();
        String sql = "SELECT " + (columnas == null ? "*" : String.join(", ", columnas)) + " FROM " + tabla;
        if (condicion != null) sql += " WHERE " + condicion;

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int numColumnas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object[] fila = new Object[numColumnas];
                for (int i = 0; i < numColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                datos.add(fila);
            }
        }
        return datos;
    }
}