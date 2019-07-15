package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @BeforeEach
    void setUp() {
        File f = new File("settings.sav");
        if (!f.isDirectory() && f.isFile()) {
            f.delete();
        }
    }

    @Test
    @DisplayName("Create new player, check serialized file")
    void addNewPlayer() throws IOException {
        String input = "Pluto\n1\nP\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Settings settings = Settings.getInstance();
        settings.addNewPlayer();
        ArrayList<Player> players = settings.getPlayers();
        File file = new File("settings.sav");

        assertNotNull(settings);
        assertTrue(file.exists());
        assertFalse(file.isDirectory());
        Player p = players.get(0);
        assertEquals("Pluto", p.getUser());
        assertEquals("\u001B[41m", p.getDisc().getColor());
        assertEquals('P', p.getDisc().getSymbol());
    }
}