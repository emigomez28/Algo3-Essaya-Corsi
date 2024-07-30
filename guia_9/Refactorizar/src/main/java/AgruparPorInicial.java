import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgruparPorInicial {
    public static Map<String, List<String>> agruparPorInicial(List<String> nombres) {
        Map<String, List<String>> resultado = new Map<>();
        for (String nombre : nombres) {
            if (nombre.length() > 0) {
                char inicial = Character.toUpperCase(nombre.charAt(0));
                List<String> nombresInicial = resultado.get(inicial);
                if (nombresInicial == null) {
                    nombresInicial = new ArrayList<>();
                    resultado.put(inicial, nombresInicial);
                }
                nombresInicial.add(nombre);
            }
        }
        return resultado;
    }

    public static Map<Character, List<String>> agruparPorInicialFuncional(List<String> nombres) {
        return nombres.stream()
                .filter(nombre -> !nombre.isEmpty())
                .collect(Collectors.groupingBy(
                        nombre -> Character.toUpperCase(nombre.charAt(0)),
                        Collectors.toList()
                ));
    }



}
