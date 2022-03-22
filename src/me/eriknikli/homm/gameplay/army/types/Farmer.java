package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.utils.Range;

/**
 * Farmert leíró egységtípus
 */
public class Farmer extends UnitType {

    @Override
    public String name() {
        return "Farmer";
    }

    @Override
    public String description() {
        return "The Farmer is a basic unit, it's cheap but it is not that effective and has no special effect.";
    }

    @Override
    public int price() {
        return 2;
    }

    @Override
    public Range damage() {
        return new Range(1, 1);
    }

    @Override
    public double maxHealth() {
        return 3;
    }

    @Override
    public int speed() {
        return 4;
    }

    @Override
    public int priority() {
        return 8;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_FARMER;
    }
}
