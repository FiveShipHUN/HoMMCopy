package me.eriknikli.homm.gameplay;

/**
 * Ez a hős valódi játékos
 */
public class PlayerHero extends Hero {

    public PlayerHero(String name, Difficulty d) {
        super(name, d.gold());
    }


}
