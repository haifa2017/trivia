package com.adaptionsoft.games.trivia.uglytrivia;

import static com.adaptionsoft.games.trivia.uglytrivia.Game.NUMBER_OF_PLACES;

public class Player {

    private int goldCoinsNumber = 0;
    private boolean isInPenaltyBox = false;
    private final String name;
    private int position = 0;

    public Player(String name) {
        this.name = name;
    }


    public void incrementCoins() {
        goldCoinsNumber++;
    }

    public int getCoins() {
        return goldCoinsNumber;
    }

    public void putInPenaltyBox() {
        isInPenaltyBox = true;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public String getName() {
        return name;
    }

    public void tryGettingOutOfPrison(int roll) {
        if (roll % 2 == 0) {
            return;
        }
        this.isInPenaltyBox = false;
    }

    public int move(int roll) {
        this.position = this.position + roll;

        if (this.position > 11) {
            this.position = this.position - NUMBER_OF_PLACES;
        }

        return this.position;
    }

    public int getPosition() {
        return this.position;
    }
}
