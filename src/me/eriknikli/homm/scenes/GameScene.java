package me.eriknikli.homm.scenes;

import me.eriknikli.homm.gameplay.AIHero;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.scenes.components.game.GameBoard;
import me.eriknikli.homm.scenes.components.game.HeroPanel;
import me.eriknikli.homm.scenes.components.game.RoundPanel;
import me.eriknikli.homm.scenes.components.log.LogFrame;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
     * A tábla
     */
    private final GameBoard board;
    private final HeroPanel playerHPanel;
    private final HeroPanel aiHPanel;
    private final LogFrame log;
    private final RoundPanel order;
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
     * @return a baloldali játékos
     */
    public Hero left() {
        return left;
    }

    /**
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
    public GameScene(Hero player, Hero enemy) {
        left = player;
        right = enemy;
        board = new GameBoard(this);
        var layout = new GridBagLayout();
        setLayout(layout);
        var c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(3, 3, 3, 3);
        add(order = new RoundPanel(board), c);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 10, 0, 10);
        c.fill = GridBagConstraints.VERTICAL;
        playerHPanel = new HeroPanel(player, this);
        add(playerHPanel, c);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = c.weightx = 0.5;
        add(board, c);
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(0, 10, 0, 10);
        aiHPanel = new HeroPanel(enemy, this);
        if (enemy instanceof AIHero ai) {
            ai.setGame(this);
        }
        add(aiHPanel, c);
        setBackground(aiHPanel.getBackground());
        log = new LogFrame();

        board.spawnUnits(left(), true);
        board.spawnUnits(right(), false);

    }

    /**
     * Ah yes, log
     *
     * @param log log
     */
    public void log(String log) {
        log(log, null);
    }

    private String hex(int r, int g, int b) {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public void log(String log, Hero which) {
        log = "<html>" +
                "<p>" + log + "</p>" +
                "</html>";
        this.log.log(log, which);
    }

    /**
     * A játék elindítása
     */
    public void startGame() {
        prepPhase = false;
        board.select(null);
        for (var t : board.tiles) {
            t.setBackground(Color.BLACK);
        }
        left().onStartMatch();
        right().onStartMatch();
        log("The Match has begun!");
        board.nextTurn();
    }

    /**
     * @return a tábla
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * @return A játékos állapotjelzője
     */
    public HeroPanel playerPanel() {
        return playerHPanel;
    }

    /**
     * @return a bot állapotjelzője
     */
    public HeroPanel aiPanel() {
        return aiHPanel;
    }

    @Override
    public void _update() {
        playerHPanel._update();
        aiHPanel._update();
        order._update();
    }
}
