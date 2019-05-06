package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.AutomatePlayer;
import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.trivia.runner.IAutomatePlayer;
import com.adaptionsoft.games.trivia.uglytrivia.Game;
import com.adaptionsoft.games.trivia.uglytrivia.IPrinter;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SomeTest {

    @Test
    @Ignore
    public void generate_golden_master() {
        AutomatePlayer automatePlayer = new AutomatePlayer();
        PlayerWrapper playerWrapper = new PlayerWrapper(automatePlayer);

        GameRunner.playGame(playerWrapper);
        playerWrapper.print();
    }

    @Test
    public void first_sample_golden_master() throws IOException, URISyntaxException {
        List<String> expectedString = Files.readAllLines(Paths.get("src/test/resources/goldenMaster.txt"));

        Integer[] rolls = {2, 3, 3, 4, 5, 5, 2, 2, 5, 4, 2, 4, 1, 3, 5, 3};
        Boolean[] answers = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        FakePlayer fakePlayer = new FakePlayer(rolls, answers);

        FakePrinter fakePrinter = new FakePrinter();
        GameRunner.playGame(fakePlayer, fakePrinter);

        Assertions.assertThat(fakePrinter.output).isEqualTo(expectedString);
    }

    @Test
    public void second_sample_golden_master() throws IOException {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<String> expectedString = Files.readAllLines(Paths.get("src/test/resources/goldenMasterv2.txt"));

        Integer[] rolls = {5, 2, 2, 1, 3, 4, 2, 1, 1, 4, 3, 5, 2, 5, 3, 4, 1, 1, 1, 2, 1, 3, 5};
        Boolean[] answers = {false, false, true, true, false, false, false, false, true, false, false, false, true, true, false, false, false, false, false, false, false, false, false};
        FakePlayer fakePlayer = new FakePlayer(rolls, answers);

        System.setOut(new PrintStream(byteArrayOutputStream));
        GameRunner.playGame(fakePlayer);
        System.setOut(originalOut);

        String output = new String(byteArrayOutputStream.toByteArray());

        List<String> outputStrings = Arrays.asList(output.split("\r\n"));
        Assertions.assertThat(outputStrings).isEqualTo(expectedString);
    }

    @Test
    public void third_sample_golden_master() throws IOException, URISyntaxException {
        List<String> expectedString = Files.readAllLines(Paths.get("src/test/resources/goldenMasterWithPlayerNotGettingOutOfPenaltyBox.txt"));

        Integer[] rolls = {5, 1, 1, 4, 2, 2, 1, 4, 1, 2, 4, 1, 4, 4, 1, 2, 4, 1};
        Boolean[] answers = {true, false, false, true, false, false, false, false, false, true, true, false, false, false, false, false, false, false};
        FakePlayer fakePlayer = new FakePlayer(rolls, answers);

        FakePrinter fakePrinter = new FakePrinter();
        GameRunner.playGame(fakePlayer, fakePrinter);

        Assertions.assertThat(fakePrinter.output).isEqualTo(expectedString);
    }

    @Test
    public void player_should_not_have_question_when_he_is_in_penalty_box() throws IOException {

        FakePrinter fakePrinter = new FakePrinter();
        Game game = new Game(fakePrinter);
        game.add("Che");

        game.roll(1);
        game.wrongAnswer();
        game.roll(2);
        game.wrongAnswer();
        String outputResult = fakePrinter.output.get(fakePrinter.output.size() - 1);
        Assertions.assertThat(outputResult).isEqualTo("Che is not getting out of the penalty box");
    }

    @Test
    public void player_should_answer_when_he_get_out_of_penalty_box() {
        FakePrinter fakePrinter = new FakePrinter();
        Game game = new Game(fakePrinter);
        game.add("Che");

        game.roll(1);
        game.wrongAnswer();
        game.roll(1);
        game.wrongAnswer();

        String outputResult = fakePrinter.output.get(fakePrinter.output.size() - 1);
        Assertions.assertThat(outputResult).isEqualTo("Che was sent to the penalty box");

    }

    public class PlayerWrapper implements IAutomatePlayer {
        private final IAutomatePlayer automatePlayer;
        private final List<Integer> rolls = new ArrayList<>();
        private final List<Boolean> answers = new ArrayList<>();

        public PlayerWrapper(IAutomatePlayer automatePlayer) {

            this.automatePlayer = automatePlayer;
        }

        @Override
        public boolean hasWrongAnswer() {
            boolean answer = this.automatePlayer.hasWrongAnswer();
            answers.add(answer);
            return answer;
        }

        @Override
        public int getRoll() {
            int roll = this.automatePlayer.getRoll();
            rolls.add(roll);
            return roll;
        }

        public void print() {
            System.out.println("rolls = " + rolls);
            System.out.println("answers = " + answers);
        }

    }

    public class FakePrinter implements IPrinter {

        List<String> output = new ArrayList<>();

        @Override
        public void print(String x) {
            output.add(x);

        }
    }
}

