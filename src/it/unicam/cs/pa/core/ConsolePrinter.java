package it.unicam.cs.pa.core;

public class ConsolePrinter {

    public ConsolePrinter() {};

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

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
