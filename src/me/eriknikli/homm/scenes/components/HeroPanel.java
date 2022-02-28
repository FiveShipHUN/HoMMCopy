package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.gameplay.Hero;

import javax.swing.*;

/**
 * Hősről tárolt információkat kijelző panel
 */
public class HeroPanel extends JPanel {

    /**
     * A hős
     */
    private final Hero hero;

    public HeroPanel(Hero hero) {
        this.hero = hero;
    }

    /**
     * @return visszaadja a hőst, akié a panel
     */
    public Hero hero() {
        return hero;
    }

}
