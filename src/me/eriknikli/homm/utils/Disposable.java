package me.eriknikli.homm.utils;

/**
 * Dispose-olható objektumokhoz
 */
public interface Disposable {

    /**
     * Dispose függvény, célja, hogy segítse a memóriaterület és erőforrások felszabadulását adott objektum destruktálásakor
     */
    void dispose();

    /**
     * Meghívja {@code Utils.tryDispose(d)} függvényt
     * @param d a paraméter, amit átad a meghívott függvénynek
     */
    static void tryDispose(Disposable d) {
        Utils.tryDispose(d);
    }

}
