package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;

public class Swordman extends UnitType {
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
