package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.utils.Range;

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
        return "Swordsman is a good unit for area-damage.";
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
        return Registry.I_SWORDSMAN;
    }
}
