package Klondike;

import Solitario.Carta;
import Solitario.Solitario;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;

public class SolitarioKlondike implements Solitario, Serializable {
    private final MazoKlondike mazoKlondike;
    private final BaseKlondike baseKlondike;
    private final TableauKlondike tableauKlondike;


    public SolitarioKlondike(MazoKlondike mazo, TableauKlondike tableau, BaseKlondike base) {
        this.mazoKlondike = mazo;
        this.tableauKlondike = tableau;
        this.baseKlondike = base;
    }

    public SolitarioKlondike(Integer semilla) {
        this.mazoKlondike = new MazoKlondike(semilla);
        Deque<Carta> cartasIniciales = mazoKlondike.sacarNoVistas(28);
        this.tableauKlondike = new TableauKlondike(cartasIniciales, 7);
        this.baseKlondike = new BaseKlondike();
    }

    public SolitarioKlondike() {
        this.mazoKlondike = new MazoKlondike();
        var cartasIniciales = mazoKlondike.sacarNoVistas(28);
        this.tableauKlondike = new TableauKlondike(cartasIniciales, 7);
        this.baseKlondike = new BaseKlondike();
    }

    public Carta pedirCarta() {
        if (!mazoKlondike.puedoPedirCarta()) {
            rellenarMazo();
        }
        return mazoKlondike.revelarCarta();
    }

    public void rellenarMazo() {
        mazoKlondike.rellenar();
    }

    public boolean moverMazoATableau(Integer colDestino) {
        Carta carta = mazoKlondike.moverCarta();
        if (!tableauKlondike.colocarCarta(carta, colDestino)) {
            mazoKlondike.devolverCarta(carta);
            return false;
        }
        return true;
    }

    public boolean moverMazoABase(Integer colDestino) {
        Carta carta = mazoKlondike.moverCarta();
        if (carta != null && !baseKlondike.colocarCarta(carta, colDestino)) {
            mazoKlondike.devolverCarta(carta);
            return false;
        }
        return true;
    }

    public boolean moverTableauATableau(Integer colOrigen, Integer filaOrigen, Integer colDestino) {
        return tableauKlondike.moverCartas(colOrigen, filaOrigen, colDestino);
    }

    public boolean moverTableauABase(Integer colOrigen, Integer colDestino) {
        Carta carta = tableauKlondike.getCarta(colOrigen);
        if (carta != null && baseKlondike.colocarCarta(carta, colDestino)) {
            tableauKlondike.sacarCarta(colOrigen);
            return true;
        }
        return false;
    }

    public void moverBaseATableau(Integer colOrigen, Integer colDestino) {
        Carta carta = baseKlondike.sacarCarta(colOrigen);
        if (carta != null && !tableauKlondike.colocarCarta(carta, colDestino)){
            baseKlondike.colocarCarta(carta, colOrigen);
        }
    }

    public boolean juegoGanado() {return this.baseKlondike.estaCompleta();}

    public Deque<Carta> verCartasMazo() {
        return mazoKlondike.verCartas();
    }
    public ArrayList<ArrayList<Carta>> verCartasTableau() { return tableauKlondike.verTableau(); }
    public ArrayList<Deque<Carta>> verCartasBase() { return baseKlondike.verBase(); }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static SolitarioKlondike deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (SolitarioKlondike) ois.readObject();
    }

}
