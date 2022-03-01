package me.eriknikli.homm.utils;

import java.util.Random;

/**
 * Random szám generálásra.
 */
public class RNG {

    /**
     * Random objektum
     */
    private static Random rng;

    /**
     * @return a Random objektumot
     */
    public static Random rng() {
        if (rng == null) {
            rng = new Random();
        }
        return rng;
    }

    /**
     * @return egy random integert
     */
    public static int randomInt() {
        return rng().nextInt();
    }

    /**
     * @param max a felső korlát, ha max negatív, akkor alsó korlát (nyitott / exclusive)
     * @return egy random integer [0 ; max) között ; ha max negatív, akkor (max ; 0] között (max = 0 esetén 0)
     */
    public static int randomInt(int max) {
        if (max == 0) {
            return 0;
        }
        if (max < 0) {
            max = -max;
            return -rng().nextInt(max);
        }
        return rng().nextInt(max);
    }

    /**
     * @param max a felső korlát (zárt / inclusive)
     * @return egy random integer [0 ; max] között
     */
    public static int randomIntI(int max) {
        return randomInt(max + 1);
    }

    /**
     * @param max a felső korlát (nyitott / exclusive)
     * @return egy random integer [0 ; max) között, amúgy {@code randomInt(max)}-t hívja meg
     */
    public static int randomIntE(int max) {
        return randomInt(max);
    }

    /**
     * @param min alsó korlát (zárt / inclusive)
     * @param max felső korlát (nyitott / exclusive)
     * @return egy random integer [min ; max) között
     */
    public static int randomIntIE(int min, int max) {
        int signum = 1;
        if (min == 0 && max == 0) {
            return 0;
        }
        if (min == 0) {
            return randomIntE(max);
        }
        if (min < 0 && max < 0) {
            signum = -1;
            max = -max;
            min = -min;
        } else if (min < 0) {
            return min + randomIntIE(0, max - min);
        }
        if (max < min) {
            int s = min;
            min = max;
            max = s;
        }
        return (min + rng().nextInt(max - min)) * signum;
    }

    /**
     * @param min alsó korlát (zárt / inclusive)
     * @param max felső korlát (zárt / inclusive)
     * @return egy random integer [min ; max] között (shortcut {@code randomIntII(min, max)}-ra)
     */
    public static int randomInt(int min, int max) {
        return randomIntII(min, max);
    }

    /**
     * @param min alsó korlát (zárt / inclusive)
     * @param max felső korlát (zárt / inclusive)
     * @return egy random integer [min ; max] között
     */
    public static int randomIntII(int min, int max) {
        return randomIntIE(min, max + 1);
    }

    /**
     * @param min alsó korlát (nyílv / exclusive)
     * @param max felső korlát (zárt / inclusive)
     * @return egy random integer (min ; max] között
     */
    public static int randomIntEI(int min, int max) {
        return randomIntII(min + 1, max);
    }

    /**
     * @param min alsó korlát (nyílv / exclusive)
     * @param max felső korlát (nyílv / exclusive)
     * @return egy random integer (min ; max) között
     */
    public static int randomIntEE(int min, int max) {
        return randomIntII(min + 1, max - 1);
    }

    /**
     * @return egy random double [0 ; 1] között
     */
    public static double randomDouble() {
        return rng().nextDouble();
    }

    /**
     * @param min alsó korlát (zárt / inclusive)
     * @param max felső korlát (zárt / inclusive)
     * @return egy random double [min ; max] között
     */
    public static double randomDouble(double min, double max) {
        if (min > max) {
            double s = max;
            max = min;
            min = s;
        }
        return (max - min) * randomDouble() + min;
    }

    /**
     * @return egy random boolean (kb. 50%-50%)
     */
    public static boolean randomBoolean() {
        return rng().nextBoolean();
    }

    /**
     * Generál egy random booleant adott esély alapján
     *
     * @param trueWeight esély, hogy igaz legyen
     * @return egy random boolean adott eséllyel
     */
    public static boolean randomBoolean(float trueWeight) {
        return rng().nextFloat() <= trueWeight;
    }

}
