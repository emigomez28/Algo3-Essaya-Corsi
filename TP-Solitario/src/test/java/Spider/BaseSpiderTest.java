package Spider;

import org.junit.Test;
import Solitario.*;

import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Deque;

import static org.junit.Assert.*;

public class BaseSpiderTest {

    @Test
    public void testBaseVacia() {
        BaseSpider base = new BaseSpider();
        assertFalse(base.estaCompleta());
    }
    @Test
    public void testColocarABase() {
        BaseSpider base = new BaseSpider();
        var aux = new ArrayList<Carta>();
        var palo = Palo.CORAZONES;

        for (int i = 1; i < 14; i++) {
            var carta = new Carta(palo.getColor(), palo, i, false);
            aux.add(carta);
        }

        base.colocarCartas(aux);
        assertFalse(base.estaCompleta());
    }

    @Test
    public void testBaseCompleta() {
        ArrayList<Deque<Carta>> aux = rellenarBase();
        var base = new BaseSpider(aux);

        assertTrue(base.estaCompleta());
    }

    @Test
    public void testBaseCasiCompletaMoverCartasNoEsValido() {
        var b = rellenarBase();
        var aux = new ArrayDeque<Carta>();
        b.add(b.size()-1, aux);
        BaseSpider base = new BaseSpider(b);

        var palo = Palo.CORAZONES;
        var cartas = new ArrayList<Carta>();
        for (int i = 1; i < 14; i++) {
            var carta = new Carta(palo.getColor(), palo, i, false);
            cartas.add(carta);
        }

        var carta_aux = new Carta(palo.getColor(), palo, 2, false);
        cartas.add(0, carta_aux);

        base.colocarCartas(cartas);
        assertFalse(base.estaCompleta());
    }

    private ArrayList<Deque<Carta>> rellenarBase() {
        var res = new ArrayList<Deque<Carta>>();
        for (Palo palo : Palo.values()) {
            Deque<Carta> pila = new ArrayDeque<>();
            for (int i = 1; i < 14; i++) {
                var carta = new Carta(palo.getColor(), palo, i, false);
                pila.push(carta);
            }
            res.add(pila);
            res.add(pila);
        }
        return res;
    }
}
