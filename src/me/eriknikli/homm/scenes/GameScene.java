package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.AIHero;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.scenes.components.game.GameBoard;
import me.eriknikli.homm.scenes.components.game.HeroPanel;

import java.awt.*;

/**
 * Játék Scene-je
 */
public class GameScene extends Scene {


    /**
     * Inicializálja a scene-t
     */
    public GameScene(Hero player) {
        var layout = new GridBagLayout();
        setLayout(layout);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 10, 0, 10);
        c.fill = GridBagConstraints.VERTICAL;
        var leftH = new HeroPanel(player);
        add(leftH, c);
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
        c.insets = new Insets(0, 10, 0, 10);
        var rightH = new HeroPanel(new AIHero());
        add(rightH, c);
        setBackground(rightH.getBackground());
    }
}
