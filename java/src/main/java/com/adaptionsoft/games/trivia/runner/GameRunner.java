package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.trivia.uglytrivia.ConsolePrinter;
import com.adaptionsoft.games.trivia.uglytrivia.Game;
import com.adaptionsoft.games.trivia.uglytrivia.IPrinter;

public class GameRunner implements IRandom {

    private static boolean notAWinner;

    public static void main(String[] args) {
        AutomatePlayer automatePlayer = new AutomatePlayer();
        playGame(automatePlayer);

    }

    public static void playGame(IAutomatePlayer automatePlayer) {

        playGame(automatePlayer, new ConsolePrinter());
    }

    public static void playGame(IAutomatePlayer automatePlayer, IPrinter printer) {
        Game aGame = new Game(printer);

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
