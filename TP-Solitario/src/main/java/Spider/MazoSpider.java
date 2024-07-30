package Spider;

import Solitario.*;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class MazoSpider extends Mazo implements Serializable {
    public MazoSpider() {
        super();
    }

    public MazoSpider(Integer semilla) {
        super(semilla);
    }

    public MazoSpider(Deque<Carta> noVistas) {
        super(noVistas, new ArrayDeque<>());
    }

    protected ArrayList<Carta> inicializarCartas() {
        ArrayList<Carta> res = new ArrayList<>();
        for (Palo palo : Palo.values()) {
            for (int i = 1; i < 14; i++) {
                res.add(new Carta(palo.getColor(), palo, i, true));
                res.add(new Carta(palo.getColor(), palo, i, true));
            }
        }
        return res;
    }

    public Carta revelarCarta() {
        if (noVistas.isEmpty()) {
            return null;
        }
        var carta = noVistas.pop();
        carta.ponerBocaArriba();
        return carta;
    }

    public boolean quedanCartas() { return !this.noVistas.isEmpty(); }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static MazoSpider deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (MazoSpider) ois.readObject();
    }
}
