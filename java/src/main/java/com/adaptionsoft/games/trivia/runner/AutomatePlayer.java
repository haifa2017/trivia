package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

public class AutomatePlayer implements IAutomatePlayer {
     Random rand = new Random();

     @Override
     public boolean hasWrongAnswer() {
        return rand.nextInt(9) == 7;
    }

     @Override
     public int getRoll() {
        return rand.nextInt(5) + 1;
    }
}