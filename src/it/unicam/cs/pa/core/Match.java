package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Match {

    private BattleGround board;
    private Console printer;
    private Player winner;
    private ArrayList<Player> available_players;
    private Player[] players;
    private MatchStatus status = MatchStatus.PLAYING;
    private BufferedReader reader;
    private Utils util;
    private final String RESET = "\u001B[0m";

    public Match(Settings settings) {
        this.board = new BattleGround();
        this.available_players = settings.getPlayers();
        this.printer = new Console();
        this.players = new Player[2];
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.util = new Utils();
    }

    /**
     * Check if there are at least 2 available players
     *
     * @return true if available players are equal of more than 2
     */
    public boolean ready() {
        if (this.available_players.size() >= 2) {
            return true;
        } else {
            System.out.println("You need at least 2 player, please add a new player");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Match initialization:
     * selectPlayers is used to let the player choose for a character stored in settings
     * inside the loop, a move is taken from the player and inserted into the board
     * based on the response of the board, the player will change with getOtherPlayer
     * The game finish when there is winner, it is checked by the Battleground
     */
    public void start() {
        selectPlayers();
        int id = 0;
        printer.printBoardDelay(board);
        do {
            printer.printBoard(board);
            try {
                System.out.print(this.players[id].getUser() + "> ");
                int move = players[id].getMove(System.in);
                if (board.addDisc(players[id], move) == false) {
                    continue;
                } else { id = getOtherPlayer(id); }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException ne) {
                continue;
            } catch (ArrayIndexOutOfBoundsException a) {
                util.outError("Invalid position, press Enter to retry");
                continue;
            } finally {
                if (this.board.isThereAWinner() == true && !board.isFull()) {
                    this.winner = players[getOtherPlayer(id)];
                    printer.printBoard(board);
                    status = MatchStatus.END;
                    printWinner();
                }
            }
        } while(status == MatchStatus.PLAYING);
    }

    private void printWinner() {
        printer.printBoard(board);
        System.out.println("The winner is: " + this.winner.getDisc().getColor() + this.winner.getUser() + RESET);
        util.outError("Press enter to return in menu");
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
        while(true) {
            try {
                printer.clean();
                int[] index = new int[]{1};
                System.out.println("PLAYER " + n + "\n");
                this.available_players.forEach(player ->
                        System.out.println("[" + index[0]++ + "] [" + player.getDisc().getColor()
                                + player.getDisc().getSymbol() + RESET + "] -> " + player.getUser())
                );
                util.askInput();
                int opt = Integer.parseInt(this.reader.readLine());
                this.players[n - 1] = this.available_players.get(opt - 1);
                this.available_players.remove(opt - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                util.outError("Not an option, press Enter to continue");
                continue;
            }
        }
    }
}
