package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;

/**
 * Erősítés képesség
 */
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
        return "It increases your units' damage for three round depending on your Magic Power (It multiplies with 1 + Magic Power / 20).<br>The effect will be visible as <strong>Strength</strong>.";
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
