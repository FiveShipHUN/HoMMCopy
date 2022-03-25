package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.components.game.GameBoard;

/**
 * Ezt a hőst "AI" irányítja
 * A Gold ugyanúgy működik, mintegy átlag hősnek
 */
public class AIHero extends Hero {

    public AIHero() {
        super("BOT", 1000);
        random();
    }

    @Override
    public void theirTurn(GameBoard board, Unit which) {

        board.nextTurn();
    }
}
