public class Main {

    public static void main(String[] args) {

        // Guarda inicialmente la lista de 4 alumnos.
        GestionAlumnos.escribirAlumnos();

        System.out.println("=== LISTA DE ALUMNOS ===");
        GestionAlumnos.mostrarAlumnos();

        System.out.println("\n=== BUSCAR POR MATRÍCULA ===");
        Alumno encontrado = GestionAlumnos.buscarPorMatricula("3466");

        if (encontrado != null) {
            System.out.println("Alumno encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("No se encontró un alumno con esa matrícula.");
        }

        System.out.println("\n=== AGREGAR ALUMNO ===");
        GestionAlumnos.AgregarAlumno();

        System.out.println("\n=== LISTA ACTUALIZADA ===");
        GestionAlumnos.mostrarAlumnos();
    }
}
