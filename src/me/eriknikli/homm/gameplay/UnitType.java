package me.eriknikli.homm.gameplay;

public abstract class UnitType {

    public abstract String id();

    public abstract String name();

    public abstract String description();

    public abstract int price();
    public abstract int damage();
    public abstract double maxHealth();
    public abstract int speed();

}
