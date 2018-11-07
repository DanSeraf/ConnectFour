package it.unicam.cs.pa.core;

public class Cell {

    private Disc disc;
    private boolean filled = false;

    public Cell() {
        this.disc = new Disc('#');
    }

    public Disc getDisc() {
        return disc;
    }
}
