package me.eriknikli.homm.data;

import me.eriknikli.homm.gameplay.units.types.Archer;
import me.eriknikli.homm.gameplay.units.types.Farmer;
import me.eriknikli.homm.gameplay.units.types.Griffin;
import me.eriknikli.homm.gameplay.units.types.Priest;
import me.eriknikli.homm.gameplay.units.types.Swordman;

/**
 * Ebből a classból lehet elérni az asseteket és egyéb objektumokat
 * I_* => képet tárol el
 * UT_* => unit típus
 */
public class Registry {

    /**
     * Farmerhez tartozó kép
     */
    public static final ImageAsset I_FARMER = new ImageAsset();

    /**
     * Íjászhoz tartozó kép
     */
    public static final ImageAsset I_ARCHER = new ImageAsset();

    /**
     * Griffhez tartozó kép
     */
    public static final ImageAsset I_GRIFFIN = new ImageAsset();

    /**
     * Paphoz tartozó kép
     */
    public static final ImageAsset I_PRIEST = new ImageAsset();

    /**
     * Kardforgatóhoz tartozó kép
     */
    public static final ImageAsset I_SWORDSMAN = new ImageAsset();

    /**
     * Farmer egységtípus
     */
    public static final Farmer UT_FARMER = new Farmer();

    /**
     * Farmer egységtípus
     */
    public static final Archer UT_ARCHER = new Archer();

    /**
     * Farmer egységtípus
     */
    public static final Griffin UT_GRIFFIN = new Griffin();

    /**
     * Farmer egységtípus
     */
    public static final Priest UT_PRIEST = new Priest();

    /**
     * Farmer egységtípus
     */
    public static final Swordman UT_SWORDSMAN = new Swordman();

    /**
     * @return Összes kép asset
     */
    public static ImageAsset[] images() {
        return new ImageAsset[]{I_FARMER, I_ARCHER, I_SWORDSMAN, I_PRIEST, I_GRIFFIN};
    }

    /**
     * @return Összes unit típus
     */
    public static Object[] uTypes() {
        return new Object[]{UT_FARMER, UT_ARCHER, UT_SWORDSMAN, UT_PRIEST, UT_GRIFFIN};
    }

    /**
     * Inicializálja az asseteket
     */
    public static void init() {
        I_FARMER.loadImage("textures/units/farmer.png");
        I_ARCHER.loadImage("textures/units/archer.png");
        I_GRIFFIN.loadImage("textures/units/griffin.png");
        I_PRIEST.loadImage("textures/units/priest.png");
        I_SWORDSMAN.loadImage("textures/units/swordman.png");
    }

    /**
     * Destruktálja az asseteket
     */
    public static void dispose() {
        I_FARMER.dispose();
        I_ARCHER.dispose();
        I_GRIFFIN.dispose();
        I_PRIEST.dispose();
        I_SWORDSMAN.dispose();
        System.gc();
    }

}
