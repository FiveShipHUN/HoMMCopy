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

    public PrepScene(Hero hero) {
        this.hero = hero;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.01;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.VERTICAL;
        add(new BuyPanel(hero), c);
        c.gridx = 1;
        add(new CurrentStatusPanel(hero), c);
    }


}
