package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.spells.Spell;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

public class SpellsListPanel extends JPanel {

    private Hero h;
    private List<Component> components = new ArrayList<>();

    public SpellsListPanel(Hero h) {
        this.h = h;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel lbl = new JLabel("Spells");
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        c.gridx = c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        add(lbl, c);
        _update();
    }

    private void addSpell(Spell s, int i) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i * 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1;
        JLabel lbl = new JLabel();
        lbl.setText(s.name());
        lbl.setIcon(s.icon().iconByHeight(16));
        lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lbl, c);
        JLabel help = new JLabel();
        help.setToolTipText(s.helpTxt(h.skill(s.skill())));
        help.setIcon(Registry.I_HELP.iconByHeight(16));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = i * 2;
        c.weighty = 1;
        c.insets = new Insets(3, 3, 3, 3);
        c.anchor = GridBagConstraints.LINE_START;
        add(help, c);
        var btn = new JButton("Sell (" + s.price() + " gold)");
        btn.setEnabled(s.canBeSold());
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i * 2 + 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(btn, c);
        components.add(lbl);
        components.add(help);
        components.add(btn);
        btn.addActionListener(e -> {
            h.unlearnSpell(s);
            h.addGold(s.price());
            HoMM.update();
        });
    }

    public void _update() {
        for (var lbl : components) {
            remove(lbl);
        }
        components.clear();
        int i = 1;
        for (Spell s : Registry.spells()) {
            if (h.knowsSpell(s)) {
                addSpell(s, i);
                i++;
            }
        }
    }

}
