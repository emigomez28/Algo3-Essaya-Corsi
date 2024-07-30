// Como la implementación tenia una clase por cada posicion en un equipo de futbol,
// aplicar el patron Strategy permite ahorrar esas implementaciones y el sistema es mas
// escalable.

public class JugadorStrategy {
    private final Posicion posicion;
    private final Controlador controlador;

    public JugadorStrategy(Posicion p, Controlador c) {
        posicion = p;
        controlador = c;
    }

    public void jugar() {
        controlador.jugar(posicion);
    }

    public void dibujar() {
        posicion.dibujar();
    }
}

interface Posicion {
    void dibujar();
}

interface Controlador {
    void jugar(Posicion p);
}

class ControladorHumano implements Controlador {
    @Override
    public void jugar(Posicion p) {

    }
}
class ControladorIA implements Controlador {
    @Override
    public void jugar(Posicion p) {

    }
}

class PosicionArquero implements Posicion {
    @Override
    public void dibujar() {

    }
}
class PosicionDefensor implements Posicion {
    @Override
    public void dibujar() {

    }
}
class PosicionMediocampo implements Posicion {
    @Override
    public void dibujar() {

    }
}
class PosicionDelantero implements Posicion {
    @Override
    public void dibujar() {

    }
}

class Main {
    public static void main(String[] args) {
        var mediocampistaHumano = new JugadorStrategy(new PosicionMediocampo(), new ControladorHumano());
        var arqueroIA = new JugadorStrategy(new PosicionArquero(), new ControladorIA());
    }
}

