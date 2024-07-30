public class Cuboide extends Rectangulo {
    private final int profundidad;

    public Cuboide(int ancho, int alto, int profundidad) {
        super(ancho, alto);
        this.profundidad = profundidad;
    }

    public int getVolumen() {
        return ancho * alto * profundidad;
    }
}

// Se viola el principio de DRY, como Rectangulo es SuperClase, cuboide hereda
// sus métodos y comportamiento, entre esos getArea() == ancho * alto