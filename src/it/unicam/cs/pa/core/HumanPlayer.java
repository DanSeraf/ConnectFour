package it.unicam.cs.pa.core;

import java.io.Serializable;

public class HumanPlayer implements Player {

    private static int ID = 100;
    private final char symbol;
    private String username = null;

    public HumanPlayer(char symbol, String username) {
        this.symbol = symbol;
        this.ID = ID++;
        this.username = username;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getID() {
        return this.ID;
    }

    public String getUser() {
        return this.username;
    }
}
