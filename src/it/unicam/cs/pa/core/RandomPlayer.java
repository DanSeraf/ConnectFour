package it.unicam.cs.pa.core;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomPlayer extends AbstractPlayer implements Player {

    private String username;
    private Disc disc;

    public RandomPlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
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
    public String getUser() {
        return username;
    }

    @Override
    public Disc getDisc() {
        return this.disc;
    }
}
