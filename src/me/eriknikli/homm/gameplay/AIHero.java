package me.eriknikli.homm.gameplay;

/**
 * Ezt a hőst "AI" irányítja
 * A Gold ugyanúgy működik, mintegy átlag hősnek
 */
public class AIHero extends Hero {

    public AIHero() {
        super("BOT", 1000);
        random();
    }
}
