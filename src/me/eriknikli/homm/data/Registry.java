package me.eriknikli.homm.data;

import me.eriknikli.homm.gameplay.army.types.Archer;
import me.eriknikli.homm.gameplay.army.types.Farmer;
import me.eriknikli.homm.gameplay.army.types.Griffin;
import me.eriknikli.homm.gameplay.army.types.Priest;
import me.eriknikli.homm.gameplay.army.types.Swordsman;
import me.eriknikli.homm.gameplay.army.types.UnitType;
import me.eriknikli.homm.gameplay.spells.Attack;
import me.eriknikli.homm.gameplay.spells.Fireball;
import me.eriknikli.homm.gameplay.spells.Resurrect;
import me.eriknikli.homm.gameplay.spells.Shock;
import me.eriknikli.homm.gameplay.spells.Spell;
import me.eriknikli.homm.gameplay.spells.Strengthening;
import me.eriknikli.homm.gameplay.spells.TeleportSwap;
import me.eriknikli.homm.utils.Utils;

/**
 * Ebből a classból lehet elérni az asseteket és egyéb, konstans objektumokat
 * I_* => kép asset
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


    public static final ImageAsset I_SHOCK = new ImageAsset();

    public static final ImageAsset I_FIREBALL = new ImageAsset();

    public static final ImageAsset I_RESURRECT = new ImageAsset();

    public static final ImageAsset I_STRENGTHENING = new ImageAsset();

    public static final ImageAsset I_TELEPORT = new ImageAsset();

    /**
     * Help icon
     */
    public static final ImageAsset I_HELP = new ImageAsset();

    /**
     * Farmer egységtípus
     */
    public static final Farmer UT_FARMER = new Farmer();

    /**
     * Íjász egységtípus
     */
    public static final Archer UT_ARCHER = new Archer();

    /**
     * Griffin egységtípus
     */
    public static final Griffin UT_GRIFFIN = new Griffin();

    /**
     * Pap egységtípus
     */
    public static final Priest UT_PRIEST = new Priest();

    /**
     * Kardforgató egységtípus
     */
    public static final Swordsman UT_SWORDSMAN = new Swordsman();

    /**
     * Villámcsapás varázslat
     */
    public static final Attack S_ATTACK = new Attack();

    /**
     * Villámcsapás varázslat
     */
    public static final Shock S_SHOCK = new Shock();

    /**
     * Tűzlabda varázslat
     */
    public static final Fireball S_FIREBALL = new Fireball();

    /**
     * Tűzlabda varázslat
     */
    public static final Resurrect S_RESURRECT = new Resurrect();

    /**
     * Erősítés varázslat
     */
    public static final Strengthening S_STRENGTHENING = new Strengthening();

    /**
     * Teleportálás varázslat
     */
    public static final TeleportSwap S_TELEPORT_SWAP = new TeleportSwap();

    /**
     * @return Összes kép asset
     */
    public static ImageAsset[] images() {
        return new ImageAsset[]{I_FARMER, I_ARCHER, I_SWORDSMAN, I_PRIEST, I_GRIFFIN, I_HELP, I_FIREBALL, I_SHOCK, I_RESURRECT, I_STRENGTHENING, I_TELEPORT};
    }

    /**
     * @return Összes unit típus
     */
    public static UnitType[] uTypes() {
        return new UnitType[]{UT_FARMER, UT_ARCHER, UT_SWORDSMAN, UT_PRIEST, UT_GRIFFIN};
    }

    /**
     * @return Összes varázslat
     */
    public static Spell[] spells() {
        return new Spell[]{S_SHOCK, S_FIREBALL, S_RESURRECT, S_STRENGTHENING, S_TELEPORT_SWAP, S_ATTACK};
    }

    /**
     * Inicializálja az asseteket
     */
    public static void init() {
        I_FARMER.loadImage("textures/units/farmer.png");
        I_ARCHER.loadImage("textures/units/archer.png");
        I_GRIFFIN.loadImage("textures/units/griffin.png");
        I_PRIEST.loadImage("textures/units/priest.png");
        I_SWORDSMAN.loadImage("textures/units/swordsman.png");
        I_SHOCK.loadImage("textures/spells/shock.png");
        I_FIREBALL.loadImage("textures/spells/fireball.png");
        I_RESURRECT.loadImage("textures/spells/resurrect.png");
        I_STRENGTHENING.loadImage("textures/spells/buff.png");
        I_TELEPORT.loadImage("textures/spells/tp.png");
        I_HELP.loadImage("textures/help.png");
    }

    /**
     * Destruktálja az asseteket
     */
    public static void dispose() {
        for (var img : images()) {
            Utils.tryDispose(img);
        }
        System.gc();
    }

}
