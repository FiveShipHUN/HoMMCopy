package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.army.Unit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;

public class UnitInfoPanel extends JPanel {

    private Unit unit;
    private JLabel name;
    private JLabel hp;

    public UnitInfoPanel(Unit unit) {
        this.unit = unit;
        setLayout(new GridBagLayout());
        name = new JLabel(unit.type().name());
        name.setIcon(unit.type().image().iconByHeight(32));
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(name, c);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        hp = new JLabel();
        add(hp, c);
        _update();
    }

    public void _update() {
        if (unit.isAlive()) {
            var f = NumberFormat.getInstance();
            f.setMaximumFractionDigits(0);
            f.setMinimumFractionDigits(0);
            hp.setText(f.format(unit.health()) + " / " + f.format(unit.startHP()));
        } else {
            hp.setText("Dead");
        }
    }

}
