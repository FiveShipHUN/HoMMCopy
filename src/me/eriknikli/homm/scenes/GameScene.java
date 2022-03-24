package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.OPAIHero;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.scenes.components.game.GameBoard;
import me.eriknikli.homm.scenes.components.game.HeroPanel;

import java.awt.*;

/**
 * Játék Scene-je
 */
public class GameScene extends Scene {

    /**
     * A bal oldali hős
     */
    private final Hero left;
    /**
     * A jobb oldali hős
     */
    private final Hero right;
    /**
     * Előkészületi fázisban?
     */
    private boolean prepPhase = true;

    /**
     * @param h a hős, akinek az ellenfelét keressük
     * @return left (bal oldali játékos), ha right.equals(h), ellenkező esetben mindig right (jobb oldali játékos)
     */
    public Hero enemyOf(Hero h) {
        if (right.equals(h)) {
            return left;
        }
        return right;
    }

    /**
     *
     * @return a baloldali játékos
     */
    public Hero left() {
        return left;
    }

    /**
     *
     * @return a jobboldali játékos
     */
    public Hero right() {
        return right;
    }

    /**
     * @return előkészületi fázisban?
     */
    public boolean isInPrepPhase() {
        return prepPhase;
    }

    /**
     * @return játék fázisban?
     */
    public boolean isInGame() {
        return !isInPrepPhase();
    }

    /**
     * Inicializálja a scene-t
     */
    public GameScene(Hero player) {
        left = player;
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
        add(new GameBoard(this), c);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 10, 0, 10);
        var rightH = new HeroPanel(right = new OPAIHero());
        add(rightH, c);
        setBackground(rightH.getBackground());
    }
}
