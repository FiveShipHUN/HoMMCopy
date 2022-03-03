package me.eriknikli.homm.gameplay.units.types;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.data.ImageAsset;

public class Farmer extends UnitType {

    @Override
    public String name() {
        return "Farmer";
    }

    @Override
    public String description() {
        return "The Farmer is a basic unit, it's cheap but it is not that effective.";
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
        return Registry.I_FARMER;
    }
}
