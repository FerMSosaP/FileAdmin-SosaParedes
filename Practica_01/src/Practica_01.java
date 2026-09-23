import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Practica_01 {

    private static final String ARCHIVO = "notas.txt";

    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("===== SISTEMA DE NOTAS =====");

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Ver todas las notas");
            System.out.println("3. Calcular promedio");
            System.out.println("4. Salir");
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
                        System.out.println("Promedio: " + promedio);
                        break;

                    case 4:
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
                opcion = 0;
            }

        } while (opcion != 4);

        scanner.close();
    }

    private static void agregarNota(Scanner scanner) {
        System.out.print("Escribe tu nota: ");

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
}