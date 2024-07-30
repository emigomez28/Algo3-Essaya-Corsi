package Klondike;

import Solitario.Carta;
import Solitario.Mazo;
import Solitario.Palo;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;

public class MazoKlondike extends Mazo implements Serializable {
    public MazoKlondike() {
        super();
    }
    public MazoKlondike(Integer semilla) {
        super(semilla);
    }

    protected MazoKlondike(Deque<Carta> noVistas, Deque<Carta> vistas) {
         super(noVistas, vistas);
    }

    protected ArrayList<Carta> inicializarCartas() {
        ArrayList<Carta> res = new ArrayList<>();
        for (Palo palo : Palo.values()) {
            for (int i = 1; i < 14; i++) {
                var carta = new Carta(palo.getColor(), palo, i, true);
                res.add(carta);
            }
        }
        return res;
    }

    protected void rellenar() {
        while (!vistas.isEmpty()) {
            Carta carta = vistas.pop();
            carta.ponerBocaAbajo();
            noVistas.push(carta);
        }
    }

    public Carta revelarCarta() {
        Carta carta = noVistas.pop();
        carta.ponerBocaArriba();
        vistas.push(carta);
        return vistas.peek();
    }

    public boolean puedoPedirCarta() {
        return !this.noVistas.isEmpty();
    }

    public Carta moverCarta() {
       if (!this.vistas.isEmpty()) {
            return vistas.pop();
       }
       return null;
    }

    public void devolverCarta(Carta carta) {
        vistas.push(carta);
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static MazoKlondike deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (MazoKlondike) ois.readObject();
    }
}

