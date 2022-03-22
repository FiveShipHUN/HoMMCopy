package me.eriknikli.homm.scenes.components.prep;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.gameplay.army.types.UnitType;
import me.eriknikli.homm.scenes.components.InputField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Adott típusú egységet lehet itt venni
 */
public class SpecificUnitBuyingPanel extends JPanel {


    private final JButton buyBtn;
    private final JButton subtractOne;
    private final JButton subtractTen;
    private final JButton subtractFifty;
    private final JLabel costLbl;
    private final InputField amountLbl;
    private final JButton addTen;
    private final JButton clearBtn;
    private final JButton addOne;
    private final JButton addFifty;
    private final UnitType ut;
    private final Hero hero;
    private int amountToBuy;

    public SpecificUnitBuyingPanel(UnitType ut, Hero hero) {
        this.ut = ut;
        this.hero = hero;
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
        subtractOne = new JButton("-1");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(subtractOne, c);
        subtractTen = new JButton("-10");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        add(subtractTen, c);
        subtractFifty = new JButton("-50");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        add(subtractFifty, c);
        //amountLbl = new JLabel("Amount: 0");
        amountLbl = new InputField("Soldiers:", true);
        amountLbl.field.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        add(amountLbl, c);
        costLbl = new JLabel("Cost: 0 gold");
        costLbl.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        add(costLbl, c);
        clearBtn = new JButton("Clear");
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        add(clearBtn, c);
        addOne = new JButton("+1");
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(addOne, c);
        addTen = new JButton("+10");
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(10, 10, 10, 10);
        add(addTen, c);
        addFifty = new JButton("+50");
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(10, 10, 10, 10);
        add(addFifty, c);
        buyBtn = new JButton("Buy");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        add(buyBtn, c);
        amountToBuy = 0;
        _update();
        subtractFifty.addActionListener(e -> addXToAmount(-50));
        subtractTen.addActionListener(e -> addXToAmount(-10));
        subtractOne.addActionListener(e -> addXToAmount(-1));
        addOne.addActionListener(e -> addXToAmount(1));
        addTen.addActionListener(e -> addXToAmount(10));
        addFifty.addActionListener(e -> addXToAmount(50));
        amountLbl.field.setBackground(Color.RED);
        amountLbl.field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boolean good = true;
                try {
                    amountToBuy = Integer.parseInt(amountLbl.field.getText());
                } catch (Exception ex) {
                    amountToBuy = 0;
                    good = false;
                }
                try {
                    good = _update() && good;
                } catch (Exception ex) {
                    good = false;
                }
                amountLbl.field.setBackground(!good ? Color.RED : Color.WHITE);
                buyBtn.setEnabled(good && buyBtn.isEnabled());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                insertUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                insertUpdate(e);
            }
        });
        clearBtn.addActionListener(e -> addXToAmount(-amountToBuy));
        buyBtn.setEnabled(false);
        buyBtn.addActionListener(e -> {
            if (amountToBuy > 0) {
                if (hero.subtractGold(ut.price() * amountToBuy)) {
                    Unit unit = new Unit(ut, amountToBuy);
                    hero.addUnit(unit);
                    amountToBuy = 0;
                    HoMM.update();
                }
            }
        });
    }

    public void addXToAmount(int x) {
        amountToBuy += x;
        HoMM.update();
    }

    /**
     * @return true ha pirossal ki kell festenie az input fieldet
     */
    public boolean _update() {
        subtractFifty.setEnabled(amountToBuy >= 50);
        subtractTen.setEnabled(amountToBuy >= 10);
        subtractOne.setEnabled(amountToBuy >= 1);
        addOne.setEnabled(hero.canAfford(ut.price() * (amountToBuy + 1)));
        addTen.setEnabled(hero.canAfford(ut.price() * (amountToBuy + 10)));
        addFifty.setEnabled(hero.canAfford(ut.price() * (amountToBuy + 50)));
        costLbl.setText("Cost: " + (amountToBuy * ut.price()) + " gold");
        buyBtn.setEnabled(hero.canAfford(ut.price() * amountToBuy));
        if (amountToBuy < 0) {
            return false;
        }
        try {
            amountLbl.field.setText("" + amountToBuy);
        } catch (Exception e) {
            return true;
        }
        return hero.canAfford(amountToBuy * ut.price());
    }

}
