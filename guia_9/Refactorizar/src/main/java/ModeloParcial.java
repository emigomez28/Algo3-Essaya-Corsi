import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// Usando conceptos de programación funcional, diseñar una función que reciba una
// lista de palabras, y devuelva la lista de las 10 palabras más cortas que contienen las
// 5 vocales
public class ModeloParcial {
    public List<String> top10MasCortasYVocales(List<String> palabras) {
        return palabras.stream()
                .filter(str -> str.contains("a"))
                .filter(str -> str.contains("e"))
                .filter(str -> str.contains("i"))
                .filter(str -> str.contains("o"))
                .filter(str -> str.contains("u"))
                .collect(Collectors.groupingBy(
                        str -> str, Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Encontrar el elemento mas grande de la una lista

    public Optional<Integer> masGrande(List<Integer> lista) {
        return lista.stream().min(Comparator.comparingInt(x -> x));
    }

    // Invertir una palabra
    public String invertirPalabra(String palabra) {
        return palabra
    }

}
