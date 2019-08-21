package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.BattleGround;
import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.io.*;

public class InteractivePlayer implements Player, Serializable {

    private static final long serialVersionUID = 6446382602942139248L;
    private String username;
    private Disc disc;
    private transient BufferedReader in;
    private transient PrintStream out;
    private BattleGround battleground;
    private int pid;
    private int xsize;
    private int ysize;


    public InteractivePlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
    }

    private boolean isValid(String msg) {
        try {
            int opt = Integer.parseUnsignedInt(msg);
            return (opt<=ysize);
        } catch (NumberFormatException e) {
            return false;
        }
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
            while(true) {
                try {
                    String opt = in.readLine();
                    if (isValid(opt)) {
                        return Integer.parseUnsignedInt(opt);
                    } else { continue; }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else { return -1; }
    }

    @Override
    public void init(int pid, BattleGround bg) {
        this.pid = pid;
        this.battleground = bg;
        this.in = new BufferedReader(new InputStreamReader(System.in));
        this.out = System.out;
        this.xsize = bg.getX();
        this.ysize = bg.getY();
    }

    @Override
    public void youWin() {
        out.println("Congratulation " + this.username + " you have won!");
    }

    @Override
    public void youLoose() {
        out.println("Ohhhh " + this.username + " you have lost dude!");
    }

    @Override
    public void winForError(Throwable e) {
        out.println("Wow, you have won, your opponent has made an error: " + e.getMessage());
    }

    @Override
    public void looseForError(Throwable e) {
        out.println("Damn you have lost: " + e.getMessage());
    }
}
