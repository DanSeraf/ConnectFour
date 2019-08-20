package it.unicam.cs.pa.core;

import it.unicam.cs.pa.exception.FullColumnException;
import it.unicam.cs.pa.player.Player;

public class BattleGround {

    private static final int X_SIZE = 6;
    private static final int Y_SIZE = 7;
    private static int X;
    private static int Y;
    private Cell[][] board;
    private boolean winner = false;
    private Console console = Console.getInstance();
    private Automa state;

    public BattleGround(int x, int y, Automa state) {
        this.state = state;
        this.X = x;
        this.Y = y;
        this.board = new Cell[x][y];
        fill();
    }

    public BattleGround(Automa states) {
        this( X_SIZE, Y_SIZE, states );
    }

    public static int getX() {
        return X;
    }

    public static int getY() {
        return Y;
    }

    /**
     * Fill the board with empty Cell
     */
    public void fill() {
        for ( int x = 0; x < X_SIZE; x++ ) {
            for (int y = 0; y < Y_SIZE; y++) {
                this.board[x][y] = new Cell();
            }
        }
    }

    public boolean canMove(Player p) {
        if (p.getPid() != state.currentPlayer()) return false;
        return true;
    }

    /**
     * Add disc in the first empty space
     *
     * @param player player who is playing
     * @param move x position of move
     *
     */
    public void addDisc(Player player, int move) {
        // not the turn of the player
        if (player.getPid() != state.currentPlayer()) return;

        try {
            validateMove(move);
            for (int x = X - 1; x >= 0; x--) {
                console.printFallingDisc(player, x, move);
                board[x][move].setDisc(player.getDisc());
                state.insert(board);
                checkWinner(x, move, player.getDisc().getSymbol());
                break;
            }
        } catch (FullColumnException | ArrayIndexOutOfBoundsException f) {
            return;
        }
    }

    /**
     * validate move request by user
     * @param move column of the board
     * @return true if the column is not full
     * @return false if the column is full
     */
    public void validateMove(int move) {
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
        if (checkLines(symbol, y, "vertical")
        || checkLines(symbol, x, "horizontal")
        || checkDiagonal(symbol, x, y)) {
            this.winner = true;
        }
    }

    private boolean checkLines(char symbol, int pos, String mode) {
        int count = 0;
        int len = mode == "vertical" ? X : Y;

        for (int i = 0; i < len; i++) {
            if (mode == "vertical" && board[i][pos].getDiscSymbol() == symbol) count++;
            else if (mode == "horizontal" && board[pos][i].getDiscSymbol() == symbol) count++;
            if (count == 4) return true;
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
            if (x == X-1 || y == Y-1) {
                break;
            } else if (this.board[++x][++y].getDiscSymbol() == symbol) {
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
            } else if (this.board[--x][--y].getDiscSymbol() == symbol) {
                count++;
            } else { break; }
        }
        return count;
    }

    private int checkDiagonalRT(char symbol, int x, int y) {
        int count = 0;
        while(true) {
            if (x == 0 || y == Y -1) {
                break;
            } else if (this.board[--x][++y].getDiscSymbol() == symbol) {
                count++;
            } else { break; }
        }
        return count;
    }

    private int checkDiagonalLB(char symbol, int x, int y) {
        int count = 0;
        while(true) {
            if (x == X - 1 || y == 0) {
                break;
            } else if (this.board[++x][--y].getDiscSymbol() == symbol) {
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
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
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

    public Cell[][] getBoard() {
        return this.board;
    }
}