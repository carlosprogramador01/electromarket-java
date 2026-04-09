package com.electromarket.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL      = "jdbc:mysql://localhost:3306/electromarketdb";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado -> " + e.getMessage());
        }
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexion -> " + e.getMessage());
            }
        }
    }

}