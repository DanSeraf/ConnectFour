package it.unicam.cs.pa.core;

import java.io.IOException;

public interface Player {

    int getMove() throws IOException;

    char getSymbol();

    String getUser();

}
