package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.BattleGround;
import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer implements Player, Serializable {

    private static final long serialVersionUID = -4654776393486895934L;
    private String username;
    private Disc disc;
    private int xsize;
    private int ysize;
    private int pid;
    private BattleGround battleground;

    public RandomPlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
    }


    @Override
    public String getUser() {
        return this.username;
    }

    @Override
    public Disc getDisc() {
        return this.disc;
    }

    @Override
    public int getPid() {
        return this.pid;
    }

    @Override
    public int getMove() {
        if (battleground.canMove(this)) {
            return ThreadLocalRandom.current().nextInt(0,ysize);
        }
        return -1;
    }

    @Override
    public void init(int pid, BattleGround bg) {
        this.pid = pid;
        this.battleground = bg;
        this.xsize = bg.getX();
        this.ysize = bg.getY();
    }

    @Override
    public void youWin() {
        System.out.println("Congratulation " + this.username + " you have won!");
    }

    @Override
    public void youLoose() {
        System.out.println("Ohhhh " + this.username + " you have lost dude!");
    }

    @Override
    public void winForError(Throwable e) {
        System.out.println("Wow, you have won, your opponent has made an error: " + e.getMessage());
    }

    @Override
    public void looseForError(Throwable e) {
        System.out.println("Damn you have lost: " + e.getMessage());
    }
}
