package Spider;

import Solitario.*;
import Solitario.Palo;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class SolitarioSpiderTest {

    @Test
    public void testPedirCartasValido(){
        ArrayList<ArrayList<Carta>> cartasIniciales = new ArrayList<>(){{
        add(new ArrayList<>(){{add((new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 2, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.PICAS.getColor(), Palo.PICAS, 10, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 5, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 8, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 1, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.PICAS.getColor(), Palo.PICAS, 11, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 9, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 12, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.PICAS.getColor(), Palo.PICAS, 3, true)));}});
        add(new ArrayList<>(){{add((new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 6, true)));}});
        }};
        var tableau = new TableauSpider(cartasIniciales);
        Deque<Carta> noVistas = new ArrayDeque<>(){{
            push(new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 10, true));
            push(new Carta(Palo.PICAS.getColor(), Palo.PICAS, 9, true));
            push(new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 8, true));
            push(new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 7, true));
            push(new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 6, true));
            push(new Carta(Palo.PICAS.getColor(), Palo.PICAS, 5, true));
            push(new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 4, true));
            push(new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 3, true));
            push(new Carta(Palo.PICAS.getColor(), Palo.PICAS, 2, true));
            push(new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 1, true));
        }};
        var mazo = new MazoSpider(noVistas);
        var base = new BaseSpider();
        var solitario = new SolitarioSpider(mazo, tableau, base);

        solitario.pedirCarta();
        boolean movimientoCorrecto = true;
        for(int i = 0; i < 10; i++){
            int num = tableau.getCarta(i).getNumero();
            if (num != i){
                movimientoCorrecto = false;
                return;
            }
        }
        
        assertTrue(movimientoCorrecto);
    }

    @Test
    public void testPedirCartasInvalido(){
        ArrayList<ArrayList<Carta>> cartasIniciales = new ArrayList<>(){{
            add(new ArrayList<>(){{add((new Carta(Palo.PICAS.getColor(), Palo.PICAS, 10, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 2, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 5, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 8, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 1, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.PICAS.getColor(), Palo.PICAS, 11, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.CORAZONES.getColor(), Palo.CORAZONES, 9, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.TREBOLES.getColor(), Palo.TREBOLES, 12, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.PICAS.getColor(), Palo.PICAS, 3, true)));}});
            add(new ArrayList<>(){{add((new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 6, true)));}});
        }};
        var tableau = new TableauSpider(cartasIniciales);
        var mazo = new MazoSpider(new ArrayDeque<>());
        var base = new BaseSpider();
        var solitario = new SolitarioSpider(mazo, tableau, base);

        solitario.pedirCarta();
        boolean movimientoCorrecto = true;
        for(int i = 0; i < 10; i++){
            int num = tableau.getCarta(i).getNumero();
            if (num != 10){
                movimientoCorrecto = false;
                return;
            }
        }

        assertTrue(movimientoCorrecto);
    }

    @Test
    public void testMoverEntreTableauValido() {
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
        var mazo = new MazoSpider();
        var base = new BaseSpider();

        var solitario = new SolitarioSpider(mazo, tableau, base);

        solitario.moverTableauATableau(0,0, 1);
        assertEquals(tableau.getCarta(1), carta_1);
    }

    @Test
    public void testMoverEntreTableauInvalido() {
        var aux = new ArrayList<ArrayList<Carta>>();
        var col_1 = new ArrayList<Carta>();
        var col_2 = new ArrayList<Carta>();
        var carta_1 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 7, false);
        var carta_2 = new Carta(Palo.DIAMANTES.getColor(), Palo.DIAMANTES, 12, true);
        var carta_3 = new Carta(Palo.PICAS.getColor(), Palo.PICAS,1, true);
        col_1.add(carta_1);
        col_2.add(carta_2);
        col_2.add(carta_3);
        aux.add(col_1);
        aux.add(col_2);
        var tableau = new TableauSpider(aux);
        var mazo = new MazoSpider();
        var base = new BaseSpider();

        var solitario = new SolitarioSpider(mazo, tableau, base);

        solitario.moverTableauATableau(0,0, 1);

        assertNotEquals(tableau.getCarta(1), carta_1);
    }
    @Test
    public void testJuegoGanado() {
        var tableau = new TableauSpider(new ArrayList<>());
        var mazo = new MazoSpider(new ArrayDeque<>());
        var res = rellenarBase();
        var base = new BaseSpider(res);
        var solitario = new SolitarioSpider(mazo, tableau, base);

        var juegoGanado = solitario.juegoGanado();

        assertTrue(juegoGanado);
    }

    @Test
    public void testMovimientoGanador(){
        var mazo = new MazoSpider(new ArrayDeque<>());
        var res = this.rellenarBase();
        var ultimaPila = res.remove(res.size()-1);
        res.add(new ArrayDeque<>());
        var listaParaGanar = pasarDePilaALista(ultimaPila);
        var base = new BaseSpider(res);
        var aux = new ArrayList<ArrayList<Carta>>();
        aux.add(listaParaGanar);
        var tableau = new TableauSpider(aux);
        var solitario = new SolitarioSpider(mazo, tableau, base);
        var colOrigen = 0;
        var filOrigen = 0;

        boolean estaGanado1 = solitario.juegoGanado();
        solitario.moverTableauABase(colOrigen, filOrigen);
        boolean estaGanado2 = solitario.juegoGanado();

        assertFalse(estaGanado1);
        assertTrue(estaGanado2);
    }

    @Test
    public void testPersistenciaGanado() throws IOException, ClassNotFoundException{
        var tableau = new TableauSpider(new ArrayList<>());
        var mazo = new MazoSpider(new ArrayDeque<>());
        var res = rellenarBase();
        var base = new BaseSpider(res);
        var solitario = new SolitarioSpider(mazo, tableau, base);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        solitario.serializar(bytes);

        SolitarioSpider juegoDeserializado = SolitarioSpider.deserializar(new ByteArrayInputStream(bytes.toByteArray()));

        assertNotNull(juegoDeserializado);

        boolean estaGanadoOriginal = solitario.juegoGanado();
        boolean estaGanadoDeserializado = juegoDeserializado.juegoGanado();

        assertEquals(estaGanadoOriginal, estaGanadoDeserializado);
        assertTrue(estaGanadoOriginal);
        assertTrue(estaGanadoDeserializado);
    }

    @Test
    public void testPersistenciaCasiGanado() throws IOException, ClassNotFoundException {
        var mazo = new MazoSpider(new ArrayDeque<>());
        var res = rellenarBase();
        var ultimaPila = res.remove(res.size()-1);
        res.add(new ArrayDeque<>());
        var listaParaGanar = pasarDePilaALista(ultimaPila);
        var base = new BaseSpider(res);
        var aux = new ArrayList<ArrayList<Carta>>();
        aux.add(listaParaGanar);
        var tableau = new TableauSpider(aux);
        var solitario = new SolitarioSpider(mazo, tableau, base);
        var colOrigen = 0;
        var filOrigen = 0;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        solitario.serializar(bytes);

        SolitarioSpider solitarioDeserializado = SolitarioSpider.deserializar(new ByteArrayInputStream(bytes.toByteArray()));

        assertNotNull(solitarioDeserializado);

        boolean estaGanadoOriginal = solitario.juegoGanado();
        boolean estaGanadoDeserializado = solitarioDeserializado.juegoGanado();
        assertEquals(estaGanadoOriginal, estaGanadoDeserializado);
        assertFalse(estaGanadoOriginal);
        assertFalse(estaGanadoDeserializado);

        solitario.moverTableauABase(colOrigen, filOrigen);
        solitarioDeserializado.moverTableauABase(colOrigen, filOrigen);
        estaGanadoOriginal = solitario.juegoGanado();
        estaGanadoDeserializado = solitarioDeserializado.juegoGanado();
        assertEquals(estaGanadoOriginal, estaGanadoDeserializado);
        assertTrue(estaGanadoOriginal);
        assertTrue(estaGanadoDeserializado);
    }

    private ArrayList<Deque<Carta>> rellenarBase() {
        var base = new ArrayList<Deque<Carta>>();
        for (Palo palo : Palo.values()) {
            Deque<Carta> pila1 = new ArrayDeque<>();
            Deque<Carta> pila2 = new ArrayDeque<>();
            for (int i = 1; i < 14; i++) {
                var carta1 = new Carta(palo.getColor(), palo, i, false);
                var carta2 = new Carta(palo.getColor(), palo, i, false);
                pila1.push(carta1);
                pila2.push(carta2);
            }
            base.add(pila1);
            base.add(pila2);
        }
        return base;
    }

    private ArrayList<Carta> pasarDePilaALista(Deque<Carta> cartas){
        return new ArrayList<>(cartas);
    }
}
