package it.unicam.cs.pa.core;

import it.unicam.cs.pa.exception.FullColumnException;
import it.unicam.cs.pa.player.Player;

public class BattleGround {
    private static BattleGround battleGround = new BattleGround();
    private static final int X_SIZE = 6;
    private static final int Y_SIZE = 7;
    private Cell[][] board;
    private boolean winner = false;

    private BattleGround() {
        this.board = new Cell[X_SIZE][Y_SIZE];
    }

    public static BattleGround getInstance() {
        return battleGround;
    }

    /**
     * Fill the board with empty Cell
     */
    public void reset() {
        for ( int x = 0; x < X_SIZE; x++ ) {
            for (int y = 0; y < Y_SIZE; y++) {
                this.board[x][y] = new Cell();
            }
        }
    }

    /**
     * Add disc in the first empty space
     *
     * @param player player who is playing
     * @param move x position of move
     *
     */
    public void addDisc(Player player, int move) throws ArrayIndexOutOfBoundsException {
        for (int x = X_SIZE-1; x >= 0; x--) {
            if (!this.board[x][move].isFilled()) {
                Console.getInstance().printFallingDisc(player, x, move);
                this.board[x][move].setDisc(player.getDisc());
                checkWinner(x, move, player.getDisc().getSymbol());
                break;
            }
        }
    }

    /**
     * validate move request by user
     * @param move
     * @return true if the column is not full
     * @return false if the column is full
     */
    public void validateMove(int move)                                                                                                       {
        if (board[0][move].isFilled()) {
            throw new FullColumnException("Invalid position, the column is full!");
        }
    }

    /**
     * Check for a winner, so check vertical, horizontal and diagonal
     *
     * @param x x move
     * @param y y move
     * @param symbol symbol of the player who is playing
     *
     */
    private void checkWinner(int x, int y, char symbol) {
        if (checkVertical(symbol, y)
        || checkHorizontal(symbol, x)
        || checkDiagonal(symbol, x, y)) {
            this.winner = true;
        }
    }

    private boolean checkVertical(char symbol, int y) {
        int count = 0;
        for (int i = 0; i<X_SIZE; i++) {
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
        for (int i = 0; i<Y_SIZE; i++) {
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
        return checkDiagonalRB(symbol, x, y) + checkDiagonalLT(symbol, x, y) >= 3
                || checkDiagonalRT(symbol, x, y) + checkDiagonalLB(symbol, x, y) >= 3;
    }

    private int checkDiagonalRB(char symbol, int x, int y) {
        int count = 0;
        while (true) {
            if (x == X_SIZE-1 || y == Y_SIZE-1) {
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
            if (x == 0 || y == Y_SIZE-1) {
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
            if (x == X_SIZE-1 || y == 0) {
                break;
            } else if (this.board[++x][--y].getDisc().getSymbol() == symbol) {
                count++;
            } else { break; }
        }
        return count;
    }
    /**
     * check if board is completely filled
     * @return true if board is full
     */
    public boolean isFull() {
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
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
        return Y_SIZE;
    }

    public int getxSize() {
        return X_SIZE;
    }

    public Cell[][] getBoard() {
        return this.board;
    }
}
