package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.BattleGround;
import it.unicam.cs.pa.core.Disc;

public interface Player {

    /**
     * can implement your own logic here, to get the move
     *
     * @return a number between 0 and 7
     */
    int move();

    /**
     * @return username of the player
     */
    String getUser();

    /**
     * @return disc of the player
     */
    Disc getDisc();

    void init(int pid, BattleGround bg);

}
