package me.eriknikli.homm.assets;

/**
 * Ebből a classból lehet elérni az asseteket
 * I_* => képet tárol el
 */
public class Assets {

    /**
     * Farmerhez tartozó kép
     */
    public static final ImageAsset I_FARMER = new ImageAsset();

    /**
     * Inicializálja az asseteket
     */
    public static void init() {
        I_FARMER.loadImage("textures/units/farmer.png");
    }

    /**
     * Destruktálja az asseteket
     */
    public static void dispose() {
        I_FARMER.dispose();
        System.gc();
    }

}
