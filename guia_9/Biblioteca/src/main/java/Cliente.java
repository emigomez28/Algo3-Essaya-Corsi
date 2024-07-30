import java.util.ArrayList;

public class Cliente {
    public String nombre;
    private ArrayList<Libro> librosPrestados;

    public Cliente(String nombre, ArrayList<Libro> librosPrestados) {
        this.nombre = nombre;
        this.librosPrestados = librosPrestados;
    }

    public String getNombre() {
        return nombre;
    }

    public void borrarLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

}
