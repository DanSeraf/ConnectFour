package it.unicam.cs.pa.core;

import it.unicam.cs.pa.player.InteractivePlayer;
import it.unicam.cs.pa.player.RandomPlayer;
import it.unicam.cs.pa.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private final String RESET = "\u001B[0m";
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
        Player newPlayer = createNewPlayer(symbol, username, color);
        players.add(newPlayer);
        serializePlayers();
    }

    private char getSymbol() throws IOException, StringIndexOutOfBoundsException {
        do {
            util.clean();
            out.println("-CHOOSE ONE SYMBOL-");
            util.askInput();
            char symbol = reader.readLine().charAt(0);
            if (!checkSym(symbol)) {
                return symbol;
            } else {
                util.waitInput("Another player has the same symbol, choose another one");
            }
        } while(true);
    }

    private String getUsername() throws IOException {
        out.println("-USERNAME-");
        util.askInput();
        return reader.readLine();
    }

    private DiscColors getDiscColor() throws IOException {
        do {
            util.clean();
            out.println("-CHOOSE A COLOR-");
            out.print("[1] RED \n[2] GREEN \n[3] YELLOW \n[4] BLUE \n[5] PURPLE \n[6] CYAN \n[7] WHITE\n");
            util.askInput();
            List<DiscColors> colors = Arrays.asList(DiscColors.values());
            int opt = Integer.parseInt(reader.readLine());
            try {
                return colors.get(opt - 1);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                util.waitInput("Input error");
            }
        } while( true );
    }

    /**
     * check if player has the same symbol of a saved player
     * @param symbol symbol of new player
     * @return true if another player has the same symbol
     */
    private boolean checkSym(char symbol) {
        for (Player player : players) {
            if (sym_check.test(player, symbol)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * let choose the user which kind of player he want to create
     *
     * @param symbol the unique symbol of the player disc
     * @param username of the player
     * @param color of the player disc
     * @return the selected kind of player
     */
    private Player createNewPlayer(char symbol, String username, DiscColors color) {
        do {
            util.clean();
            out.println("-PLAYER TYPE-");
            out.println("[1] Human Player");
            out.println("[2] Random Player");
            util.askInput();
            try {
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        return new InteractivePlayer(symbol, username, color);
                    case 2:
                        return new RandomPlayer(symbol, username, color);
                    default:
                        util.waitInput("Invalid option, press Enter to continue");
                        return createNewPlayer(symbol, username, color);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException n) {
                util.waitInput("Invalid option, press Enter to continue");
            }
        } while(true);
    }

    /**
     * Display players saved
     */
    public void viewPlayers() {
        out.println("PLAYERS");

        this.players.forEach(player ->
            out.println("[" + player.getDisc().getColor() + player.getDisc().getSymbol() + RESET + "]-" + player.getUser())
        );

        util.askInput();
        util.waitInput("Press Enter to continue");
    }

    /**
     * delete players saved
     *
     * @throws IOException
     */
    public void deletePlayer() throws IOException {
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
        return (ArrayList<Player>) this.players.clone();
    }
}