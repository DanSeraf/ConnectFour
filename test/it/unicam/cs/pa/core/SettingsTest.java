package it.unicam.cs.pa.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    @DisplayName("Object Settings correctly created")
    void createSettings() {
        Settings settings = new Settings(System.in, System.out);
        assertNotNull(settings);
    }

    @Test
    @DisplayName("Object Player serialized when a new player is created")
    void serializedPlayer() throws IOException {
        String inputdata = "Pluto\n1\n@\n2";
        System.setIn(new ByteArrayInputStream(inputdata.getBytes()));
        Settings settings = new Settings(System.in, System.out);
        settings.addNewPlayer();
        File file = new File("Players.sav");
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("New player correctly created with correct input")
    void addNewPlayer() throws IOException {
        String inputdata = "Pluto\n1\n@\n2";
        System.setIn(new ByteArrayInputStream(inputdata.getBytes()));
        Settings settings = new Settings(System.in, System.out);
        settings.addNewPlayer();
    }

    @Test
    @DisplayName("NumberFormatException correctly thrown when generated a new player with incorrect input")
    void NfeOnAddNewPlayer() {
        // Check number format exception on disc color selection
        String inputdata = "Pluto\ntt\n@\n2";
        System.setIn(new ByteArrayInputStream(inputdata.getBytes()));
        Settings settings1 = new Settings(System.in, System.out);
        assertThrows(NumberFormatException.class, () -> settings1.addNewPlayer());
        // Check number format exception on select type of player
        String inputdata1 = "Pluto\n2\n#\nbb";
        System.setIn(new ByteArrayInputStream(inputdata1.getBytes()));
        Settings settings2 = new Settings(System.in, System.out);
    }
}