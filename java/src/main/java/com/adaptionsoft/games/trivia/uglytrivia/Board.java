package com.adaptionsoft.games.trivia.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
    int[] places;
    List<Player> players = new ArrayList<>();
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    public Board() {
        this.places = new int[Game.MAX_PLAYER_NUMBER];

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void incrementPlayerCoins(int currentPlayer) {
        players.get(currentPlayer).incrementCoins();
    }

    public int getCoins(int currentPlayer) {
        return players.get(currentPlayer).getCoins();
    }

    public void putCurrentPlayerInPenaltyBox(int currentPlayer) {
        players.get(currentPlayer).putInPenaltyBox();
    }

    public boolean isInPenaltyBox(int currentPlayer) {
        return players.get(currentPlayer).isInPenaltyBox();
    }
}