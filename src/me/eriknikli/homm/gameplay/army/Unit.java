package me.eriknikli.homm.gameplay.army;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.army.types.UnitType;
import me.eriknikli.homm.scenes.components.Tile;

/**
 * Egy egység
 */
public class Unit {

    /**
     * Az egység típusa
     */
    private final UnitType type;
    /**
     * Mennyi katona van az adott egységben
     */
    private int amount;
    /**
     * Életereje az egységnek
     */
    private double health;

    /**
     * Mező ahol található
     */
    private Tile tile;

    /**
     * Ennek a hősnek a birtokában van
     */
    private Hero hero;

    /**
     * Kezdő életereje a hősnek
     */
    private double startHP;

    /**
     * Létrehoz egy új egységet
     *
     * @param type az egyésg típusa, Registry.UT_*
     * @param a    mennyiség
     * @throws IllegalArgumentException ha az amount nem pozitív
     */
    public Unit(UnitType type, int a) {
        this.type = type;
        amount = a;
        health = type().maxHealth() * a;
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
    }

    /**
     * @return a katonák száma az egységben
     */
    public int amount() {
        return amount;
    }

    /**
     * Hozzáad ennyi katonát az egységhez
     *
     * @param amount katonák száma
     */
    public void addAmount(int amount) {
        this.amount += amount;
        this.health += amount * type.maxHealth();
    }

    /**
     * @return az egység típusa
     */
    public UnitType type() {
        return type;
    }

    /**
     * Lesebzi az egységet
     *
     * @param dam a sebzés mértéke
     */
    public void dealDamage(double dam) {
        health -= dam;
        amount = (int) (health / type().maxHealth());
        if (health <= 0) {
            die();
        }
    }

    /**
     * Meghal az adott egység, és a program "feltakarítja" a maradékait (pl. mezőről eltünteti és a hőstől is, stb...)
     */
    public void die() {

    }

    /**
     * @return a hős jelenlegi életerejét
     */
    public double health() {
        return health;
    }

    /**
     * @return a hőst
     */
    public Hero hero() {
        return hero;
    }



}
