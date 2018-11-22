package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Cell;
import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomPlayer implements Player, Serializable {

    private String username;
    private Disc disc;
    private Cell[][] board;

    public RandomPlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
    }

    public void updateBoard(Cell[][] board) {
        this.board = board;
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
