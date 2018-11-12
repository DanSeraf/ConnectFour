package it.unicam.cs.pa.core;

public class Cell {

    private Disc disc;
    private boolean filled = false;

    public Cell() {
        this.disc = new Disc();
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(char symbol) {
        this.disc.setSymbol(symbol);
        this.filled = true;
    }

    public boolean isFilled() {
        return this.filled;
    }
}
