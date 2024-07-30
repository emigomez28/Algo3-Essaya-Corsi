package Solitario;

import java.io.*;

public class Carta implements Serializable {
    private final Palo palo;
    private final Color color;
    private final int numero;
    private boolean bocaAbajo;

    public Carta(Color color, Palo palo, int numero, boolean bocaAbajo) {
        this.palo = palo;
        this.color = color;
        this.numero = numero;
        this.bocaAbajo = bocaAbajo;
    }

    public void ponerBocaArriba() {
        bocaAbajo = false;
    }

    public void ponerBocaAbajo() {
        bocaAbajo = true;
    }

    public boolean estaBocaAbajo() {
        return bocaAbajo;
    }

    public Color getColor() {
        return color;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return numero;
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static Carta deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (Carta) ois.readObject();
    }
}