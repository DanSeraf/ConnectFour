package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;

import java.util.concurrent.TimeUnit;

/**
 *
 * Simple screen utility to print board and animation
 *
 */

/**
 * TODO BATTLEGROUND IN CONSTRUCTOR
 *
 */

public class ConsolePrinter {

    private static final String RESET = "\u001B[0m";
    private String row_del = "o---";
    private String row_end = "o\n";

    public ConsolePrinter() {}

    /**
     * clean the screen and go up to the console
     */
    public void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * print the board in a cool format
     * @param board Battleground object
     */
    public void printBoard(BattleGround board) {
        Cell[][] bg = board.getBoard();
        printNumberIndicator(board.getySize());
        for ( int x = 0; x < board.getxSize(); x++){
            printDelimiter(board.getySize());
            for ( int y=0; y < board.getySize(); y++){
                if (bg[x][y].isFilled() == true) {
                    System.out.print("|" + bg[x][y].getDisc().getColor() + " " + bg[x][y].getDisc().getSymbol() + " " + RESET);
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        printDelimiter(board.getySize());
        System.out.println();
    }

    /**
     * Iterate through the column till the last added disc
     */

    public void printFallingDisc(Cell[][] board, int x_size, int y_size, Player player, int x_move, int y_move) {
        try {
            for (int i = 0; i < x_move; i++) {
                clean();
                discAnimation(board, x_size, y_size, player, i, y_move);
                TimeUnit.MILLISECONDS.sleep(270);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     *
     * @param board
     * @param x_size
     * @param y_size
     * @param player
     * @param x_move
     * @param y_move
     */
    private void discAnimation(Cell[][] board, int x_size, int y_size, Player player, int x_move, int y_move) {
        printNumberIndicator(y_size);
        for ( int x = 0; x < x_size; x++) {
            printDelimiter(y_size);
            for ( int y=0; y < y_size; y++) {
                if (board[x][y].isFilled() == true) {
                    System.out.print("|" + board[x][y].getDisc().getColor()+ " " + board[x][y].getDisc().getSymbol() + " " + RESET);
                } else if (y == y_move && x == x_move) {
                    System.out.print("|" + player.getDisc().getColor() + " " + player.getDisc().getSymbol() + " " + RESET);
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        printDelimiter(y_size);
        System.out.println();

    }

    public void printBoardDelay(BattleGround board) {
        Cell[][] bg = board.getBoard();
        printNumberIndicator(board.getySize());
        try {
            for (int x = 0; x < board.getxSize(); x++) {
                TimeUnit.MILLISECONDS.sleep(80);
                printDelimiter(board.getySize());
                for (int y = 0; y < board.getySize(); y++) {
                    TimeUnit.MILLISECONDS.sleep(50);
                    if (bg[x][y].isFilled() == true) {
                        System.out.print("| " + bg[x][y].getDisc().getColor() + bg[x][y].getDisc().getSymbol() + RESET + " ");
                    } else {
                        System.out.print("|   ");
                    }
                }
                System.out.print("|");
                System.out.println();
            }
            printDelimiter(board.getySize());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printNumberIndicator(int n) {
        System.out.println(".___.___.___.___.___.___.___.");
        System.out.print("| ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " | ");
        }
        System.out.println();
    }

    private void printDelimiter(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(row_del);
        }
        System.out.print(row_end);
    }
}
