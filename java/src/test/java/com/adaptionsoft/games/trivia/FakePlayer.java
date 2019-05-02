package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.IAutomatePlayer;

import java.util.*;

public class FakePlayer implements IAutomatePlayer {
    private Queue<Integer> rolls;
    private Queue<Boolean> answers;


    public FakePlayer(Integer[] rolls, Boolean[] answers) {
        this.rolls = new LinkedList<Integer>(Arrays.asList(rolls));
        this.answers = new LinkedList<Boolean>(Arrays.asList(answers));
    }

    @Override
    public boolean hasWrongAnswer() {
        return answers.poll();
    }

    @Override
    public int getRoll() {
        return rolls.poll();
    }
}
