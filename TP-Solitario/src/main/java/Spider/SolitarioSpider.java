package Spider;

import Solitario.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SolitarioSpider implements Solitario, Serializable {
    private final TableauSpider tableau;
    private final MazoSpider mazo;
    private final BaseSpider base;

    public SolitarioSpider(MazoSpider mazo, TableauSpider tableau, BaseSpider base) {
        this.mazo = mazo;
        this.tableau = tableau;
        this.base = base;
    }

    public SolitarioSpider(Integer semilla) {
        this.mazo = new MazoSpider(semilla);
        Deque<Carta> cartasIniciales = mazo.sacarNoVistas(54);
        this.base = new BaseSpider();
        this.tableau = new TableauSpider(cartasIniciales);
    }

    public SolitarioSpider() {
        this.mazo = new MazoSpider();
        Deque<Carta> cartasIniciales = mazo.sacarNoVistas(54);
        this.base = new BaseSpider();
        this.tableau = new TableauSpider(cartasIniciales);
    }

    public boolean pedirCarta(){
        if (this.tableau.columnasOcupadas() && this.mazo.quedanCartas()){
            var topeTableau = this.tableau.getSizeTableau();
            for (int i = 0; i < topeTableau; i++){
                Carta carta = this.mazo.revelarCarta();
                this.tableau.colocarCartaDelMazo(carta, i);
            }
            return true;
        }
        return false;
    }

     public boolean moverTableauATableau(Integer colOrigen, Integer filaOrigen, Integer colDestino) {
          return this.tableau.moverCartas(colOrigen, filaOrigen, colDestino);
     }

    public boolean moverTableauABase(Integer colOrigen, Integer filOrigen){
        List<Carta> cartasAgrupadas = this.tableau.getCartasAgrupadasBase(colOrigen, filOrigen);
        if (cartasAgrupadas != null) {
            this.base.colocarCartas(cartasAgrupadas);
            return true;
        }
        return false;
    }

    public boolean juegoGanado() {return this.base.estaCompleta();}

    public Deque<Carta> verCartasMazo() {
        return mazo.verCartas();
    }

    public ArrayList<ArrayList<Carta>> verCartasTableau() { return tableau.verTableau(); }

    public ArrayList<Deque<Carta>> verCartasBase() { return base.verBase(); }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static SolitarioSpider deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (SolitarioSpider) ois.readObject();
    }

}
