import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Impuras implements Serializable {
    // Es Impura porque muta a la lista que llega por parametro
    // Corrección: Todo ok
    public boolean eliminarUltimo(List<Integer> pila) {
        if (pila.isEmpty()) {
            return false;
        }
        pila.remove(pila.size() - 1);
        return true;
    }
    // Es pura
    // Corrección: Todo ok
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    // Es pura
    // Corrección: El hecho de escribir y leer archivos la hace impura, es un
    // efecto colateral
    public void guardar(Serializable o, String ruta) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(ruta);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(o);
        }
    }
    // Es impura porque frente a un mismo parámetro puede devolver cosas distintas
    // Corrección: Todo ok
    public int tirarDado(int n) {
        Random random = new Random();
        return random.nextInt(n) + 1;
    }
    // Es pura
    // Corrección: Todo ok
    public static int mcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    // Es pura
    // Corrección: Todo ok
    public static List<String> aMayusculas(List<String> strings) {
        List<String> resultado = new ArrayList<>();
        for (String str : strings) {
            resultado.add(str.toUpperCase());
        }
        return resultado;
    }
    // Es pura
    // Corrección: Es impura ya que 2 invoaciónes pueden producir efectos diferentes
    public boolean esEnElFuturo(LocalDateTime t) {
        LocalDateTime now = LocalDateTime.now();
        return t.isAfter(now);
    }
}
