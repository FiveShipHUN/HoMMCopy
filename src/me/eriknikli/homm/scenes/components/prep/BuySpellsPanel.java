package me.eriknikli.homm.scenes.components.prep;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Itt lehet spelleket venni
 */
public class BuySpellsPanel extends JPanel {

    public BuySpellsPanel() {
        var layout = new GridLayout();
        setLayout(layout);
        add(new JLabel(""));
    }

}
