package me.eriknikli.homm.test;

import me.eriknikli.homm.gameplay.spells.Fireball;
import me.eriknikli.homm.gameplay.spells.Resurrect;
import me.eriknikli.homm.gameplay.spells.Shock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Leellenőrzi, hogy a követelménynek megfelelőek-e a varázslatok beállításai
 */
public class SpellPropertiesTest {

    @Test
    @DisplayName("Spell Test - Shock")
    public void testShock() {
        var s = new Shock();
        Assertions.assertEquals(s.price(), 60);
        Assertions.assertEquals(s.mana(), 5);
        Assertions.assertEquals(s.power(1), 30);
        Assertions.assertEquals(s.power(2), 60);
    }

    @Test
    @DisplayName("Spell Test - Fireball")
    public void testFireball() {
        var s = new Fireball();
        Assertions.assertEquals(s.price(), 120);
        Assertions.assertEquals(s.mana(), 9);
        Assertions.assertEquals(s.power(1), 20);
        Assertions.assertEquals(s.power(2), 40);
    }

    @Test
    @DisplayName("Spell Test - Resurrect")
    public void testResurrect() {
        var s = new Resurrect();
        Assertions.assertEquals(s.price(), 120);
        Assertions.assertEquals(s.mana(), 6);
        Assertions.assertEquals(s.power(1), 50);
        Assertions.assertEquals(s.power(2), 100);
    }

}
