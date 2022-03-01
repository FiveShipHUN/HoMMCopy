package me.eriknikli.homm.test;

import me.eriknikli.homm.utils.RNG;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Random számgenerálás tesztelése TEST_COUNT (alapból 100) random esettel
 * Szélsőséges eset: negatív számokat alap Javas random generátor nem szereti, ez reméljük igen
 * Illetve a korlátok bevitelkor megcserélhetőek, ezeket a kód automatán "visszacseréli"
 */
public class RandomTest {

    public static final int TEST_COUNT = 100;

    @RepeatedTest(TEST_COUNT)
    @DisplayName("Random Test")
    public void test() {
        var rng = RNG.randomDouble(5, 10);
        Assertions.assertTrue(5 <= rng && rng <= 10, rng + " nem 5 és 10 között van.");
        rng = RNG.randomDouble(-1, 1);
        Assertions.assertTrue(-1 <= rng && rng <= 1, rng + " nem -1 és 1 között van.");
        rng = RNG.randomDouble(0, -1);
        Assertions.assertTrue(-1 <= rng && rng <= 0, rng + " nem -1 és 0 között van.");
        var rngi = RNG.randomIntIE(5, 10);
        Assertions.assertTrue(5 <= rngi && rngi <= 10, rngi + " nem 5 és 10 között van.");
        rngi = RNG.randomIntIE(-5, 10);
        Assertions.assertTrue(-5 <= rngi && rngi <= 10, rngi + " nem -5 és 10 között van.");
        rngi = RNG.randomIntIE(5, -10);
        Assertions.assertTrue(-10 <= rngi && rngi <= 5, rngi + " nem -10 és 5 között van.");
    }

}
