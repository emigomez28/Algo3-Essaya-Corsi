public class ProductoGenerico implements ProductoSol {
      private String nombre;
      private final double precio;
      public ProductoGenerico(String nombre, double precio) {
          this.nombre = nombre;
          this.precio = precio;
      }

      public double calcularPrecio() {
           return precio;
      }
}


