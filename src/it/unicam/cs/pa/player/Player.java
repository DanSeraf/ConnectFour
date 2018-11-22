package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;

import java.io.IOException;

public interface Player {

    int getMove() throws IOException;

    String getUser();

    Disc getDisc();

}
