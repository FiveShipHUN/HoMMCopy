package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.components.game.GameBoard;

/**
 * Ez a hős valódi játékos
 */
public class PlayerHero extends Hero {

    public PlayerHero(String name, Difficulty d) {
        super(name, d.gold());
    }


    @Override
    public void theirTurn(GameBoard board, Unit unit) {
        board.select(unit.tile());
    }
}
