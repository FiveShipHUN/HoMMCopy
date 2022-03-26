package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.utils.Range;

import java.util.HashSet;

/**
 * Kardforgatót leíró egységtípus
 */
public class Swordsman extends UnitType {
    @Override
    public String name() {
        return "Swordsman";
    }

    @Override
    public String description() {
        return "Swordsmen are a good for damaging a larger area.<br>When it attacks, every enemy unit next to the attacked unit will be damaged too.";
    }

    @Override
    public int price() {
        return 8;
    }

    @Override
    public Range damage() {
        return new Range(5, 10);
    }

    @Override
    public double maxHealth() {
        return 15;
    }

    @Override
    public int speed() {
        return 5;
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_SWORDSMAN;
    }

    @Override
    public HashSet<Unit> otherTargets(Unit target) {
        HashSet<Unit> set = new HashSet<>();
        set.add(target);
        for (var t : target.tile().neighbors()) {
            if (t.unit() != null && t.unit().isWith(target)) {
                set.add(t.unit());
            }
        }
        return set;
    }
}
