package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.GameScene;
import me.eriknikli.homm.scenes.MainMenuScene;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * A pálya, ahol zajlik a játék
 */
public class GameBoard extends JPanel {

    /**
     * Mezők
     */
    public Tile[] tiles;

    /**
     * A map szélessége, alapból 12
     */
    public final int width;

    /**
     * A map magassága, alapból 10
     */
    public final int height;
    /**
     * A scene, ami ezt tartalmazza
     */
    private final GameScene scene;

    /**
     * Az egységek sorrendje
     */
    private final List<Unit> order = new ArrayList<>();

    /**
     * A köröket számolja
     */
    private int turnCounter = 0;

    /**
     * Vége?
     */
    private boolean ended = false;

    /**
     * Kiválasztott mező
     */
    private Tile selectedTile;
    private int roundCounter = 1;

    /**
     * Map inicializálása 12 és 10-es szélességgel
     */
    public GameBoard(GameScene s) {
        this(s, 12, 10);
    }

    /**
     * Map inicializálása
     *
     * @param width  szélesség
     * @param height magasság
     */
    public GameBoard(GameScene s, int width, int height) {
        this.scene = s;
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        var layout = new GridLayout(height + 2, width + 2);
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);
        addColumnLabel(true);
        for (int y = 0; y < height; y++) {
            addRowLbl(y, true);
            for (int x = 0; x < width; x++) {
                add(tiles[index(x, y)] = new Tile(x, y, this));
            }
            addRowLbl(y, false);
        }
        addColumnLabel(false);
        setBackground(Color.GRAY);
    }

    /**
     * Lespawnolja adott hősök unitjait random
     *
     * @param h    a hős, akinek le kell spawnolnia
     * @param left bal oldali játékos?
     */
    public void spawnUnits(Hero h, boolean left) {
        var units = new ArrayList<>(h.units());
        Collections.shuffle(units);
        List<Integer> ys = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            ys.add(i);
        }
        Collections.shuffle(ys);
        int x = left ? 0 : width - 1;
        for (int i = 0; i < units.size(); i++) {
            int y = ys.get(i);
            Unit u = units.get(i);
            u.setHero(h);
            var t = tileOf(x, y);
            t.setUnit(u);
        }
        order.addAll(units);
        order.sort((a, b) -> Integer.compare(b.priority(), a.priority()));
        updateBoard();
    }

    /**
     * Kitörli adott egységet a sorrendből
     */
    public void removeFromOrder(Unit u) {
        order.remove(u);
    }

    /**
     * Hozzáadja a mezősorszámot, ami a sorokhoz tartozik
     *
     * @param y    a sor indexe
     * @param left bal oldalon van-e a felirat?
     */
    private void addRowLbl(int y, boolean left) {
        var lbl = new JLabel((y + 1) + "");
        setPropsForColumnRowLbl(lbl);
        lbl.setHorizontalTextPosition(left ? SwingConstants.RIGHT : SwingConstants.LEFT);
        lbl.setHorizontalAlignment(left ? SwingConstants.RIGHT : SwingConstants.LEFT);
        add(lbl);
    }

    /**
     * Hozzáadja a mezősorszámot, ami az oszlopokhoz tartozik
     *
     * @param x      az oszlop indexe
     * @param topRow felső részen van-e a felirat?
     */
    private void addColumnLbl(int x, boolean topRow) {
        var lbl = new JLabel((x + 1) + "");
        setPropsForColumnRowLbl(lbl);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(topRow ? SwingConstants.BOTTOM : SwingConstants.TOP);
        add(lbl);
    }

    /**
     * Hozzáadja a mezősorszámokat az oszlopokhoz
     *
     * @param topRow felső részen van a felirat?
     */
    private void addColumnLabel(boolean topRow) {
        add(new JLabel(""));
        for (int x = 0; x < width; x++) {
            addColumnLbl(x, topRow);
        }
        add(new JLabel(""));
    }

    /**
     * Közös beállítások a sor/oszlop jelölő szövegnek (pl. font)
     *
     * @param lbl a szöveg-objektum
     */
    private void setPropsForColumnRowLbl(JLabel lbl) {
        lbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
    }

    /**
     * megmondja index alapján az x koordinátát
     *
     * @param index a mező indexe
     * @return x koordináta
     */
    public int x(int index) {
        return index % width;
    }


    /**
     * megmondja index alapján az y koordinátát
     *
     * @param index a mező indexe
     * @return y koordináta
     */
    public int y(int index) {
        return index / width;
    }


    /**
     * megmondja koordináták alapján a mező indexét a tömbbe
     *
     * @param x az x koordináta
     * @param y az y koordináta
     * @return az index
     */
    public int index(int x, int y) {
        if (0 <= x && x < width && 0 <= y && y < height) {
            return x + y * width;
        }
        return -1;
    }

    /**
     * Visszaadja az adott mezőt koordináta alapján
     *
     * @param x x koordináta
     * @param y y koordináta
     * @return az adott kordinátán lévő mező, ha nem létezik, akkor null
     */
    public Tile tileOf(int x, int y) {
        int i = index(x, y);
        if (0 <= i && i < tiles.length) {
            return tiles[i];
        } else {
            return null;
        }
    }

    /**
     * Megkeresi az elérhető mezőket adott mezőről a megadott távolságban
     *
     * @param from        Innen indul az algoritmus
     * @param range       Mekkora távolságról van szó
     * @param includeSelf hozzáadja saját magát?
     * @return mezők, amiket el lehet érni innen
     */
    public HashSet<Tile> tilesInRange(Tile from, int range, boolean includeSelf) {
        HashSet<Tile> inRange = new HashSet<>();
        HashSet<Tile> queue = new HashSet<>();
        queue.add(from);
        for (int i = 0; i <= range; i++) {
            for (var e : new ArrayList<>(queue)) {
                for (Tile t : e.neighbors()) {
                    if (!inRange.contains(t) && t.unit() == null) {
                        queue.add(t);
                    }
                }
                inRange.add(e);
                //e.DEBUG_setRange(i);
                queue.remove(e);
            }
        }
        if (!includeSelf) {
            inRange.remove(from);
        } else {
            inRange.add(from);
        }
        return inRange;
    }

    /**
     * @return A scene, ami ezt tartalmazza
     */
    public GameScene game() {
        return scene;
    }


    /**
     * Következő forduló
     */
    public void nextTurn() {
        updateBoard();
        if (turnCounter >= order.size()) {
            turnCounter = 0;
            for (Unit u : order) {
                u.onStartRound();
            }
            game().left().onStartRound();
            game().right().onStartRound();
            roundCounter++;
        }
        if (scene.left().units().isEmpty() && scene.right().units().isEmpty()) {
            JOptionPane.showMessageDialog(null, "It's a draw!", "Draw", JOptionPane.WARNING_MESSAGE);
            HoMM.game().setScene(new MainMenuScene());
            ended = true;
        } else if (scene.left().units().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You lost the battle!", "Loser", JOptionPane.WARNING_MESSAGE);
            HoMM.game().setScene(new MainMenuScene());
            ended = true;
        } else if (scene.right().units().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You won the battle!", "Winner", JOptionPane.WARNING_MESSAGE);
            HoMM.game().setScene(new MainMenuScene());
            ended = true;
        }
        if (!ended) {
            var unit = order.get(turnCounter);
            turnCounter++;
            if (unit.isAlive() && !unit.isSick()) {
                unit.onTurnStarted();
                unit.hero().theirTurn(this, unit);
            }
            unit.sickness(false);
        }
        updateBoard();
    }


    /**
     * @return kiválasztott mező
     */
    public Tile selected() {
        return selectedTile;
    }

    /**
     * @param selectedTile Válaszd ki ezt a mezőt
     */
    public void select(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    public void updateBoard() {
        for (Tile t : tiles) {
            t.setBackground(Color.BLACK);
        }
        for (var u : scene.left().units()) {
            if (u.tile() != null) {
                u.tile().setBackground(new Color(0, 80, 0));
            }
        }
        for (var u : scene.right().units()) {
            if (u.tile() != null) {
                u.tile().setBackground(new Color(108, 0, 0));
            }
        }
        if (selected() != null) {
            selected().setBackground(Color.YELLOW);
        }
        if (game().isInPrepPhase()) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < 2; x++) {
                    var t = tileOf(x, y);
                    if (t.unit() == null) {
                        t.setBackground(Color.DARK_GRAY);
                    }
                }
            }
        } else {
            try {
                for (var t : selected().unit().inRange(this)) {
                    t.setBackground(Color.DARK_GRAY);
                }
                for (var u : selected().unit().validTargets()) {
                    u.tile().setBackground(Color.ORANGE);
                }
            } catch (Exception e) {
            }
        }
        for (var t : tiles) {
            if (t.unit() != null) {
                t.refreshUnitInfo();
            }
        }
        scene._update();
    }

    public int round() {
        return roundCounter;
    }

    public Collection<Unit> order() {
        return order;
    }

    public void checkWin() {
        if (scene.left().units().isEmpty() && scene.right().units().isEmpty()) {
            JOptionPane.showMessageDialog(null, "It's a draw!", "Draw", JOptionPane.WARNING_MESSAGE);
            HoMM.game().setScene(new MainMenuScene());
            ended = true;
        } else if (scene.left().units().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You lost the battle!", "Loser", JOptionPane.WARNING_MESSAGE);
            HoMM.game().setScene(new MainMenuScene());
            ended = true;
        } else if (scene.right().units().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You won the battle!", "Winner", JOptionPane.WARNING_MESSAGE);
            HoMM.game().setScene(new MainMenuScene());
            ended = true;
        }
        updateBoard();
    }
}
