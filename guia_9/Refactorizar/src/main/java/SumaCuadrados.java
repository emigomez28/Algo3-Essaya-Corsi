import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SumaCuadrados {
    public static int sumaCuadrados(int n) {
        int suma = 0;
        for (int i = 1; i <= n; i++) {
            suma += i * i;
        }
        return suma;
    }

    public static int sumaCuadradosFuncional(int n) {
        return IntStream.rangeClosed(1, n)
                .map(i -> i*i)
                .sum();
    }

}
