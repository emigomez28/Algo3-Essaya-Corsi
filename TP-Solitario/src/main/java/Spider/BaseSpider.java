package Spider;

import Solitario.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BaseSpider extends Base implements Serializable {
    public BaseSpider(){
        super(2);
    }

    protected BaseSpider(ArrayList<Deque<Carta>> base) {
        super(base);
    }

    public void colocarCartas(List<Carta> cartas) {
        var i = 0;
        for (Deque<Carta> columna : this.base) {
            if (columna.isEmpty()) {
                var colDestino = this.base.get(i);
                colDestino.addAll(cartas);
                return;
            }
            i++;
        }
    }

    public void serializar(OutputStream os) throws IOException {
        var oos = new ObjectOutputStream(os);
        oos.writeObject(this);
        oos.flush();
    }

    public static BaseSpider deserializar(InputStream is) throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(is);
        return (BaseSpider) ois.readObject();
    }
}