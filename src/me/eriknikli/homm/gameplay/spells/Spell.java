package me.eriknikli.homm.gameplay.spells;

import me.eriknikli.homm.data.ImageAsset;

import java.util.Objects;

public abstract class Spell {

    public abstract String name();

    public abstract int price();

    public abstract int mana();

    public abstract String desc(Object[] params);

    public String helpTxt(Object... params) {
        return "<html>" +
                "<h2>" +
                name() +
                "</h2>" +
                "<p>" +
                desc(params) +
                "</p>" +
                "<ul>" +
                "<li>" +
                "Price: " + price() + " gold" +
                "</li>" +
                "<li>" +
                "Mana: " + mana() +
                "</li>" +
                "</ul>" +
                "</html>";
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
}
