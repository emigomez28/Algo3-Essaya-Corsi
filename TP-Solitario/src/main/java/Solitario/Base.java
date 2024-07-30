package Solitario;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public abstract class Base implements Serializable {
    protected final ArrayList<Deque<Carta>> base;

    public Base(int k){
        this.base = new ArrayList<>();
        for (int i = 0; i < (Palo.values().length) * k; i++) {
            base.add(new ArrayDeque<>());
        }
    }

    protected Base(ArrayList<Deque<Carta>> base) {
        this.base = base;
    }

    public boolean estaCompleta() {
        for (Deque<Carta> pila: base) {
            if (pila.isEmpty() || pila.peek().getNumero() != 13) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Deque<Carta>> verBase() {
        return new ArrayList<>(this.base);
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static Base deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (Base) ois.readObject();
    }
}
