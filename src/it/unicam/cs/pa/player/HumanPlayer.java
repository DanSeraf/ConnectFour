package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HumanPlayer extends AbstractPlayer implements Player {

    private static final long serialVersionUID = 6446382602942139248L;
    private String username;
    private Disc disc;
    private BufferedReader reader;

    public HumanPlayer(char symbol, String username, DiscColors color, InputStream in) {
        this.username = username;
        this.disc = new Disc(symbol, color);
        this.reader = new BufferedReader(new InputStreamReader(in));
    }

    public HumanPlayer(char symbol, String username, DiscColors color) {
        this(symbol, username, color, System.in);
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
