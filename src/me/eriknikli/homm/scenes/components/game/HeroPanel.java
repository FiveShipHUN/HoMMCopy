package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.gameplay.spells.Spell;
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
    private final JLabel mana;
    private JButton endTurnBtn;
    private final HashSet<UnitInfoPanel> units = new HashSet<>();
    HashSet<ActivateSpellPanel> spells = new HashSet<>();
    private final GameScene scene;

    /**
     * Létrehozza a Hero panelt
     *
     * @param hero a hero, aminek panelt készít
     */
    public HeroPanel(Hero hero, GameScene scene) {
        int i = 0;
        this.scene = scene;
        this.hero = hero;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i++;
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.PAGE_START;
        var lbl = new JLabel(hero.name);
        lbl.setToolTipText(hero.helpTxt());
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setVerticalTextPosition(SwingConstants.CENTER);
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        add(lbl, c);
        mana = new JLabel("Mana: " + hero.getMana());
        c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = i++;
        c.weightx = 0;
        mana.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        mana.setForeground(new Color(141, 221, 232));
        add(mana, c);
        setBackground(Color.DARK_GRAY);
        for (var unit : hero().units()) {
            addUnitInfo(unit, i);
            i++;
        }
        for (var s : Registry.spells()) {
            if (hero.knowsSpell(s)) {
                addSpell(s, i);
                i++;
            }
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
        _update();
    }

    private void addSpell(Spell s, int i) {
        ActivateSpellPanel panel = new ActivateSpellPanel(hero, s, scene);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        add(panel, c);
        spells.add(panel);
    }

    private void addUnitInfo(Unit unit, int i) {
        UnitInfoPanel info = new UnitInfoPanel(unit);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
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
        mana.setText("Mana: " + hero.getMana());
        for (var u : units) {
            u._update();
        }
        for (var s : spells) {
            s._update();
        }
    }

}
