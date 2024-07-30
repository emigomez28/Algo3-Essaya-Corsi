public class CuboideSol extends Rectangulo {
    private final int profundidad;
    public CuboideSol(int ancho, int alto, int profundidad) {
        super(ancho, alto);
        this.profundidad = profundidad;
    }

    public int getVolumen() {
        return getArea() * profundidad;
    }
}
