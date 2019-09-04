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
    
    /**
     * @return id of the player
     */
    int getPid();
    
    /**
     * initialization of the Player
     */
    void init(int pid, BattleGround bg);
    
    /**
     * get move from the player
     */
    int getMove();
    
    /**
     * @throw exeption if other player win
     */
    void winForError(Throwable e);

    /**
     * @throw exeption if other player win
     */
    void looseForError(Throwable e);

    /**
     * message showed when the player win
     */
    void youWin();

    /**
     * message showed when the player lost
     */
    void youLoose();
}
