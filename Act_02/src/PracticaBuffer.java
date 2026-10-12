import java.io.*;

public class PracticaBuffer {
    public static void ejecutarStream(){
        long inicio = System.nanoTime();

        try (FileOutputStream fos = new FileOutputStream("El Jeloudas.txt")) {
            for (int i = 0; i < 100000000; i++){
                fos.write("J3loU w0r1d".getBytes());
            }
            System.out.println("Di: \"J3loU w0r1d\"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long fin = System.nanoTime();
        long duracionNs = fin - inicio;
        double duracionMs = duracionNs / 1_000_000.0;

        System.out.println("Duración: " + duracionNs + " ns (" + duracionMs + " ms)");
    }

    public static void ejecutarBuffer(){
        long inicio = System.nanoTime();

        try (BufferedOutputStream fos = new BufferedOutputStream (new FileOutputStream("El Jeloudas.txt"))) {
            for (int i = 0; i < 1000000000; i++){
                fos.write("J3loU w0r1d".getBytes());
            }
            System.out.println("Di: \"J3loU w0r1d\"");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long fin = System.nanoTime();
        long duracionNs = fin - inicio;
        double duracionMs = duracionNs / 1_000_000.0;

        System.out.println("Duración: " + duracionNs + " ns (" + duracionMs + " ms)");
    }

}
