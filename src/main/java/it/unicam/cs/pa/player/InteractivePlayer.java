package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.BattleGround;
import it.unicam.cs.pa.core.Disc;
import it.unicam.cs.pa.core.DiscColors;

import java.io.*;

public class InteractivePlayer implements Player, Serializable {

    private static final long serialVersionUID = 6446382602942139248L;
    private String username;
    private Disc disc;
    private BattleGround bg;
    private transient BufferedReader in;
    private transient PrintStream out;
    private int pid;
    private int xsize;
    private int ysize;


    public InteractivePlayer(char symbol, String username, DiscColors color, InputStream in, PrintStream out) {
        this.username = username;
        this.disc = new Disc(symbol, color);
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public InteractivePlayer(char symbol, String username, DiscColors color) {
        this( symbol, username, color, System.in, System.out );
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
    public void startFighting() {
        while(true) {
            if (bg.canMove(this)) {
                try {
                    String opt = in.readLine();
                    bg.addDisc(this, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void init(int pid, BattleGround bg) {
        this.pid = pid;
        this.bg = bg;
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
