package me.eriknikli.homm.gameplay.army;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.Skill;
import me.eriknikli.homm.gameplay.army.types.Archer;
import me.eriknikli.homm.gameplay.army.types.UnitType;
import me.eriknikli.homm.scenes.GameScene;
import me.eriknikli.homm.scenes.components.game.GameBoard;
import me.eriknikli.homm.scenes.components.game.Tile;
import me.eriknikli.homm.utils.Disposable;
import me.eriknikli.homm.utils.Log;

import java.awt.Color;
import java.util.HashSet;

/**
 * Egy egység
 */
public class Unit implements Disposable {

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
     * Kezdő életereje az egységnek
     */
    private double startHP;

    /**
     * Meg volt-e támadva
     */
    private boolean wasAttacked = false;

    /**
     * Az előző pozíciója az egységnek
     */
    private Tile lastTile = null;

    /**
     * DEBUG-hoz, meg tudjam nézni a priority() értékét
     */
    @Deprecated
    private int DEBUG_priority;

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
        startHP = health = type().maxHealth() * a;
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }

    }

    /**
     * Ha elindul egy kör, akkor lesz meghívva ez a függvény
     */
    public void onTurnStarted() {
        type().onStartTurn(this);
    }

    /**
     * @return a katonák száma az egységben
     */
    public int amount() {
        return Math.max(0, amount);
    }

    /**
     * Hozzáad ennyi katonát az egységhez
     *
     * @param amount katonák száma
     */
    public void addAmount(int amount) {
        amount = Math.max(amount, 0);
        this.amount += amount;
        var hp = amount * type.maxHealth();
        this.health += hp;
        this.startHP += hp;
    }

    /**
     * @return az egység típusa
     */
    public UnitType type() {
        return type;
    }

    /**
     * Beállítja a hőst, ha hero() == null
     *
     * @param h a hős
     */
    public void setHero(Hero h) {
        if (hero() == null) {
            this.hero = h;
        }
    }

    /**
     * Lesebzi az egységet
     *
     * @param dam a sebzés mértéke
     */
    public void getDamage(double dam) {
        dam -= (1.0 - hero().skill(Skill.DEFENSE) / 20.0);
        health -= dam;
        amount = (int) Math.ceil(health / type().maxHealth());
        amount = Math.max(0, amount);
        if (health <= 0) {
            die();
        }
        health = Math.max(health, 0);
    }

    /**
     * Meghal az adott egység, a destruktort hívja meg
     */
    public void die() {
        health = 0;
        dispose();
    }

    /**
     * @return Meghalt-e az egység?
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Destruktor, ami elveszi a hőstől és a mezőről ezt a unitot
     */
    @Override
    public final void dispose() {
        onDispose();
        hero.removeUnit(this);
        tile().setUnit(null);
        tile = null;
        if (HoMM.game().scene() instanceof GameScene gs) {
            gs.getBoard().removeFromOrder(this);
        }
    }

    /**
     * Feltakarításkor fut le, ezt örököltesd, ha szeretnél eventet hook-olni ide, ne a diposet! (final miatt nem is kell engednie)
     */
    public void onDispose() {

    }

    /**
     * @return él-e még ez az egység?
     */
    public boolean isAlive() {
        return health > 0;
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

    /**
     * @return meg volt-e támadva az adott egyésg ebben a körben? Minden kör elején visszaáll false-ra
     */
    public boolean wasAttacked() {
        return wasAttacked;
    }

    /**
     * @return tud-e visszatámadni?
     */
    public boolean canCounterAttack() {
        return type().canCounterAttack(this) && amount > 0 && health > 0;
    }

    /**
     * @return lehetséges célpontok
     */
    public HashSet<Unit> validTargets() {
        return type().validTargets(this);
    }

    /**
     * @return a mezőt, amin a hős van
     */
    public Tile tile() {
        return tile;
    }

    /**
     * @param who az egység
     * @return megnézi, hogy az adott egység egy csapatba van-e a másikkal (ha nem, akkor lehet, hogy who null, ha nem, akkor viszont egyértelműen ellenfelek)
     */
    public boolean isWith(Unit who) {
        if (who == null) {
            return false;
        }
        return who.hero() == hero();
    }

    /**
     * Gyógyul a hős
     */
    public void heal(double amount, boolean canRevive) {
        health += amount;
        health = Math.min(startHP, health);
        if (!canRevive) {
            health = Math.min(health, this.amount * type.maxHealth());
        } else {
            this.amount = (int) (health / type.maxHealth());
        }

    }

    /**
     * @return a kezdeményezés attól függően, hogy a hősnek mekkora a morálja
     */
    public int priority() {
        return DEBUG_priority = type().priority() + hero().skill(Skill.MORAL);
    }

    /**
     * Ezt az egyésget elmozgatja
     *
     * @param tile a mező, amire mozgatjuk
     */
    public void moveTo(Tile tile) {
        tile().setUnit(null);
        lastTile = tile();
        tile.setUnit(this);
        setTile(tile);
    }

    /**
     * Használd a moveTo-t!
     *
     * @param tile a mező, amit beállítunk
     */
    @Deprecated
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public HashSet<Tile> inRange(GameBoard board) {
        return board.tilesInRange(tile(), this.type().speed(), false);
    }

    public void attack(Unit t, boolean ranged) {
        for (var target : t.type().otherTargets(t)) {
            if (!(type() instanceof Archer)) {
                ranged = false;
            }
            Log.debug("Target: " + target.type().name() + ", Attacker: " + type().name() + ", Ranged?: " + ranged);
            var dam = type().damage().random();
            dam *= amount();
            dam *= (1.0 + (hero().skill(Skill.ATTACK) / 10.0));
            target.getDamage(dam);
            if (!ranged) {
                if (target.canCounterAttack()) {
                    dam = target.type().damage().random();
                    dam *= target.amount();
                    dam *= (1.0 + (hero().skill(Skill.ATTACK) / 10.0));
                    dam *= 0.5;
                    getDamage(dam);
                    target.wasAttacked = true;
                }
            }
        }
    }

    public void onStartRound() {
        type().onStartRound(this);
        wasAttacked = false;
    }

    public Color color() {
        if (hero instanceof PlayerHero) {
            return new Color(8, 68, 6);
        }
        return new Color(91, 0, 0);
    }

    public double startHP() {
        return startHP;
    }
}
