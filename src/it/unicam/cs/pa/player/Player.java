package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;

import java.io.IOException;
import java.io.InputStream;

public interface Player {

    /**
     * can implement your own logic here, to get the move
     *
     * @return a number between 0 and 7
     */
    int getMove();

    /**
     * @return username of the player
     */
    String getUser();

    /**
     * @return disc of the player
     */
    Disc getDisc();

}
