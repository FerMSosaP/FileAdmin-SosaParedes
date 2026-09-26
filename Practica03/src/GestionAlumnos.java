import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionAlumnos {

    private static final String ARCHIVO = "AlumnosDB.txt";

    public static void escribirAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        alumnos.add(new Alumno("5799", "Sergio", 9.7));
        alumnos.add(new Alumno("3466", "Diego", 9.0));
        alumnos.add(new Alumno("2388", "Fernando", 8.5));
        alumnos.add(new Alumno("2388", "Jaime", 10.0));

        guardarAlumnos(alumnos);
    }

    public static void mostrarAlumnos() {
        ArrayList<Alumno> alumnos = leerAlumnos();

        System.out.println("Total de estudiantes: " + alumnos.size());

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }

    public static Alumno buscarPorMatricula(String mat) {
        ArrayList<Alumno> alumnos = leerAlumnos();

        for (Alumno alumno : alumnos) {
            if (alumno.getMatricula().equals(mat)) {
                return alumno;
            }
        }

        return null;
    }

    public static void AgregarAlumno() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Promedio: ");
        double promedio = Double.parseDouble(scanner.nextLine());

        ArrayList<Alumno> alumnos = leerAlumnos();
        alumnos.add(new Alumno(matricula, nombre, promedio));

        guardarAlumnos(alumnos);

        System.out.println("Alumno agregado correctamente.");
    }

    private static ArrayList<Alumno> leerAlumnos() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (FileInputStream fis = new FileInputStream(archivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (ArrayList<Alumno>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer los alumnos: " + e.getMessage(), e);
        }
    }

    private static void guardarAlumnos(ArrayList<Alumno> alumnos) {
        try (FileOutputStream fos = new FileOutputStream(ARCHIVO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(alumnos);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los alumnos: " + e.getMessage(), e);
        }
    }
}
