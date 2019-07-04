package it.unicam.cs.pa.player;

import it.unicam.cs.pa.core.DiscColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @Test
    @DisplayName("Human player creation")
    void createHumanPlayer() {
        HumanPlayer p1 = new HumanPlayer('#', "Stallino", DiscColors.BLUE);
        assertNotNull(p1);
        assertEquals("Stallino", p1.getUser());
        assertEquals('#', p1.getDisc().getSymbol());
        assertEquals(DiscColors.BLUE.toString(), p1.getDisc().getColor());
    }

    @Test
    @DisplayName("Get move")
    void getMove() {
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        HumanPlayer testplayer = new HumanPlayer('#', "Pluto", DiscColors.GREEN);
        assertEquals(3, testplayer.getMove());
    }
}