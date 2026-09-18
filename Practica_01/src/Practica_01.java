import java.io.*;
import java.util.Scanner;

public class Practica_01 {

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
                        System.out.println("¡Bye bye!");
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
        String nota = scanner.nextLine();

        try {
            guardarNota(nota);
            System.out.println("Nota guardada.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void verNotas() {
        String notas = "";

        try {
            notas = leerNotas();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(notas);
    }


    private static double calcularPromedio() {
        try {
            String notas = leerNotas();

            if (notas.trim().isEmpty()) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            String[] lineas = notas.split("\\R");

            double suma = 0;
            int totalNotas = 0;

            for (String linea : lineas) {
                if (!linea.trim().isEmpty()) {
                    double nota = Double.parseDouble(linea.trim());
                    suma += nota;
                    totalNotas++;
                }
            }

            if (totalNotas == 0) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            return suma / totalNotas;

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;

        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }


    private static void guardarNota(String nota) throws IOException {
        FileWriter archivo = new FileWriter("notas.txt", true);
        BufferedWriter escritor = new BufferedWriter(archivo);

        escritor.write(nota);
        escritor.newLine();

        escritor.close();
    }


    private static String leerNotas() throws IOException {
        File archivo = new File("notas.txt");

        if (!archivo.exists()) {
            return "";
        }

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        StringBuilder notas = new StringBuilder();
        String linea;

        while ((linea = lector.readLine()) != null) {
            notas.append(linea).append("\n");
        }

        lector.close();

        return notas.toString();
    }
}