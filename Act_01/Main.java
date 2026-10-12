import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[]args){

        try (FileOutputStream fos = new FileOutputStream("El Jeloudas.txt")) {
            fos.write("J3loU w0r1d".getBytes());
            System.out.println("Texto, di: J3loU w0r1d");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}