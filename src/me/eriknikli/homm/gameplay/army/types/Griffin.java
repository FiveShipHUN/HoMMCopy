package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.utils.Range;

/**
 * Griffint leíró egységtípus
 */
public class Griffin extends UnitType {
    @Override
    public String name() {
        return "Griffin";
    }

    @Override
    public String description() {
        return "Griffin is a powerful unit.";
    }

    @Override
    public int price() {
        return 0;
    }

    @Override
    public Range damage() {
        return new Range(5, 10);
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
        return Registry.I_GRIFFIN;
    }
}
