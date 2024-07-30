public class ServicioWeb {
    public void agregarAlCarrito(CarritoDeCompras carrito, Object item) {
        carrito.getItems().add(item);
    }
}
