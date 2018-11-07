package it.unicam.cs.pa.core;

public class BattleGround {
    public static final int X_SIZE = 6;
    public static final int Y_SIZE = 7;
    private Cell[][] board;
    private int x_size;
    private int y_size;

    public BattleGround(int x, int y) {
        this.x_size = x;
        this.y_size = y;
        this.board = new Cell[x][y];
        populate();
    }

    public BattleGround() {
        this( X_SIZE, Y_SIZE );
    }

    public void populate() {
        for ( int x = 0; x < this.x_size; x++ ) {
            for (int y = 0; y < this.y_size; y++) {
                this.board[x][y] = new Cell();
            }
        }
    }

    public void showElem() {
        System.out.println();
        for ( int x = 0; x < this.x_size; x++ ) {
            for (int y = 0; y < this.y_size; y++) {
                System.out.print(this.board[x][y].getDisc().getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
