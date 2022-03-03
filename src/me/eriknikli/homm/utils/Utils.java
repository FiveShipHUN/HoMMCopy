package me.eriknikli.homm.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Segítő függvények, főleg olyanok amiket máshova nem illenek be
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
            o.getClass().getMethod("dispose").invoke(o);
        } catch (Exception e) {
            Log.debug("Object of class " + o.getClass() + " could not be disposed.", e);
        }
    }

    /**
     * @param d az objektum, amit dispose-olni kell,
     */
    public static void tryDispose(Disposable d) {
        if (d != null) {
            d.dispose();
        }
    }

    /**
     * Átméretez egy képet (src: <a href="https://stackoverflow.com/questions/9417356/bufferedimage-resize">https://stackoverflow.com/questions/9417356/bufferedimage-resize</a>)
     *
     * @param img  a kép maga
     * @param newW új szélesség
     * @param newH új magasság
     * @return az átméretezett kép
     */
    public static BufferedImage resizeImg(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public static BufferedImage cpy(BufferedImage img) {
        return resizeImg(img, img.getWidth(), img.getHeight());
    }

}
