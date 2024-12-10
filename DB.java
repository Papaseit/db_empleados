package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:mysql://localhost:3307/db_empleados";
    private static String user = "root";
    private static String password = "Papaseit_23";
    private static Connection conexion = null;



    public static Connection getConexion(){
        if (conexion == null){
            try {
                conexion = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion establecida");
                System.out.println(conexion.getCatalog());
            }catch (SQLException e){
                System.out.println(e);
            }
        }
        return conexion;
    }
}
