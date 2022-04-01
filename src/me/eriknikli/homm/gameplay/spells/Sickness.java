package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.army.Unit;

/**
 * Betegség képesség
 */
public class Sickness extends Spell {


    @Override
    public String name() {
        return "Sickness";
    }

    @Override
    public int price() {
        return 200;
    }

    @Override
    public int mana() {
        return 15;
    }

    @Override
    public String desc() {
        return "Makes a unit skip its turn.<br>Cannot be casted if your enemy has only one unit left.";
    }

    @Override
    public double power(int skill) {
        return 1.0;
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_SICKNESS;
    }

    @Override
    public void cast(Unit selected, Unit target) {
        if (target.hero().units().size() > 1) {
            target.sickness();
        }
    }
}
