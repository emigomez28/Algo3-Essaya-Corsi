import Solitario.Carta;
import Solitario.Palo;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testPonerBocaArribaCarta() {
        Palo palo = Palo.PICAS;
        var numero = 1;
        Carta carta = new Carta(palo.getColor(), palo, numero, true);

        carta.ponerBocaArriba();

        assertFalse(carta.estaBocaAbajo());
    }

    @Test
    public void testPonerBocaAbajoCarta() {
        Palo palo = Palo.PICAS;
        var numero = 1;
        Carta carta = new Carta(palo.getColor(), palo, numero, false);

        carta.ponerBocaAbajo();

        assertTrue(carta.estaBocaAbajo());
    }

    @Test
    public void testVerPalo() {
        Palo palo = Palo.PICAS;
        var numero = 1;
        Carta carta = new Carta(palo.getColor(), palo, numero, false);

        var paloObtenido = carta.getPalo();

        assertEquals(palo, paloObtenido);
    }

    @Test
    public void testVerNumero() {
        Palo palo = Palo.PICAS;
        var numero = 1;
        Carta carta = new Carta(palo.getColor(), palo, numero, false);

        var numeroObtenido = carta.getNumero();

        assertEquals(numero, numeroObtenido);
    }

    @Test
    public void testVerColor() {
        Palo palo = Palo.PICAS;
        var numero = 1;
        Carta carta = new Carta(palo.getColor(), palo, numero, false);
        var colorEsperado = palo.getColor();

        var colorObtenido = carta.getColor();

        assertEquals(colorEsperado, colorObtenido);
    }
}
