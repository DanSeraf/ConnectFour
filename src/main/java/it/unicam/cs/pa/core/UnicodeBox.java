package it.unicam.cs.pa.core;

/**
 * Utility enum for the Console class 
 * It contains all the unicode string to draw boxes
 */

public enum UnicodeBox {
    EMPTYCELL,
    PIPE,
    SIDE,
    URCORNER,
    ULCORNER,
    DRCORNER,
    DLCORNER,
    MSEPARATOR,
    LSEPARATOR,
    RSEPARATOR,
    USEPARATOR,
    DSEPARATOR;

    @Override
    public String toString() {
        switch ( this ) {
            case EMPTYCELL: return "│   ";
            case PIPE: return "│";
            case SIDE: return "───";
            case URCORNER: return "┐";
            case ULCORNER: return "┌───";
            case DRCORNER: return "┘";
            case DLCORNER: return "└───";
            case MSEPARATOR: return "┼───";
            case LSEPARATOR: return "├───";
            case RSEPARATOR: return "┤";
            case USEPARATOR: return "┬───";
            case DSEPARATOR: return "┴───";
        }
        return "";
    }
}
