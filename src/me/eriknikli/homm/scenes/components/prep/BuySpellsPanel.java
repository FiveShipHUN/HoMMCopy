package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.spells.Spell;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

/**
 * Itt lehet spelleket venni
 */
public class BuySpellsPanel extends JPanel {

    private List<Component> components = new ArrayList<>();
    private Hero h;

    public BuySpellsPanel(Hero h) {
        this.h = h;
        var layout = new GridBagLayout();
        setLayout(layout);
        var c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        JLabel lbl = new JLabel("Spells");
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridwidth = 3;
        add(lbl, c);
        _update();
    }

    private void addSpell(Spell s, int i) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weighty = 1;
        JLabel lbl = new JLabel();
        lbl.setText(s.name());
        lbl.setIcon(s.icon().iconByHeight(16));
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        add(lbl, c);
        JLabel help = new JLabel();
        help.setToolTipText(s.helpTxt(h.skill(s.skill())));
        help.setIcon(Registry.I_HELP.iconByHeight(16));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = i;
        c.weighty = 1;
        c.insets = new Insets(3, 3, 3, 3);
        c.anchor = GridBagConstraints.PAGE_START;
        add(help, c);
        var btn = new JButton("Buy (" + s.price() + " gold)");
        btn.setEnabled(!h.knowsSpell(s) && h.canAfford(s.price()));
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = i;
        c.weighty = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        add(btn, c);
        components.add(lbl);
        components.add(help);
        components.add(btn);
        btn.addActionListener(e -> {

            if (!h.knowsSpell(s) && h.subtractGold(s.price())) {
                h.learnSpell(s);
                HoMM.update();
            }
        });
    }

    public void _update() {
        for (var c : components) {
            remove(c);
        }
        components.clear();
        int i = 1;
        for (Spell s : Registry.spells()) {
            if (s.canBeBought()) {
                addSpell(s, i);
                i++;
            }
        }
    }

}
