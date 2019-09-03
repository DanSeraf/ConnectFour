package it.unicam.cs.pa.core;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Menu {

    private Utils util;
    private Settings settings = Settings.getInstance();
    private BufferedReader reader;

    public Menu(InputStream in) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.util = new Utils();
    }

    public Menu() {
        this(System.in);
    }

    public void show() {
        do {
            util.clean();
            System.out.println("[1] PLAY");
            System.out.println("[2] ADD NEW PLAYER");
            System.out.println("[3] DELETE PLAYER");
            System.out.println("[4] VIEW PLAYERS");
            System.out.println("[5] EXIT");
            util.askInput();
            try {
                int option = Integer.parseUnsignedInt(reader.readLine());
                switch(option) {
                    case 1: int nplayers = getNumberOfPlayers();
                        Match match = new Match(nplayers);
                        match.start();
                        continue;
                    case 2: util.clean();
                        settings.addNewPlayer();
                        continue;
                    case 3: util.clean();
                        settings.deletePlayer();
                        continue;
                    case 4: util.clean();
                        settings.viewPlayers();
                        continue;
                    case 5: util.clean();
                        System.out.println("GOODBYE!");
                        TimeUnit.SECONDS.sleep(1);
                        util.clean();
                        System.exit(0);
                    default: System.out.println("Invalid option");
                        util.waitInput("Press enter to continue");
                }
            } catch (NumberFormatException ex) {
                util.waitInput("Not an option, press Enter to continue");
            } catch (InterruptedException | IOException ie) {
                ie.printStackTrace();
            }
        } while(true);
    }

    public int getNumberOfPlayers() {
        System.out.println("Number of players:");
        util.askInput();
        while(true) {
            try {
                int np = Integer.parseUnsignedInt(reader.readLine());
                return np;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException n) {
                util.waitInput("Input error!");
            }
        }
    }
}
