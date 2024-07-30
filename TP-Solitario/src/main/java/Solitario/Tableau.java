package Solitario;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class Tableau implements Serializable {
    protected final ArrayList<ArrayList<Carta>> tableau;

    public Tableau(Deque<Carta> cartasIniciales, int tam) {
        this.tableau = new ArrayList<>();
        for (int i = 0; i < tam; i++) {
            tableau.add(new ArrayList<>());
        }
        repartirCartas(cartasIniciales);
    }

    protected Tableau(ArrayList<ArrayList<Carta>> tableau) {
        this.tableau = tableau;
    }

    public abstract boolean esMovimientoValido(Carta carta, Integer colDestino);

    public abstract void repartirCartas(Deque<Carta> cartasIniciales);

    public abstract List<Carta> getCartasAgrupadas(Integer colOrigen, Integer filaOrigen);

    public boolean colocarCarta(Carta carta, Integer colDestino) {
        if (this.esMovimientoValido(carta, colDestino)){
            var destino = this.tableau.get(colDestino);
            destino.add(carta);
            return true;
        }
        return false;
    }

    public boolean moverCartas(Integer colOrigen, Integer filaOrigen, Integer colDestino){
        var origen = this.tableau.get(colOrigen);
        var cartaBase = origen.get(filaOrigen);

        if (this.esMovimientoValido(cartaBase, colDestino)){
            var destino = this.tableau.get(colDestino);
            List<Carta> cartasAgrupadas = getCartasAgrupadas(colOrigen, filaOrigen);
            if (cartasAgrupadas != null) {
                destino.addAll(cartasAgrupadas);
                return true;
            }
        }
        return false;
    }

    public Carta getCarta(int colOrigen){
        var colBuscada = this.tableau.get(colOrigen);
        if (!colBuscada.isEmpty()) {
            return colBuscada.get(colBuscada.size()-1);
        }
        return null;
    }

    public void sacarCarta(int colOrigen) {
        var origen = this.tableau.get(colOrigen);
        var posicion = origen.size()-1;
        origen.remove(posicion);
        if (!origen.isEmpty()) {
            var nuevoFrente = origen.get(posicion - 1);
            nuevoFrente.ponerBocaArriba();
        }
    }

    public ArrayList<ArrayList<Carta>> verTableau() {
        return new ArrayList<>(this.tableau);
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static Tableau deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (Tableau) ois.readObject();
    }
}
