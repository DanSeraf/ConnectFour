package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Match {

    private Player p1;
    private Player p2;
    private BattleGround board;
    private ManagePlayers manage;
    private ConsolePrinter cprinter;
    private Player Winner;
    private ArrayList<Player> players;
    private MatchStatus status = MatchStatus.PLAYING;

    public Match(ManagePlayers manage) {
        this.board = new BattleGround();
        this.players = manage.getPlayers();
    }

    public void start() {
        selectPlayers();
    }

    private void selectPlayers() {
        selectPlayer(1);
        selectPlayer(2);
    }

    public static Player getOtherPlayer(int id, Player[] players) {
        return players[(id+1)%2];
    }

    private void selectPlayer(int x) {
        int index = 1;
        System.out.println("Player " + x + " select available player:");
        for (Player player :
                this.players) {
            System.out.println("[" + index++ + "] " + player.getUser() + " - (" + player.getSymbol() + ")");
        }
        try {
            System.out.print("OPTION ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int opt = Integer.parseInt(reader.readLine());
            this.p1 = this.players.get(opt - 1);
            this.players.remove(opt - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
