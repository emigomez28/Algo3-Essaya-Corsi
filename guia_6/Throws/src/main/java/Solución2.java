import java.util.ArrayList;
import java.util.List;

public class Solución2 {
    public static void main (String[] args) {
        try {
            List<Double> numeros = pedirNumeros();
            double p = promedio(numeros);
            System.out.printf("El promedio es: %f\n", p);
        } catch (ListaVaciaException2 ex) {
            System.out.println("La lista esta vacía");
        }
    }

    private static List<Double> pedirNumeros() throws ListaVaciaException2 {
        List<Double> numeros = new ArrayList<>();
        // [...] pide numeros al usuario y los agrega a la lista
        if (numeros.isEmpty()) {
            throw new ListaVaciaException2();
        }
        return numeros;
    }

    private static double promedio(List<Double> numeros) {
        double suma = 0;
        for (Double n : numeros) {
            suma += n;
        }
        return suma / numeros.size();
    }
}

    class ListaVaciaException2 extends Exception {}
}
