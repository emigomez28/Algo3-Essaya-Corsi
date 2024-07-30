package Spider;

import Solitario.Tableau;
import Solitario.Carta;

import java.io.*;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class TableauSpider extends Tableau implements Serializable {
    public TableauSpider(Deque<Carta> cartasIniciales) { super(cartasIniciales, 10);}

    protected TableauSpider(ArrayList<ArrayList<Carta>> tableau){ super(tableau);}

    public void repartirCartas(Deque<Carta> cartasIniciales) {
        for (int i = 0; i < this.tableau.size(); i++) {
            var colActual = this.tableau.get(i);
            for (int j = 0; j < 4; j++) {
                var cartaActual = cartasIniciales.pop();
                colActual.add(cartaActual);
            }
            if (i < 4) {
                var cartaActual = cartasIniciales.pop();
                colActual.add(cartaActual);
            }
            var cartaActual = cartasIniciales.pop();
            cartaActual.ponerBocaArriba();
            colActual.add(cartaActual);
        }
    }

    public boolean esMovimientoValido(Carta carta, Integer colDestino) {
        if (colDestino > 10) {
            return false;
        }
        var colBuscada = this.tableau.get(colDestino);
        Carta cartaAComparar;
        if (colBuscada.isEmpty()) {
            return true;
        }
        cartaAComparar = colBuscada.get(colBuscada.size()-1);
        return cartaAComparar.getNumero()-1 == carta.getNumero();
    }

    protected void colocarCartaDelMazo(Carta carta, Integer colDestino){
        var colBuscada = this.tableau.get(colDestino);
        colBuscada.add(carta);
    }

    private boolean sePuedenMoverJuntas(Integer colOrigen, Integer filaOrigen) {
        var origen = this.tableau.get(colOrigen);
        for (int i = filaOrigen; i < origen.size()-1; i++) {
            Carta cartaActual = origen.get(i);
            Carta cartaSiguiente = origen.get(i+1);
            var consecutivas = (cartaActual.getNumero() == cartaSiguiente.getNumero()+1);
            var mismoPalo = (cartaActual.getPalo() == cartaSiguiente.getPalo());
            if (!consecutivas || !mismoPalo) {
                return false;
            }
        }
        return true;
    }

    public List<Carta> getCartasAgrupadas(Integer colOrigen, Integer filaOrigen){
        var origen = this.tableau.get(colOrigen);
        if (this.sePuedenMoverJuntas(colOrigen, filaOrigen)) {
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
        return null;
    }

    public List<Carta> getCartasAgrupadasBase(Integer colOrigen, Integer filaOrigen) {
        var origen = this.tableau.get(colOrigen);
        if (origen.size() - filaOrigen == 13) {
            return getCartasAgrupadas(colOrigen, filaOrigen);
        }
        return null;
    }

    public boolean columnasOcupadas() {
        for (ArrayList<Carta> columna : tableau) {
            if (columna.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getSizeTableau() {
        return this.tableau.size();
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static TableauSpider deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (TableauSpider) ois.readObject();
    }
}
