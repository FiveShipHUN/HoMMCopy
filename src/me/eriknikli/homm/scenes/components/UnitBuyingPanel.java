package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.data.Registry;

import javax.swing.*;
import java.awt.*;

/**
 * Itt lehet egységeket venni
 */
public class UnitBuyingPanel extends JPanel {

    public UnitBuyingPanel() {
        setLayout(new GridLayout());
        for (var ut : Registry.uTypes()) {
            add(new SpecificUnitBuyingPanel(ut));
        }
    }

}
