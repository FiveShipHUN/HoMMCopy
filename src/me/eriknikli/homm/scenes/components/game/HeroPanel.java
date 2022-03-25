package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.scenes.GameScene;

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
    private JButton endTurnBtn;

    /**
     * Létrehozza a Hero panelt
     *
     * @param hero a hero, aminek panelt készít
     */
    public HeroPanel(Hero hero, GameScene scene) {
        this.hero = hero;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.PAGE_START;
        var lbl = new JLabel(hero.name);
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setVerticalTextPosition(SwingConstants.CENTER);
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        add(lbl, c);
        setBackground(Color.DARK_GRAY);
        if (hero instanceof PlayerHero) {
            endTurnBtn = new JButton("Fight!");
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            add(endTurnBtn, c);
            c.insets = new Insets(10, 10, 10, 10);
            endTurnBtn.addActionListener(e -> {
                if (scene.isInPrepPhase()) {
                    endTurnBtn.setEnabled(false);
                    endTurnBtn.setVisible(false);
                    endTurnBtn.setText("End Turn");
                    scene.startGame();
                } else {
                    scene.getBoard().nextTurn();
                }
            });
        }
    }

    /**
     * @return visszaadja a hőst, akié a panel
     */
    public Hero hero() {
        return hero;
    }

    public JButton endTurnBtn() {
        return endTurnBtn;
    }

}
