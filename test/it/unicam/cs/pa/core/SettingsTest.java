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
        String inputdata = "Pluto\n1\n#\n1";
        System.setIn(new ByteArrayInputStream(inputdata.getBytes()));
        Settings settings = new Settings(System.in, System.out);
        settings.addNewPlayer();
        File file = new File("settings.sav");
        assertTrue(file.exists());
    }

    @Test
    @DisplayName("New player correctly created with correct input")
    void addNewPlayer() throws IOException {
        String inputdata = "Pluto\n1\n@\n1";
        System.setIn(new ByteArrayInputStream(inputdata.getBytes()));
        Settings settings = new Settings(System.in, System.out);
        settings.addNewPlayer();
    }
}