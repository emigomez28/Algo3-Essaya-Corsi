public abstract class ImpresoraTemplate {
    public final void imprimir() {
        String err = chequeoInicial();
        if (err != null) {
            System.out.println(err);
            return;
        }
        leerDocumento();
        calentar();
        realizar_impresion();
    }
    protected abstract void leerDocumento();
    protected abstract void calentar();
    protected abstract void realizar_impresion();
    protected abstract String chequeoInicial();


}

class ImpresoraLaser2 extends ImpresoraTemplate {
    @Override
    protected String chequeoInicial() {
        return tonerVacio() ? "sinToner" : null;
    }
    @Override
    protected void leerDocumento() {

    }
    @Override
    protected void calentar() {

    }
    @Override
    protected void realizar_impresion() {

    }

    private boolean tonerVacio() {
        return false;
    }


}

class ImpresoraInyeccion2 extends ImpresoraTemplate {
    @Override
    protected void leerDocumento() {

    }

    @Override
    protected void calentar() {

    }

    @Override
    protected void realizar_impresion() {

    }

    @Override
    protected String chequeoInicial() {
        return cartuchoVacio() ? "El cartucho esta vacio" : null;
    }




    private boolean cartuchoVacio() {
        return false;
    }
}

class Impresora3D2 extends ImpresoraTemplate {

    @Override
    protected void leerDocumento() {

    }

    @Override
    protected void calentar() {

    }

    @Override
    protected void realizar_impresion() {

    }

    @Override
    protected String chequeoInicial() {
        return filamentoVacio() ? "El filamento esta vacio": null;
    }

    private boolean filamentoVacio() {
        return false;
    }
}
