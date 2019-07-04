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
 * It manage the players characters
 * The settings will be serialized in a file called "settings.sav"
 *
 */

public class Settings {

    private static Settings settings = new Settings();
    final String RESET = "\u001B[0m";
    private ArrayList<Player> players;
    private BiPredicate<Player, Character> sym_check = (p, c) -> p.getDisc().getSymbol()==c;
    private BufferedReader reader;
    private PrintStream out;
    private Utils util;

    private Settings() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.out = System.out;
        this.util = new Utils();
        restorePlayers();
    }

    public static Settings getInstance() {
        return settings;
    }

    /**
     * add new player to the game
     * ask for name and disc (symbol, color)
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
            util.clean();
            out.println("-CHOOSE ONE SYMBOL-");
            util.askInput();
            symbol = reader.readLine().charAt(0);
            if (!checkSym(symbol)) {
                break;
            } else {
                util.waitInput("Another player has the same symbol, choose another one");
            }
        } while(true);
        return symbol;
    }

    private String getUsername() throws IOException {
        out.println("Choose one username");
        util.askInput();
        return reader.readLine();
    }

    private DiscColors getDiscColor() throws IOException {
        while (true) {
            util.clean();
            out.println("-CHOOSE A COLOR-");
            out.print("[1] RED \n[2] GREEN \n[3] YELLOW \n[4] BLUE \n[5] PURPLE \n[6] CYAN \n[7] WHITE\n");
            util.askInput();
            try {
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
                        util.waitInput("Invalid option, press Enter to continue");
                }
            } catch (NumberFormatException ne) {
                util.waitInput("Not a valid option, press Enter to continue");
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
                return true;
            }
        }
        return false;
    }

    /**
     * select which kind of player they want to create
     * @param symbol symbol that identify the player
     * @param username username of the player
     */
    private void createPlayer(char symbol, String username, DiscColors color) throws IOException {
        boolean should_break = false;
        while(!should_break) {
            util.clean();
            out.println("-PLAYER TYPE-");
            out.println("[1] Human Player");
            out.println("[2] Random Player");
            util.askInput();
            try {
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        players.add(new HumanPlayer(symbol, username, color));
                        should_break = true;
                        break;
                    case 2:
                        players.add(new RandomPlayer(symbol, username, color));
                        should_break = true;
                        break;
                    default:
                        util.waitInput("Invalid option, Press Enter to continue");
                }
            } catch (NumberFormatException ne) {
                util.waitInput("Invalid option, press Enter to continue");
            } finally {
                serializePlayers();
            }
        }
    }

    /**
     * check if the file "settings.sav" exists
     */
    private void restorePlayers() {
        File f = new File("settings.sav");
        if(f.exists() && !f.isDirectory()) {
            deserializePlayers();
        } else {
            players = new ArrayList<>();
        }
    }

    public void viewPlayers() {
        out.println("PLAYERS");

        this.players.forEach(player ->
            out.println("[" + player.getDisc().getColor() + player.getDisc().getSymbol() + RESET + "]-" + player.getUser())
        );

        util.askInput();
        util.waitInput("Press Enter to continue");
    }

    public void deletePlayer() throws IOException{
        do {
            util.clean();
            int[] index = new int[]{1};
            out.println("DELETE PLAYERS ([0] to Exit)");
            this.players.forEach(player ->
                    out.println("[" + index[0]++ + "] [" + player.getDisc().getColor() + player.getDisc().getSymbol() + RESET + "]" + player.getUser())
            );

            util.askInput();
            int opt = Integer.parseInt(this.reader.readLine());
            if (opt == 0) { break; }
            try {
                this.players.remove(opt - 1);
                serializePlayers();
            } catch (IndexOutOfBoundsException ie) {
                break;
            }
        } while(true);
    }

    /**
     * serialize players ArrayList
     * it will be saved in the current working directory
     */
    private void serializePlayers() {
        FileOutputStream out_file = null;
        ObjectOutputStream obj_file = null;
        try {
            out_file = new FileOutputStream("settings.sav");
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
     * load players serialized in Settings.sav
     */
    private void deserializePlayers() {
        FileInputStream input_file = null;
        ObjectInputStream obj_file = null;
        try {
            input_file = new FileInputStream("settings.sav");
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

    /**
     *
     * @return clone of players arraylist
     */
    public ArrayList<Player> getPlayers() {
        return (ArrayList<Player>)this.players.clone();
    }
}