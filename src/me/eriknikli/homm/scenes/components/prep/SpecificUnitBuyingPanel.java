package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.data.Registry;
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
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setText(ut.name());
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setIcon(ut.image().iconByHeight(Integer.max(5, lbl.getHeight())));
        add(lbl, c);
        var help = new JLabel();
        // help.setIcon(Registry.I_HELP.iconByHeight(lbl.getHeight()));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                lbl.setIcon(ut.image().iconByHeight(Integer.max(5, lbl.getHeight())));
                help.setIcon(Registry.I_HELP.iconByHeight(Integer.max(5, (int) (lbl.getHeight() * .5f))));

            }
        });
        help.setToolTipText(ut.helpTxt());
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        add(help, c);
        c = new GridBagConstraints();
        var subtractOne = new JButton("-1");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(subtractOne, c);
        var subtractTen = new JButton("-10");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        add(subtractTen, c);
        var subtractFifty = new JButton("-50");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        add(subtractFifty, c);
        var amountLbl = new JLabel("Amount: 0");
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(amountLbl, c);
        var costLbl = new JLabel("Cost: 0 gold");
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        add(costLbl, c);
        var buyBtn = new JButton("Buy");
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        add(buyBtn, c);
        var addOne = new JButton("+1");
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(addOne, c);
        var addTen = new JButton("+10");
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        add(addTen, c);
        var addFifty = new JButton("+50");
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        add(addFifty, c);
    }

}
