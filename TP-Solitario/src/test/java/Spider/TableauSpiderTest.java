package Spider;

import Solitario.Carta;
import Solitario.Palo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TableauSpiderTest {

    @Test
    public void testColocarCartaMismoPaloEsValido() {
        var palo = Palo.CORAZONES;
        var carta1 = new Carta(palo.getColor(), palo, 4, false);
        var carta2 = new Carta(palo.getColor(), palo, 3, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauSpider(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);
        assertTrue(esValido);
    }

    @Test
    public void testColocarCartaMismoPaloEsInvalido() {
        var palo = Palo.DIAMANTES;
        var carta1 = new Carta(palo.getColor(), palo, 4, false);
        var carta2 = new Carta(palo.getColor(), palo, 7, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauSpider(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);
        assertFalse(esValido);
    }

    @Test
    public void testColocarCartaMismoColorEsValido() {
        var palo1 = Palo.PICAS;
        var palo2 = Palo.TREBOLES;
        var carta1 = new Carta(palo1.getColor(), palo1, 4, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauSpider(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);
        assertTrue(esValido);
    }

    @Test
    public void testColocarCartaMismoColorEsInvalido() {
        var palo1 = Palo.PICAS;
        var palo2 = Palo.TREBOLES;
        var carta1 = new Carta(palo1.getColor(), palo1, 4, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 7, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauSpider(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);
        assertFalse(esValido);
    }
    @Test
    public void testColocarCartaFueraDeRangoEsInvalido() {
        var palo = Palo.DIAMANTES;
        var carta = new Carta(palo.getColor(), palo, 4, false);
        var aux = new ArrayList<ArrayList<Carta>>();
        for (int i = 0; i < 7; i++) {
            aux.add(new ArrayList<>());
        }
        var tableau = new TableauSpider(aux);

        boolean esValido = tableau.colocarCarta(carta, 77);

        assertFalse(esValido);
    }

    @Test
    public void testTomarCarta() {
        var palo1 = Palo.TREBOLES;
        var palo2 = Palo.CORAZONES;
        var carta1 = new Carta(palo1.getColor(), palo1, 4, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 6;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{
            add(carta1);
            add(carta2);
        }};
        aux.add(colDestino, columna);
        var tableau = new TableauSpider(aux);

        var cartaTomada = tableau.getCarta(colDestino);

        assertEquals(carta2, cartaTomada);
    }

    @Test
    public void testTomarCartaColVacia() {
        var aux = new ArrayList<ArrayList<Carta>>();
        for (int i=0; i<7; i++) {
            aux.add(new ArrayList<>());
        }
        var tableau = new TableauSpider(aux);
        var colOrigen = 2;

        var carta = tableau.getCarta(colOrigen);

        assertNull(carta);
    }

    @Test
    public void testSacarCarta() {
        var mazo = new MazoSpider();
        var cartasIniciales = mazo.sacarNoVistas(54);
        var tableau = new TableauSpider(cartasIniciales);
        var colOrigen = 1;

        var cartaEnELTope = tableau.getCarta(colOrigen);
        tableau.sacarCarta(colOrigen);
        var cartaEnLaBase = tableau.getCarta(colOrigen);
        tableau.sacarCarta(colOrigen);

        assertFalse(cartaEnELTope.estaBocaAbajo());
        assertFalse(cartaEnLaBase.estaBocaAbajo());
    }

    @Test
    public void testMoverCartaEsValido() {
        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        var carta_1 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 1, false);
        var carta_2 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 12, true);
        var carta_3 = new Carta(Palo.PICAS.getColor(), Palo.PICAS,2, false);
        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);

        aux.add(col_1);
        aux.add(col_2);

        var tableau = new TableauSpider(aux);
        tableau.moverCartas(0, 0, 1);
        assertEquals(tableau.getCarta(1), carta_1);

    }

    @Test
    public void testMoverCartaEsInvalido() {
        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        var carta_1 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 6, false);
        var carta_2 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 12, true);
        var carta_3 = new Carta(Palo.PICAS.getColor(), Palo.PICAS,1, true);
        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);

        aux.add(col_1);
        aux.add(col_2);

        var tableau = new TableauSpider(aux);
        tableau.moverCartas(0, 0, 1);
        assertNotEquals(tableau.getCarta(1), carta_1);

    }

    @Test
    public void testMoverAColVaciaEsValido() {
        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();
        var carta = new Carta(Palo.PICAS.getColor(), Palo.PICAS, 8, false);
        col_1.add(carta);
        aux.add(0,col_1);
        aux.add(1, col_2);
        var tableau = new TableauSpider(aux);

        tableau.moverCartas(0,0,1);
        System.out.println(col_2);
        assertFalse(col_2.isEmpty());
    }


}
