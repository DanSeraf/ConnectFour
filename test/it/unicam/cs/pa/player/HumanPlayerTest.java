package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.DiscColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    @DisplayName("Human Player creation")
    public void createPlayer() {
        char symbol = '#';
        DiscColors disc_color = DiscColors.BLUE;
        String playername = "Pluto";
        HumanPlayer p1 = new HumanPlayer(symbol, playername, disc_color);
        assertNotNull(p1);
        assertEquals(playername, p1.getUser());
        assertEquals(symbol, p1.getDisc().getSymbol());
        assertEquals(disc_color.toString(), p1.getDisc().getColor());
    }
}