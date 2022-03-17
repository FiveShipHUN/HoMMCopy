package me.eriknikli.homm.scenes.components.game;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
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
     * Map inicializálása 12 és 10-es szélességgel
     */
    public GameBoard() {
        this(12, 10);
    }

    /**
     * Map inicializálása
     *
     * @param width  szélesség
     * @param height magasság
     */
    public GameBoard(int width, int height) {
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
                if (!inRange.contains(t)) {
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

}
