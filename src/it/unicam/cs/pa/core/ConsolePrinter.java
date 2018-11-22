package it.unicam.cs.pa.core;

/**
 * it prints the board is after a player's move
 * and clean the screen before to print the board
 */

public class ConsolePrinter {

    private static final String RESET = "\u001B[0m";
    private String row_del = "o---";
    private String row_end = "o\n";

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
     * @param board Battleground object
     */
    public void printBoard(BattleGround board) {
        Cell[][] bg = board.getBoard();
        for (int i = 0; i < board.getySize(); i++) {
            System.out.print("  " + i + " ");
        }
        System.out.println();
        for ( int x = 0; x < board.getxSize(); x++){
            printDel(board.getySize());
            for ( int y=0; y < board.getySize(); y++){
                if (bg[x][y].isFilled() == true) {
                    System.out.print("| " + bg[x][y].getDisc().getColor() + bg[x][y].getDisc().getSymbol() + RESET + " ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        printDel(board.getySize());
    }

    private void printDel(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(row_del);
        }
        System.out.print(row_end);
    }
}
