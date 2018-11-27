package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.HumanPlayer;
import it.unicam.cs.pa.player.RandomPlayer;
import it.unicam.cs.pa.player.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.function.BiPredicate;

/**
 *
 * Settings
 * it stores the players characters and the boards size
 *
 */

public class Settings {

    private ArrayList<Player> players;
    private BiPredicate<Player, Character> sym_check = (p, c) -> p.getDisc().getSymbol()==c;
    private BufferedReader reader;
    private PrintStream out;
    private Utils util;

    public Settings(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
        this.util = new Utils();
        restorePlayers();
    }

    /**
     * add new player to the game
     * ask for name and symbol
     */
    public void addNewPlayer() throws IOException {
        String username = getUsername();
        DiscColors color = getDiscColor();
        char symbol = getSymbol();
        createPlayer(symbol, username, color);
    }

    private char getSymbol() throws IOException, StringIndexOutOfBoundsException {
        char symbol;
        do {
            out.println("Choose one symbol");
            symbol = reader.readLine().charAt(0);
            if (checkSym((char) symbol) == false) {
                break;
            } else {
                util.outError("Another player has the same symbol, choose another one");
            }
        } while (true);
        return symbol;
    }

    private String getUsername() throws IOException {
        out.println("Choose one username");
        String username = reader.readLine();
        return username;
    }

    private DiscColors getDiscColor() throws IOException, NumberFormatException {
        while (true) {
            out.println("Select the color you want to use.");
            out.print("[1] RED \n[2] GREEN \n[3] YELLOW \n[4] BLUE \n[5] PURPLE \n[6] CYAN \n[7] WHITE\n");
            int opt = Integer.parseInt(reader.readLine());
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
                    out.println("Invalid option");
                    util.outError("Press Enter to continue");
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
                out.println("Another player has the same symbol");
                util.outError("Press Enter to exit");
            }
        }
        return false;
    }

    /**
     * select which kind of player they want to create
     * @param symbol symbol that identify the player
     * @param username username of the player
     */
    private void createPlayer(char symbol, String username, DiscColors color) throws IOException, NumberFormatException {
        out.println("Select type of new player");
        out.println("[1] Human Player");
        out.println("[2] Random Player");
        int option = Integer.parseInt(reader.readLine());
        switch(option) {
            case 1:
                players.add(new HumanPlayer(symbol, username, color));
                break;
            case 2:
                players.add(new RandomPlayer(symbol, username, color));
                break;
            default: out.println("Invalid option");
                util.outError("Press Enter to exit");
        }
        serializePlayers();

    }

    /**
     * check if Players.sav exists
     */
    private void restorePlayers() {
        File f = new File("Players.sav");
        if(f.exists() && !f.isDirectory()) {
            deserializePlayers();
        } else {
            players = new ArrayList<>();
        }
    }

    /**
     * view players
     */
    public void viewPlayers() {
        final String RESET = "\u001B[0m";
        out.println("Players:");
        this.players.forEach(player ->
            out.println("[" + player.getDisc().getColor() + player.getDisc().getSymbol() + RESET + "]-" + player.getUser())
        );
        util.outError("Press Enter to continue");
    }

    public void deletePlayer() {
        int[] index = new int[]{1};
        out.println("Select player you want to remove");
        this.players.forEach(player ->
                out.println("[" + index[0]++ + "] " + player.getUser() + " - (" + player.getDisc().getSymbol() + ")")
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
