package it.unicam.cs.pa.core;

import java.util.Scanner;

public class GameStarter {

    public static void main(String[] argv) {
        Menu menu = new Menu();
        menu.showMenu();
    }

    public static Player getOtherPlayer(int id, Player[] players) {
        return players[(id+1)%2];
    }
}
