package it.unicam.cs.pa.core;

public class Cell {

    private Disc disc;
    private boolean filled = false;

    public Cell() {
        this.disc = new Disc();
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
        this.filled = true;
    }

    public Disc getDisc() {
        return disc;
    }

    public boolean isFilled() {
        return this.filled;
    }
}
