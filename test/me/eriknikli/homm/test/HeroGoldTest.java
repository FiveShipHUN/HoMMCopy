package me.eriknikli.homm.test;

import me.eriknikli.homm.gameplay.Difficulty;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teszteli a Hero Gold systemét
 */
public class HeroGoldTest {

    private Hero h;

    @BeforeEach
    public void init() {
        h = new PlayerHero("test", Difficulty.NORMAL);
    }

    @Test
    @DisplayName("ConstructorTest")
    public void testConstructor() {
        Hero d1 = new PlayerHero("easy", Difficulty.EASY);
        Hero d2 = new PlayerHero("normal", Difficulty.NORMAL);
        Hero d3 = new PlayerHero("hard", Difficulty.HARD);
        Assertions.assertEquals(1300, d1.gold());
        Assertions.assertEquals(1000, d2.gold());
        Assertions.assertEquals(700, d3.gold());
    }

    @Test()
    @DisplayName("Add/Set/Subtract Gold")
    public void testSetGold() {
        Assertions.assertTrue(h.subtractGold(1000));
        Assertions.assertFalse(h.subtractGold(1));
        Assertions.assertFalse(h.addGold(-1));
        Assertions.assertTrue(h.addGold(500));
        Assertions.assertTrue(h.addGold(-500));
        Assertions.assertFalse(h.setGold(-1));
        Assertions.assertTrue(h.setGold(1));
    }

}
