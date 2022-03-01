package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.gameplay.Unit;

import javax.swing.*;
import java.awt.*;

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
     * Unit
     */
    private Unit unit;

    /**
     * Létrehoz egy mezőt
     *
     * @param x az x koordinátája a mezőnek
     * @param y az y koordinátája a mezőnek
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        setFont(new Font("Times New Roman", Font.BOLD, 20));
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
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

    /**
     * @return A jelenleg itt tartozkodó Unit-ot visszaadja
     */
    public Unit unit() {
        return unit;
    }

    /**
     * Ide mozgatja az adott unit-ot
     *
     * @param u
     */
    public void setUnit(Unit u) {
        this.unit = u;
    }


}
