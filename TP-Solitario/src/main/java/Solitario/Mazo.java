package Solitario;

import java.io.*;
import java.util.*;

import static java.util.Collections.shuffle;

public abstract class Mazo implements Serializable {
    protected final Deque<Carta> vistas;
    protected final Deque<Carta> noVistas;

    public abstract Carta revelarCarta();

    protected abstract ArrayList<Carta> inicializarCartas();

    public Mazo() {
        ArrayList<Carta> cartas = inicializarCartas();
        shuffle(cartas);
        Deque<Carta> noVistas = pasarAPila(cartas);
        this.vistas = new ArrayDeque<>();
        this.noVistas = noVistas;
    }

    public Mazo(Integer semilla) {
        ArrayList<Carta> cartas = inicializarCartas();
        Random rand = new Random();
        rand.setSeed(semilla);
        shuffle(cartas, rand);
        var noVistas = pasarAPila(cartas);
        this.vistas = new ArrayDeque<>();
        this.noVistas = noVistas;
    }

    protected Mazo(Deque<Carta> noVistas, Deque<Carta> vistas) {
        this.noVistas = noVistas;
        this.vistas = vistas;
    }

    protected ArrayDeque<Carta> pasarAPila(ArrayList<Carta> cartas) {
        var noVistas = new ArrayDeque<Carta>();
        for (Carta carta: cartas) {
            noVistas.push(carta);
        }
        return noVistas;
    }

    public Deque<Carta> sacarNoVistas(int cantidad) {
        Deque<Carta> res = new ArrayDeque<>();
        int i = 0;
        while (!noVistas.isEmpty() && i < cantidad) {
            res.add(noVistas.pop());
            i++;
        }
        return res;
    }

    public Deque<Carta> verCartas() {
        return new ArrayDeque<>(this.noVistas);
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static Mazo deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (Mazo) ois.readObject();
    }
}
