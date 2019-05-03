package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.AutomatePlayer;
import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.trivia.runner.IAutomatePlayer;
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
        PrintStream originalOut = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<String> expectedString = Files.readAllLines(Paths.get("src/test/resources/goldenMaster.txt"));

        Integer[] rolls = {2, 3, 3, 4, 5, 5, 2, 2, 5, 4, 2, 4, 1, 3, 5, 3};
        Boolean[] answers = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        FakePlayer fakePlayer = new FakePlayer(rolls, answers);

        System.setOut(new PrintStream(byteArrayOutputStream));
        GameRunner.playGame(fakePlayer);
        System.setOut(originalOut);

        String output = new String(byteArrayOutputStream.toByteArray());

        List<String> outputStrings = Arrays.asList(output.split("\r\n"));
        Assertions.assertThat(outputStrings).containsExactlyElementsOf(expectedString);
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
        Assertions.assertThat(outputStrings).containsExactlyElementsOf(expectedString);
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
}

