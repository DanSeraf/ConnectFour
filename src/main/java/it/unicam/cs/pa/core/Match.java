package it.unicam.cs.pa.core;

import it.unicam.cs.pa.exception.FullColumnException;
import it.unicam.cs.pa.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static it.unicam.cs.pa.core.MatchStatus.*;


public class Match {

    private static final int PLAYER1 = 0;
    private static final int PLAYER2 = 1;
    private Automa state;
    private Console console = Console.getInstance();
    private BattleGround battleground;
    private ArrayList<Player> available_players;
    private Player[] players;
    private MatchStatus status;
    private BufferedReader reader;
    private Utils util;
    private final String RESET = "\u001B[0m";

    public Match() {
        this.available_players = Settings.getInstance().getPlayers();
        this.players = new Player[2];
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.util = new Utils();
        this.state = new Automa();
        this.battleground = new BattleGround(state);
        console.init(battleground);
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
            util.waitInput("You need at least 2 players\nPlease add a new player");
            return false;
        }
    }

    private void winForError(int pid, Throwable e) {
        this.players[pid].winForError(e);
        this.players[getOtherPlayer(pid)].looseForError(e);
    }

    private boolean init(int pid) {
        try {
            this.players[pid].init(pid, battleground);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            winForError(getOtherPlayer(pid), e);
            return false;
        }

    }

    public void start() {
        selectPlayers();
        if (!init(PLAYER1)) return;
        if (!init(PLAYER2)) return;
        setStatus(PLAYING);
        fight();
    }

    private void fight() {
        players[PLAYER1].startFighting();
        players[PLAYER2].startFighting();
        while (doAction());
    }

    /**
     * Match initialization:
     * selectPlayers is used to let the player choose for a character stored in settings
     * inside the loop, a move is taken from the player and inserted into the board
     * based on the response of the board, the player will change with getOtherPlayer
     * The game finish when there is winner, it is checked by the Battleground
     */
    private boolean doAction() {
        util.clean();
        console.printBoard();
        if (battleground.isThereAWinner()) status = END;

        if (status == PLAYING) {
            System.out.print(this.players[state.currentPlayer()].getUser() + "> ");
            return true;
        }
        else if (status == END) {
            players[state.currentPlayer()].youWin();
            players[getOtherPlayer(state.currentPlayer())].youLoose();
            return false;
        } else if (status == FULLBOARD) {
            System.out.println("The board is Full");
            players[PLAYER1].youWin();
            players[PLAYER2].youWin();
            return false;
        }
        return false;
    }

    public void setStatus(MatchStatus stat) {
        this.status = stat;
    }

    /**
     * change the player
     */
    private static int getOtherPlayer(int id) {
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
                util.clean();
                int[] index = new int[]{1};
                System.out.println("PLAYER " + n);
                this.available_players.forEach(player ->
                        System.out.println("[" + index[0]++ + "] [" + player.getDisc().getColor()
                                + player.getDisc().getSymbol() + RESET + "] -> " + player.getUser())
                );
                util.askInput();
                int opt = Integer.parseInt(this.reader.readLine());
                this.players[n - 1] = this.available_players.get(opt - 1);
                this.available_players.remove(opt - 1);
                break;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                util.waitInput("Not an option, press Enter to continue");
            }
        }
    }
}
