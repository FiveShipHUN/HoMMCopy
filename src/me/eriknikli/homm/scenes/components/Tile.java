package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.HoMM;

import javax.swing.*;

/**
 * Mezőt leíró osztály
 */
public class Tile extends JButton {

    /**
     * X koordináta
     */
    private final int x;

    /**
     * Y koordináta
     */
    private final int y;

    /**
     * Létrehoz egy mezőt
     *
     * @param x az x koordinátája a mezőnek
     * @param y az y koordinátája a mezőnek
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        setText(x + " , " + y);
        setIcon(new ImageIcon(HoMM.__DEBUG));
    }

    /**
     * @return visszaadja x koordinátát
     */
    public int x() {
        return x;
    }

    /**
     * @return visszaadja y koordinátát
     */
    public int y() {
        return y;
    }


}
