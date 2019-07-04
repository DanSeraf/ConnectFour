package it.unicam.cs.pa.core;

import java.io.*;

public class Utils {

    BufferedReader in;
    PrintStream out;

    public Utils(InputStream in, PrintStream out) {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public Utils() {
        this(System.in, System.out);
    }

    /**
     * Wait for the user input
     *
     * @param message for the user
     */
    public void waitInput(String message) {
        try {
            out.println(message);
            in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * clean the screen and go up to the console
     */
    public void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void askInput() {
        out.print(">> ");
    }
}
