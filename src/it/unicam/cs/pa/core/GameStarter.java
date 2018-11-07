package it.unicam.cs.pa.core;

import java.util.concurrent.TimeUnit;

public class GameStarter {
    public static void main(String[] argv) {
        BattleGround my_field = new BattleGround(7,6);
        my_field.showElem();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        my_field.clear();
        my_field.showElem();
    }
}
