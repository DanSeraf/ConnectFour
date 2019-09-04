package it.unicam.cs.pa.core;

import java.util.LinkedList;

/**
 *
 * This class is responsable to keep track of the rounds
 * After every move from the players it update a LinkedList that contains all the boards after the move
 * It is also responsable of decide which is the current player
 *
 */ 

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
