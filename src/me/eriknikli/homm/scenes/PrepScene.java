package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.scenes.components.UnitBuyingPanel;

import java.awt.*;

/**
 * Felkészülési szakaszban használt Scene
 */
public class PrepScene extends Scene {

    private Hero hero;

    public PrepScene(Hero hero) {
        this.hero = hero;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        add(new UnitBuyingPanel(),c);
        c = new GridBagConstraints();
    }


}
