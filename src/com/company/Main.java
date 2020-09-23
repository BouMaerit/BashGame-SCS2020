package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
