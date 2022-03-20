package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Itt lehet egységeket venni
 */
public class BuyPanel extends JPanel {

    public BuyPanel(Hero hero) {
        var layout = new GridBagLayout();
        setLayout(layout);
        int i = 0;
        for (var ut : Registry.uTypes()) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = i % 3;
            c.gridy = i / 3;
            c.insets = new Insets(5,5,5,5);
            add(new SpecificUnitBuyingPanel(ut, hero), c);
            i++;
        }
        add(new BuySpellsPanel());
        add(new ImproveSkillsPanel());
    }

}
