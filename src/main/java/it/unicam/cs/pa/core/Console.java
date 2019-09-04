package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static it.unicam.cs.pa.core.UnicodeBox.*;

/**
 *
 * Simple screen utility to print board and animation
 *
 */
public class Console {

    private static final Console console = new Console();
    private static final String row_start = "├───";
    private static final String row_del = "┼───";
    private static final String row_end = "┤\n";
    private Utils util;
    private Cell[][] board;
    private PrintStream out;
    private int xsize;
    private int ysize;

    private Console() {
        this.util = new Utils();
        this.out = System.out;
    }

    public void init(BattleGround bg) {
        this.board = bg.getBoard();
        this.xsize = bg.getX();
        this.ysize = bg.getY();
    }

    /**
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
        printNumberIndicator();
        for ( int x = 0; x < xsize; x++){
            printDelimiter();
            for ( int y=0; y < ysize; y++){
                if (board[x][y].isFilled()) {
                    out.print(PIPE + board[x][y].getDisc().toString());
                } else {
                    out.print(EMPTYCELL);
                }
            }
            out.print(PIPE);
            out.println();
        }
        printBottomDelimiter();
    }

    /**
     * Iterate through the column till the last added disc
     */
    public void printFallingDisc(Player player, int x_move, int y_move) {
        try {
            for (int x = 0; x < x_move; x++) {
                System.out.println(x);
                util.clean();
                discAnimation(player, x, y_move);
                TimeUnit.MILLISECONDS.sleep(160);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void discAnimation(Player player, int x_move, int y_move) {
        printNumberIndicator();
        for ( int x = 0; x < xsize; x++) {
            printDelimiter();
            for ( int y=0; y < ysize; y++) {
                if (board[x][y].isFilled() == true) {
                    out.print(PIPE + board[x][y].getDisc().toString());
                } else if (y == y_move && x == x_move) {
                    out.print(PIPE + player.getDisc().toString());
                } else {
                    out.print(EMPTYCELL);
                }
            }
            out.print("│");
            out.println();
        }
        printBottomDelimiter();
    }

    public void printBoardDelay() {
        util.clean();
        printNumberIndicator();
        try {
            for (int x = 0; x < xsize; x++) {
                TimeUnit.MILLISECONDS.sleep(80);
                printDelimiter();
                for (int y = 0; y < ysize; y++) {
                    TimeUnit.MILLISECONDS.sleep(50);
                    out.print(EMPTYCELL);
                }
                out.print(PIPE);
                out.println();
            }
            printBottomDelimiter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printNumberIndicator() {
        out.print(ULCORNER);
        for (int j = 0; j < ysize-1; j++) {
            out.print(USEPARATOR);
        }
        out.println(URCORNER);
        out.print(PIPE + " ");
        for (int i = 0; i < ysize; i++) {
            if (i > 9) out.print(i + "" + PIPE + " ");
            else out.print(i + " " + PIPE + " ");
        }
        out.println();
    }

    private void printDelimiter() {
        out.print(LSEPARATOR);
        for (int i = 0; i < ysize-1; i++) {
            out.print(MSEPARATOR);
        }
        out.println(RSEPARATOR);
    }

    private void printBottomDelimiter() {
        out.print(DLCORNER);
        for (int i = 0; i < ysize-1; i++) {
            out.print(DSEPARATOR);
        }
        out.println(DRCORNER);
    }
}
