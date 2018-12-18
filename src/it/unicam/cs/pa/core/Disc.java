package it.unicam.cs.pa.core;

import java.io.Serializable;

public class Disc implements Serializable {

    private DiscColors color;
    private char symbol;

    public Disc(char symbol, DiscColors color) {
        this.symbol = symbol;
        this.color = color;
    }

    public Disc() {
    }

    public char getSymbol() {
        return this.symbol;
    }

    public String getColor() {
        return this.color.toString();
    }
}
