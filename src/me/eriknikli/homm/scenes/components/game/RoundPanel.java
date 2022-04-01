package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.army.Unit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Itt látható a kör állapota
 */
public class RoundPanel extends JPanel {

    private JLabel title;
    private GameBoard board;
    private List<Component> components = new ArrayList<>();

    public RoundPanel(GameBoard board) {
        this.board = board;
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        title = new JLabel("Round ");
        title.setFont(new Font("Times New Roman", Font.BOLD, 25));
        title.setForeground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(title, c);

        _update();
    }

    public void _update() {
        for (var c : components) {
            remove(c);
        }
        components.clear();
        title.setText("Round " + board.round());
        int i = 1;
        for (var unit : board.order()) {
            addUnitInfo(unit, i++);
        }
    }

    private void addUnitInfo(Unit unit, int i) {
        UnitInfoPanel info = new UnitInfoPanel(unit, true);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        add(info, c);
        var lbl = new JLabel(unit.priority() + "");
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbl.setBackground(Color.GRAY);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = i;
        add(lbl, c);
        components.add(info);
        components.add(lbl);
    }

}
