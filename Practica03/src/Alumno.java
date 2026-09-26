import java.io.Serializable;

public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    private String matricula;
    private String nombre;
    private double promedio;

    public Alumno() {
    }

    public Alumno(String matricula, String nombre, double promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Nombre=" + nombre +
                ", matricula=" + matricula +
                ", promedio=" + promedio;
    }
}
