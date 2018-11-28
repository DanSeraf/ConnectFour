package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomPlayer extends AbstractPlayer {

    private static final long serialVersionUID = -4654776393486895934L;
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
        return this.username;
    }

    @Override
    public Disc getDisc() {
        return this.disc;
    }
}
