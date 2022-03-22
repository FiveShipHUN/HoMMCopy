package me.eriknikli.homm.gameplay;

public enum Skill {

    ATTACK("Attack", "It improves attack damage of your units."),
    DEFENSE("Defense", "It reduces incoming damages against your units."),
    MAGIC_POWER("Magic Power", "It improves the effect of your spells."),
    KNOWLEDGE("Knowledge", "It increases your maximum mana."),
    MORAL("Moral", "It increases your units priority."),
    LUCK("Luck", "It increases the chance of a critical attack.");

    private String name;
    private String desc;

    Skill(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String display() {
        return name;
    }

    public String desc() {
        return desc;
    }

}
