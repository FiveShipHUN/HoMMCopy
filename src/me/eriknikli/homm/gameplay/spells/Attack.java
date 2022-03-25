package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Skill;

/**
 * A támadás, mint spellként megcsinálva
 */
public class Attack extends Spell {


    @Override
    public String name() {
        return "Attack";
    }

    @Override
    public int price() {
        return 0;
    }

    @Override
    public int mana() {
        return 0;
    }

    @Override
    public double power(int skill) {
        return skill * 10;
    }

    @Override
    public String desc() {
        return "The default spell, the Attack, which damages a selected unit on the board.";
    }

    @Override
    public ImageAsset icon() {
        return Registry.I_SWORDSMAN;
    }

    @Override
    public Skill skill() {
        return Skill.ATTACK;
    }

    @Override
    public boolean canBeBought() {
        return false;
    }

    @Override
    public boolean canBeSold() {
        return canBeBought();
    }
}
