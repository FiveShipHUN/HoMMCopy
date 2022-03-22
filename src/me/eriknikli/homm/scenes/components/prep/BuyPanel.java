package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Itt lehet egységeket venni
 */
public class BuyPanel extends JPanel {

    private final BuySpellsPanel spells;
    private final SpecificUnitBuyingPanel[] unit = new SpecificUnitBuyingPanel[5];

    public BuyPanel(Hero hero) {
        var layout = new GridBagLayout();
        setLayout(layout);
        int i = 0;
        for (var ut : Registry.uTypes()) {
            addComponent(unit[i] = new SpecificUnitBuyingPanel(ut, hero), i);
            i++;
        }
        addComponent(spells = new BuySpellsPanel(hero), i);

        // add(skills = new ImproveSkillsPanel(), c);
        // i++;
    }

    public void _update() {
        spells._update();
        for (var v : unit) {
            v._update();
        }
    }

    public void addComponent(Component co, int i) {
        var c = new GridBagConstraints();
        c.gridx = i % 3;
        c.gridy = i / 3;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = c.weighty = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        add(co, c);
    }

}
