public class Producto {
    private String nombre;
    private double precio;
    private String tipo;

    public Producto(String nombre, double precio, String tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public double calcularPrecio() {
        if (tipo.equals("electrónica")) {
            return precio * 1.1;  // agregar impuesto de 10%
        } else if (tipo.equals("ropa")) {
            return precio * 1.2;  // agregar impuesto de 20%
        } else {
            return precio;
        }
    }
}

// Rompe con el principio Open/Closed