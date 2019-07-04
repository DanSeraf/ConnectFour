package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class Menu {

    PrintStream out;
    Utils util;
    Settings settings = Settings.getInstance();

    public Menu() {
        this.out = new PrintStream(System.out);
        this.util = new Utils();
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(reader.readLine());
                switch(option) {
                    case 1: Match match = new Match();
                        if (match.ready()) {
                            match.start();
                        }
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
}
