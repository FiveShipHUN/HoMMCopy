package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.spells.Attack;
import me.eriknikli.homm.gameplay.spells.Spell;
import me.eriknikli.homm.scenes.GameScene;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ActivateSpellPanel extends JPanel {

    private final Spell s;
    private final JButton button;
    private final Hero hero;
    private final GameScene scene;

    public ActivateSpellPanel(Hero hero, Spell s, GameScene scene) {
        this.scene = scene;
        this.s = s;
        this.hero = hero;
        setLayout(new GridBagLayout());
        button = new JButton();
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        if (s instanceof Attack) {
            button.setText("Attack");
        } else {
            button.setText("Cast " + s.name());
        }
        button.addActionListener(e -> {
            if (!s.hasTarget()) {
                s.cast(scene.getBoard().selected().unit(), null);
            } else {

                for (var d : scene.playerPanel().spells) {
                    d.reset();
                }
                setBackground(Color.RED);
                scene.left().setCastingSpell(s);
            }
        });
        button.setIcon(s.icon().iconByHeight(32));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        add(button, c);
        setBackground(Color.DARK_GRAY);
        _update();
    }

    public void reset() {
        setBackground(Color.DARK_GRAY);
    }

    public void _update() {
        button.setEnabled(hero.getMana() >= s.mana() && scene.isInGame() && hero instanceof PlayerHero && hero.canCastSpell());
    }

}
