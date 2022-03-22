package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.utils.Range;

import java.util.HashSet;

/**
 * Íjászt leíró egységtípus
 */
public class Archer extends UnitType {

    @Override
    public String name() {
        return "Archer";
    }

    @Override
    public String description() {
        return "Archers are good for attacking distant units.<br>It can attack any unit if there are no enemy units nearby.";
    }

    @Override
    public int price() {
        return 6;
    }

    @Override
    public Range damage() {
        return new Range(2, 4);
    }

    @Override
    public double maxHealth() {
        return 7;
    }

    @Override
    public int speed() {
        return 4;
    }

    @Override
    public int priority() {
        return 9;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_ARCHER;
    }

    @Override
    public HashSet<Unit> validTargets(Unit who) {
        var c = super.validTargets(who);
        if (c.isEmpty()) {
            return new HashSet<>(who.tile().board().game().enemyOf(who.hero()).units());
        }
        return c;
    }
}
