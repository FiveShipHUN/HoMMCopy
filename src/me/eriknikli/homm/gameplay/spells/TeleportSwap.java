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
        return "It swaps two unit on the board. One has to be yours and the other has to be an enemy.<br>The unit gets a <strong>Confused</strong> effect for 2 turn which decreases damage and makes the unit unable to teleport.";
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
