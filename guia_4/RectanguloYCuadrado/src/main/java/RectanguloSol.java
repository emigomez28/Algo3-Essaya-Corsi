public class RectanguloSol implements Figura {
    private final int base;
    private final int altura;
    public RectanguloSol(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }
    public int calcularArea() {
        return base * altura;
    }
}
