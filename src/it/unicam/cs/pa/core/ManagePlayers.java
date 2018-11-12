package it.unicam.cs.pa.core;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ManagePlayers {

    ConsolePrinter cprinter;

    public ManagePlayers() {
        this.cprinter = new ConsolePrinter();
    }

    public void addNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username of new player? ");
        String username = scanner.nextLine();
        System.out.println("Which symbol do you want to use? ");
        char symbol = scanner.next().charAt(0);
        createPlayer(symbol, username);
    }

    private void createPlayer(char symbol, String username) {
        cprinter.clear();
        System.out.println("Select type of new player");
        System.out.println("[1] Human Player");
        System.out.println("[2] AI (not implemented)");
        try {
            BufferedReader buff_reader = new BufferedReader(new InputStreamReader(System.in));
            int option = Integer.parseInt(buff_reader.readLine());
            switch(option) {
                case 1:
                    createHuman(symbol, username);
                    break;
                /**
                 * TODO Implement other player classes
                 * case 2: Player p = new AI();
                 */
                default: System.out.println("Invalid option");
                    TimeUnit.SECONDS.sleep(2);
            }
        } catch(NumberFormatException ex) {
            System.out.println("Not a number");
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createHuman(char symbol, String username) {
        try {
            Player new_player_obj = new HumanPlayer(symbol, username);
            FileOutputStream f = new FileOutputStream(new File("Players.save"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(new_player_obj);
            o.close();
            f.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewPlayers() {
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.save"));
            while(true) {
                Player p = (Player) in.readObject();
                System.out.println(p.getUser());
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException ex) {
            System.out.println("Class not found");
        }
    }

    private void savePlayer() {

    }
}
