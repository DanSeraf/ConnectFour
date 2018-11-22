package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends AbstractPlayer implements Player {

    private String username;
    private Disc disc;

    public HumanPlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
    }

    @Override
    public int getMove() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(reader.readLine());
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
