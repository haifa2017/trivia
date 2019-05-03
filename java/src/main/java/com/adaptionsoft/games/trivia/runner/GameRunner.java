package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.uglytrivia.Game;

public class GameRunner implements IRandom {

    private static boolean notAWinner;

    public static void main(String[] args) {
        AutomatePlayer automatePlayer = new AutomatePlayer();
        playGame(automatePlayer);

    }

    public static void playGame(IAutomatePlayer automatePlayer) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        do {

            aGame.roll(automatePlayer.getRoll());

            if (automatePlayer.hasWrongAnswer()) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }

        } while (notAWinner);
    }
}
