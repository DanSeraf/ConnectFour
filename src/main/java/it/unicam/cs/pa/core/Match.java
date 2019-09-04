package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static it.unicam.cs.pa.core.MatchStatus.*;

/**
 *
 *
 */

public class Match {

    private RoundsManager state;
    private int nplayers;
    private Console console = Console.getInstance();
    private BattleGround battleground;
    private ArrayList<Player> available_players;
    private Player[] players;
    private MatchStatus status;
    private MatchSetup setup;
    private BufferedReader reader;
    private Utils util;

    public Match(int nplayers) {
        this.available_players = Settings.getInstance().getPlayers();
        this.players = new Player[nplayers];
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.util = new Utils();
        this.state = new RoundsManager(nplayers);
        this.setup = new MatchSetup(state, nplayers);
        this.nplayers = nplayers;
    }

    /**
     * Check if there are at least 2 available players
     *
     * @return true if available players are equal of more than 2
     */
    public boolean ready() {
        if (this.available_players.size() >= nplayers) {
            return true;
        } else {
            util.waitInput("You need at least" + nplayers + "players\nPlease add a new player");
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
        if (ready()) {
            battleground = setup.selectBoard();
            players = setup.selectPlayers();
            console.init(battleground);
        } else return;

        for (int n = 0; n < nplayers; n++) {
            if (!init(n)) return;
        }

        setStatus(PLAYING);
        console.printBoardDelay();
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
        if (battleground.isThereAWinner()) setStatus(END);
        if (battleground.isFull()) setStatus(FULLBOARD);

        if (status == PLAYING) {
            System.out.print(players[state.currentPlayer()].getUser() + "> ");
            int move = players[state.currentPlayer()].getMove();
            battleground.addDisc(players[state.currentPlayer()], move);
            return true;
        }
        else if (status == END) {
            printWinner();
            util.waitInput("Press Enter to continue");
            return false;
        } else if (status == FULLBOARD) {
            util.waitInput("There is no winner, the board is full\nPress Enter to continue");
            return false;
        }
        return false;
    }

    public void printWinner() {
        for (int pn = 0; pn < nplayers; pn++) {
            if (pn == state.currentPlayer()) players[pn].youWin();
            else players[pn].youLoose();
        }
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

}
