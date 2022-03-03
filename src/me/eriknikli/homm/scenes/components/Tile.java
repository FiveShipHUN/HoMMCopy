package me.eriknikli.homm.scenes.components;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.units.Unit;
import me.eriknikli.homm.utils.RNG;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        setUnit(new Unit(RNG.randomElement(Registry.uTypes()), 100));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                refreshUnitInfo();
            }
        });
    }

    /**
     * Update-eli a unit információit ezen a mezőn
     */
    public void refreshUnitInfo() {
        if (unit() == null) {
            setIcon(null);
            setText("");
        } else {
            var width = (int) (getWidth() * 0.8f);
            var height = (int) (getHeight() * 0.8f);
            width = height = Math.min(width, height);
            setIcon(unit().type().image().icon(width, height));
            setText(unit().amount() + "");
        }
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
     * @param u a unit, amit ide szeretnél rakni
     */
    public void setUnit(Unit u) {
        refreshUnitInfo();
        this.unit = u;
    }


}
