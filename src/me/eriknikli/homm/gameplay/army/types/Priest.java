package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.utils.Range;

/**
 * Papot leíró egységtípus
 */
public class Priest extends UnitType {
    @Override
    public String name() {
        return "Priest";
    }

    @Override
    public String description() {
        return "Priest is the main support character. It can heal units that are alive.";
    }

    @Override
    public int price() {
        return 100;
    }

    @Override
    public Range damage() {
        return new Range(1, 3);
    }

    @Override
    public double maxHealth() {
        return 2;
    }

    @Override
    public int speed() {
        return 5;
    }

    @Override
    public int priority() {
        return 16;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_PRIEST;
    }
}
