package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.scenes.components.prep.BuyPanel;
import me.eriknikli.homm.scenes.components.prep.CurrentStatusPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Felkészülési szakaszban használt Scene
 */
public class PrepScene extends Scene {

    private final Hero hero;
    private final BuyPanel buy;
    private final CurrentStatusPanel status;

    public PrepScene(Hero hero) {
        this.hero = hero;
        //hero.learnSpell(Registry.S_FIREBALL);
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.01;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = c.weightx = 1;
        add(buy = new BuyPanel(hero), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_START;
        add(status = new CurrentStatusPanel(hero), c);
        _update();
    }

    @Override
    public void _update() {
        buy._update();
        status._update();
    }


}
