package me.eriknikli.homm.gameplay.units.types;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.data.ImageAsset;

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
    public int damage() {
        return 0;
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
