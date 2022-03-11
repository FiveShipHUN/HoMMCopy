package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.utils.Range;

public class Archer extends UnitType {

    @Override
    public String name() {
        return "Archer";
    }

    @Override
    public String description() {
        return "Archers are good for attacking ";
    }

    @Override
    public int price() {
        return 0;
    }

    @Override
    public Range damage() {
        return new Range(2, 4);
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
        return Registry.I_ARCHER;
    }
}
