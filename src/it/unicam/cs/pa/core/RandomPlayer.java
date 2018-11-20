package it.unicam.cs.pa.core;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer extends AbstractPlayer implements Player {

    private final char symbol;
    private String username;

    public RandomPlayer(char symbol, String username) {
        this.symbol = symbol;
        this.username = username;
    }

    @Override
    public int getMove() {
        return ThreadLocalRandom.current().nextInt(0, 5);
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getUser() {
        return username;
    }
}
