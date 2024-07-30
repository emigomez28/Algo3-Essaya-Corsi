public class Cuadrado extends Rectangulo {
    public Cuadrado(int lado) {
        super(lado, lado);
    }

    @Override
    public void setAncho(int ancho) {
        super.setAncho(ancho);
        super.setAlto(ancho);
    }

    @Override
    public void setAlto(int alto) {
        super.setAncho(alto);
        super.setAlto(alto);
    }
}

// Rompe el principio de LSP -> El cuadrado ya no se comporta como un rectangulo
