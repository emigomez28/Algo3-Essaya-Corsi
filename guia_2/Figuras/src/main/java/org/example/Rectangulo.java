package org.example;

public class Rectangulo implements Figura {
    private final int base;
    private final int altura;

    public Rectangulo(int base, int altura) {
        this.base = base;
        this.altura = altura;
    }

    public int area() {
        return base * altura;
    }
}

