package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.gameplay.army.types.UnitType;
import me.eriknikli.homm.gameplay.spells.Spell;

import java.util.HashMap;
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
     * Spellek, amiket ismer a hős
     */
    private final HashSet<Spell> spells = new HashSet<>();

    /**
     * Skillek
     */
    private final HashMap<Skill, Integer> skills = new HashMap<>();

    /**
     * Megtanult skillek száma eddig
     */
    private int learntSkills = 0;

    /**
     * Hőst hoz létre, magába nem használható
     *
     * @param name      a hős neve
     * @param startGold a hős ennyi golddal kezd
     */
    protected Hero(String name, int startGold) {
        this.name = name;
        setGold(startGold);
        for (var s : Skill.values()) {
            skills.put(s, 1);
        }
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
     * @return ha sikerült megtanulnia a képességet
     */
    public boolean learnSpell(Spell s) {
        if (!knowsSpell(s)) {
            spells.add(s);
            return true;
        } else {
            return false;
        }
    }

    public boolean knowsSpell(Spell o) {
        for (Spell s : spells) {
            if (o.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param type az egység típusa amit keresünk
     * @return visszaadja az adott típusú egyésg objektumot, amelyet a hős birtokol, null ha még ilyen nem létezik
     */
    public Unit unitOf(UnitType type) {
        for (var u : units) {
            if (u.type().equals(type)) {
                return u;
            }
        }
        return null;
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

    /**
     * @param gold aranymennyiség
     * @return van-e ennyi aranya ennek a hősnek?
     */
    public boolean canAfford(int gold) {
        return this.gold >= gold;
    }

    /**
     * Megtanult spell árának visszakérése, és annak elfelejtése
     *
     * @param s a spell
     */
    public void unlearnSpell(Spell s) {
        spells.remove(s);
    }

    /**
     * Egy adott egység kitörlése a hős egységei közül
     *
     * @param u az egység
     */
    public void removeUnit(Unit u) {
        units.remove(u);
    }

    /**
     * @param s az adott skill
     * @return mekkora szintű ezen a skillen, >=1-nek kell lennie
     */
    public int skill(Skill s) {
        if (s == null) {
            return 0;
        }
        return skills.getOrDefault(s, 0);
    }

    /**
     * @return Tud-e skillt fejleszteni
     */
    public boolean canImprove(Skill s) {
        return canAfford(nextSkillPrice()) && skill(s) < 10;
    }

    /**
     * @param s a skill
     * @return Tud-e az adott skillből visszavenni
     */
    public boolean canDeprove(Skill s) {
        return skill(s) > 1;
    }

    /**
     * Skillből vesz vissza eggyel és visszaadja a pénzt
     *
     * @param s a skill
     */
    public void decreaseSkill(Skill s) {
        if (canDeprove(s)) {
            skills.put(s, skill(s) - 1);
            learntSkills--;
            addGold(nextSkillPrice());
        }
        HoMM.update();
    }

    /**
     * Javít az adott skillből ha tud, és visszaadja az aranyat
     *
     * @param s a skill
     */
    public void increaseSkill(Skill s) {
        if (canImprove(s)) {
            skills.put(s, skill(s) + 1);
            subtractGold(nextSkillPrice());
            learntSkills++;
        }
        HoMM.update();
    }

    public int nextSkillPrice() {
        return skillPriceN(learntSkills);
    }

    private int skillPriceN(int n) {
        if (n == 0) {
            return 5;
        }
        return (int) Math.ceil(skillPriceN(n - 1) * 1.1);
    }

    public void resetSkills() {
        for (Skill s : Skill.values()) {
            while (canDeprove(s)) {
                decreaseSkill(s);
            }
        }
    }


}
