package Spider;

import Solitario.Carta;
import Solitario.Palo;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;
public class MazoSpiderTest {

    @Test
    public void testRevelarCarta(){
        var ultimaCartaAgregada = new Carta(Palo.PICAS.getColor(), Palo.PICAS, 1, true);
        var cartas = new ArrayDeque<Carta>() {{
            push(new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 2, true));
            push(new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 3, true));
            push(new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 4, true));
            push(ultimaCartaAgregada);
        }};
        var mazo = new MazoSpider(cartas);

        var cartaPedida = mazo.revelarCarta();

        assertEquals(ultimaCartaAgregada, cartaPedida);
    }

    @Test
    public void testRevelarCartaMazoVacio(){
        var mazo = new MazoSpider(new ArrayDeque<>());

        var cartaPedida = mazo.revelarCarta();

        assertNull(cartaPedida);
    }

    @Test
    public void testQuedanCartasMazoNoVacio(){
        Deque<Carta> noVistas = new ArrayDeque<>(){{
            push(new Carta(Palo.PICAS.getColor(), Palo.PICAS, 7,true));
            push(new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 3,true));
        }};
        var mazo = new MazoSpider(noVistas);

        boolean quedanCartas = mazo.quedanCartas();

        assertTrue(quedanCartas);
    }

    @Test
    public void testQuedanCartasMazoVacio(){
        Deque<Carta> noVistas = new ArrayDeque<>();
        var mazo = new MazoSpider(noVistas);

        boolean quedanCartas = mazo.quedanCartas();

        assertFalse(quedanCartas);
    }

}
