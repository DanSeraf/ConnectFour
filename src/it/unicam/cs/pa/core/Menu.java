package it.unicam.cs.pa.core;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {

    Scanner scanner;
    ManagePlayers manage;
    Match match;
    ConsolePrinter cprinter;

    public Menu() {
        scanner = new Scanner(System.in);
        match = new Match();
        cprinter = new ConsolePrinter();
        checkForManager();
        showMenu();
    }

    public void showMenu() {
        do {
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
                    case 1: this.match.start();
                    cprinter.clear();
                    continue;
                    case 2: cprinter.clear();
                        this.manage.addNewPlayer();
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
            } finally {
                serialize_manager();
            }
        } while (true);
    }

    private void checkForManager() {
        File f = new File("settings.sav");
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
