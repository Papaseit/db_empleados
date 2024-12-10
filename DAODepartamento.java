package org.example;

import java.sql.Connection;

public class DAODepartamento {
    Connection conexion;

    Departamento departamento;

    public DAODepartamento() {
        conexion = DB.getConexion();


    }
}
