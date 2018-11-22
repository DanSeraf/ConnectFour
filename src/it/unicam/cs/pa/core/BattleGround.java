package it.unicam.cs.pa.core;

import java.io.IOException;

public class BattleGround {
    public static final int X_SIZE = 6;
    public static final int Y_SIZE = 7;
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

    private void fill() {
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
            return false;
        }
        return false;
    }

    private void checkWinner(int x, int y, char symbol) {
        if (checkVertical(symbol, y) == true
        || checkHorizontal(symbol, x) == true
        || checkDiagonal(symbol, x, y) == true) {
            this.winner = true;
        }
    }

    private boolean checkVertical(char symbol, int y) {
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


    private boolean checkHorizontal(char symbol, int x) {
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

    private boolean checkDiagonal(char symbol, int x, int y) {
        if (checkDiagonalRB(symbol, x, y) + checkDiagonalLT(symbol, x, y) >= 3
            || checkDiagonalRT(symbol, x, y) + checkDiagonalLB(symbol, x, y) >= 3) {
            return true;
        } else {
            return false;
        }
    }

    private int checkDiagonalRB(char symbol, int x, int y) {
        int count = 0;
        while (true) {
            if (x == this.x_size-1 || y == this.y_size-1) {
                break;
            } else if (this.board[++x][++y].getDisc().getSymbol() == symbol) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int checkDiagonalLT(char symbol, int x, int y) {
        int count = 0;
        while (true) {
            if (x == 0 || y == 0) {
                break;
            } else if (this.board[--x][--y].getDisc().getSymbol() == symbol) {
                count++;
            } else { break; }
        }
        return count;
    }

    private int checkDiagonalRT(char symbol, int x, int y) {
        int count = 0;
        while(true) {
            if (x == 0 || y == this.y_size-1) {
                break;
            } else if (this.board[--x][++y].getDisc().getSymbol() == symbol) {
                count++;
            } else { break; }
        }
        return count;
    }

    private int checkDiagonalLB(char symbol, int x, int y) {
        int count = 0;
        while(true) {
            if (x == this.x_size-1 || y == 0) {
                break;
            } else if (this.board[++x][--y].getDisc().getSymbol() == symbol) {
                count++;
            } else { break; }
        }
        return count;
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
