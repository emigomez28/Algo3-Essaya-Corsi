package Klondike;

import Solitario.*;
import org.junit.Test;

import java.util.ArrayDeque;

import static org.junit.Assert.*;

public class MazoKlondikeTest {
    @Test
    public void testMazoVacio() {
        var mazo = new MazoKlondike(new ArrayDeque<>(), new ArrayDeque<>());
        assertFalse(mazo.puedoPedirCarta());
    }

    @Test
    public void testRevelarCarta() {
        var ultimaCartaAgregada = new Carta(Palo.PICAS.getColor(), Palo.PICAS, 1, true);
        var cartas = new ArrayDeque<Carta>() {{
            push(new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 2, true));
            push(new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 3, true));
            push(new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 4, true));
            push(ultimaCartaAgregada);
        }};
        var mazo = new MazoKlondike(cartas, new ArrayDeque<>());

        var cartaPedida = mazo.revelarCarta();

        assertEquals(ultimaCartaAgregada, cartaPedida);
    }
    @Test
    public void testRevelarHastaVaciar() {
        var mazo = new MazoKlondike(7);
        while (mazo.puedoPedirCarta()) {
            assertNotNull(mazo.revelarCarta());
        }
    }
    @Test
    public void testPedirYDevolver() {
        var mazo = new MazoKlondike();
        mazo.revelarCarta();
        var cartaMovida = mazo.moverCarta();

        mazo.devolverCarta(cartaMovida);
        var cartaMovida2 = mazo.moverCarta();

        assertEquals(cartaMovida, cartaMovida2);
    }
    @Test
    public void testPedirSinRevelar() {
        var mazo = new MazoKlondike();

        var cartaMovida = mazo.moverCarta();

        assertNull(cartaMovida);
    }
}