package me.eriknikli.homm.test;

import me.eriknikli.homm.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Leellenőrzi, hogy az Utils.clamp(double x, double min, double max) jól működik-e
 * Szélsőséges értékek: rosszul használt min és max, és ha ugyanakkora
 */
public class ClampTest {

    @Test
    public void test() {
        Assertions.assertEquals(5, Utils.clamp(5, 3, 7));
        Assertions.assertEquals(5, Utils.clamp(5, 5, 5));
        Assertions.assertEquals(5, Utils.clamp(4, 5, 9));
        Assertions.assertEquals(5, Utils.clamp(4, 9, 5));
        Assertions.assertEquals(5, Utils.clamp(40, 1, 5));
        Assertions.assertEquals(5, Utils.clamp(40, 5, 1));
    }

}
