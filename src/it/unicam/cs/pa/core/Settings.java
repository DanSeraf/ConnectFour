package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.HumanPlayer;
import it.unicam.cs.pa.player.RandomPlayer;
import it.unicam.cs.pa.player.Player;
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
    private BiPredicate<Player, Character> sym_check = (p, c) -> p.getDisc().getSymbol()==c;
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
        System.out.println("Choose one username");
        String username = scanner.nextLine();
        DiscColors color = getDiscColor(scanner);
        System.out.println("Choose one character");
        char symbol = scanner.next().charAt(0);
        if (checkSym(symbol) == false && (!username.isEmpty() || symbol != ' ')) {
            createPlayer(symbol, username, color);
        }
    }

    private DiscColors getDiscColor(Scanner scanner) {
        while (true) {
            System.out.println("Select the color you want to use.");
            System.out.print("[1] RED \n[2] GREEN \n[3] YELLOW \n[4] BLUE \n[5] PURPLE \n[6] CYAN \n[7] WHITE\n");
            int opt = scanner.nextInt();
            try {
                switch (opt) {
                    case 1:
                        return DiscColors.RED;
                    case 2:
                        return DiscColors.GREEN;
                    case 3:
                        return DiscColors.YELLOW;
                    case 4:
                        return DiscColors.BLUE;
                    case 5:
                        return DiscColors.PURPLE;
                    case 6:
                        return DiscColors.CYAN;
                    case 7:
                        return DiscColors.WHITE;
                    default:
                        System.out.println("Invalid option");
                        System.out.println("Press enter to continue");
                        System.in.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    private void createPlayer(char symbol, String username, DiscColors color) {
        System.out.println("Select type of new player");
        System.out.println("[1] Human Player");
        System.out.println("[2] Random Player");
        try {
            int option = Integer.parseInt(this.reader.readLine());
            switch(option) {
                case 1:
                    this.players.add(new HumanPlayer(symbol, username, color));
                    break;
                case 2:
                    this.players.add(new RandomPlayer(symbol, username, color));
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
        final String RESET = "\u001B[0m";
        System.out.println("Players:");
        this.players.forEach(player ->
            System.out.println("[" + player.getDisc().getColor() + player.getDisc().getSymbol() + RESET + "]-" + player.getUser())
        );
        System.out.println("Press Enter to continue");
        System.in.read();
    }

    public void deletePlayer() {
        int[] index = new int[]{1};
        System.out.println("Select player you want to remove");
        this.players.forEach(player ->
                System.out.println("[" + index[0]++ + "] " + player.getUser() + " - (" + player.getDisc().getSymbol() + ")")
        );
        try {
            int opt = Integer.parseInt(this.reader.readLine());
            this.players.remove(opt-1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serializePlayers();
        }
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
