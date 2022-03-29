package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.army.Unit;

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
        return "It increases your units' damage depending on your Magic Power (It multiplies with 1 + Magic Power / 20) till the next round.";
    }

    @Override
    public double power(int skill) {
        return 1.0 + skill / 20.0;
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_STRENGTHENING;
    }

    @Override
    public void cast(Unit selected, Unit target) {
        selected.damMod = power(selected.hero().skill(skill()));
    }

    @Override
    public boolean hasTarget() {
        return false;
    }
}
