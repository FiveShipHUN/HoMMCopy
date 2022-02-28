package me.eriknikli.homm.scenes;

import javax.swing.*;

public class GameBoard extends JPanel {

    public Tile[] tiles;
    public final int width;
    public final int height;

    public GameBoard() {
        this(12, 10);
    }

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(x(i), y(i));
        }
    }

    public int x(int index) {
        return index % width;
    }

    public int y(int index) {
        return index / height;
    }

    public int index(int x, int y) {
        return x + y * width;
    }

}
