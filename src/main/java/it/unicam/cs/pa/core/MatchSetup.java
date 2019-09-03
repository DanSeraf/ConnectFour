package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MatchSetup {
    private Player[] players;
    private int nplayers;
    private ArrayList<Player> availablePlayers = Settings.getInstance().getPlayers();
    private RoundsManager state;
    private Utils util = new Utils();
    private BufferedReader reader;

    public MatchSetup(RoundsManager state, int nplayers, InputStream in) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.state = state;
        this.nplayers = nplayers;
        this.players = new Player[nplayers];
    }

    public MatchSetup(RoundsManager state, int nplayers) {
        this(state, nplayers, System.in);
    }

    public Player[] selectPlayers() {
        for (int n = 1; n <= nplayers; n++) {
            try {
                selectPlayer(n);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    /**
     * Let user select which character they want to use from the available_players ArrayList
     * delete the chosen player from available_players
     * @param n number of the player
     */
    private void selectPlayer(int n) throws IOException {
        util.clean();
        while(true) {
            try {
                int[] index = new int[]{1};
                System.out.println("PLAYER " + n);
                availablePlayers.forEach(player ->
                        System.out.println("[" + index[0]++ + "] " +
                                "[" + player.getDisc().toString() + "] -> " + player.getUser())
                );
                util.askInput();
                int opt = Integer.parseInt(reader.readLine());
                players[n - 1] = availablePlayers.get(opt - 1);
                availablePlayers.remove(opt - 1);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                util.waitInput("Not an option, press Enter to continue");
            }
        }
    }

    public BattleGround selectBoard() {
        System.out.println("-BOARD SIZE-\n[1] DEFAULT\n[2] CUSTOM");
        while(true) {
            try {
                util.askInput();
                int opt = Integer.parseUnsignedInt(reader.readLine());
                switch (opt) {
                    case 1:
                        return new BattleGround(state);
                    case 2:
                        return selectCustomBoard();
                    default:
                        util.waitInput("Invalid selection");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BattleGround selectCustomBoard() {
        util.askInput();
        while(true) {
            try {
                System.out.println("Select the X size");
                util.askInput();
                int x = Integer.parseUnsignedInt(reader.readLine());
                System.out.println("Select the Y size");
                util.askInput();
                int y = Integer.parseUnsignedInt(reader.readLine());
                return new BattleGround(x, y, state);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException n) {
                util.waitInput("Not a valid input");
            }
        }
    }
}
