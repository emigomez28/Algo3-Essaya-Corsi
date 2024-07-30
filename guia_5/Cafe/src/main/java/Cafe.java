// EL diseño es poco escalable ya que para añadir algo mas al cafe tendría que poner
// otro método (ej: agregar espuma al cafe -> public void agregarEspuma() { ... })
// Esto rompe con el principio Open/Closed ya que no es fácil de escalar y los métodos
// son muy parecidos entre sí.
//
// Por otro lado, se puede ver que al cafe se le agregan decoraciones, siempre es
// el mismo cafe pero con un extra -> Aplico el patron Decorator

public class Cafe {
    private String descripcion;
    private double precio;

    public Cafe(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void agregarLeche() {
        descripcion += ", con leche";
        precio += 0.5;
    }

    public void agregarAzucar() {
        descripcion += ", con azucar";
        precio += 0.25;
    }

    public void agregarMiel() {
        descripcion += ", con miel";
        precio += 0.75;
    }
}
