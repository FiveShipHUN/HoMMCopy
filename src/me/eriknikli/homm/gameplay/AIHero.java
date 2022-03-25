package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.components.game.GameBoard;
import me.eriknikli.homm.utils.RNG;

import java.util.ArrayList;

/**
 * Ezt a hőst "AI" irányítja
 * A Gold ugyanúgy működik, mintegy átlag hősnek
 */
public class AIHero extends Hero {

    public AIHero() {
        super("BOT", 1000);
        random();
    }

    public Unit lastMoved = null;

    @Override
    public void theirTurn(GameBoard board, Unit which) {
        var targets = new ArrayList<>(which.validTargets());
        if (targets.isEmpty()) {
            var valids = new ArrayList<>(which.inRange(board));
            which.moveTo(RNG.randomElement(valids));
        } else {
            var target = RNG.randomElement(targets);
            which.attack(target);
        }
        board.nextTurn();
        lastMoved = which;
    }
}
