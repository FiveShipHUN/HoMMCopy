package me.eriknikli.homm.utils;

/**
 * Dispose-olható objektumokhoz
 */
public interface Disposable {

    /**
     * Dispose függvény, célja, hogy segítse a memóriaterület és erőforrások felszabadulását adott objektum destruktálásakor
     */
    void dispose();

}
