package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;

public class Strengthening extends Spell {


    @Override
    public String name() {
        return "Strengthening";
    }

    @Override
    public int price() {
        return 150;
    }

    @Override
    public int mana() {
        return 2;
    }

    @Override
    public String desc() {
        return "It increases your units' damage for a round depending on your Magic Power (It multiplies with 1 + Magic Power / 20).";
    }

    @Override
    public double power(int skill) {
        return 1.0 + skill / 20.0;
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_STRENGTHENING;
    }
}
