package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.AIHero;
import me.eriknikli.homm.gameplay.Difficulty;
import me.eriknikli.homm.gameplay.PlayerHero;
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
        c.insets = new Insets(0, 10, 0, 10);
        c.fill = GridBagConstraints.VERTICAL;
        var leftH = new HeroPanel(new PlayerHero("Player", Difficulty.NORMAL));
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
