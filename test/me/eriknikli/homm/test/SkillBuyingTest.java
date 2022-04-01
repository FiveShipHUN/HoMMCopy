package me.eriknikli.homm.test;

import me.eriknikli.homm.gameplay.Difficulty;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teszteli, hogy a Skillek vásárlási értéke megfelelően növekszik
 */
public class SkillBuyingTest {

    @Test
    @DisplayName("Skill Buying")
    public void test() {
        PlayerHero hero = new PlayerHero("Test Béla", Difficulty.NO_LIMIT);
        Assertions.assertEquals(5, hero.nextSkillPrice());
        hero.increaseSkill(Skill.ATTACK);
        Assertions.assertEquals(6, hero.nextSkillPrice());
        hero.increaseSkill(Skill.LUCK);
        Assertions.assertEquals(7, hero.nextSkillPrice());
        hero.increaseSkill(Skill.MORAL);
        Assertions.assertEquals(8, hero.nextSkillPrice());
        hero.increaseSkill(Skill.DEFENSE);
        Assertions.assertEquals(9, hero.nextSkillPrice());
        hero.increaseSkill(Skill.KNOWLEDGE);
        Assertions.assertEquals(10, hero.nextSkillPrice());
        hero.increaseSkill(Skill.MAGIC_POWER);
        Assertions.assertEquals(11, hero.nextSkillPrice());
        hero.increaseSkill(Skill.ATTACK);
        Assertions.assertEquals(13, hero.nextSkillPrice());
        hero.increaseSkill(Skill.ATTACK);
    }

}
