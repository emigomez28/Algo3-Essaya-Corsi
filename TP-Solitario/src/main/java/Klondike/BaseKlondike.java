package Klondike;

import Solitario.Base;
import Solitario.Carta;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BaseKlondike extends Base implements Serializable {
    public BaseKlondike(){
        super(1);
    }

    protected BaseKlondike(ArrayList<Deque<Carta>> base) {
        super(base);
    }

    private boolean esMovimientoValido(Carta carta, Integer colDestino) {
        if (carta.estaBocaAbajo() || colDestino >= base.size()) {
            return false;
        } else if (base.get(colDestino).isEmpty()) {
            return carta.getNumero() == 1;
        }
        Carta tope = base.get(colDestino).peek();
        return tope.getPalo() == carta.getPalo() && tope.getNumero()+1 == carta.getNumero();
    }

    public boolean colocarCarta(Carta carta, Integer colDestino) {
        if (esMovimientoValido(carta, colDestino)) {
            base.get(colDestino).push(carta);
            return true;
        }
        return false;
    }

    public Carta sacarCarta(Integer colOrigen){
        var colBuscada = this.base.get(colOrigen);
        if (colBuscada.isEmpty()) {
            return null;
        }
        return colBuscada.pop();
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static BaseKlondike deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (BaseKlondike) ois.readObject();
    }
}
