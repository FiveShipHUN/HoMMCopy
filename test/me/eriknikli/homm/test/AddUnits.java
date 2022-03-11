package me.eriknikli.homm.test;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.AIHero;
import me.eriknikli.homm.gameplay.army.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Leteszteli, hogy a Unit addolás jól működik, merge-el ha kell, ha kell akkor hozzáadja a listához, mint új objektum a unit-ot
 */
public class AddUnits {


    @Test()
    @DisplayName("Add Units Test")
    public void test() {
        AIHero hero = new AIHero();
        Unit u1 = new Unit(Registry.UT_ARCHER, 100);
        Unit u2 = new Unit(Registry.UT_ARCHER, 200);
        Unit u3 = new Unit(Registry.UT_FARMER, 500);
        Unit u4 = new Unit(Registry.UT_GRIFFIN, 500);
        Unit u5 = new Unit(Registry.UT_GRIFFIN, 300);
        hero.addUnit(u1);
        hero.addUnit(u2);
        hero.addUnit(u3);
        hero.addUnit(u4);
        hero.addUnit(u5);
    }

}
