package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.gameplay.Hero;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Itt látható a jelenlegi állapota a hősnek
 */
public class CurrentStatusPanel extends JPanel {

    private Hero hero;

    public CurrentStatusPanel(Hero hero) {
        this.hero = hero;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        var lbl = new JLabel(hero.name);
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        add(lbl, c);
    }

}
