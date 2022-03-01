package me.eriknikli.homm.gameplay;

public class Farmer extends UnitType {
    @Override
    public String id() {
        return "farmer";
    }

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
}
