package it.unicam.cs.pa.core;

import java.io.*;
import java.util.Scanner;

public class Menu {

    Scanner scanner;
    ManagePlayers manage;
    Match match;
    ConsolePrinter cprinter;

    public Menu() {
        scanner = new Scanner(System.in);
        cprinter = new ConsolePrinter();
        showMenu();
    }

    public void showMenu() {
        do {
            checkForManager();
            cprinter.clear();
            System.out.println("[1] Play");
            System.out.println("[2] Add Player");
            System.out.println("[3] View Players");
            System.out.println("[4] Exit");
            System.out.print("OPTION ");
            try {
                BufferedReader buff_reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(buff_reader.readLine());
                switch(option) {
                    case 1: cprinter.clear();
                        this.match = new Match(this.manage);
                        this.match.start();
                        continue;
                    case 2: cprinter.clear();
                        this.manage.addNewPlayer();
                        serialize_manager();
                        continue;
                    case 3: cprinter.clear();
                        this.manage.viewPlayers();
                        continue;
                    case 4: System.exit(0);
                    default: System.out.println("Invalid option");
                        System.out.println("Press enter to continue");
                        System.in.read();
                        continue;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Not number");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private void checkForManager() {
        File f = new File("Settings.sav");
        if(f.exists() && !f.isDirectory()) {
            deserialize_manager();
        } else {
            this.manage = new ManagePlayers();
        }
    }

    private void deserialize_manager() {
        try {
            FileInputStream in_file = new FileInputStream("Settings.sav");
            ObjectInputStream in = new ObjectInputStream(in_file);
            this.manage = (ManagePlayers) in.readObject();
            in.close();
            in_file.close();
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serialize_manager() {
        try {
            FileOutputStream out_file = new FileOutputStream("Settings.sav");
            ObjectOutputStream out = new ObjectOutputStream(out_file);
            out.writeObject(this.manage);
            out.close();
            out_file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
