package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.Disc;

import java.io.IOException;
import java.io.InputStream;

public interface Player {

    int getMove() throws IOException;

    String getUser();

    Disc getDisc();

}
