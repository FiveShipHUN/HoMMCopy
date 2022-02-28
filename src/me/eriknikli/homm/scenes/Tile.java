package me.eriknikli.homm.scenes;

import javax.swing.*;

public class Tile extends JButton {

    private int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
