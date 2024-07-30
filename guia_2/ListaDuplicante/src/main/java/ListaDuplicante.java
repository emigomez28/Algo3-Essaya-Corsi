import java.util.ArrayList;

public class ListaDuplicante<T>{
    private ArrayList<T> lista;

    public ListaDuplicante(ArrayList<T> lista) {
        this.lista = lista;
    }

    public void add(T x) {
        lista.add(x);
        lista.add(x);
    }

    public T get(int i) {
        return lista.get(i);
    }

}
