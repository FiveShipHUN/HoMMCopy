package me.eriknikli.homm.test;

import me.eriknikli.homm.gameplay.Difficulty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Difficulty#displayName() metódus tesztje
 */
public class DifficultyDisplayNameTest {

    @Test
    @DisplayName("Difficulty Display Name")
    public void test() {
        Assertions.assertEquals("Easy", Difficulty.EASY.displayName());
        Assertions.assertEquals("Normal", Difficulty.NORMAL.displayName());
        Assertions.assertEquals("Hard", Difficulty.HARD.displayName());
        Assertions.assertEquals("No Limit", Difficulty.NO_LIMIT.displayName());
    }

}
