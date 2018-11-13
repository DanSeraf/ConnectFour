package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Match {

    private BattleGround board;
    private ManagePlayers manage;
    private ConsolePrinter cprinter;
    private Player Winner;
    private ArrayList<Player> available_players;
    private Player[] players;
    private Player current_player;
    private MatchStatus status = MatchStatus.PLAYING;
    private BufferedReader reader;

    public Match(ManagePlayers manage) {
        this.board = new BattleGround();
        this.available_players = manage.getPlayers();
        this.cprinter = new ConsolePrinter();
        this.players = new Player[2];
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        selectPlayers();
        int id = 0;
        //TODO Select board size
        do {
            cprinter.clear();
            cprinter.printBoard(board.getxSize(), board.getySize(), board);
            System.out.print("> " + this.players[id].getUser() + " move: ");
            try {
                int move = Integer.parseInt(this.reader.readLine());
                this.board.addDisc(this.players[id].getSymbol(), move);
                id = getOtherPlayer(id);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException ex) {
                //TODO check invalid number insertion
            }
        } while(true);
    }

    private void selectPlayers() {
        selectPlayer(1);
        selectPlayer(2);
    }

    public static int getOtherPlayer(int id) {
        return (id+1)%2;
    }

    /**
     * Let user select which players they want to use from the available_player ArrayList
     * delete player choosen from available ArrayList
     * @param x number of the player
     */
    private void selectPlayer(int x) {
        int index = 1;
        System.out.println("Player " + x + " select available player:");
        for (Player player :
                this.available_players) {
            System.out.println("[" + index++ + "] " + player.getUser() + " - (" + player.getSymbol() + ")");
        }
        try {
            System.out.print("OPTION ");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opt = Integer.parseInt(this.reader.readLine());
            this.players[x-1] = this.available_players.get(opt - 1);
            this.available_players.remove(opt - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cprinter.clear();
    }
}
