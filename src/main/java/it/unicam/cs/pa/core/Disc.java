package it.unicam.cs.pa.core;

import java.io.Serializable;

/**
 *
 * Disc is represented with a color and a symbol
 *
 * The Disc is Serializable because it's used by the Player
 * that is serialized
 *
 */
public class Disc implements Serializable {

    private DiscColors color;
    private char symbol;

    public Disc() {}

    public Disc(char symbol, DiscColors color) {
        this.symbol = symbol;
        this.color = color;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public String getColor() {
        return this.color.toString();
    }
}
