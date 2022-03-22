package me.eriknikli.homm.gameplay;

/**
 * Ezt a hős AI irányítja
 * Ennek az AI-nak nincsenek Gold korlátjai, főleg Enemy Creator-hoz készül
 */
public class OPAIHero extends Hero {

    public OPAIHero() {
        super("BOT", 1000);
    }

    @Override
    public boolean setGold(int amount) {
        return true;
    }

    @Override
    public boolean addGold(int amount) {
        return true;
    }

    @Override
    public boolean subtractGold(int amount) {
        return true;
    }

    @Override
    public boolean canAfford(int gold) {
        return true;
    }
}
