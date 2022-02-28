package me.eriknikli.homm.scenes;

import javax.swing.*;

public class Tile extends JButton {

    private final int x;
    private final int y;

    /**
     * Létrehoz egy mezőt
     * @param x az x koordinátája a mezőnek
     * @param y az y koordinátája a mezőnek
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        setText(x + " , " + y);
    }

    /**
     *
     * @return visszaadja x koordinátát
     */
    public int x() {
        return x;
    }

    /**
     *
     * @return visszaadja y koordinátát
     */
    public int y() {
        return y;
    }

}
