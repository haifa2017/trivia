package com.adaptionsoft.games.trivia.uglytrivia;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void player_in_prison_should_get_out_of_prison_if_roll_odd() {
        Player player = new Player("Charles");

        player.putInPenaltyBox();
        player.tryGettingOutOfPrison(1);

        assertThat(player.isInPenaltyBox()).isFalse();
    }

    @Test
    public void player_in_prison_should_not_get_out_of_prison_if_roll_even() {
        Player player = new Player("Jean");

        player.putInPenaltyBox();
        player.tryGettingOutOfPrison(2);

        assertThat(player.isInPenaltyBox()).isTrue();
    }

    @Test
    public void player_not_in_prison_should_not_be_in_prison_if_trying_to_get_out_of_prison() {
        Player player = new Player("Yves");

        player.tryGettingOutOfPrison(3);

        assertThat(player.isInPenaltyBox()).isFalse();
    }
}