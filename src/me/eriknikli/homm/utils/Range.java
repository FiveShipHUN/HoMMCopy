package me.eriknikli.homm.utils;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * Egy intervallumot ír le
 */
public class Range implements Serializable {

    private double min;
    private double max;

    /**
     * Készít egy [0,1] intervallumot
     */
    public Range() {
        this(0, 1);
    }

    /**
     * Készít egy intervallumot, ami [x , x]
     *
     * @param x az adott intervallum alsó- és felsőkorlátja is
     */
    public Range(double x) {
        this(x, x);
    }

    /**
     * [min,max] intervallumot készít (ha max kisebb mint min, akkor felcseréli azokat)
     *
     * @param min az intervallum alsó határa
     * @param max az intervallum felső határa
     */
    public Range(double min, double max) {
        set(min, max);
        validateValue();
    }

    /**
     * Beállítja az alsó korlátját az intervallumnak
     *
     * @param min az alsó korlát (zárt / inclusive)
     */
    public void setMin(double min) {
        set(min, max);
    }

    /**
     * Beállítja a felső korlátját az intervallumnak
     *
     * @param max a felső korlát (zárt / inclusive)
     */
    public void setMax(double max) {
        set(min, max);
    }

    /**
     * Beállítja a korlátokat
     *
     * @param min az alsó korlát (zárt / inclusive)
     * @param max a felső korlát (zárt / inclusive)
     */
    public void set(double min, double max) {
        this.min = min;
        this.max = max;
        validateValue();
    }

    /**
     * Visszaad egy random számot az intervallumon belül
     */
    public double random() {
        if (min == max) {
            return min;
        }
        return RNG.randomDouble(min, max);
    }

    /**
     * Megnézi, hogy a min kisebb-e mint a max, ha nem akkor megcseréli őket
     */
    public void validateValue() {
        if (min > max) {
            double d = min;
            min = max;
            max = d;
        }
    }

    /**
     * Összehasonlítja ezt egy másik objektummal, és megmondja, ha az értékeik egyeznek (Auto-generált)
     *
     * @param o a másik objektum
     * @return ha az értékek egyeznek akkor true, amúgy false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Double.compare(range.min, min) == 0 && Double.compare(range.max, max) == 0;
    }

    /**
     * IDE által generált hashcode algoritmus
     *
     * @return a hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    /**
     * @return shortcut {@code toString(2)}-re
     */
    @Override
    public String toString() {
        return toString(2);
    }

    /**
     * @param rounding kerekítés
     * @return {@code [min ; max]} formába adja vissza az adott intervallumot String-ként
     */
    public String toString(int rounding) {
        var f = NumberFormat.getNumberInstance();
        f.setMinimumFractionDigits(0);
        f.setMaximumFractionDigits(rounding);
        String min = f.format(this.min);
        String max = f.format(this.max);
        return "[" + min + " ; " + max + "]";
    }

    /**
     * @param rounding kerekítés
     * @return {@code [min ; max]} formába adja vissza az adott intervallumot String-ként
     */
    public String toStringNoBrackets(int rounding) {
        var f = NumberFormat.getNumberInstance();
        f.setMinimumFractionDigits(0);
        f.setMaximumFractionDigits(rounding);
        String min = f.format(this.min);
        String max = f.format(this.max);
        if (min.equalsIgnoreCase(max)) {
            return min;
        }
        return "" + min + " - " + max + "";
    }

    /**
     * @return alsó korlát
     */
    public double min() {
        return min;
    }

    /**
     * @return felső korlát
     */
    public double max() {
        return max;
    }
}
