package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.army.types.UnitType;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

/**
 * Egységeket listázza
 */
public class UnitListPanel extends JPanel {

    private final Hero h;
    private final HashMap<UnitType, JLabel> lbls = new HashMap<>();
    private final HashMap<UnitType, JButton> btns = new HashMap<>();

    public UnitListPanel(Hero h) {
        this.h = h;
        setLayout(new GridBagLayout());
        var lbl = new JLabel("Units");
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5, 5, 5, 5);
        add(lbl, c);
        c = new GridBagConstraints();
        int i = 1;
        for (var ut : Registry.uTypes()) {
            c.gridx = 0;
            c.gridy = i;
            c.anchor = GridBagConstraints.FIRST_LINE_END;
            c.insets = new Insets(3, 3, 3, 3);
            c.weighty = 1;
            var u = h.unitOf(ut);
            lbls.put(ut, lbl = new JLabel(""));
            lbl.setIcon(ut.image().iconByHeight(16));
            add(lbl, c);
            var btn = new JButton("Sell All");
            c.gridx = 1;
            c.insets = new Insets(3, 3, 3, 3);
            add(btn, c);
            btns.put(ut, btn);
            btn.addActionListener(e -> {
                var un = h.unitOf(ut);
                h.addGold(un.amount() * un.type().price());
                h.removeUnit(un);
                HoMM.update();
            });
            i++;
        }
        _update();
    }

    public void _update() {
        for (var kvp : lbls.entrySet()) {
            var ut = kvp.getKey();
            var u = h.unitOf(ut);
            var lbl = lbls.get(kvp.getKey());
            lbl.setText(ut.name() + ": " + (u == null ? "0" : "" + u.amount()));
            lbl.setFont(new Font("Arial", Font.PLAIN, 20));
            var btn = btns.get(kvp.getKey());
            btn.setEnabled(u != null && u.amount() > 0);
        }
    }

}
