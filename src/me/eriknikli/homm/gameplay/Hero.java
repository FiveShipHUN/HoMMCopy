package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.gameplay.army.Unit;

import java.util.HashSet;

/**
 * Hős, abstract, mert csak PlayerHero és AIHero létezhet, "sima" hős nem
 */
public abstract class Hero {

    /**
     * Név
     */
    public final String name;
    /**
     * Jelenlegi arany
     */
    private int gold;

    /**
     * Unitjai a hősnek
     */
    private final HashSet<Unit> units = new HashSet<>();

    /**
     * Hőst hoz létre, magába nem használható
     * @param name a hős neve
     * @param startGold a hős ennyi golddal kezd
     */
    protected Hero(String name, int startGold) {
        this.name = name;
        setGold(startGold);

    }


    /**
     * @return jelenlegi aranymennyiség
     */
    public int gold() {
        return gold;
    }

    /**
     * @return a unitjai a hősnek
     */
    public HashSet<Unit> units() {
        return units;
    }

    /**
     * Hozzáadja az adott unit-ot a hőshöz
     * Ha van hasonló típus már akkor merge-li
     */
    public void addUnit(Unit unit) {
        for (Unit u : units()) {
            if (u.type().equals(unit.type())) {
                u.addAmount(unit.amount());
                return;
            }
        }
        units.add(unit);
    }

    /**
     * Beállít adott mennyiségű aranyat ennek a hősnek. A gold soha nem megy 0 alá, és nem történik változás, ha ezt szeretnénk tenni.
     * (Tehát setGold(-1)-nek érdemi hatása nincs, nem lesz az aktuális gold se 0, se -1)
     *
     * @param amount mennyiség, amit szeretnénk beállítani
     * @return true ha amount >= 0, false ha amount < 0
     */
    public boolean setGold(int amount) {
        if (amount < 0) {
            return false;
        }
        gold = amount;
        return true;
    }

    /**
     * Hozzáad a jelenlegi aranyhoz
     *
     * @param amount a mennyiség, amit szeretnénk hozzáadni, lehet negatív is
     * @return sikerült-e adott mennyiségű aranyat hozzáadni, ha negatívba mennénk át, akkor false és érdemi változás nem történik
     */
    public boolean addGold(int amount) {
        return setGold(gold() + amount);
    }

    /**
     * Kivon a jelenlegi aranyból, shortcut {@code addGold(-amount)}-ra
     *
     * @param amount a mennyiség, amennyit szeretnénk hozzáadni, lehet negatív is
     * @return sikerült-e adott mennyiségű aranyat levonnunk, ha negatívba menne át, akkor nem történik semmi
     */
    public boolean subtractGold(int amount) {
        return addGold(-amount);
    }

}
