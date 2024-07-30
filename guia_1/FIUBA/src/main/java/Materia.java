package fiuba;

import java.net.StandardSocketOptions;

public class Materia {
    private final String codigo;
    private final String nombre;
    private final int creditos;

    public Materia(String codigo, String nombre, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public void mostrarMateria() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Creditos: " + creditos);
    }

    public int obtenerCreditos() {
        return creditos;
    }

    public String obtenerCodigo() {
        return codigo;
    }
}
