public class ProductoRopa implements ProductoSol {
    private String nombre;
    private final double precio;
    public ProductoRopa(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public double calcularPrecio() {
        return precio * 1.2;
    }
}
