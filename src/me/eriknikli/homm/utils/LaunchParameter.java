package me.eriknikli.homm.utils;

import me.eriknikli.homm.HoMM;

/**
 * Indítási paramétereket tartalmaz
 */
public class LaunchParameter {

    /**
     * Az ablak X pozíciója
     */
    public int x = 0;

    /**
     * Az ablak Y pozíciója
     */
    public int y = 0;

    /**
     * Az ablak hossza
     */
    public int width = 1280;

    /**
     * Az ablak magassága
     */
    public int height = 768;

    /**
     * Átmérezhető-e az ablak?
     */
    public boolean resizable = false;

    /**
     * Legyen-e hatása az {@code initFromConfig()} függvénynek?
     */
    public boolean ignoreConfig;

    /**
     * Kinagyítsa-e az ablakot automatikusan? Ha igen, akkor az x, y, width és height paraméterek elveszítik értelmüket
     */
    public boolean maximizeWindow = true;

    public LaunchParameter() {
    }

    public void initFromCfg() {
        if (!ignoreConfig) {
            x = HoMM.cfg().x;
            y = HoMM.cfg().y;
            width = HoMM.cfg().width;
            height = HoMM.cfg().height;
            maximizeWindow = HoMM.cfg().maximizeWindow;
        }
    }

}
