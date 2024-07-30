package Klondike;

import org.junit.Test;
import Solitario.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TableauKlondikeTest {
    @Test
    public void testColocarCartaMismoPaloEsInvalido() {
        var palo = Palo.PICAS;
        var carta1 = new Carta(palo.getColor(), palo, 4, false);
        var carta2 = new Carta(palo.getColor(), palo, 3, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);

        assertFalse(esValido);
    }


    @Test
    public void testColocarCartaSobreNumeroMenorEsInvalido() {
        var palo1 = Palo.PICAS;
        var palo2 = Palo.CORAZONES;
        var carta1 = new Carta(palo1.getColor(), palo1, 2, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 2;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);

        assertFalse(esValido);
    }

    @Test
    public void testColocarCartaSobreNumeroMayorNoContiguoEsInvalido() {
        var palo1 = Palo.PICAS;
        var palo2 = Palo.CORAZONES;
        var carta1 = new Carta(palo1.getColor(), palo1, 10, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 4;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);

        assertFalse(esValido);
    }

    @Test
    public void testColocarCartaSobreMismoColorEsInvalido() {
        var palo1 = Palo.DIAMANTES;
        var palo2 = Palo.CORAZONES;
        var carta1 = new Carta(palo1.getColor(), palo1, 4, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);

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
        var tableau = new TableauKlondike(aux);

        boolean esValido = tableau.colocarCarta(carta, 77);

        assertFalse(esValido);
    }

    @Test
    public void testColocarCartaEsValido() {
        var palo1 = Palo.TREBOLES;
        var palo2 = Palo.CORAZONES;
        var carta1 = new Carta(palo1.getColor(), palo1, 4, false);
        var carta2 = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 6;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(null);
        }
        var columna = new ArrayList<Carta>() {{ add(carta1); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);

        boolean esValido = tableau.colocarCarta(carta2, colDestino);

        assertTrue(esValido);
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
        var tableau = new TableauKlondike(aux);

        var cartaTomada = tableau.getCarta(colDestino);

        assertEquals(carta2, cartaTomada);
    }

    @Test
    public void testTomarCartaColVacia() {
        var aux = new ArrayList<ArrayList<Carta>>();
        for (int i=0; i<7; i++) {
            aux.add(new ArrayList<>());
        }
        var tableau = new TableauKlondike(aux);
        var colOrigen = 2;

        var carta = tableau.getCarta(colOrigen);

        assertNull(carta);
    }

    @Test
    public void testSacarCarta() {
        var mazo = new MazoKlondike();
        var cartasIniciales = mazo.sacarNoVistas(28);
        var tableau = new TableauKlondike(cartasIniciales, 7);
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
        var carta_3 = new Carta(Palo.PICAS.getColor(), Palo.PICAS,2, true);
        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);

        aux.add(col_1);
        aux.add(col_2);

        var tableau = new TableauKlondike(aux);
        tableau.moverCartas(0, 0, 1);
        assertEquals(tableau.getCarta(1), carta_1);

    }

    @Test
    public void testMoverCartaEsInvalido() {
        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        var carta_1 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 2, false);
        var carta_2 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 12, true);
        var carta_3 = new Carta(Palo.PICAS.getColor(), Palo.PICAS,1, true);
        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);

        aux.add(col_1);
        aux.add(col_2);

        var tableau = new TableauKlondike(aux);
        tableau.moverCartas(0, 0, 1);
        assertNotEquals(tableau.getCarta(1), carta_1);

    }

    @Test
    public void testMoverKEnColVacia() {
        var palo = Palo.DIAMANTES;
        var carta = new Carta(palo.getColor(), palo, 13, false);
        var colOrigen = 0;
        var filaOrigen = 0;
        var colDestino = 3;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(new ArrayList<>());
        }
        var columna = new ArrayList<Carta>() {{ add(carta); }};
        aux.add(colOrigen, columna);
        var tableau = new TableauKlondike(aux);

        tableau.moverCartas(colOrigen, filaOrigen, colDestino);
        var cartaMovida = tableau.getCarta(colDestino);

        assertEquals(carta, cartaMovida);
    }

    @Test
    public void testMoverVariasCartasEsValido() {
        var carta_1 = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,5, false);
        var carta_2 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,5, false);
        var carta_3 = new Carta(Palo.PICAS.getColor(),Palo.PICAS,4, false);
        var carta_4 = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,3, false);
        var carta_5 = new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 2, false);

        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);
        col_2.add(carta_4);
        col_2.add(carta_5);

        aux.add(col_1);
        aux.add(col_2);

        var tableu = new TableauKlondike(aux);
        tableu.moverCartas(1, 1, 0);
        assertEquals(tableu.getCarta(0), carta_5);
        assertEquals(tableu.getCarta(1), carta_2);
    }

    @Test
    public void testMoverVariasCartasNumeroNoContiguoEsInvalido() {
        var carta_1 = new Carta(Palo.PICAS.getColor(),Palo.PICAS,7, false);
        var carta_2 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,5, false);
        var carta_3 = new Carta(Palo.PICAS.getColor(),Palo.PICAS,4, false);
        var carta_4 = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,3, false);

        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);
        col_2.add(carta_4);

        aux.add(col_1);
        aux.add(col_2);

        var tableu = new TableauKlondike(aux);
        tableu.moverCartas(1, 0, 0);
        assertEquals(tableu.getCarta(0), carta_1);
        assertEquals(tableu.getCarta(1), carta_4);
    }

    @Test
    public void testMoverVariasCartasMismoColorEsInvalido() {
        var carta_1 = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,6, false);
        var carta_2 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,5, false);
        var carta_3 = new Carta(Palo.PICAS.getColor(),Palo.PICAS,4, false);
        var carta_4 = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,3, false);
        var carta_5 = new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 2, false);

        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);
        col_2.add(carta_4);
        col_2.add(carta_5);

        aux.add(col_1);
        aux.add(col_2);

        var tableu = new TableauKlondike(aux);
        tableu.moverCartas(1, 1, 0);
        assertEquals(tableu.getCarta(0), carta_1);
        assertEquals(tableu.getCarta(1), carta_5);
    }
    @Test
    public void testMoverVariasCartasMismoPaloEsInvalido() {
        var carta_1 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,6, false);
        var carta_2 = new Carta(Palo.CORAZONES.getColor(),Palo.CORAZONES,5, false);
        var carta_3 = new Carta(Palo.PICAS.getColor(),Palo.PICAS,4, false);
        var carta_4 = new Carta(Palo.DIAMANTES.getColor(),Palo.DIAMANTES,3, false);

        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();

        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);
        col_2.add(carta_4);

        aux.add(col_1);
        aux.add(col_2);

        var tableu = new TableauKlondike(aux);
        tableu.moverCartas(1, 0, 0);
        assertNotEquals(tableu.getCarta(0), carta_4);
    }
}