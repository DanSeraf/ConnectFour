package it.unicam.cs.pa.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiPredicate;

/**
 *
 * Settings
 * let user add new player
 * this class is serializable because a Settings.sav will be created to save this object
 *
 */

public class Settings {

    private ArrayList<Player> players;
    // Check if new player has the same symbol of another player
    private BiPredicate<Player, Character> sym_check = (p, c) -> p.getSymbol()==c;

    public Settings() {
        restorePlayers();
    }

    public void addNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username of new player? ");
        String username = scanner.nextLine();
        System.out.println("Which symbol do you want to use? ");
        char symbol = scanner.next().charAt(0);
        if (checkSym(symbol) == false) {
            createPlayer(symbol, username);
        }
        serializePlayers();
    }

    private boolean checkSym(char symbol) {
        for (Player player : this.players) {
            if (this.sym_check.test(player, symbol)) {
                System.out.println("Another player has the same symbol, press Enter to exit");
                try {
                    System.in.read();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * select which kind of player they want to create
     * @param symbol symbol that identify the player
     * @param username username of the player
     */

    private void createPlayer(char symbol, String username) {
        System.out.println("Select type of new player");
        System.out.println("[1] Human Player");
        try {
            BufferedReader buff_reader = new BufferedReader(new InputStreamReader(System.in));
            int option = Integer.parseInt(buff_reader.readLine());
            switch(option) {
                case 1:
                    Player new_player_obj = new HumanPlayer(symbol, username);
                    this.players.add(new_player_obj);
                    break;
                /**
                 * TODO Implement other player classes
                 * case 2: Player p = new AI();
                 */
                default: System.out.println("Invalid option");
                    System.out.println("Press enter to continue");
                    System.in.read();
            }
        } catch(NumberFormatException ex) {
            System.out.println("Not a number");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void restorePlayers() {
        File f = new File("Players.sav");
        if(f.exists() && !f.isDirectory()) {
            deserializePlayers();
        } else {
            this.players = new ArrayList<>();
        }
    }

    /**
     * view players saved in Settings.sav
     */

    public void viewPlayers() throws IOException {
        System.out.println("Players:");
        this.players.forEach(player ->
            System.out.println("(" + player.getSymbol() + ") - " + player.getUser())
        );
        System.out.println("Press Enter to continue");
        System.in.read();
    }

    private void serializePlayers() {
        FileOutputStream out_file = null;
        ObjectOutputStream obj_file = null;
        try {
            out_file = new FileOutputStream("Players.sav");
            obj_file = new ObjectOutputStream(out_file);
            obj_file.writeObject(this.players);
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

    private void deserializePlayers() {
        FileInputStream input_file = null;
        ObjectInputStream obj_file = null;
        try {
            input_file = new FileInputStream("Players.sav");
            obj_file = new ObjectInputStream(input_file);
            this.players = (ArrayList<Player>) obj_file.readObject();
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

    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>)this.players.clone();
    }
}
