package me.eriknikli.homm.gameplay;

/**
 * Ezt a hős AI irányítja
 * Ennek az AI-nak nincsenek Gold korlátjai, főleg Enemy Creator-hoz készül
 * Skillekből is végtelenségig tanulhat
 */
public class OPAIHero extends AIHero {

    public OPAIHero() {
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

    @Override
    public boolean canImprove(Skill s) {
        return true;
    }
}
