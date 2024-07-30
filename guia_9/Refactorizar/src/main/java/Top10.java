import java.io.StringBufferInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Top10 {
    public static List<String> top10(List<String> strings) {
        Map<String, Integer> mapaDeFrecuencias = new HashMap<>();
        for (String str : strings) {
            mapaDeFrecuencias.put(str, mapaDeFrecuencias.getOrDefault(str, 0) + 1);
        }

        List<Map.Entry<String, Integer>> items = new
                ArrayList<>(mapaDeFrecuencias.entrySet());
        items.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<String> top10 = new ArrayList<>();
        for (int i = 0; i < Math.min(10, items.size()); i++) {
            top10.add(items.get(i).getKey());
        }

        return top10;
    }

    public static List<String> top10Funcional(List<String> strings) {
        return strings.stream()
                .collect(Collectors.groupingBy(
                        str -> str,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
