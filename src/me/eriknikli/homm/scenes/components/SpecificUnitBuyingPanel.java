package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.gameplay.army.types.UnitType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Adott típusú egységet lehet itt venni
 */
public class SpecificUnitBuyingPanel extends JPanel {


    public SpecificUnitBuyingPanel(UnitType ut) {
        setLayout(new GridBagLayout());
        GridBagConstraints c;
        c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        JLabel lbl = new JLabel(ut.image().iconByHeight(Integer.max(32, getHeight())));
        lbl.setText(ut.name());
        add(lbl, c);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                lbl.setIcon(ut.image().iconByHeight(Integer.max(32, getHeight())));
            }
        });
    }

}
