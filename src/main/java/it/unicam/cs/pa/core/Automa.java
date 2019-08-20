package it.unicam.cs.pa.core;

import java.util.LinkedList;

public class Automa {

    private LinkedList<Cell[][]> automa = new LinkedList<>();

    public Automa() { }

    public void insert(Cell[][] board) {
        automa.add(board);
    }

    public int currentPlayer() {
        return automa.size()%2;
    }

    public Cell[][] undo() {
        return automa.pop();
    }
}
