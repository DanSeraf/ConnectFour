package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Match {

    private BattleGround board;
    private ConsolePrinter cprinter;
    private Player Winner;
    private ArrayList<Player> available_players;
    private Player[] players;
    private MatchStatus status = MatchStatus.PLAYING;
    private BufferedReader reader;

    public Match(Settings settings) {
        this.board = new BattleGround();
        this.available_players = settings.getPlayers();
        this.cprinter = new ConsolePrinter();
        this.players = new Player[2];
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * match initialization, selectPlayers is used to let the player choose for a character stored in settings
     */
    public void start() {
        selectPlayers();
        int id = 0;
        //TODO Select board size
        do {
            cprinter.clean();
            cprinter.printBoard(board.getxSize(), board.getySize(), board);
            System.out.print(">" + players[id].getUser() + " move: ");
            try {
                int move = players[id].getMove();
                if (board.addDisc(players[id].getSymbol(), move) == false) {
                    continue;
                }
            } catch (NumberFormatException ex) {
                //TODO check invalid number insertion
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (this.board.isThereAWinner() == true) {
                    cprinter.clean();
                    cprinter.printBoard(board.getxSize(), board.getySize(), board);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    status = MatchStatus.END;
                } else { id = getOtherPlayer(id); }
            }
        } while(status == MatchStatus.PLAYING);
        cprinter.clean();
        System.out.println("The winner is: " + players[id].getUser());
        System.out.println("Press enter to return in menu");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
     * Let user select which players they want to use from the available_player ArrayList
     * delete player choose from available ArrayList
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
        cprinter.clean();
    }
}
