package me.eriknikli.homm.test;

import me.eriknikli.homm.gameplay.AIHero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * Leteszteli, hogy tud-e generálni random ai-t
 */
public class RandomAIHeroTest {

    public static final double WAIT_TIME_IN_SECONDS = 0.1;

    @Test()
    @DisplayName("Random AI Hero")
    @Timeout(value = (int) (WAIT_TIME_IN_SECONDS * 1000), unit = TimeUnit.MILLISECONDS)
    public void test() {
        AIHero ai = new AIHero();
        Assertions.assertFalse(ai.units().isEmpty(), "Az AI-nak nincsenek egységei.");
    }
}
