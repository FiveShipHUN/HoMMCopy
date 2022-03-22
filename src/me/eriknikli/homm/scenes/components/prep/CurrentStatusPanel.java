package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.gameplay.Hero;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Itt látható a jelenlegi állapota a hősnek
 */
public class CurrentStatusPanel extends JPanel {

    private final UnitListPanel units;
    private final SpellsListPanel spells;
    private final JLabel gold;
    private Hero hero;

    public CurrentStatusPanel(Hero hero) {
        this.hero = hero;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = c.weighty = 0;
        c.insets = new Insets(5, 5, 5, 5);
        var lbl = new JLabel(hero.name);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        c.anchor = GridBagConstraints.PAGE_START;
        add(lbl, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weightx = c.weighty = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        add(gold = new JLabel("Gold: " + hero.gold()), c);
        gold.setHorizontalAlignment(SwingConstants.CENTER);
        gold.setHorizontalTextPosition(SwingConstants.CENTER);
        gold.setFont(new Font("Arial", Font.BOLD, 15));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        //c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(units = new UnitListPanel(hero), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 1;
        //c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(10, 10, 10, 10);
        add(spells = new SpellsListPanel(hero), c);
    }

    public void _update() {
        units._update();
        spells._update();
        gold.setText("Gold: " + hero.gold());

    }

}
