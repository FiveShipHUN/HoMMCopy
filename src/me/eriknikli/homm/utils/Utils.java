package me.eriknikli.homm.utils;

/**
 * Segítő függvények, főleg olyanok amiket máshova nem tudtam rakni
 */
public class Utils {


    /**
     * Megpróbálja adott objektumnak a {@code dispose()} függvényt meghívni reflection-ön keresztül
     *
     * @param o Az adott objektum, amit dispose-olni kell, lehet null is, az miatt lett csinálva, hogy ne kelljen null-check-et írni<br>
     *          Ha ez Disposable, akkor meghívja a {@code tryDispose((Disposable) o)} függvényt
     */
    public static void tryDispose(Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof Disposable d) {
            tryDispose(d);
        }
        try {

        } catch (Exception e) {

        }
    }

    /**
     *
     * @param d az objektum, amit dispose-olni kell,
     */
    public static void tryDispose(Disposable d) {
        if (d != null) {
            d.dispose();
        }
    }

}
