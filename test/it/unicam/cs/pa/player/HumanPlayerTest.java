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
        HumanPlayer p1 = new HumanPlayer('#', "Stallino", DiscColors.BLUE, System.in);
        assertEquals("Stallino", p1.getUser());
        assertEquals('#', p1.getDisc().getSymbol());
        assertEquals(DiscColors.BLUE.toString(), p1.getDisc().getColor());
        assertNotNull(p1);
    }

    @Test
    @DisplayName("Get move")
    void getMove() throws IOException {
        String data = "3";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        HumanPlayer testplayer = new HumanPlayer('#', "Pluto", DiscColors.GREEN, System.in);
        assertEquals(3, testplayer.getMove());
    }
}