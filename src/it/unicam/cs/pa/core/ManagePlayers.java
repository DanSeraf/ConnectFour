package it.unicam.cs.pa.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ManagePlayers implements  Serializable{

    private ArrayList<Player> players = new ArrayList<>();

    public ManagePlayers() {
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
        Player new_player_obj = new HumanPlayer(symbol, username);
        this.players.add(new_player_obj);
    }

    public void viewPlayers() {
        try {
            for (Player player:
                    this.players) {
                System.out.println("Player: " + player.getUser() + " " + player.getSymbol());
                TimeUnit.SECONDS.sleep(3);
            }
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void savePlayer() {

    }
}
