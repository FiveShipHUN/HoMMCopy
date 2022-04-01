package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.gameplay.Skill;
import me.eriknikli.homm.gameplay.army.Unit;

import java.util.Objects;

/**
 * A Spellek ősosztálya
 */
public abstract class Spell {

    public abstract String name();

    public abstract int price();

    public abstract int mana();

    public abstract double power(int skill);

    public abstract String desc();

    public String helpTxt(int skill) {
        return "<html>" +
                "<h2>" +
                name() +
                "</h2>" +
                "<p>" +
                desc() +
                "</p>" +
                "<ul>" +
                "<li>" +
                "Price: " + price() + " gold" +
                "</li>" +
                "<li>" +
                "Mana: " + mana() +
                "</li>" +
                "<li>" +
                "Power: " + power(skill) +
                "</li>" +
                "</ul>" +
                "</html>";
    }

    public Skill skill() {
        return Skill.MAGIC_POWER;
    }

    public boolean canBeSold() {
        return true;
    }

    public boolean canBeBought() {
        return true;
    }

    public abstract ImageAsset icon();

    @Override
    public int hashCode() {
        return Objects.hash(name());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Spell s) {
            return Objects.equals(s.name(), name());
        }
        return false;
    }

    public boolean hasSelected() {
        return false;
    }

    public boolean hasTarget() {
        return true;
    }

    public abstract void cast(Unit selected, Unit target);

    @Override
    public String toString() {
        return name();
    }

}
