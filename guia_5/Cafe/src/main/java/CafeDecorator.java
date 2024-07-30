// De esta forma evitamos tener un mismo objeto muy cargado con métodos para cada
// variante de cafe. En caso de querer agregar espuma al cafe solo hay q repetir la
// lógica.

public interface CafeDecorator {
    String getDescripcion();
    double getPrecio();

}

class CafeSolo implements CafeDecorator {
    private String descripcion;
    private double precio;

    public CafeSolo(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public double getPrecio() {
        return precio;
    }
}

class CafeConLeche implements CafeDecorator {

    private CafeDecorator cafeDecorado;

    public CafeConLeche(CafeDecorator cafeDecorado) {
        this.cafeDecorado = cafeDecorado;
    }

    public String getDescripcion() {
        return cafeDecorado.getDescripcion() + ", con leche";
    }

    public double getPrecio() {
        return cafeDecorado.getPrecio() + 0.5;
    }
}

class CafeConAzucar implements CafeDecorator {
    private CafeDecorator cafeDecorado;

    public CafeConAzucar(CafeDecorator cafeDecorado) {
        this.cafeDecorado = cafeDecorado;
    }

    public String getDescripcion() {
        return cafeDecorado.getDescripcion() + ", con azucar";
    }

    public double getPrecio() {
        return cafeDecorado.getPrecio() + 0.25;
    }
}

class CafeConMiel implements CafeDecorator {
    private CafeDecorator cafeDecorado;

    public CafeConMiel(CafeDecorator cafeDecorado) {
        this.cafeDecorado = cafeDecorado;
    }

    public String getDescripcion() {
        return cafeDecorado.getDescripcion() + ", con miel";
    }

    public double getPrecio() {
        return cafeDecorado.getPrecio() + 0.75;
    }
}
