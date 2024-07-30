package Solitario;

public enum Palo {
    CORAZONES(Color.ROJO, "♥"),
    PICAS(Color.NEGRO, "♦"),
    TREBOLES(Color.NEGRO, "♣"),
    DIAMANTES(Color.ROJO, "♠"),
    ;

    private final Color color;
    private final String simbolo;

    Palo(Color color, String simbolo) {
        this.color = color;
        this.simbolo = simbolo;
    }

    public Color getColor() {
        return this.color;
    }

    public String getSimbolo() {
        return simbolo;
    }
}