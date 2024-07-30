public class ProductoElectronico implements ProductoSol {
    private String nombre;
    private double precio;
    public ProductoElectronico(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public double calcularPrecio() {
        return precio * 1.1;
    }
}
