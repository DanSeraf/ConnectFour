package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.DiscColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPlayerTest {

    @Test
    @DisplayName("Random player creation")
    public void createRandomPlayer() {
        char symbol = '@';
        DiscColors disc_color = DiscColors.CYAN;
        String playername = "Randino";
        RandomPlayer rp1 = new RandomPlayer(symbol, playername, disc_color);
        assertNotNull(rp1);
        assertEquals(playername, rp1.getUser());
        assertEquals(symbol, rp1.getDisc().getSymbol());
        assertEquals(disc_color.toString(), rp1.getDisc().getColor());
    }

    @Test
    @DisplayName("Get move")
    public void getMove() {
        RandomPlayer rp1 = new RandomPlayer('@', "Random", DiscColors.GREEN);
        assertNotNull(rp1);
        assertTrue(0 <= rp1.getMove() && rp1.getMove() <= 6);
    }
}
