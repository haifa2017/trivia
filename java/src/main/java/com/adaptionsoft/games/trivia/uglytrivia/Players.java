package com.adaptionsoft.games.trivia.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {
    List<Player> players = new ArrayList<Player>();

    public Players() {
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