package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.scenes.GameScene;
import me.eriknikli.homm.scenes.components.game.GameBoard;
import me.eriknikli.homm.utils.RNG;

import java.util.ArrayList;

/**
 * Ezt a hőst "AI" irányítja
 * A Gold ugyanúgy működik, mintegy átlag hősnek
 */
public class AIHero extends Hero {

    private GameScene scene;

    public AIHero() {
        super("BOT", 1000);
        this.scene = scene;
        random();
    }

    public Unit lastMoved = null;

    @Override
    public void theirTurn(GameBoard board, Unit which) {
        var targets = new ArrayList<>(which.validTargets());
        if (canCastSpell()) {
            var target = RNG.randomElement(scene.left().units());
            var spell = RNG.randomElement(spells());
            if (spell.mana() <= getMana()) {
                String log = "Bot has casted the " + spell + " spell";
                if (spell.hasTarget()) {
                    log += " on your " + target.type().name();
                } else if (spell.hasSelected()) {
                    log += " and on his " + which.type().name();
                }
                log += ".";
                scene.log(log, this);
                spell.cast(which, target);
                onCastSpell();
                setMana(getMana() - spell.mana());
            }
        }
        if (targets.isEmpty()) {
            var valids = new ArrayList<>(which.inRange(board));
            which.moveTo(RNG.randomElement(valids));
            scene.log("Bot's " + which.type().name() + " has moved away.", this);
        } else {
            var target = RNG.randomElement(targets);
            which.attack(target, !target.tile().neighbors().contains(which.tile()));
            scene.log("Bot's " + which.type().name() + " is attacked your " + target.type().name() + ".", this);
        }
        board.nextTurn();
        lastMoved = which;

    }

    public void setGame(GameScene scene) {
        if (this.scene == null) {
            this.scene = scene;
        }
    }



}
