package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuEmpleado {

    private DAOEmpleado daoEmpleado;

    public MenuEmpleado() {
        this.daoEmpleado = new DAOEmpleado();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Gestión de Empleados");
            System.out.println("1. Mostrar todos los empleados");
            System.out.println("2. Añadir un empleado");
            System.out.println("3. Modificar un empleado por ID");
            System.out.println("4. Eliminar un empleado por ID");
            System.out.println("5. Eliminar todos los empleados");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("Lista de empleados:");
                        daoEmpleado.getAllEmpleados().forEach(empleado ->
                                System.out.println("ID: " + empleado.getId() + ", Nombre: " + empleado.getNombre() +
                                        ", Edad: " + empleado.getEdad() +
                                        ", Departamento: " + empleado.getDpto_id().getNombre())
                        );
                    } catch (SQLException e) {
                        System.err.println("Error al mostrar los empleados: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Escribe el nombre del empleado: ");
                    scanner.nextLine();
                    String nombre = scanner.nextLine();
                    System.out.print("Escribe la edad del empleado: ");
                    int edad = scanner.nextInt();
                    System.out.print("Escribe el ID del departamento: ");
                    int idDepto = scanner.nextInt();

                    Departamento departamento = new Departamento(idDepto, "");
                    Empleado nuevoEmpleado = new Empleado(0, nombre, edad, departamento);

                    try {
                        daoEmpleado.addEmpleado(nuevoEmpleado);
                        System.out.println("Empleado añadido correctamente.");
                    } catch (SQLException e) {
                        System.err.println("Error al añadir el empleado: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Escribe el ID del empleado a modificar: ");
                    int idModificar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Escribe el nuevo nombre del empleado: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Escribe la nueva edad del empleado: ");
                    int nuevaEdad = scanner.nextInt();
                    System.out.print("Escribe el nuevo ID del departamento: ");
                    int nuevoIdDepto = scanner.nextInt();

                    Departamento nuevoDepto = new Departamento(nuevoIdDepto, "");
                    Empleado empleadoModificado = new Empleado(idModificar, nuevoNombre, nuevaEdad, nuevoDepto);

                    try {
                        daoEmpleado.updateEmpleado(empleadoModificado);
                        System.out.println("Empleado modificado correctamente.");
                    } catch (SQLException e) {
                        System.err.println("Error al modificar el empleado: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Escribe el ID del empleado a eliminar: ");
                    int idEliminar = scanner.nextInt();

                    try {
                        daoEmpleado.deleteEmpleadoById(idEliminar);
                        System.out.println("Empleado eliminado correctamente.");
                    } catch (SQLException e) {
                        System.err.println("Error al eliminar el empleado: " + e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        daoEmpleado.deleteAllEmpleados();
                        System.out.println("Todos los empleados han sido eliminados.");
                    } catch (SQLException e) {
                        System.err.println("Error al eliminar los empleados: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del menú.");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
