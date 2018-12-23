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
     * Print message and ask to press Enter to continue
     *
     * @param message
     */
    public void outError(String message) {
        try {
            out.println(message);
            in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
