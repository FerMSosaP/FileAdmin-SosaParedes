import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Practica_01_y_02 {

    private static final String ARCHIVO = "notas.txt";
    private static final String ARCHIVO_ASISTENCIA = "asistencia.txt";

    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("===== SISTEMA DE NOTAS =====");

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Ver todas las notas");
            System.out.println("3. Calcular promedio");
            System.out.println("4. Agregar alumno/asistencia");
            System.out.println("5. Ver alumnos y asistencias");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarNota(scanner);
                        break;

                    case 2:
                        verNotas();
                        break;

                    case 3:
                        double promedio = calcularPromedio();
                        System.out.println("\nPromedio: " + promedio);
                        break;

                    case 4:
                        agregarAlumnoAsitencia(scanner);
                        break;

                    case 5:
                        verVerAlumno();
                        break;

                    case 6:
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
                opcion = 0;
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static void agregarNota(Scanner scanner) {
        System.out.print("\nEscribe tu nota: ");

        try {
            double nota = Double.parseDouble(scanner.nextLine());

            guardarNota(nota);

            System.out.println("Nota guardada.");

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void verNotas() {
        try {
            ArrayList<Double> notas = leerNotas();

            if (notas.isEmpty()) {
                System.out.println("No hay notas registradas.");
                return;
            }

            System.out.println("\n--- Notas registradas ---");

            for (double nota : notas) {
                System.out.println(nota);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static double calcularPromedio() {
        try {
            ArrayList<Double> notas = leerNotas();

            if (notas.isEmpty()) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            double suma = 0;

            for (double nota : notas) {
                suma += nota;
            }

            return suma / notas.size();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void guardarNota(double nota)
            throws IOException, ClassNotFoundException {

        ArrayList<Double> notas = leerNotas();

        notas.add(nota);

        ObjectOutputStream escritor =
                new ObjectOutputStream(new FileOutputStream(ARCHIVO));

        escritor.writeObject(notas);

        escritor.close();
    }

    private static ArrayList<Double> leerNotas()
            throws IOException, ClassNotFoundException {

        File archivo = new File(ARCHIVO);

        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        ObjectInputStream lector =
                new ObjectInputStream(new FileInputStream(archivo));

        ArrayList<Double> notas =
                (ArrayList<Double>) lector.readObject();

        lector.close();

        return notas;
    }

    private static void agregarAlumnoAsitencia(Scanner scanner) {

        System.out.println("\n--- Coloque el nombre del alumno para poner asistencia ---");
        System.out.print("Nombre del alumno: ");
        String nombre = scanner.nextLine();

        try {

            LinkedHashMap<String, Integer> alumnos =
                    leerAsistencias();

            if (alumnos.containsKey(nombre)) {

                int asistencias = alumnos.get(nombre);

                alumnos.put(nombre, asistencias + 1);

            } else {

                alumnos.put(nombre, 1);
            }

            guardarAsistencias(alumnos);

            System.out.println("Asistencia guardada.");

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static void verVerAlumno() {

        try {

            LinkedHashMap<String, Integer> alumnos =
                    leerAsistencias();

            if (alumnos.isEmpty()) {

                System.out.println("No hay alumnos registrados.");
                return;
            }

            int totalAsistencias = 0;

            System.out.println("\n--- Lista de alumnos ---");

            for (Map.Entry<String, Integer> TotalAsisteciaalumno :
                    alumnos.entrySet()) {

                System.out.println(
                        TotalAsisteciaalumno.getKey()
                                + " - Total Asistencias: "
                                + TotalAsisteciaalumno.getValue()
                );
            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static void guardarAsistencias(
            LinkedHashMap<String, Integer> alumnos)
            throws IOException {

        ObjectOutputStream escritor =
                new ObjectOutputStream(
                        new FileOutputStream(ARCHIVO_ASISTENCIA));

        escritor.writeObject(alumnos);

        escritor.close();
    }


    private static LinkedHashMap<String, Integer> leerAsistencias()
            throws IOException, ClassNotFoundException {

        File archivo = new File(ARCHIVO_ASISTENCIA);

        if (!archivo.exists() || archivo.length() == 0) {

            return new LinkedHashMap<>();
        }

        ObjectInputStream lector =
                new ObjectInputStream(
                        new FileInputStream(archivo));

        LinkedHashMap<String, Integer> alumnos =
                (LinkedHashMap<String, Integer>) lector.readObject();

        lector.close();

        return alumnos;
    }
}