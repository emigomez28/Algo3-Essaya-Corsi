package org.example;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var mazo_1 = new ArrayList<Integer>();
        var mazo_2 = new ArrayList<Integer>();

        for (int i = 0; i < 10; i++) {
            mazo_1.add(i);
            mazo_2.add(i);
        }

        shuffle(mazo_1);
        System.out.println("Semilla aleatoria: " + mazo_1);

        shuffle(mazo_2, new Random(1));
        System.out.println("Misma semilla siempre: " + mazo_2);
    }
}