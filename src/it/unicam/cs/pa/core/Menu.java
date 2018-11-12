package it.unicam.cs.pa.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {

    Scanner scanner;
    ManagePlayers manage;
    Match match;
    ConsolePrinter cprinter;

    public Menu() {
        scanner = new Scanner(System.in);
        manage = new ManagePlayers();
        match = new Match();
        cprinter = new ConsolePrinter();
        showMenu();
    }

    public void showMenu() {
        do {
            cprinter.clear();
            System.out.println("[1] Play");
            System.out.println("[2] Add Players");
            System.out.println("[3] View Players");
            System.out.println("[4] Exit");
            System.out.print("OPTION ");
            try {
                BufferedReader buff_reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(buff_reader.readLine());
                switch(option) {
                    case 1: this.match.start();
                    cprinter.clear();
                    continue;
                    case 2: this.manage.addNewPlayer();
                        cprinter.clear();
                        continue;
                    case 3: this.manage.viewPlayers();
                        cprinter.clear();
                        continue;
                    case 4: System.exit(0);
                    default: System.out.println("Invalid option");
                        TimeUnit.SECONDS.sleep(2);
                        cprinter.clear();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Not number");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

}
