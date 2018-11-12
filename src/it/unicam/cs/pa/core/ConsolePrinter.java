package it.unicam.cs.pa.core;

public class ConsolePrinter {
    public ConsolePrinter() {};

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
