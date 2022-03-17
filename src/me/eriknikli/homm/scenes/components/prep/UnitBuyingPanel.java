package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.data.Registry;

import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * Itt lehet egységeket venni
 */
public class UnitBuyingPanel extends JPanel {

    public UnitBuyingPanel() {
        var layout = new GridLayout();
        layout.setHgap(5);
        layout.setVgap(layout.getHgap());
        layout.setColumns(3);
        layout.setRows(3);
        setLayout(layout);
        for (var ut : Registry.uTypes()) {
            add(new SpecificUnitBuyingPanel(ut));
        }
        add(new BuySpellsPanel());
        add(new ImproveSkillsPanel());
    }

}
