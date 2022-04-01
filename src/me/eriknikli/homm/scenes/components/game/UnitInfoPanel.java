package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.army.Unit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;

/**
 * Egy egység leírását tartalmazó panel
 */
public class UnitInfoPanel extends JPanel {

    private Unit unit;
    private boolean showOwner;
    private JLabel name;
    private JLabel hp;

    public UnitInfoPanel(Unit u) {
        this(u, false);
    }

    public UnitInfoPanel(Unit unit, boolean showOwner) {
        this.unit = unit;
        this.showOwner = showOwner;
        setLayout(new GridBagLayout());
        name = new JLabel("<html>" + unit.type().name() + (showOwner ? " <br>(<strong>" + unit.hero().name + "</strong>)" : "") + "</html>");
        name.setIcon(unit.type().image().iconByHeight(32));
        name.setFont(getFont().deriveFont(Font.BOLD));
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
            try {
                hp.setText("<html>" + f.format(unit.health()) + " / " + f.format(unit.startHP()) + "</html>");
            } catch (Exception e) {
            }
        } else {
            hp.setText("Dead");
        }
        setBackground(new Color((float) (1 - unit.health() / unit.startHP()), (float) (unit.health() / unit.startHP()), 0));
    }

}
