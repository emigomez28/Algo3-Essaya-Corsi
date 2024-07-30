package org.example;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Figura> figuras = new ArrayList<>();
        figuras.add(new Circulo(3));
        figuras.add(new Cuadrado(5));
        figuras.add(new Rectangulo(2, 4));

        System.out.printf("El area total es: %f\n", (areaTotal(figuras)));

    }

    private static double areaTotal(List<Figura> figuras) {
        double total = 0;
        for (Figura f: figuras) {
            total += f.area();
        }
        return total;
    }
}
