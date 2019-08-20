package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.DiscColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPlayerTest {

    @Test
    @DisplayName("Random player creation")
    void createRandomPlayer() {
        RandomPlayer rp1 = new RandomPlayer('@', "Random", DiscColors.GREEN);
        assertNotNull(rp1);
        assertEquals("Random", rp1.getUser());
        assertEquals('@', rp1.getDisc().getSymbol());
        assertEquals(DiscColors.GREEN.toString(), rp1.getDisc().getColor());
    }

    @Test
    @DisplayName("Get move")
    void getMove() {
        RandomPlayer rp1 = new RandomPlayer('@', "Random", DiscColors.GREEN);
        assertNotNull(rp1);
        assertTrue(0 <= rp1.move() && rp1.move() <= 6);
        assertTrue(0 <= rp1.move() && rp1.move() <= 6);
        assertTrue(0 <= rp1.move() && rp1.move() <= 6);
        assertTrue(0 <= rp1.move() && rp1.move() <= 6);
    }
}
