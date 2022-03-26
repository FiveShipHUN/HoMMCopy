package me.eriknikli.homm.gameplay.army.types;

import me.eriknikli.homm.data.ImageAsset;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.components.game.Tile;
import me.eriknikli.homm.utils.Range;

import java.util.HashSet;

/**
 * Egységek típusát leíró osztály, minden egységtípusnak saját osztálya lesz
 * Lehetne ENUM is, de úgy látom, hogy a speciális képességeket külön osztályokkal könnyebb megvalósítani
 */
public abstract class UnitType {

    /**
     * @return név
     */
    public abstract String name();

    /**
     * @return leírás
     */
    public abstract String description();

    /**
     * @return ár "darabonként"
     */
    public abstract int price();

    /**
     * @return sebzés "darabonként"
     */
    public abstract Range damage();

    /**
     * @return max / kezdeti életerő "darabonként"
     */
    public abstract double maxHealth();

    /**
     * @return sebesség
     */
    public abstract int speed();

    /**
     * Prioritás / Kezdeményezés, ez alapján dől el, melyik egység támad hamarabb
     */
    public abstract int priority();

    /**
     * @return Ikonhoz kép, ami a mezőn látszódik
     */
    public abstract ImageAsset image();

    /**
     * Egységet készít ebből a típusból
     *
     * @param amount mennyiség
     * @return egy új egység adott mennyiséggel ezzel a típussal
     */
    public final Unit createUnit(int amount) {
        return new Unit(this, amount);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnitType) {
            return getClass().equals(obj.getClass());
        }
        return false;
    }

    public boolean canCounterAttack(Unit u) {
        return !u.wasAttacked();
    }

    public HashSet<Unit> validTargets(Unit who) {
        var set = new HashSet<Unit>();
        for (Tile t : who.tile().neighbors()) {
            if (t.unit() != null && !t.unit().isWith(who)) {
                set.add(t.unit());
            }
        }
        return set;
    }

    public void onStartTurn(Unit who) {

    }

    public void onStartRound(Unit who) {

    }

    public HashSet<Unit> otherTargets(Unit target) {
        HashSet<Unit> set = new HashSet<>();
        set.add(target);
        return set;
    }

    public String helpTxt() {
        return "<html>" +
                "<h2>" +
                name() +
                "</h2>" +
                "<p>" +
                description() +
                "</p>" +
                "<ul>" +
                "<li>" +
                "Price / soldier: " + price() + " gold" +
                "</li>" +
                "<li>" +
                "Damage: " + damage().toStringNoBrackets(1) +
                "</li>" +
                "<li>" +
                "Starting HP: " + maxHealth() +
                "</li>" +
                "<li>" +
                "Speed: " + speed() +
                "</li>" +
                "<li>" +
                "Priority: " + priority() +
                "</li>" +
                "</ul>" +
                "</html>";
    }
}
