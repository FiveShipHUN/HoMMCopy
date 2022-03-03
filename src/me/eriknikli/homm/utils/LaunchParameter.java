package me.eriknikli.homm.utils;

import me.eriknikli.homm.data.Config;

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
    public boolean resizable = true;

    /**
     * Legyen-e hatása az {@code initFromConfig()} függvénynek?
     */
    public boolean ignoreConfig;

    public LaunchParameter() {
    }

    public void initFromCfg() {
        if (!ignoreConfig) {
            x = Config.instance().x;
            y = Config.instance().y;
            width = Config.instance().width;
            height = Config.instance().height;
        }
    }

}
