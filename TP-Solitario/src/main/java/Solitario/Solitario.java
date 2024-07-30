package Solitario;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Deque;
import java.util.List;

public interface Solitario {
     boolean moverTableauATableau(Integer colOrigen, Integer filaOrigen, Integer colDestino);

     boolean juegoGanado();

     List<Deque<Carta>> verCartasBase();

     void serializar(OutputStream os) throws IOException;
}
