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
        File f = new File("Manage.sav");
        if(f.exists() && !f.isDirectory()) {
            deserialize_manager();
        } else {
            this.manage = new ManagePlayers();
        }
    }

    private void deserialize_manager() {
        FileInputStream input_file = null;
        ObjectInputStream obj_file = null;
        try {
            input_file = new FileInputStream("Manage.sav");
            obj_file = new ObjectInputStream(input_file);
            this.manage = (ManagePlayers) obj_file.readObject();
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (obj_file != null) {
                try {
                    obj_file.close();
                    input_file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void serialize_manager() {
        FileOutputStream out_file = null;
        ObjectOutputStream obj_file = null;
        try {
            out_file = new FileOutputStream("Manage.sav");
            obj_file = new ObjectOutputStream(out_file);
            obj_file.writeObject(this.manage);
            obj_file.close();
            out_file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if ((obj_file) != null) {
                try {
                    obj_file.close();
                    out_file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
