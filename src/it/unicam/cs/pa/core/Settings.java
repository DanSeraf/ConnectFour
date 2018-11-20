package it.unicam.cs.pa.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiPredicate;

/**
 *
 * Settings
 * it stores the players characters and the boards size
 * TODO (feature) add new boards
 * TODO remove character
 *
 */

public class Settings {

    // list of players that can be used
    private ArrayList<Player> players;
    // Check if new player has the same symbol of another player
    private BiPredicate<Player, Character> sym_check = (p, c) -> p.getSymbol()==c;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Settings() {
        restorePlayers();
    }

    /**
     * add new player to the game
     * ask for name and symbol
     */
    public void addNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username of new player? ");
        String username = scanner.nextLine();
        System.out.println("Which symbol do you want to use? ");
        char symbol = scanner.next().charAt(0);
        if (checkSym(symbol) == false && (!username.isEmpty() || symbol != ' ')) {
            createPlayer(symbol, username);
        }
    }

    /**
     * check if player has the same symbol of a saved player
     * @param symbol symbol of new player
     * @return true if another player has the same symbol
     */
    private boolean checkSym(char symbol) {
        for (Player player : this.players) {
            if (this.sym_check.test(player, symbol)) {
                System.out.println("Another player has the same symbol");
                System.out.println("Press Enter to exit");
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
        System.out.println("[2] Random Player");
        try {
            int option = Integer.parseInt(this.reader.readLine());
            switch(option) {
                case 1:
                    this.players.add(new HumanPlayer(symbol, username));
                    break;
                case 2:
                    this.players.add(new RandomPlayer(symbol, username));
                    break;
                default: System.out.println("Invalid option");
                    System.out.println("Press enter to continue");
                    System.in.read();
            }
        } catch(NumberFormatException ex) {
            System.out.println("Not a number");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            serializePlayers();
        }
    }

    /**
     * check if Players.sav exists
     */
    private void restorePlayers() {
        File f = new File("Players.sav");
        if(f.exists() && !f.isDirectory()) {
            deserializePlayers();
        } else {
            this.players = new ArrayList<>();
        }
    }

    /**
     * view players
     */
    public void viewPlayers() throws IOException {
        System.out.println("Players:");
        this.players.forEach(player ->
            System.out.println("(" + player.getSymbol() + ") - " + player.getUser())
        );
        System.out.println("Press Enter to continue");
        System.in.read();
    }

    /**
     * serialize players ArrayList
     * it will be saved in the current working directory
     */
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

    /**
     * load players serialized in Players.sav
     */
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
