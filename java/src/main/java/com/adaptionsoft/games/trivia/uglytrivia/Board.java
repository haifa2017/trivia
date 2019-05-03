package com.adaptionsoft.games.trivia.uglytrivia;

import java.util.LinkedList;

public class Board {
    int[] places;
    int[] purses;
    boolean[] inPenaltyBox;
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    public Board() {
        this.places = new int[Game.MAX_PLAYER_NUMBER];
        this.purses = new int[Game.MAX_PLAYER_NUMBER];
        this.inPenaltyBox = new boolean[Game.MAX_PLAYER_NUMBER];
    }
}