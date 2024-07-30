// Se puede observar que hay bastante repetición de código, violando el principio DRY,
// ya que todas las impresoras usan el método imprimir y siguen un algoritmo,
// aunque pueden ser distintos

// Propongo el patron Template, haciendo a Impresora una clase abstracta y
// que cada impresora tenga su forma de implementar el método imprimir()

public interface Impresora {
    void imprimir();
}

class ImpresoraLaser implements Impresora {
    public void imprimir() {
        if (tonerVacio()) {
            System.out.println("Sin toner!");
            return;
        }
        leerDocumento();
        calentar();
        realizar_impresion();
    }

    private boolean tonerVacio() { return false;}
    private void leerDocumento() { }
    private void calentar() { }
    private void realizar_impresion() { }
}

class ImpresoraInyeccion implements Impresora {
    public void imprimir() {
        if (cartuchoVacio()) {
            System.out.println("Sin tinta!");
            return;
        }
        leerDocumento();
        calentar();
        realizar_impresion("d");
    }

    private boolean cartuchoVacio() { return false; }
    private void leerDocumento() { }
    private void calentar() {}
    private void realizar_impresion(String parametro) {  }
}

class Impresora3D implements Impresora {
    public void imprimir() {
        if (filamentoVacio()) {
            System.out.println("Sin filamento!");
            return;
        }
        leerDocumento();
        calentar();
        realizar_impresion("d");
    }

    private boolean filamentoVacio() { return false; }
    private void leerDocumento() {  }
    private void calentar() {  }
    private void realizar_impresion(String parametro) { }
}
