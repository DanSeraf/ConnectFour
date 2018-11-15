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
                    checkWinner(x--, move, symbol);
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid position, press enter to retry.");
            System.in.read();
            return false;
        }
        return false;
    }

    private void checkWinner(int x, int y, char symbol) {
        if (checkVertical(symbol, x, y) == true
        || checkHorizontalDx(symbol, x, y++) == true
        || checkDiagonalSx(symbol, x--, y--) == true
        || checkDiagonalDx(symbol, x--, y++) == true
        || checkHorizontalSx(symbol, x, y--) == true) {
            this.winner = true;
        }
    }

    private boolean checkCount(int count) {
        return count==4 ? true : false;
    }

    public boolean checkVertical(char symbol, int x, int y) {
        int count = 0;
        if (x <= (this.x_size-1)/2) {
            for (int i = 0; i < 4; i++, x++) {
                if (this.board[x][y].isFilled() == true && this.board[x][y].getDisc().getSymbol() == symbol) {
                    count++;
                } else { return false; }
            }
        }
        return checkCount(count);
    }


    public boolean checkHorizontalDx(char symbol, int x, int y) {
        int count = 0;
        if (y <= (this.y_size/2) - 1) {
            for (int i = 0; i < 4; i++, y++) {
                if (this.board[x][y].isFilled() == true && this.board[x][y].getDisc().getSymbol() == symbol) {
                    count++;
                } else { return false; }
            }
        }
        return checkCount(count);
    }

    public boolean checkHorizontalSx(char symbol, int x, int y) {
        int count = 0;
        if (y >= (this.y_size/2)) {
            for (int i = 0; i < 4; i++, y--) {
                if (this.board[x][y].isFilled() == true && this.board[x][y].getDisc().getSymbol() == symbol) {
                    count++;
                } else { return false; }
            }
        }
        return checkCount(count);
    }

    public boolean checkDiagonalDx(char symbol, int x, int y) {
        int count = 0;
        if (x >= (this.x_size-1)/2 && y <= (this.y_size/2)-1) {
            for (int i = 0; i < 4; i++, x++, y++) {
                if (this.board[x][y].getDisc().getSymbol() == symbol) {
                    count++;
                } else { return false; }
            }
        }
        return checkCount(count);
    }

    public boolean checkDiagonalSx(char symbol, int x, int y) {
        int count = 0;
        if (x >= (this.x_size-1)/2 && y >= (this.y_size/2)) {
            for (int i = 0; i < 4; i++, x--, y--) {
                if (this.board[x][y].getDisc().getSymbol() == symbol) {
                    count++;
                } else { return false; }
            }
        }
        return checkCount(count);
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
