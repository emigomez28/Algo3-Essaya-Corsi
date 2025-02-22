import java.util.ArrayList;

public class Alumno {
    private final int padron;
    private ArrayList<Carrera> carrerasActivas;
    private ArrayList<Materia> materiasAprobadas;

    public Alumno(int padron) {
        this.padron = padron;
        this.carrerasActivas = new ArrayList<Carrera>();
        this.materiasAprobadas = new ArrayList<Materia>();
    }

    public boolean inscribir(Carrera carrera) {
        if (carrerasActivas.contains(carrera)) {
            return false;
        }
        carrerasActivas.add(carrera);
        return true;
    }

    public boolean aprobar(Materia materia) {
        if (materiasAprobadas.contains(materia)) {
            return false;
        }
        materiasAprobadas.add(materia);
        return true;
    }

    public String obtenerEstadoCarrera(Carrera info) {
        int creditos = 0;
        System.out.println("Su padron es: " + padron);
        for (int i = 0; i < materiasAprobadas.size(); i++) {
            creditos += materiasAprobadas.get(i).obtenerCreditos();
        }
        return "Tenes %d creditos".formatted(creditos);
    }
}
