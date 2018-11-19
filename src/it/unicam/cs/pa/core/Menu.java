package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Menu {

    Settings settings;
    ConsolePrinter cprinter;

    public Menu() {
        this.settings = new Settings();
        this.cprinter = new ConsolePrinter();
    }

    public void showMenu() {
        do {
            cprinter.clean();
            System.out.println("[1] Play");
            System.out.println("[2] Add Player");
            System.out.println("[3] View Players");
            System.out.println("[4] Exit");
            System.out.print("OPTION ");
            try {
                BufferedReader buff_reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(buff_reader.readLine());
                switch(option) {
                    case 1: cprinter.clean();
                        Match match = new Match(this.settings);
                        match.start();
                        continue;
                    case 2: cprinter.clean();
                        this.settings.addNewPlayer();
                        continue;
                    case 3: cprinter.clean();
                        this.settings.viewPlayers();
                        continue;
                    case 4: System.exit(0);
                    default: System.out.println("Invalid option");
                        System.out.println("Press enter to continue");
                        System.in.read();
                        continue;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Not a number");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(true);
    }
}
