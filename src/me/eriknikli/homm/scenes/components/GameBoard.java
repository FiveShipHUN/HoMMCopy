package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.scenes.Scene;

import javax.swing.*;
import java.awt.*;

/**
 * A játékmezők
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
        setLayout(layout);
        addColumnLabel(true);
        for (int y = 0; y < height; y++) {
            addVerticalLabelOf(y, true);
            for (int x = 0; x < width; x++) {
                add(tiles[index(x, y)] = new Tile(x, y));
            }
            addVerticalLabelOf(y, false);
        }
        addColumnLabel(false);
        setBackground(Color.GRAY);
    }

    private void addVerticalLabelOf(int y, boolean left) {
        var lbl = new JLabel((y + 1) + "");
        setPropsForColumnRowLbl(lbl);
        lbl.setHorizontalTextPosition(left ? SwingConstants.RIGHT : SwingConstants.LEFT);
        lbl.setHorizontalAlignment(left ? SwingConstants.RIGHT : SwingConstants.LEFT);
        add(lbl);
    }

    private void addColumnLabel2(int x, boolean topRow) {
        var lbl = new JLabel((x + 1) + "");
        setPropsForColumnRowLbl(lbl);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(topRow ? SwingConstants.BOTTOM : SwingConstants.TOP);
        add(lbl);
    }

    private void setPropsForColumnRowLbl(JLabel lbl) {
        lbl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
    }

    private void addColumnLabel(boolean topRow) {
        add(new JLabel(""));
        for (int x = 0; x < width; x++) {
            addColumnLabel2(x, topRow);
        }
        add(new JLabel(""));
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

}
