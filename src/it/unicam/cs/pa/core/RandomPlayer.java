package it.unicam.cs.pa.core;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomPlayer extends AbstractPlayer implements Player {

    private final char symbol;
    private String username;

    public RandomPlayer(char symbol, String username) {
        this.symbol = symbol;
        this.username = username;
    }

    @Override
    public int getMove() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextInt(0, 7);
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
