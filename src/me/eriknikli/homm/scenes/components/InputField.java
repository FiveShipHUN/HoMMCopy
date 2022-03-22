package me.eriknikli.homm.scenes.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Egy panel amibe van egy Label és egy Text Field
 */
public class InputField extends JPanel {

    public final JTextField field;
    public final JLabel lbl;

    public InputField(String text, boolean inNewLine) {
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        this.lbl = new JLabel(text);
        add(lbl, c);
        c = new GridBagConstraints();
        c.gridx = inNewLine ? 0 : 1;
        c.gridy = inNewLine ? 1 : 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.field = new JTextField();
        add(field, c);
    }
}
