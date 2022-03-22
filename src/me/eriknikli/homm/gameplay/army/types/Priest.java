package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Skill;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.components.game.Tile;
import me.eriknikli.homm.utils.RNG;
import me.eriknikli.homm.utils.Range;

/**
 * Papot leíró egységtípus
 */
public class Priest extends UnitType {
    @Override
    public String name() {
        return "Priest";
    }

    @Override
    public String description() {
        return "Priest is the main support character.<br>It heals friendly units which are next to this at the start of the round and can resurrect them too.<br>It cannot heal itself. It cannot bring back fully destroyed units.<br>The amount of heeling is random, but cannot be greater than Magic Power * 10.";
    }

    @Override
    public int price() {
        return 50;
    }

    @Override
    public Range damage() {
        return new Range(1, 3);
    }

    @Override
    public double maxHealth() {
        return 2;
    }

    @Override
    public int speed() {
        return 5;
    }

    @Override
    public int priority() {
        return 16;
    }

    @Override
    public ImageAsset image() {
        return Registry.I_PRIEST;
    }

    @Override
    public void onStartTurn(Unit who) {
        for (Tile t : who.tile().neighbors()) {
            if (t.unit() != null) {
                if (t.unit().isWith(who)) {
                    t.unit().heal(RNG.randomDouble(1, who.hero().skill(Skill.MAGIC_POWER)), true);
                }
            }
        }
    }
}
