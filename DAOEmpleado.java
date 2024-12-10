package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEmpleado {

    private Connection conexion;

    public DAOEmpleado() {
        conexion = DB.getConexion();
    }

    public ArrayList<Empleado> getAllEmpleados() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM empleados");
        ResultSet resultados = sentencia.executeQuery();
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();

        while (resultados.next()) {
            int idEmpleado = resultados.getInt("id");
            String nombreEmpleado = resultados.getString("nombre");
            int edadEmpleado = resultados.getInt("edad");
            int idDepartamento = resultados.getInt("dpto_id");

            Departamento departamento = getDepartamentoById(idDepartamento);

            Empleado empleado = new Empleado(idEmpleado, nombreEmpleado, edadEmpleado, departamento);
            listaEmpleados.add(empleado);
        }
        return listaEmpleados;
    }


    private Departamento getDepartamentoById(int id) throws SQLException {
        String query = "SELECT * FROM departamento WHERE id = ?";
        PreparedStatement sentencia = conexion.prepareStatement(query);
        sentencia.setInt(1, id);
        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            int idDepartamento = resultado.getInt("id");
            String nombreDepartamento = resultado.getString("nombre");
            return new Departamento(idDepartamento, nombreDepartamento);
        }
        return null;
    }

    public Empleado getEmpleadoById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM empleados WHERE id = ?");
        sentencia.setInt(1, id);
        ResultSet resultados = sentencia.executeQuery();

        if (resultados.next()) {
            Departamento departamento = new Departamento(
                    resultados.getInt("departamento_id"),
                    resultados.getString("departamento_nombre")
            );

            return new Empleado(
                    resultados.getInt("id"),
                    resultados.getString("nombre"),
                    resultados.getInt("edad"),
                    departamento
            );
        }

        return null;
    }

    public int addEmpleado(Empleado empleado) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement(
                "INSERT INTO empleados (nombre, edad, dpto_id) VALUES (?, ?, ?)"
        );
        sentencia.setString(1, empleado.getNombre());
        sentencia.setInt(2, empleado.getEdad());
        sentencia.setInt(3, empleado.getDpto_id().getId());
        return sentencia.executeUpdate();
    }

    public int updateEmpleado(Empleado empleado) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement(
                "UPDATE empleados SET nombre = ?, edad = ?, dpto_id = ? WHERE id = ?"
        );
        sentencia.setString(1, empleado.getNombre());
        sentencia.setInt(2, empleado.getEdad());
        sentencia.setInt(3, empleado.getDpto_id().getId());
        sentencia.setInt(4, empleado.getId());
        return sentencia.executeUpdate();
    }

    public int deleteEmpleadoById(int id) throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement(
                "DELETE FROM empleados WHERE id = ?"
        );
        sentencia.setInt(1, id);
        return sentencia.executeUpdate();
    }

    public int deleteAllEmpleados() throws SQLException {
        PreparedStatement sentencia = conexion.prepareStatement("DELETE * FROM empleados");
        return sentencia.executeUpdate();
    }
    
}
