package it.unicam.cs.pa.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;



class DiscColorsTest {
    @Test
    @DisplayName("Get all colors available")
    void getColors() {
        List<DiscColors> colors = Arrays.asList(DiscColors.values());
        assertEquals("\u001B[41m", colors.get(0).toString());
        assertEquals("\u001B[42m", colors.get(1).toString());
        assertEquals("\u001B[43m", colors.get(2).toString());
        assertEquals("\u001B[44m", colors.get(3).toString());
        assertEquals("\u001B[45m", colors.get(4).toString());
        assertEquals("\u001B[46m", colors.get(5).toString());
        assertEquals("\u001B[47m", colors.get(6).toString());
        assertEquals("\u001B[0m", colors.get(7).toString());
    }

}