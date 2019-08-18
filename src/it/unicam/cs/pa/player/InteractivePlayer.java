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
    private int pid;
    private int xsize;
    private int ysize;

    public InteractivePlayer(char symbol, String username, DiscColors color) {
        this.username = username;
        this.disc = new Disc(symbol, color);
    }

    @Override
    public int move() {
        return 0;
    }

    @Override
    public void init(int pid, BattleGround bg) {
        this.pid = pid;
        this.bg = bg;
        this.xsize = bg.getX();
        this.ysize = bg.getY();
    }

    @Override
    public String getUser() {
        return this.username;
    }

    @Override
    public Disc getDisc() {
        return this.disc;
    }
}
