package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.BattleGround;
import it.unicam.cs.pa.core.Disc;

public interface Player {

    /**
     * @return username of the player
     */
    String getUser();

    /**
     * @return disc of the player
     */
    Disc getDisc();

    int getPid();

    void init(int pid, BattleGround bg);

    void startFighting();

    void winForError(Throwable e);

    void looseForError(Throwable e);

    void youWin();

    void youLoose();
}
