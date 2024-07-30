package Klondike;

import Solitario.Carta;
import Solitario.Tableau;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TableauKlondike extends Tableau implements Serializable {
    public TableauKlondike(Deque<Carta> cartasIniciales, int tam) {
        super(cartasIniciales, tam);
    }

    protected TableauKlondike(ArrayList<ArrayList<Carta>> tableau) {
        super(tableau);
    }

    public void repartirCartas(Deque<Carta> cartasIniciales) {
        for (int i = 0; i < this.tableau.size(); i++) {
            var colActual = this.tableau.get(i);
            for (int j = 0; j <= i-1; j++) {
                var cartaActual = cartasIniciales.pop();
                colActual.add(cartaActual);
            }
            var cartaActual = cartasIniciales.pop();
            cartaActual.ponerBocaArriba();
            colActual.add(cartaActual);
        }
    }

    public boolean esMovimientoValido(Carta carta, Integer colDestino) {
        if (colDestino < tableau.size() && !carta.estaBocaAbajo()) {
            ArrayList<Carta> destino = tableau.get(colDestino);
            if (destino.isEmpty()) {
                return carta.getNumero() == 13;
            }
            Carta ultima = destino.get(destino.size()-1);
            return ultima.getNumero() == carta.getNumero() + 1 && ultima.getColor() != carta.getColor();
        }
        return false;
    }

    public List<Carta> getCartasAgrupadas(Integer colOrigen, Integer filaOrigen){
        var origen = this.tableau.get(colOrigen);
        List<Carta> cartasAgrupadas = new ArrayList<>();
        int i = filaOrigen;
        while (filaOrigen < origen.size()) {
            var cartaActual = origen.remove(i);
            cartasAgrupadas.add(cartaActual);
        }
        if (!origen.isEmpty()) {
            var nuevoFrente = origen.get(origen.size() - 1);
            nuevoFrente.ponerBocaArriba();
        }
        return cartasAgrupadas;
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static TableauKlondike deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (TableauKlondike) ois.readObject();
    }
}
