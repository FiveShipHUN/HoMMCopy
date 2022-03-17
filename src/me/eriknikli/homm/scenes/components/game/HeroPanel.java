package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.Hero;

import javax.swing.*;
import java.awt.*;

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
        setLayout(new GridLayout(2, 1));
        var lbl = new JLabel("Player");
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setVerticalTextPosition(SwingConstants.CENTER);
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        add(lbl);
        setBackground(Color.DARK_GRAY);
    }

    /**
     * @return visszaadja a hőst, akié a panel
     */
    public Hero hero() {
        return hero;
    }

}
