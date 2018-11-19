package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class HumanPlayer implements Player, Serializable {

    private final char symbol;
    private String username;
    BufferedReader reader;

    public HumanPlayer(char symbol, String username) {
        this.symbol = symbol;
        this.username = username;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public int getMove() throws IOException {
        System.out.print(">" + this.username + " move: ");
        int opt = Integer.parseInt(this.reader.readLine());
        return opt;
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String getUser() {
        return this.username;
    }
}
