package me.eriknikli.homm.gameplay;

import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.army.Unit;
import me.eriknikli.homm.gameplay.spells.Spell;
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

    public String helpTxt() {
        var skills = "";
        for (Skill s : Skill.values()) {
            skills += "<li>";
            skills += "<strong>" + s.display() + "</strong>" + ": " + skill(s);
            skills += "</li>";
        }
        var spells = "";
        for (Spell s : Registry.spells()) {
            if (knowsSpell(s)) {
                spells += "<li>";
                spells += s.name();
                spells += "</li>";
            }
        }
        var units = "";
        for (var unit : units()) {
            units += "<li>";
            units += "<strong>" + unit.type().name() + "</strong>: " + unit.amount();
            units += "</li>";
        }
        return "<html>" +
                "<h1>" +
                name +
                "</h1>" +
                "<h3>Skills</h3>" +
                "<ul>" +
                skills +
                "</ul>" +
                "<h3>Spells</h3>" +
                "<ul>" +
                spells +
                "</ul>" +
                "<h3>Units</h3>" +
                "<ul>" +
                units +
                "</ul>" +
                "</html>";
    }

}
