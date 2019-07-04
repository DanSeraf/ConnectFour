package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;
import it.unicam.cs.pa.core.Utils;

import java.io.*;

public class HumanPlayer implements Player, Serializable {

    private static final long serialVersionUID = 6446382602942139248L;
    private String username;
    private Disc disc;

    public HumanPlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
    }

    @Override
    public int getMove() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                return Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
