package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.components.game.Tile;

/**
 * Tűzlabda spell
 */
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

    @Override
    public void cast(Unit selected, Unit target) {
        for (var tile : target.tile().neighbors()) {
            castHere(tile, selected.hero());
        }
        castHere(target.tile(), selected.hero());
        if (selected.isDead()) {
            selected.tile().board().nextTurn();
        }
    }

    private void castHere(Tile t, Hero h) {
        var unit = t.unit();
        if (unit != null) {
            unit.getDamage(power(h.skill(skill())), false);
        }
    }
}
