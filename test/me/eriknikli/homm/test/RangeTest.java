package me.eriknikli.homm.test;

import me.eriknikli.homm.utils.Range;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Megnézi, hogy a Range osztály értékbeállítása jól működik-e, kijavítja-e, ha a felső korlát kisebb értékű, mint az alsó korlát
 */
public class RangeTest {

    @Test
    @DisplayName("Range test")
    public void test() {
        var r1 = new Range(3, 5);
        var r2 = new Range(5, 4);
        Assertions.assertEquals("[3 ; 5]", r1.toString());
        Assertions.assertEquals("[4 ; 5]", r2.toString());
        // Range#set is működik
        r2.set(1, -1);
        Assertions.assertEquals("[-1 ; 1]", r2.toString());
    }

}
