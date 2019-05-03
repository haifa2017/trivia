package com.adaptionsoft.games.trivia.uglytrivia;

import java.util.ArrayList;

public class Game {
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    public static final int CATEGORY_NUMBER = 4;
    static final int MAX_PLAYER_NUMBER = 6;
    private static final int MIN_PLAYER_NUMBER = 2;
    private static final int NUMBER_MAX_QUESTION = 50;
    private final Board board = new Board();
    private final IPrinter printer;
    private ArrayList<String> players = new ArrayList<>();
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game(IPrinter printer) {
        this.printer = printer;
        for (int index = 0; index < NUMBER_MAX_QUESTION; index++) {
            board.popQuestions.addLast(createQuestion(index, "Pop Question "));
            board.scienceQuestions.addLast((createQuestion(index, "Science Question ")));
            board.sportsQuestions.addLast((createQuestion(index, "Sports Question ")));
            board.rockQuestions.addLast(createQuestion(index, "Rock Question "));
        }

    }
    public Game() {
        this(new ConsolePrinter());
    }

    private String createQuestion(int index, String s) {
        return s + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= MIN_PLAYER_NUMBER);
    }

    public boolean add(String playerName) {
        Player player = new Player(playerName);
        board.addPlayer(player);

        players.add(playerName);
        board.places[howManyPlayers()] = 0;

        printer.print(playerName + " was added");
        printer.print("They are player number " + players.size());
        return true;
    }

    private int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        printer.print(players.get(currentPlayer) + " is the current player");
        printer.print("They have rolled a " + roll);


        if (board.isInPenaltyBox(currentPlayer)) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                printer.print(players.get(currentPlayer) + " is getting out of the penalty box");
                board.places[currentPlayer] = board.places[currentPlayer] + roll;
                if (board.places[currentPlayer] > 11) board.places[currentPlayer] = board.places[currentPlayer] - 12;

                printer.print(players.get(currentPlayer)
                        + "'s new location is "
                        + board.places[currentPlayer]);
                printer.print("The category is " + currentCategory());
                askQuestion();
            } else {
                printer.print(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            board.places[currentPlayer] = board.places[currentPlayer] + roll;
            if (board.places[currentPlayer] > 11) board.places[currentPlayer] = board.places[currentPlayer] - 12;

            printer.print(players.get(currentPlayer)
                    + "'s new location is "
                    + board.places[currentPlayer]);
            printer.print("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory().equals(POP)) {
            printer.print(board.popQuestions.removeFirst());
        }
        if (currentCategory().equals(SCIENCE))
            printer.print(board.scienceQuestions.removeFirst());
        if (currentCategory().equals(SPORTS))
            printer.print(board.sportsQuestions.removeFirst());
        if (currentCategory().equals(ROCK))
            printer.print(board.rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (isPop()) return POP;
        if (isScience()) return SCIENCE;
        if (isSports()) return SPORTS;
        return ROCK;
    }

    private boolean isSports() {

        return board.places[currentPlayer] % CATEGORY_NUMBER == 2;
    }

    private boolean isScience() {
        return board.places[currentPlayer] % CATEGORY_NUMBER == 1;
    }

    private boolean isPop() {
        return board.places[currentPlayer] % CATEGORY_NUMBER == 0;
    }

    public boolean wasCorrectlyAnswered() {
        if (board.isInPenaltyBox(currentPlayer)) {
            if (isGettingOutOfPenaltyBox) {
                printer.print("Answer was correct!!!!");
                board.incrementPlayerCoins(currentPlayer);
                printer.print(players.get(currentPlayer)
                        + " now has "
                        + board.getCoins(currentPlayer)
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            printer.print("Answer was correct!!!!");
            board.incrementPlayerCoins(currentPlayer);
            printer.print(players.get(currentPlayer)
                    + " now has "
                    + board.getCoins(currentPlayer)
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        printer.print("Question was incorrectly answered");
        printer.print(players.get(currentPlayer) + " was sent to the penalty box");
        board.putCurrentPlayerInPenaltyBox(currentPlayer);

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(board.getCoins(currentPlayer) == 6);
    }
}
