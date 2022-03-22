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
     * Normál, 1000 arannyal kezd
     */
    NORMAL(1000),
    /**
     * Nehéz, 700 arannyal kezd
     */
    HARD(700),
    /**
     * Nincs korlát
     */
    NO_LIMIT(Integer.MAX_VALUE);

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

    /**
     *
     */
    public String displayName() {
        String name = toString();
        String cpy = "";
        boolean toUpper = true;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c == '_') {
                c = ' ';
                toUpper = true;
            } else if (toUpper) {
                toUpper = false;
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }
            cpy += c;
        }
        return cpy;
    }

}
