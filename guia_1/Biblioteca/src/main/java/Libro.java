import java.util.ArrayList;

public class Libro {
    public String codigo;
    public String titulo;
    public String fecha;
    public ArrayList<String> autores;

    public Libro(String codigo, String titulo, String fecha, ArrayList<String> autores) {
       this.codigo = codigo;
       this.titulo = titulo;
       this.fecha = fecha;
       this.autores = autores;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }
}
