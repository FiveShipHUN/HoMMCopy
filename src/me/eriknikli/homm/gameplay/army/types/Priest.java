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
        return "Priest is the main support character.";
    }

    @Override
    public int price() {
        return 0;
    }

    @Override
    public Range damage() {
        return new Range(1, 1);
    }

    @Override
    public double maxHealth() {
        return 0;
    }

    @Override
    public int speed() {
        return 0;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_PRIEST;
    }
}
