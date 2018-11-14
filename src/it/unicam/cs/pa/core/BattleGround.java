package it.unicam.cs.pa.core;

import java.io.IOException;

public class BattleGround {
    public static final int X_SIZE = 7;
    public static final int Y_SIZE = 6;
    private Cell[][] board;
    private int x_size;
    private int y_size;

    public BattleGround(int x, int y) {
        this.x_size = x;
        this.y_size = y;
        this.board = new Cell[x][y];
        fill();
    }

    public BattleGround() {
        this( X_SIZE, Y_SIZE );
    }

    public void fill() {
        for ( int x = 0; x < this.x_size; x++ ) {
            for (int y = 0; y < this.y_size; y++) {
                this.board[x][y] = new Cell();
            }
        }
    }

    //TODO change this function to boolean to check array index out of bound (manage with exception?)
    public boolean addDisc(char symbol, int move) {
        try {
            for (int x = this.x_size - 1; x >= 0; x--) {
                if (this.board[x][move].isFilled() == false) {
                    this.board[x][move].setDisc(symbol);
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid position, press enter to retry.");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
            //TODO manage exception for out of bound
        }
        return false;
    }

    public void checkVertical() {

    }

    public void checkHorizontal() {

    }

    public int getySize() {
        return this.y_size;
    }

    public int getxSize() {
        return this.x_size;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
}
