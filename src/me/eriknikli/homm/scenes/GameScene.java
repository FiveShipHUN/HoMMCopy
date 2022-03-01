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
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        add(new HeroPanel(new Hero()), c);
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        add(new GameBoard(), c);
        c.gridx = 0;
        c.gridy = 2;
        add(new HeroPanel(new Hero()), c);
    }
}
