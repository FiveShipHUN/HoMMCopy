package me.eriknikli.homm.gameplay;

public class Archer extends UnitType {
    @Override
    public String id() {
        return "archer";
    }

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
}
