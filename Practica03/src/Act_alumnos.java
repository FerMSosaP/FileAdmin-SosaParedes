import java.io.*;
import java.util.ArrayList;

class Act_alumnos {

    public static void escribirAlumnos(){
        var alumnos = new ArrayList<Alumno>();
        alumnos.add(new Alumno("5799", "Sergio",9.7));
        alumnos.add(new Alumno("3466", "Diego",9));
        alumnos.add(new Alumno("2388", "Fernando",8.5));
        alumnos.add(new Alumno("2388", "Jaime",10));

        try (var fos = new FileOutputStream("AlumnosDB.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(alumnos);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarAlumnos(){

        var alumnos = new ArrayList<Alumno>();
        try (var fis = new FileInputStream("AlumnosDB.txt");
             ObjectInputStream cis = new ObjectInputStream(fis)){
            ArrayList<Alumno> ListaAlumnos = (ArrayList<Alumno>) cis.readObject();
            System.out.println("Total de Estudiantes: Los alumnos son "+ListaAlumnos.size()+"."+'\n'+ListaAlumnos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}