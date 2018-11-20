package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends AbstractPlayer implements Player {

    private final char symbol;
    private String username;

    public HumanPlayer(char symbol, String username) {
        this.symbol = symbol;
        this.username = username;
    }

    @Override
    public int getMove() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(">" + this.username + " move: ");
        return Integer.parseInt(reader.readLine());
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
