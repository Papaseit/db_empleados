package org.example;

public class Empleado {
    private int id;

    private String nombre;

    private int edad;

    private Departamento dpto_id;


    public Empleado(int id, String nombre, int edad, Departamento dpto_id) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dpto_id = dpto_id;
    }

    public Empleado() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Departamento getDpto_id() {
        return dpto_id;
    }

    public void setDpto_id(Departamento dpto_id) {
        this.dpto_id = dpto_id;
    }
}
