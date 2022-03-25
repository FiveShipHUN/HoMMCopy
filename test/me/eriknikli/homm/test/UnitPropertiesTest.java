package me.eriknikli.homm.test;

import me.eriknikli.homm.gameplay.army.types.Archer;
import me.eriknikli.homm.gameplay.army.types.Farmer;
import me.eriknikli.homm.gameplay.army.types.Griffin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Leellenőrzi, hogy a követelménynek megfelelőek-e az egységek beállításai
 */
public class UnitPropertiesTest {

    @Test
    @DisplayName("Unit Properties Test - Farmer")
    public void testFarmer() {
        var u = new Farmer();
        Assertions.assertEquals(2, u.price());
        Assertions.assertEquals(1, u.damage().min());
        Assertions.assertEquals(1, u.damage().max());
        Assertions.assertEquals(3, u.maxHealth());
        Assertions.assertEquals(4, u.speed());
        Assertions.assertEquals(8, u.priority());
    }

    @Test
    @DisplayName("Unit Properties Test - Archer")
    public void testArcher() {
        var u = new Archer();
        Assertions.assertEquals(6, u.price());
        Assertions.assertEquals(2, u.damage().min());
        Assertions.assertEquals(4, u.damage().max());
        Assertions.assertEquals(7, u.maxHealth());
        Assertions.assertEquals(4, u.speed());
        Assertions.assertEquals(9, u.priority());
    }

    @Test
    @DisplayName("Unit Properties Test - Griffin")
    public void testGriffin() {
        var u = new Griffin();
        Assertions.assertEquals(15, u.price());
        Assertions.assertEquals(5, u.damage().min());
        Assertions.assertEquals(10, u.damage().max());
        Assertions.assertEquals(30, u.maxHealth());
        Assertions.assertEquals(7, u.speed());
        Assertions.assertEquals(15, u.priority());
    }

}
