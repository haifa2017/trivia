package com.adaptionsoft.games.trivia.uglytrivia;

public class Game {
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    public static final int CATEGORY_NUMBER = 4;
    public static final int NUMBER_OF_PLACES = 12;
    final Players players = new Players();
    private static final int NUMBER_MAX_QUESTION = 50;
    public final Board board = new Board();
    private final IPrinter printer;
    public int currentPlayer = 0;

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
        return players.isPlayersNumberValid();
    }

    public boolean add(String playerName) {
        Player player = new Player(playerName);
        players.addPlayer(player);

        printer.print(playerName + " was added");
        printer.print("They are player number " + howManyPlayers());
        return true;
    }

    private int howManyPlayers() {
        return players.count();
    }

    public void roll(int roll) {
        printer.print(players.getPlayerName(currentPlayer) + " is the current player");
        printer.print("They have rolled a " + roll);

        if (players.isInPenaltyBox(currentPlayer)) {
            if (roll % 2 != 0) {
                players.getPlayerOutOfPenaltyBox(currentPlayer, roll);
                printer.print(players.getPlayerName(currentPlayer) + " is getting out of the penalty box");
                movePlayer(roll);
            } else {
                printer.print(players.getPlayerName(currentPlayer) + " is not getting out of the penalty box");
            }

        } else {

            movePlayer(roll);
        }
    }

    private void movePlayer(int roll) {
        int newPosition = players.movePlayer(currentPlayer, roll);

        printer.print(players.getPlayerName(currentPlayer)
                + "'s new location is "
                + newPosition);
        printer.print("The category is " + currentCategory());
        askQuestion();
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

        return getPlayerPosition(currentPlayer) % CATEGORY_NUMBER == 2;
    }

    public int getPlayerPosition(int currentPlayer) {
        return players.getPlayerPosition(currentPlayer);
    }

    private boolean isScience() {
        return getPlayerPosition(currentPlayer) % CATEGORY_NUMBER == 1;
    }

    private boolean isPop() {
        return getPlayerPosition(currentPlayer) % CATEGORY_NUMBER == 0;
    }

    public boolean wasCorrectlyAnswered() {
        if (players.isInPenaltyBox(currentPlayer)) {
            currentPlayer++;
            if (currentPlayer == howManyPlayers()) {
                currentPlayer = 0;
            }
            return true;
        }
        return answerWasCorrect();
    }

    private boolean answerWasCorrect() {
        printer.print("Answer was correct!!!!");
        players.incrementPlayerCoins(currentPlayer);
        printer.print(players.getPlayerName(currentPlayer)
                + " now has "
                + players.getCoins(currentPlayer)
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == howManyPlayers()) {
            currentPlayer = 0;
        }

        return winner;
    }

    public boolean wrongAnswer() {
        if (!players.isInPenaltyBox(currentPlayer)) {
            playerGoToPenaltybox();
        }

        currentPlayer++;
        if (currentPlayer == howManyPlayers()) currentPlayer = 0;
        return true;
    }

    private void playerGoToPenaltybox() {
        printer.print("Question was incorrectly answered");
        printer.print(players.getPlayerName(currentPlayer) + " was sent to the penalty box");
        players.putCurrentPlayerInPenaltyBox(currentPlayer);
    }


    private boolean didPlayerWin() {
        return !(players.getCoins(currentPlayer) == 6);
    }
}
