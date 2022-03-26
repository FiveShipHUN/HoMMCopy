package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.GameScene;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;

/**
 * Hősről tárolt információkat kijelző panel
 */
public class HeroPanel extends JPanel {

    /**
     * A hős
     */
    private final Hero hero;
    private JButton endTurnBtn;
    private HashSet<UnitInfoPanel> units = new HashSet<>();

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
        int i = 1;
        for (var unit : hero().units()) {
            addUnitInfo(unit, i);
            i++;
        }
        if (hero instanceof PlayerHero) {
            endTurnBtn = new JButton("Fight!");
            c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = i;
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

    private void addUnitInfo(Unit unit, int i) {
        UnitInfoPanel info = new UnitInfoPanel(unit);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i;
        add(info, c);
        units.add(info);
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

    public void _update() {
        for (var u : units) {
            u._update();
        }
    }

}
