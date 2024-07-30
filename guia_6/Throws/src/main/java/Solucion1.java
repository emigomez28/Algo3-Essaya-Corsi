import java.util.ArrayList;
import java.util.List;

public class Solucion1 {
    public static void main (String[] args) {
        List<Double> numeros = pedirNumeros();
        try {
            double p = promedio(numeros); // <-------------- el error está aquí
            System.out.printf("El promedio es: %f\n", p);
        } catch (ListaVaciaException ex) {
            System.out.println("La lista esta vacía");
        }
    }

    private static List<Double> pedirNumeros() {
        List<Double> numeros = new ArrayList<>();
        // [...] pide numeros al usuario y los agrega a la lista
        return numeros;
    }

    private static double promedio(List<Double> numeros) throws ListaVaciaException {
        if (numeros.isEmpty()) {
            throw new ListaVaciaException();
        }

        double suma = 0;
        for (Double n : numeros) {
            suma += n;
        }
        return suma / numeros.size();
    }
}

class ListaVaciaException extends Exception {}

// Correcciones:
//
// * Ignorar la excepción, marcando la función main con throws ListaVaciaException.

// * Otra opción sería cambiar la excepción ListaVaciaException para que no sea chequeada.
// La forma más fácil es heredando de RuntimeException en lugar de Exception.