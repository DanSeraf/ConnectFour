package it.unicam.cs.pa.core;

/**
 * it prints the board is after a player's move
 * and clean the screen before to print the board
 */

public class ConsolePrinter {

    public ConsolePrinter() {};

    /**
     * clean the screen and go up to the console
     */
    public void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * print the board in a fashion format
     * @param x_size row size of board
     * @param y_size column size of board
     * @param bg Battleground object
     */
    public void printBoard(int x_size, int y_size, BattleGround bg) {
        System.out.println();
        for (int x = 0; x < x_size; x++ ) {
            for (int y = 0; y < y_size; y++) {
                System.out.print("|" + bg.getBoard()[x][y].getDisc().getSymbol() + "|");
            }
            System.out.println();
        }
        for (int i = 0; i < x_size-1; i++) {
            System.out.print( " " + i + " ");
        }
        System.out.println();
    }
}
