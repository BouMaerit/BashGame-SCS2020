package com.company;

import java.util.Scanner;

public class Game {

    private static final int MAX_TRY = 10;
    private static final int MAX_COINS = 7000;
    private static final int START_ROOM = 50;
    private static final int DIFF_GOAL =2500;

    private Room current;
    private int coins, wscounter, adcounter, trycounter, maxcoins, coindiff, maxcoindiff;
    private RoomGeneration roomGeneration;
    private boolean highest;

    public Game() {
        maxcoins = 0;
        maxcoindiff = DIFF_GOAL;
    }

    public void start() throws InterruptedException {
        highest = true;
        initGame();

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            if (scanner.nextInt() == 1) {
                highest = false;
            }
            keyLogic(scanner);
        } else {
            start();
        }
    }

    private void keyLogic(Scanner scanner) throws InterruptedException {
        while (scanner.hasNext()) {
            String key;
            key = scanner.next();
            if (key.equals("r")) {
                start();
            }
            if (trycounter < MAX_TRY) {
                trycounter++;
                movePlayer(key);
            } else {
                gameOver();
            }
        }
    }

    private void gameOver() throws InterruptedException {
        if (highest) {
            System.out.println("You have no more tries!");
            System.out.println("You reached " + coins + " coins");
            if (coins > maxcoins) {
                System.out.println(ConsoleColors.YELLOW + "You broke the old topscore of " + maxcoins + " coins!" + ConsoleColors.RESET);
                maxcoins = coins;
                System.out.println(ConsoleColors.YELLOW + "Your new topscore is " + maxcoins + " coins! Congratulations!!!" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.BLUE + "Unfortunately this is no new high score >:-( You would have to reach " + maxcoins + " coins." + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "Wanna restart?-Press r!" + ConsoleColors.RESET);
            if (coins > MAX_COINS) {
                System.out.println(ConsoleColors.GREEN_BRIGHT + "This much luck is illegal!!");
                Thread.sleep(5000);
                System.out.println("You are too good you can't play this game anymore." + ConsoleColors.RESET);
                Thread.sleep(1000);
                System.exit(0);
            }
        } else {
            System.out.println("You have no more tries!");
            System.out.println("You reached a difference of " + coindiff + " coins");
            if (coindiff < maxcoindiff) {
                System.out.println(ConsoleColors.YELLOW + "You broke the old topscore! You hit a difference of " +coindiff + " coins!" + ConsoleColors.RESET);
                maxcoindiff = coindiff;
                System.out.println(ConsoleColors.YELLOW + "Your smallest difference is " + coindiff + " coins! Congratulations!!!" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.BLUE + "Unfortunately this is no new high score >:-( You would have to reach a difference of " + maxcoindiff + " coins." + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "Wanna restart?-Press r!" + ConsoleColors.RESET);
        }
    }



    private void movePlayer(String key) {
        switch (key.toLowerCase()) {
            case "w" -> {
                wscounter++;
                move(roomGeneration.roomListws.get(wscounter));
            }
            case "a" -> {
                adcounter++;
                move(roomGeneration.roomListad.get(adcounter));
            }
            case "s" -> {
                wscounter--;
                move(roomGeneration.roomListws.get(wscounter));
            }
            case "d" -> {
                adcounter--;
                move(roomGeneration.roomListad.get(adcounter));
            }
        }
    }

    private void initGame() throws InterruptedException {
        wscounter = START_ROOM;
        adcounter = START_ROOM;
        trycounter = 0;
        coins = 0;
        coindiff = 0;
        roomGeneration = new RoomGeneration();
        current = roomGeneration.roomListws.get(START_ROOM);

        Thread.sleep(1000);
    }

    private void move(Room newRoom) {
        current = newRoom;
        System.out.println("You are currently in " + current.getName());
        System.out.println("There are " + current.getCoin() + " coins in this room");
        if (!highest) {
            if (coins > DIFF_GOAL) {
                coins = coins - current.getCoin();
            } else {
                coins = coins + current.getCoin();
            }
            coindiff = coins - DIFF_GOAL;
            System.out.println("Your current difference is " + coindiff);
        }
        if (highest) {
            coins = coins + current.getCoin();
            System.out.println("Current coins " + coins);
        }
    }
}
