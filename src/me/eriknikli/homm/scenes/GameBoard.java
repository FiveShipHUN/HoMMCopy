package me.eriknikli.homm.scenes;

import javax.swing.*;

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
     * @param width szélesség
     * @param height magasság
     */
    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(x(i), y(i));
        }
    }

    /**
     * megmondja index alapján az x koordinátát
     * @param index a mező indexe
     * @return x koordináta
     */
    public int x(int index) {
        return index % width;
    }


    /**
     * megmondja index alapján az y koordinátát
     * @param index a mező indexe
     * @return y koordináta
     */
    public int y(int index) {
        return index / height;
    }


    /**
     * megmondja koordináták alapján a mező indexét a tömbbe
     * @param x az x koordináta
     * @param y az y koordináta
     * @return az index
     */
    public int index(int x, int y) {
        return x + y * width;
    }

}
