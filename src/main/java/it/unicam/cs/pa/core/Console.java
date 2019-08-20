package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;

import java.util.concurrent.TimeUnit;

/**
 *
 * Simple screen utility to print board and animation
 *
 */
public class Console {

    private static final Console console = new Console();
    private static final String RESET = "\u001B[0m";
    private String row_del = "o───";
    private String row_end = "o\n";
    private Utils util;
    private Cell[][] board;
    private int xsize;
    private int ysize;

    private Console() {
        this.util = new Utils();
    }

    public void init(BattleGround bg) {
        this.board = bg.getBoard();
        this.xsize = bg.getX();
        this.ysize = bg.getY();
    }

    /**
     * Simple return the instance of the singleton class
     * @return instance of Console
     */
    public static Console getInstance() {
        return console;
    }

    /**
     * print the board in a cool format
     */
    public void printBoard() {
        util.clean();
        printNumberIndicator(ysize);
        for ( int x = 0; x < xsize; x++){
            printDelimiter(ysize);
            for ( int y=0; y < ysize; y++){
                if (board[x][y].isFilled()) {
                    System.out.print("|" + board[x][y].getDiscColor() + " " + board[x][y].getDiscSymbol() + " " + RESET);
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        printDelimiter(ysize);
        System.out.println();
    }

    /**
     * Iterate through the column till the last added disc
     */
    public void printFallingDisc(Player player, int x_move, int y_move) {
        try {
            for (int i = 0; i < x_move; i++) {
                util.clean();
                discAnimation(player, i, y_move);
                TimeUnit.MILLISECONDS.sleep(2000);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void discAnimation(Player player, int x_move, int y_move) {
        printNumberIndicator(ysize);
        for ( int x = 0; x < xsize; x++) {
            printDelimiter(ysize);
            for ( int y=0; y < ysize; y++) {
                if (board[x][y].isFilled() == true) {
                    System.out.print("|" + board[x][y].getDiscColor()+ " " + board[x][y].getDiscSymbol() + " " + RESET);
                } else if (y == y_move && x == x_move) {
                    System.out.print("|" + player.getDisc().getColor() + " " + player.getDisc().getSymbol() + " " + RESET);
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        printDelimiter(ysize);
        System.out.println();

    }

    public void printBoardDelay() {
        util.clean();
        printNumberIndicator(ysize);
        try {
            for (int x = 0; x < xsize; x++) {
                TimeUnit.MILLISECONDS.sleep(80);
                printDelimiter(ysize);
                for (int y = 0; y < ysize; y++) {
                    TimeUnit.MILLISECONDS.sleep(50);
                    if (board[x][y].isFilled()) {
                        System.out.print("| " + board[x][y].getDiscColor() + board[x][y].getDiscSymbol() + RESET + " ");
                    } else {
                        System.out.print("|   ");
                    }
                }
                System.out.print("|");
                System.out.println();
            }
            printDelimiter(ysize);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printNumberIndicator(int n) {
        System.out.println("┌───────────────────────────┐");
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
