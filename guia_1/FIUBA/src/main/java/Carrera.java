package fiuba;
public class Carrera {
    private final String nombre;
    private final Materia[] materiasObligatorias;
    private final Materia[] materiasOptativas;
    private final String codigo;
    private final int creditosNecesarios;

    public Carrera(String nombre, String codigo, Materia[] materiasObligatorias, Materia[] materiasOptativas, int creditosNecesarios) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.materiasObligatorias = materiasObligatorias;
        this.materiasOptativas = materiasOptativas;
        this.creditosNecesarios = creditosNecesarios;
    }

    public void mostrarCarrera() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.println("Materias obligatorias: ");
        for (int i = 0; i < materiasObligatorias.length; i++) {
            materiasObligatorias[i].mostrarMateria();
        }
        System.out.println("Materias optativas: ");
        for (int i = 0; i < materiasOptativas.length; i++) {
            materiasOptativas[i].mostrarMateria();
        }
    }

    public String obtenerCodigo() {
        return codigo;
    }
}
