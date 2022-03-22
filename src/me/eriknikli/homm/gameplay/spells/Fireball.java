package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;

public class Fireball extends Spell {


    @Override
    public String name() {
        return "Fireball";
    }

    @Override
    public int price() {
        return 120;
    }

    @Override
    public int mana() {
        return 9;
    }

    @Override
    public String desc() {
        return "It damages a 3x3 area depending on your Magic Power. (Magic * 20)<br>It will damage your units too!";
    }

    @Override
    public double power(int skill) {
        return (int) (20 * skill);
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_FIREBALL;
    }
}
