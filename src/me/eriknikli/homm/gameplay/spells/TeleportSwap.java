package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;

/**
 * Teleportálás képesség
 */
public class TeleportSwap extends Spell {


    @Override
    public String name() {
        return "Teleport-Swap";
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
        return "It swaps two unit on the board. One has to be yours and the other has to be an enemy.";
    }

    @Override
    public double power(int skill) {
        return 1.0;
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_TELEPORT;
    }
}
