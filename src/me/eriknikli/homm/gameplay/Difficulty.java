package me.eriknikli.homm.gameplay;

/**
 * Nehézségi szint
 */
public enum Difficulty {

    /**
     * Könnyű, 1300 arannyal kezd
     */
    EASY(1300),
    /**
     * Könnyű, 1000 arannyal kezd
     */
    NORMAL(1000),
    /**
     * Könnyű, 700 arannyal kezd
     */
    HARD(700);

    /**
     * Az arany
     */
    private final int gold;

    /**
     * Nehézség
     *
     * @param gold kezdő aranymennyiség ezen a nehézségi szinten
     */
    Difficulty(int gold) {
        this.gold = gold;
    }

    /**
     * @return kezdő aranymennyiség ezen a nehézségi szinten
     */
    public int gold() {
        return gold;
    }

}
