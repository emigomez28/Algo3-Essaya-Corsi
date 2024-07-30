public class CuadradoSol implements Figura {
    private final int lado;
    public CuadradoSol(int lado) {
        this.lado = lado;
    }

    @Override
    public int calcularArea() {
        return lado * lado;
    }
}
