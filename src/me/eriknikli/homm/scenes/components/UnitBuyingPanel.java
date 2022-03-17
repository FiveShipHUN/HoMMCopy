package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.data.Registry;

import javax.swing.*;
import java.awt.*;

/**
 * Itt lehet egységeket venni
 */
public class UnitBuyingPanel extends JPanel {

    public UnitBuyingPanel() {
        var layout = new GridLayout();
        layout.setHgap(5);
        layout.setVgap(layout.getHgap());
        layout.setColumns(1);
        layout.setRows(Registry.uTypes().length);
        setLayout(layout);
        for (var ut : Registry.uTypes()) {
            add(new SpecificUnitBuyingPanel(ut));
        }
    }

}
