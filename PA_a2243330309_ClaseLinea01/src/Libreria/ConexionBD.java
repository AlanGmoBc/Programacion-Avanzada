package Libreria;

import java.sql.*;

public class ConexionBD {
    
    private static Connection conexion;

    // Métodos de conexión
    public static Connection conectarSQLServer(String[] parametros) throws SQLException {
        try {
            // Cargar el driver explícitamente
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Estructura: jdbc:sqlserver://host:puerto;databaseName=nombre;seguridad
            // parametros[0]=host, [1]=db, [2]=user, [3]=pass
            String cadenaconexion = "jdbc:sqlserver://" + parametros[0] + ":1433;databaseName=" + parametros[1] 
                                    + ";encrypt=true;trustServerCertificate=true;";
            
            conexion = DriverManager.getConnection(cadenaconexion, parametros[2], parametros[3]);
            System.out.println(">>> Conexión exitosa a SQL Server (Puerto 1433)");
            return conexion;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de SQL Server no encontrado.");
            return null;
        }
    }

    public static Connection conectarMySQL(String[] parametros) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String cadenaconexion = "jdbc:mysql://" + parametros[0] + "/" + parametros[1];
            conexion = DriverManager.getConnection(cadenaconexion, parametros[2], parametros[3]);
            System.out.println(">>> Conexión exitosa a MySQL");
            return conexion;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MySQL no encontrado.");
            return null;
        }
    }

    // Método universal para cerrar la conexión actual
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println(">>> Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}