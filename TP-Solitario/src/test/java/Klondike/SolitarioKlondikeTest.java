package Klondike;

import Solitario.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static org.junit.Assert.*;

public class SolitarioKlondikeTest {
    @Test
    public void testPedirCarta() {
        var carta = new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 2, true);
        var noVistas = new ArrayDeque<Carta>() {{add(carta);}};
        var mazo = new MazoKlondike(noVistas, new ArrayDeque<>());
        var solitario = new SolitarioKlondike(
                mazo,
                new TableauKlondike(new ArrayList<>()),
                new BaseKlondike(new ArrayList<>())
        );

        var cartaPedida = solitario.pedirCarta();

        assertEquals(carta, cartaPedida);
    }

    @Test
    public void testMoverBaseATableauMismoColorEsInvalido() {
        var palo1 = Palo.DIAMANTES;
        var palo2 = Palo.CORAZONES;
        var cartaTableau = new Carta(palo1.getColor(), palo1, 4, false);
        var cartaMazo = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(new ArrayList<>());
        }
        var columna = new ArrayList<Carta>() {{ add(cartaTableau); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);
        var vistas = new ArrayDeque<Carta>() {{ add(cartaMazo); }};
        var mazo = new MazoKlondike(new ArrayDeque<>(), vistas);
        var base = new BaseKlondike(new ArrayList<>());
        var solitario = new SolitarioKlondike(mazo, tableau, base);

        solitario.moverMazoATableau(colDestino);
        var ultimaAgregada = tableau.getCarta(colDestino);

        assertNotEquals(cartaMazo.getNumero(), ultimaAgregada.getNumero());
    }

    @Test
    public void testMoverBaseATableauValido() {
        var palo1 = Palo.TREBOLES;
        var palo2 = Palo.CORAZONES;
        var cartaTableau = new Carta(palo1.getColor(), palo1, 4, false);
        var cartaMazo = new Carta(palo2.getColor(), palo2, 3, false);
        var colDestino = 0;
        var aux = new ArrayList<ArrayList<Carta>>(colDestino + 1);
        for (int i = 0; i <= colDestino; i++) {
            aux.add(new ArrayList<>());
        }
        var columna = new ArrayList<Carta>() {{ add(cartaTableau); }};
        aux.add(colDestino, columna);
        var tableau = new TableauKlondike(aux);
        var vistas = new ArrayDeque<Carta>() {{ add(cartaMazo); }};
        var mazo = new MazoKlondike(new ArrayDeque<>(), vistas);
        var base = new BaseKlondike(new ArrayList<>());
        var solitario = new SolitarioKlondike(mazo, tableau, base);

        solitario.moverMazoATableau(colDestino);
        var ultimaAgregada = tableau.getCarta(colDestino);

        assertEquals(cartaMazo.getNumero(), ultimaAgregada.getNumero());
    }

    @Test
    public void testEstaGanado() {
        var aux = rellenarBase();
        var base = new BaseKlondike(aux);
        var tableau = new ArrayList<ArrayList<Carta>>();
        var juego = new SolitarioKlondike(new MazoKlondike(), new TableauKlondike(tableau), base);

        boolean estaGanando = juego.juegoGanado();

        assertTrue(estaGanando);
    }

    @Test
    public void testMovimientoGanador() {
        var aux = rellenarBase();
        var base = new BaseKlondike(aux);
        var tableau = new ArrayList<ArrayList<Carta>>();
        var juego = new SolitarioKlondike(new MazoKlondike(), new TableauKlondike(tableau), base);
        var colOrigen = 3;
        var ultimaCarta = base.sacarCarta(colOrigen);

        boolean estaGanando1 = juego.juegoGanado();
        base.colocarCarta(ultimaCarta, colOrigen);
        boolean estaGanando2 = juego.juegoGanado();

        assertFalse(estaGanando1);
        assertTrue(estaGanando2);
    }

    @Test
    public void testMoverMazoABaseEsValido() {
        var carta_mazo = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 1, true);
        var noVistas = new ArrayDeque<Carta>() {{ add(carta_mazo);}};
        var mazo = new MazoKlondike(noVistas, new ArrayDeque<>());
        var base = new BaseKlondike();
        var tableau =  new TableauKlondike(new ArrayList<>());
        var solitario = new SolitarioKlondike(mazo, tableau, base);

        solitario.pedirCarta();
        solitario.moverMazoABase(0);

        assertEquals(carta_mazo, base.sacarCarta(0));
    }

    @Test
    public void testPersistenciaGanado() throws IOException, ClassNotFoundException {
        // Inicializamos el estado del solitario
        var aux = rellenarBase();
        var base = new BaseKlondike(aux);
        var tableau = new ArrayList<ArrayList<Carta>>();
        var juego = new SolitarioKlondike(new MazoKlondike(), new TableauKlondike(tableau), base);

        // serializamos el solitario
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        juego.serializar(bytes);

        // deserializar
        SolitarioKlondike juegoDeserializado = SolitarioKlondike.deserializar(new ByteArrayInputStream(bytes.toByteArray()));

        assertNotNull(juegoDeserializado);

        boolean estaGanandoOriginal = juego.juegoGanado();
        boolean estaGanandoDeserializado = juegoDeserializado.juegoGanado();

        assertEquals(estaGanandoOriginal, estaGanandoDeserializado);
        assertTrue(estaGanandoOriginal);
        assertTrue(estaGanandoDeserializado);
    }

    @Test
    public void testPersistenciaCasiGanado() throws IOException, ClassNotFoundException {
        // Inicializamos el estado del solitario
        var aux = rellenarBase();
        var base = new BaseKlondike(aux);
        var tableau = new ArrayList<ArrayList<Carta>>();
        for (int i = 0; i < 7; i++) {
            tableau.add(new ArrayList<>());
        }
        var juego = new SolitarioKlondike(new MazoKlondike(), new TableauKlondike(tableau), base);
        var colBase = 3;
        var colTableau = 4;
        juego.moverBaseATableau(3, 4);

        // serializamos el solitario
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        juego.serializar(bytes);

        // deserializar
        SolitarioKlondike juegoDeserializado = SolitarioKlondike.deserializar(new ByteArrayInputStream(bytes.toByteArray()));

        assertNotNull(juegoDeserializado);

        // Verifico que ninguno de los dos este ganado
        boolean estaGanandoOriginal = juego.juegoGanado();
        boolean estaGanandoDeserializado = juegoDeserializado.juegoGanado();
        assertEquals(estaGanandoOriginal, estaGanandoDeserializado);
        assertFalse(estaGanandoOriginal);
        assertFalse(estaGanandoDeserializado);

        // Hago el movimiento y verifico que los dos esten ganados
        juego.moverTableauABase(colTableau, colBase);
        juegoDeserializado.moverTableauABase(colTableau, colBase);
        estaGanandoOriginal = juego.juegoGanado();
        estaGanandoDeserializado = juegoDeserializado.juegoGanado();
        assertEquals(estaGanandoOriginal, estaGanandoDeserializado);
        assertTrue(estaGanandoOriginal);
        assertTrue(estaGanandoDeserializado);
    }

    // Función auxiliar para los tests.
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