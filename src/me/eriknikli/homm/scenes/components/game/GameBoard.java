package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.GameScene;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private int roundCounter = 0;


    /**
     * Kiválasztott mező
     */
    private Tile selectedTile;

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
        units.sort(Comparator.comparingInt(Unit::priority));
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
        return x + y * width;
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
        List<Tile> queue = new ArrayList<>();
        queue.add(from);
        for (int i = 0; i < range && queue.size() > 0; i++) {
            var e = queue.get(0);
            queue.remove(0);
            for (Tile t : e.neighbors()) {
                if (!inRange.contains(t) && t.unit() == null) {
                    queue.add(e);
                }
            }
            inRange.add(e);
        }
        if (!includeSelf) {
            inRange.remove(from);
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
        roundCounter++;
        roundCounter = roundCounter % order.size();
        var unit = order.get(roundCounter);
        unit.hero().theirTurn(this, unit);
        HeroPanel a, b;
        if (unit.hero() instanceof PlayerHero) {
            a = scene.playerPanel();
            b = scene.aiPanel();
        } else {
            a = scene.aiPanel();
            b = scene.playerPanel();
        }
        a.setBackground(new Color(0, 54, 17));
        b.setBackground(Color.DARK_GRAY);
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
        for (Tile t : tiles) {
            t.onDeselect();
        }
        this.selectedTile = selectedTile;
        if (this.selectedTile != null) {
            this.selected().onSelect();
        }
    }
}
