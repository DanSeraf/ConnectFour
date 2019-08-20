package it.unicam.cs.pa.core;

/**
 *
 * Represent each point of the field in the BattleGround
 * It contains the Disc of the player who made an insert in that position
 *
 */
public class Cell {

    private Disc disc;
    private boolean filled = false; // used to check if the cell is empty

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

    public char getDiscSymbol() { return disc.getSymbol(); }

    public String getDiscColor() { return disc.getColor(); }

    public boolean isFilled() {
        return this.filled;
    }
}
