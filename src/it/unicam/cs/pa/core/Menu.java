package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;

public class Menu {

    Settings settings;
    ConsolePrinter cprinter;
    PrintStream out;
    Utils util;

    public Menu() {
        this.settings = new Settings(System.in, System.out);
        this.cprinter = new ConsolePrinter();
        this.out = new PrintStream(System.out);
        this.util = new Utils();
    }

    public void show() {
        do {
            cprinter.clean();
            System.out.println("[1] Play");
            System.out.println("[2] Add Player");
            System.out.println("[3] Remove Player");
            System.out.println("[4] View Players");
            System.out.println("[5] Exit");
            System.out.print("OPTION ");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(reader.readLine());
                switch(option) {
                    case 1: cprinter.clean();
                        Match match = new Match(this.settings);
                        if (match.ready() == true) {
                            match.start();
                        }
                        continue;
                    case 2: cprinter.clean();
                        settings.addNewPlayer();
                        continue;
                    case 3: cprinter.clean();
                        this.settings.deletePlayer();
                        continue;
                    case 4: cprinter.clean();
                        this.settings.viewPlayers();
                        continue;
                    case 5: cprinter.clean();
                        System.exit(0);
                    default: System.out.println("Invalid option");
                        util.outError("Press enter to continue");
                        continue;
                }
            } catch (NumberFormatException ex) {
                util.outError("Probably you didn't insert a number");
                continue;
            } catch (StringIndexOutOfBoundsException s) {
                util.outError("Invalid symbol, press Enter");
                continue;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } while(true);
    }
}
