
import java.io.*;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Main {

    public static void guardar(DataOutputStream archivo, String texto)
            throws IOException {
        archivo.writeUTF(texto);
        archivo.flush();
    }

    public static void mostrar(DataOutputStream archivo, String texto)
            throws IOException {
        System.out.println(texto);
        guardar(archivo, "SALIDA: " + texto);
    }

    public static void mostrarSinSalto(DataOutputStream archivo, String texto)
            throws IOException {
        System.out.print(texto);
        guardar(archivo, "SALIDA: " + texto);
    }

    public static String leer(BufferedReader br, DataOutputStream archivo)
            throws IOException {

        String entrada = br.readLine();

        if (entrada != null) {
            guardar(archivo, "ENTRADA: " + entrada);
        }

        return entrada;
    }

    public static void main(String[] args) {

        try (
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(System.in));

                DataOutputStream archivo = new DataOutputStream(
                        new FileOutputStream("registro.bin", true))
        ) {

            guardar(archivo, "=== INICIO DE SESIÓN: "
                    + LocalDateTime.now() + " ===");

            String ch;

            do {
                mostrarSinSalto(archivo, "\n>>> ");

                ch = leer(br, archivo);

                if (ch == null) {
                    break;
                }

                switch (ch.trim()) {

                    case "Hr_pls()" -> horaActual(archivo);

                    case "Th3_c4t()" -> mostrarGato(archivo);

                    case "Plus()" -> sumar(archivo, br);

                    case "help" -> mostrarMenu(archivo);

                    case "exit()" -> mostrar(archivo,
                            "Finalizando programa...");

                    default -> mostrar(archivo,
                            "Comando no reconocido. Escribe help para ver los comandos.");
                }

            } while (!ch.equalsIgnoreCase("exit()"));

            guardar(archivo, "=== FIN DE SESIÓN ===");

        } catch (IOException e) {
            System.err.println("Error al trabajar con el archivo binario: "
                    + e.getMessage());
        }
    }

    public static void horaActual(DataOutputStream archivo)
            throws IOException {

        LocalTime hora = LocalTime.now();

        mostrar(archivo, "Hora actual: " + hora);
    }

    public static void mostrarGato(DataOutputStream archivo)
            throws IOException {

        String[] gato = {
                "⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠟⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿",
                "⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿",
                "⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣧⠀⠀⠀⠀⣠⣾⣿⣿⣿⣆⠀⠀⠀⠀⠀⣠⣾⣿⣿⣆⠀⠀⢀⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⠀⠀⠀⢸⣿⣿⣿⡃⠀⣿⡀⠀⠀⠀⠀⣿⠁⠈⣿⣿⡀⠀⠈⣿⣿⣿⣿⣿",
                "⣿⣿⣿⡟⠀⠀⠀⠈⢿⣿⣿⣷⣴⡿⠀⠀⠀⠀⠀⢿⣦⣴⣿⡿⠁⠀⠀⣿⣿⣿⣿⣿",
                "⡿⠿⠿⠿⠀⠀⠀⠀⠀⠉⠉⠉⠉⠀⠀⠀⠀⠁⠀⠈⠙⠛⠋⠀⠀⠀⠀⠛⣛⡛⠛⢻",
                "⣷⣶⣶⡶⠦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠿⠿⢿⣿",
                "⣯⣥⣴⣶⡿⠓⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠚⠿⢿⣷⣿⣷⣤",
                "⣿⣿⣋⣥⣶⣿⣿⣿⣶⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⣾⣿⣿⣿⣶⣤⣝⣿⣿",
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿",
                "⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿"
        };

        mostrar(archivo, "");

        for (String linea : gato) {
            mostrar(archivo, linea);
        }

        mostrar(archivo, "");
    }

    public static void sumar(DataOutputStream archivo, BufferedReader br)
            throws IOException {

        try {

            mostrarSinSalto(archivo,
                    "¿Cuántos números deseas sumar?: ");

            int cantidad = Integer.parseInt(leer(br, archivo));

            if (cantidad <= 0) {
                mostrar(archivo, "Debes ingresar una cantidad mayor que cero.");
                return;
            }

            double sumaTotal = 0;
            String expresion = "";

            mostrar(archivo,
                    "\nIngresa los números uno a uno:");

            for (int i = 1; i <= cantidad; i++) {

                mostrarSinSalto(archivo,
                        "Número " + i + ": ");

                double valor = Double.parseDouble(leer(br, archivo));

                sumaTotal += valor;

                if (i == 1) {
                    expresion += valor;
                } else {
                    expresion += " + " + valor;
                }
            }

            mostrar(archivo, "\n===== RESULTADO =====");
            mostrar(archivo, expresion + " = " + sumaTotal);

        } catch (NumberFormatException e) {

            mostrar(archivo,
                    "¡Debes ingresar únicamente números válidos!");

        } catch (Exception e) {

            mostrar(archivo,
                    "Ocurrió un error al realizar la suma: " + e.getMessage());
        }
    }


    public static void mostrarMenu(DataOutputStream archivo)
            throws IOException {

        mostrar(archivo, "");
        mostrar(archivo, "========== COMANDOS ==========");
        mostrar(archivo, "1. help       - Mostrar menú");
        mostrar(archivo, "2. Hr_pls()   - Mostrar hora actual");
        mostrar(archivo, "3. Th3_c4t()  - Mostrar gato ASCII");
        mostrar(archivo, "4. Plus()     - Sumar números");
        mostrar(archivo, "5. exit()     - Salir del programa");
        mostrar(archivo, "==============================");
    }
}
