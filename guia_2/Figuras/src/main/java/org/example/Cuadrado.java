package org.example;

public class Cuadrado implements Figura {
    private final int lado;

    public Cuadrado(int lado) {
        this.lado = lado;
    }

    public int area() {
        return lado * lado;
    }
}
