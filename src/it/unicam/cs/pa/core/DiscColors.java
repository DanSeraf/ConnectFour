package it.unicam.cs.pa.core;

import java.io.Serializable;

public enum DiscColors implements Serializable {
    RED,
    GREEN,
    YELLOW,
    BLUE,
    PURPLE,
    CYAN,
    WHITE;

    @Override
    public String toString() {
        switch ( this ) {
            case RED: return "\u001B[41m";
            case GREEN: return "\u001B[42m";
            case YELLOW: return "\u001B[43m";
            case BLUE: return "\u001B[44m";
            case PURPLE: return "\u001B[45m";
            case CYAN: return "\u001B[46m";
            case WHITE: return "\u001B[47m";
        }
        return "";
    }
}
