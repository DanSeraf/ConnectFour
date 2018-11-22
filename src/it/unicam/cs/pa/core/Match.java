package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Match {

    private BattleGround board;
    private ConsolePrinter printer;
    private Player winner;
    private ArrayList<Player> available_players;
    private Player[] players;
    private MatchStatus status = MatchStatus.PLAYING;
    private BufferedReader reader;

    public Match(Settings settings) {
        this.board = new BattleGround();
        this.available_players = settings.getPlayers();
        this.printer = new ConsolePrinter();
        this.players = new Player[2];
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * match initialization
     * selectPlayers is used to let the player choose for a character stored in settings
     * inside the loop, a move is taken from the player and inserted into the board
     * based on the response of the board, the player will change with getOtherPlayer
     * the game finish when there is winner, it is checked by the Battleground
     */
    public void start() {
        selectPlayers();
        int id = 0;
        //TODO Select board size
        do {
            printer.clean();
            printer.printBoard(board);
            try {
                System.out.print(">" + this.players[id].getUser() + " move: ");
                int move = players[id].getMove();
                // check for a valid move
                if (board.addDisc(players[id].getSymbol(), move) == false) {
                    continue;
                } else { id = getOtherPlayer(id); }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (this.board.isThereAWinner() == true && !board.isFull()) {
                    this.winner = players[getOtherPlayer(id)];
                    printer.printBoard(board);
                    status = MatchStatus.END;
                }
            }
        } while(status == MatchStatus.PLAYING);
        printWinner();
    }

    private void printWinner() {
        printer.clean();
        printer.printBoard(board);
        System.out.println("The winner is: " + this.winner.getUser());
        System.out.println("Press enter to return in menu");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * change the player
     */
    public static int getOtherPlayer(int id) {
        return (id+1)%2;
    }

    private void selectPlayers() {
        try {
            selectPlayer(1);
            selectPlayer(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Let user select which character they want to use from the available_players ArrayList
     * delete the chosen player from available_players
     * @param n number of the player
     */
    private void selectPlayer(int n) throws IOException {
        int[] index = new int[]{1};
        System.out.println("PLAYER " + n + " select available player:");
        this.available_players.forEach(player ->
            System.out.println("[" + index[0]++ + "] " + player.getUser() + " - (" + player.getSymbol() + ")")
        );
        System.out.print("OPTION ");
        int opt = Integer.parseInt(this.reader.readLine());
        this.players[n-1] = this.available_players.get(opt - 1);
        this.available_players.remove(opt - 1);
        printer.clean();
    }
}
