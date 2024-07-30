import fiuba.Carrera;
import fiuba.Materia;

public class Main {
    public static void main(String[] args) {
        Materia algo1 = new Materia("75.40","Algoritmos 1", 6);
        Materia fisica2 = new Materia("62.03", "Fisica 2", 8);
        Materia quimica = new Materia("32.14", "Quimica", 6);

        Carrera informatica = new Carrera("Ingenieria en informatica","4", new Materia[] {algo1, fisica2}, new Materia[] {quimica}, 16 );
        System.out.println("Ingrese su padron");
        String padron = System.console().readLine();
        Alumno alumno1 = new Alumno(Integer.parseInt(padron));
        System.out.println("Las carreras disponibles son:");
        informatica.mostrarCarrera();

        System.out.println("Ingrese el codigo de la carrera a la que desea inscribirse");
        String codigo = System.console().readLine();
        if (codigo.equals(informatica.obtenerCodigo())) {
            boolean ok = alumno1.inscribir(informatica);
            if (!ok) {
                System.out.println("No se pudo realizar la inscripcion");
                return;
            }
        }
        System.out.println("Materias disponibles:");
        algo1.mostrarMateria();
        fisica2.mostrarMateria();
        quimica.mostrarMateria();

        System.out.println("Ingrese el codigo de la materia que desea marcar como aprobada");
        String codigoMateria = System.console().readline();
        if (codigoMateria.equals(algo1.obtenerCodigo())) {
            boolean ok = alumno1.aprobar(algo1)
            if (!ok) {
                System.out.println("No pude aprobar :(");
                return;
            }
            String estadoCarrera = alumno1.obtenerEstadoCarrera(informatica);
            System.out.println(estadoCarrera);
        }

    }

}
