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
    public String desc(Object[] params) {
        return "It damages one unit depending on your Magic Power. (Magic * 30)";
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_FIREBALL;
    }
}
