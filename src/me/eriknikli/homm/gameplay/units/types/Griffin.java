package me.eriknikli.homm.gameplay.units.types;

import me.eriknikli.homm.data.ImageAsset;

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
        return null;
    }
}
