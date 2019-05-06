package com.adaptionsoft.games.trivia.uglytrivia;

import java.util.LinkedList;

public class Board {
    int[] places;
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    public Board() {
        this.places = new int[Players.MAX_PLAYER_NUMBER];

    }
}