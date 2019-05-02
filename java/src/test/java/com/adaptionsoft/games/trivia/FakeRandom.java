package com.adaptionsoft.games.trivia;

public class FakeRandom {

    private int[] tab;
    private int counter = 0;

    public FakeRandom() {
        tab = new int[]{1, 2, 3, 4, 5, 6};
    }

    public int rollDice() {
        return (++counter % 6) + 1;
    }
}
