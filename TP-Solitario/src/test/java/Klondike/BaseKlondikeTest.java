package Klondike;

import org.junit.Test;
import Solitario.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static org.junit.Assert.*;

public class BaseKlondikeTest {
    @Test
    public void testBaseVacia() {
        BaseKlondike base = new BaseKlondike();

        assertFalse(base.estaCompleta());
        for (int i = 0; i < 4; i++) {
            assertNull(base.sacarCarta(i));
        }
    }
    @Test
    public void testBaseCasiLlena() {
        ArrayList<Deque<Carta>> aux = rellenarBase();
        aux.remove(aux.size()-1);
        aux.add(new ArrayDeque<>());
        var base = new BaseKlondike(aux);

        assertFalse(base.estaCompleta());
    }
    @Test
    public void testBaseLlena() {
        ArrayList<Deque<Carta>> aux = rellenarBase();
        var base = new BaseKlondike(aux);

        assertTrue(base.estaCompleta());
    }
    @Test
    public void testColocarCartaEsValido() {
        BaseKlondike base = new BaseKlondike();
        var colDestino = 3;
        Carta carta = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,1,false);

        boolean esValido = base.colocarCarta(carta,colDestino);

        assertTrue(esValido);
    }
    @Test
    public void testNoEsMovimientoValido() {
        BaseKlondike base = new BaseKlondike();
        var colDestino = 3;
        Carta carta = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,5,false);

        boolean esValido = base.colocarCarta(carta,colDestino);

        assertFalse(esValido);
    }
    @Test
    public void testColocarCartaBocaABajoInvalido() {
        BaseKlondike base = new BaseKlondike();
        var colDestino = 3;
        Carta carta = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,1,true);

        boolean esValido = base.colocarCarta(carta,colDestino);

        assertFalse(esValido);
    }
    @Test
    public void testColocarCartasNumeroContiguoEsValido() {
        BaseKlondike base = new BaseKlondike();
        var colDestino = 1;
        Carta carta1 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,1,false);
        Carta carta2 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,2,false);

        boolean esValido1 = base.colocarCarta(carta1,colDestino);
        boolean esValido2 = base.colocarCarta(carta2,colDestino);

        assertTrue(esValido1);
        assertTrue(esValido2);
    }
    @Test
    public void testColumnaFueraDeRango() {
        BaseKlondike base = new BaseKlondike();
        var colDestino = 7777;
        Carta carta = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,1,false);

        boolean esValido = base.colocarCarta(carta, colDestino);

        assertFalse(esValido);
    }
    @Test
    public void testTomarCarta() {
        BaseKlondike base = new BaseKlondike();
        var columna = 2;
        Carta carta = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,1,false);
        base.colocarCarta(carta, columna);

        var cartaPedida = base.sacarCarta(columna);

        assertEquals(carta, cartaPedida);
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
        }
        return res;
    }
}