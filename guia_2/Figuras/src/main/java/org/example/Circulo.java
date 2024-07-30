package org.example;

public class Circulo implements Figura {
    private final int radio;

    public Circulo(int radio) {
        this.radio = radio;
    }

    public int area() {
        return (int) (Math.PI * radio * radio);
    }

}
