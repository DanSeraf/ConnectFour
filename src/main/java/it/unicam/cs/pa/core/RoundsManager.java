package it.unicam.cs.pa.core;

import java.util.LinkedList;

public class RoundsManager {

    private LinkedList<Cell[][]> boards = new LinkedList<>();
    private int nplayers;

    public RoundsManager(int np) {
        this.nplayers = np;
    }

    public void insert(Cell[][] board) {
        boards.add(board);
    }

    public int currentPlayer() {
        return boards.size()%nplayers;
    }

    public Cell[][] undo() {
        return boards.pop();
    }
}
