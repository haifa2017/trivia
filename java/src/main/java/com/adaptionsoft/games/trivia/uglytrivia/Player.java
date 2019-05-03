package com.adaptionsoft.games.trivia.uglytrivia;

public class Player {

    private int goldCoinsNumber = 0;
    private boolean isInPenaltyBox = false;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void incrementCoins() {
        goldCoinsNumber++;
    }

    public int getCoins() {
        return goldCoinsNumber;
    }
}
