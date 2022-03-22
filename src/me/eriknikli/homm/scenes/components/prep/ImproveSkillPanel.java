package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.Skill;

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
 * Itt lehet a skilleket állítani
 */
public class ImproveSkillPanel extends JPanel {

    private List<Component> list = new ArrayList<>();
    private Hero h;

    public ImproveSkillPanel(Hero h) {
        this.h = h;
        setLayout(new GridBagLayout());
        _update();
        var c = new GridBagConstraints();
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        var lbl = new JLabel("Skills");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
        add(lbl, c);
    }

    private void addSkill(final Skill s, final int i) {
        var subtractBtn = new JButton("-");
        subtractBtn.setEnabled(h.canDeprove(s));
        subtractBtn.addActionListener(e -> h.decreaseSkill(s));
        var addBtn = new JButton("+");
        addBtn.setEnabled(h.canImprove(s));
        addBtn.addActionListener(e -> h.increaseSkill(s));
        var lbl = new JLabel(s.display() + " (" + h.skill(s) + ")");
        lbl.setToolTipText(s.desc());
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = i;
        add(subtractBtn, c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = i;
        c.insets = new Insets(3, 3, 3, 3);
        add(lbl, c);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = i;
        add(addBtn, c);
        list.add(lbl);
        list.add(subtractBtn);
        list.add(addBtn);
    }

    public void _update() {
        for (var c : list) {
            remove(c);
        }
        list.clear();
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        var lbl = new JLabel("Cost: " + h.nextSkillPrice() + "");
        lbl.setFont(new Font("Arial", Font.ITALIC, 20));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        list.add(lbl);
        add(lbl, c);
        int i = 2;
        for (Skill s : Skill.values()) {
            addSkill(s, i);
            i++;
        }
        c = new GridBagConstraints();
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = i;
        c.fill = GridBagConstraints.HORIZONTAL;
        var btn = new JButton("Reset Skills");
        btn.addActionListener(e -> {
            h.resetSkills();
        });
        add(btn, c);
    }

}
