package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.utils.Range;

/**
 * Íjászt leíró egységtípus
 */
public class Archer extends UnitType {

    @Override
    public String name() {
        return "Archer";
    }

    @Override
    public String description() {
        return "Archers are good for attacking distant units but they are not trained for close-ranged combats. ";
    }

    @Override
    public int price() {
        return 6;
    }

    @Override
    public Range damage() {
        return new Range(2, 4);
    }

    @Override
    public double maxHealth() {
        return 7;
    }

    @Override
    public int speed() {
        return 4;
    }

    @Override
    public int priority() {
        return 9;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_ARCHER;
    }
}
