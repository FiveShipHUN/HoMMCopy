package me.eriknikli.homm.scenes.components.game;

import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.gameplay.army.Unit;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;

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
     * A tábla
     */
    private final GameBoard board;

    /**
     * Létrehoz egy mezőt
     *
     * @param x     az x koordinátája a mezőnek
     * @param y     az y koordinátája a mezőnek
     * @param board a tábla
     */
    public Tile(int x, int y, GameBoard board) {
        this.x = x;
        this.y = y;
        this.board = board;
        setFont(new Font("Times New Roman", Font.BOLD, 20));
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                refreshUnitInfo();
            }
        });
        addActionListener(e -> {
            onClick();
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
            try {
                var width = (int) (getWidth() * 0.8f);
                var height = (int) (getHeight() * 0.8f);
                width = height = Math.min(width, height);
                setIcon(unit().type().image().icon(width, height));
                setText(unit().amount() + "");
            } catch (Exception e) {
            }
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
        this.unit = u;
        if (u != null) {
            unit.setTile(this);
        }
        refreshUnitInfo();
    }

    /**
     * @return szomszédos mezők
     */
    public HashSet<Tile> neighbors() {
        var tiles = new HashSet<Tile>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                Tile tile = board.tileOf(x, y);
                if (tile != null) {
                    tiles.add(tile);
                }
            }
        }
        return tiles;
    }

    /**
     * @return A board, ami ezt a mezőt tartalmazza
     */
    public GameBoard board() {
        return board;
    }


    /**
     * Amikor erre a gombra rákattint mi történjen
     */
    public void onClick() {
        PlayerHero player = (PlayerHero) board().game().left();
        Unit selectedUnit = null;
        if (board.selected() != null) {
            selectedUnit = board.selected().unit;
        }
        //// Felkészülés
        if (board().game().isInPrepPhase()) {
            for (int y = 0; y < board.height; y++) {
                for (int x = 0; x < 2; x++) {
                    var t = board.tileOf(x, y);
                    if (t.getBackground().equals(Color.BLACK) && t.unit() == null) {
                        t.setBackground(Color.GRAY);
                    }
                }
            }
            if (x < 2) {
                if (unit() != null) {
                    board.select(this);
                } else {
                    if (selectedUnit != null && selectedUnit.hero() == player) {
                        selectedUnit.moveTo(this);
                        board.select(this);

                    }
                }
            }
        }
        //// INGAME
        else {
            if (unit() == null) {

            }
        }
    }

    public void onDeselect() {
        if (board.game().isInPrepPhase()) {
            setBackground(unit == null && x < 2 ? Color.GRAY : Color.BLACK);
        } else {
            setBackground(Color.BLACK);
        }
        setForeground(Color.WHITE);
    }


    public void onSelect() {
        if (board.game().isInPrepPhase()) {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
            for (Tile t : board().tilesInRange(this, unit().type().speed(), false)) {
                t.setBackground(Color.GRAY);
            }
        }
    }
}
