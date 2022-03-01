package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.scenes.components.GameBoard;
import me.eriknikli.homm.scenes.components.HeroPanel;

import java.awt.*;

/**
 * Játék jelenete
 */
public class GameScene extends Scene {


    /**
     * Inicializálja a scene-t
     */
    public GameScene() {
        var layout = new GridBagLayout();
        setLayout(layout);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        add(new HeroPanel(new Hero()), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = c.weightx = 0.5;
        add(new GameBoard(), c);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        add(new HeroPanel(new Hero()), c);
    }
}
