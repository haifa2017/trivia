package com.adaptionsoft.games.trivia.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {
    static final int MAX_PLAYER_NUMBER = 6;
    List<Player> players = new ArrayList<Player>();

    public Players() {
    }

    public int count() {
        return players.size();
    }

    public String getPlayerName(int index) {
        return players.get(index).getName();
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