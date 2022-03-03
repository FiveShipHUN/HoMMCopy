package me.eriknikli.homm.assets;

/**
 * Itt tárolja el az asseteket
 */
public class Assets {


    public static final ImageAsset I_FARMER = new ImageAsset();

    public static void init() {
        I_FARMER.loadImage("textures/units/farmer.png");
    }

}
