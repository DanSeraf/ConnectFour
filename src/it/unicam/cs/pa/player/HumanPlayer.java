package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.io.*;

public class HumanPlayer extends AbstractPlayer {

    private static final long serialVersionUID = 6446382602942139248L;
    private String username;
    private Disc disc;
    private transient BufferedReader reader;

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
