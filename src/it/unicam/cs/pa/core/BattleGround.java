package it.unicam.cs.pa.core;

import java.io.IOException;

public class BattleGround {
    public static final int X_SIZE = 7;
    public static final int Y_SIZE = 6;
    private Cell[][] board;
    private int x_size;
    private int y_size;
    private boolean winner = false;

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

    public boolean addDisc(char symbol, int move) throws IOException {
        try {
            for (int x = this.x_size-1; x >= 0; x--) {
                if (this.board[x][move].isFilled() == false) {
                    this.board[x][move].setDisc(symbol);
                    checkWinner(x, move, symbol);
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid position, press enter to retry.");
            System.in.read();
        }
        return false;
    }

    private void checkWinner(int x, int y, char symbol) {
        if (checkVertical(symbol, y) == true
        || checkHorizontal(symbol, x) == true) {
            this.winner = true;
        }
    }

    public boolean checkVertical(char symbol, int y) {
        int count = 0;
        for (int i = 0; i<this.x_size; i++) {
            if (this.board[i][y].getDisc().getSymbol() == symbol) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else { count = 0; }
        }
        return false;
    }


    public boolean checkHorizontal(char symbol, int x) {
        int count = 0;
        for (int i = 0; i<this.y_size; i++) {
            if (this.board[x][i].getDisc().getSymbol() == symbol) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else { count = 0; }
        }
        return false;
    }

    /**
     * check board diagonally from left to right and vice versa
     * @param symbol symbol that will be added
     * @return true if four symbol have been found
     */

    public boolean checkDiagonalLr(char symbol) {
        int count = 0;
        for (int row = 0; row < this.x_size-3; row++) {

        }
        return false;
    }

    /**
     * check if board is completely filled
     * in that case, exit the game
     * @return true if board is full
     */
    public boolean isFull() {
        for (int x = 0; x < this.x_size; x++) {
            for (int y = 0; y < this.y_size; y++) {
                if (!this.board[x][y].isFilled()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isThereAWinner() {
        return this.winner;
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
