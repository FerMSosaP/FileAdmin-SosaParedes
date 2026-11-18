package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String FILE_PAIN = "alumnosBD.csv";
    private static final ArrayList<Alumno> alumnos = new ArrayList<>();
    private static final Scanner kb = new Scanner(System.in);

    public static void main (String[] args){
        String option;
        System.out.println("Bienvenidos a la BD Alumnos-UACAM");
        do {
            System.out.print("\nIngrese un comando: ");
            option = kb.nextLine().trim().toUpperCase();

            switch (option) {
                case "\"_AGREGAR" -> agregarAlumnos();
                case "\"_MOSTRAR" -> listaAlumnos();
                case "\"_MODIFICAR" -> modificarAlumno();
                case "\"_ELIMINAR" -> eliminarAlumno();
                case "\"_MENU" -> mostrarMenu();
                case "\"_GUARDAR" -> guardarCSV();
                case "\"_CARGAR" -> cargarCSV();
                case "\"_SALIR" -> System.out.println("Saliendo de la Base de Datos...");
                //CRUD -> Create Read Update Delete
                default -> System.out.println("Comando no reconocido.");
            }

        } while (!option.equals("\"_SALIR"));

    }

    public static int readOption(String msg){
        while (true){
            try {
                System.out.print(msg);
                return Integer.parseInt(kb.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Debe ingresar un numero valido");
            }
        }
    }

    private static void agregarAlumnos() {

        System.out.println("\n===========================");
        try {
            int id = readOption("Ingrese el ID: ");

            for (Alumno alumno : alumnos) {
                if (alumno.getId() == id) {
                    System.out.println("Ya existe un alumno con ese ID.");
                    return;
                }
            }


            System.out.print("Ingrese el nombre: ");
            String name = kb.nextLine().trim();

            int age = readOption("Ingrese la edad: ");

            System.out.print("Ingrese el correo: ");
            String mail = kb.nextLine().trim();

            alumnos.add(new Alumno(id, name, age, mail));

            System.out.println("Alumno agregado correctamente.");
            System.out.println("---------------------------");

        } catch (Exception e) {
            System.out.println("Error al agregar alumno: " + e.getMessage());
        }
    }

    private static void listaAlumnos() {

        try {
            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos registrados.");
                return;
            }

            System.out.println("\n===== LISTA DE ALUMNOS =====");

            for (Alumno alumno : alumnos) {
                System.out.println("ID: " + alumno.getId());
                System.out.println("Nombre: " + alumno.getName());
                System.out.println("Edad: " + alumno.getAge());
                System.out.println("Correo: " + alumno.getMail());
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar alumnos: " + e.getMessage());
        }
    }

    private static void modificarAlumno() {
        System.out.println("\n===========================");
        try {
            int id = readOption("Ingrese el ID del alumno a modificar: ");

            for (int i = 0; i < alumnos.size(); i++) {

                if (alumnos.get(i).getId() == id) {

                    System.out.println("Alumno encontrado.");

                    System.out.print("Ingrese el nuevo nombre: ");
                    String name = kb.nextLine().trim();

                    int age = readOption("Ingrese la nueva edad: ");

                    System.out.print("Ingrese el nuevo correo: ");
                    String mail = kb.nextLine().trim();

                    alumnos.set(i, new Alumno(id, name, age, mail));

                    System.out.println("Alumno modificado correctamente.");
                    System.out.println("---------------------------");
                    return;
                }
            }

            System.out.println("No se encontró un alumno con ese ID.");

        } catch (Exception e) {
            System.out.println("Error al modificar alumno: " + e.getMessage());
        }
    }

    private static void eliminarAlumno() {
        try {
            int id = readOption("Ingrese el ID del alumno a eliminar: ");

            for (int i = 0; i < alumnos.size(); i++) {

                if (alumnos.get(i).getId() == id) {
                    alumnos.remove(i);

                    System.out.println("Alumno eliminado correctamente.");
                    return;
                }
            }

            System.out.println("No se encontró un alumno con ese ID.");

        } catch (Exception e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("Mostrando menú...");

        System.out.println("\n===== BASE DE DATOS ALUMNOS UACAM =====");
        System.out.println("1. \"_AGREGAR - Agregar alumno");
        System.out.println("2. \"_MOSTRAR - Listar alumnos");
        System.out.println("3. \"_MODIFICAR - Modificar alumno");
        System.out.println("4. \"_ELIMINAR - Eliminar alumno");
        System.out.println("5. \"_MENU - Mostrar menú");
        System.out.println("6. \"_GUARDAR - Guarda la lista");
        System.out.println("7. \"_CARGAR - Carga la lista");
        System.out.println("8. \"_SALIR - Salir");
        System.out.println("======================================");

    }

    public static void guardarCSV(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PAIN))) {

            writer.write("id,nombre,edad,correo");
            writer.newLine();

            for (Alumno alumno : alumnos) {
                writer.write(
                        alumno.getId() + "," +
                                alumno.getName() + "," +
                                alumno.getAge() + "," +
                                alumno.getMail()
                );

                writer.newLine();
            }

            System.out.println("Archivo CSV guardado correctamente. (^u^)7");

        } catch (java.io.IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static void cargarCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PAIN))) {

            String linea;
            boolean primeraLinea = true;

            while ((linea = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(",", -1);

                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0]);
                    String name = datos[1];
                    int age = Integer.parseInt(datos[2]);
                    String mail = datos[3];

                    alumnos.add(new Alumno(id, name, age, mail));
                }
            }

            System.out.println("Alumnos cargados correctamente. \\(*.*)/");

        } catch (java.io.FileNotFoundException e) {
            System.out.println("No existe una base de datos anterior. Se creará una nueva.");

        } catch (java.io.IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
}