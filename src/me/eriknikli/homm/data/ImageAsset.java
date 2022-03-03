package me.eriknikli.homm.data;

import me.eriknikli.homm.utils.Disposable;
import me.eriknikli.homm.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Eltárol egy BufferedImage-ből két változatot, egy eredetit és egy másolatot
 * Tekintve, hogy főleg egy méretű kép kell, ezért elég egyszer kérni egy átméretezést, a másolat az új mérettel lesz eltárolva, és így nem kell az átméretezést minden
 * egyes instance-nél meghívni.
 */
public class ImageAsset implements Disposable {

    /**
     * Az eredeti kép
     */
    private BufferedImage img;

    /**
     * Az átméretezett másolat
     */
    private BufferedImage cpy;

    /**
     * ImageIcon swing-hez
     */
    private ImageIcon swingIcon;

    /**
     * Készít egy ImageAsset-et
     *
     * @param s az útvonal, ahol a kép található (automata hozzáadja a /-t az elejére)
     * @return a kép Asset, vagy null, ha egy Exception előkerülne
     */
    public static ImageAsset fromResource(String s) {
        try (var is = ImageAsset.class.getResourceAsStream("/" + s)) {
            return new ImageAsset(ImageIO.read(Objects.requireNonNull(is)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Létrehoz egy üres ImageAsset-et, később be lehet töltetni képet
     */
    public ImageAsset() {
    }

    /**
     * ImageAsset adott képből
     *
     * @param img az adott kép
     */
    public ImageAsset(BufferedImage img) {
        try {
            setImg(img);
        } catch (IllegalAccessException ignored) {
        }
    }

    /**
     * Ha kell utólag betöltet egy képet, de csak akkor lesz hatása, ha eredetileg nem volt kép
     *
     * @param resource a resource útvonala
     */
    public void loadImage(String resource) {
        try (var is = ImageAsset.class.getResourceAsStream("/" + resource)) {
            setImg(ImageIO.read(Objects.requireNonNull(is)));
        } catch (Exception ignored) {
        }
    }

    /**
     * Beállítja az adott képet forrásnak, de csak ha eddig a forráskép nem létezett / null volt
     *
     * @param img a kép
     */
    public void setImg(BufferedImage img) throws IllegalAccessException {
        if (this.img != null) {
            throw new IllegalAccessException("You cannot set new image if it has already been set.");
        }
        this.img = img;
        this.cpy = Utils.cpy(img);
        this.swingIcon = new ImageIcon(cpy);
    }

    /**
     * @return az eredeti kép
     */
    public BufferedImage original() {
        return img;
    }

    /**
     * @return a másolt, átméretezett kép
     */
    public BufferedImage cpy() {
        return cpy;
    }

    /**
     * @return az imageicon, Swing-hez
     */
    public ImageIcon icon() {
        return swingIcon;
    }

    /**
     * Ha kell átméretezi az adott képet és visszaadja az adott képről készült icont
     *
     * @param width  szélesség
     * @param height magasság
     * @return az átméretezett icon
     */
    public ImageIcon icon(int width, int height) {
        sizedInstance(width, height);
        return swingIcon;
    }

    /**
     * Ha kell átméretezi az adott képet méretarányosan, szélesség alapján, és visszaadja az adott képről készült icont
     *
     * @param width szélesség
     * @return az átméretezett icon
     */
    public ImageIcon iconByWidth(int width) {
        sizedInstanceByWidth(width);
        return swingIcon;
    }

    /**
     * Ha kell átméretezi az adott képet méretarányosan, magasság alapján, és visszaadja az adott képről készült icont
     *
     * @param height magasság
     * @return az átméretezett icon
     */
    public ImageIcon iconByHeight(int height) {
        sizedInstanceByHeight(height);
        return swingIcon;
    }

    /**
     * Átméretezi az adott képet ha kell, ha nem akkor csak visszaadja azt.
     *
     * @param width  szélesség
     * @param height magasság
     * @return az átméretezett kép
     */
    public BufferedImage sizedInstance(int width, int height) {
        if (cpy.getWidth() == width && cpy.getHeight() == height) {
            return cpy;
        }
        cpy = Utils.resizeImg(original(), width, height);
        swingIcon = new ImageIcon(cpy);
        return cpy;
    }

    /**
     * Átméretezi az adott képet a szélesség alapján, megtartva a méretarányt
     *
     * @param width szélesség
     * @return az átméretezett kép
     */
    public BufferedImage sizedInstanceByWidth(int width) {
        int height = (int) (width * (float) original().getHeight() / original().getWidth());
        return sizedInstance(width, height);
    }

    /**
     * Átméretezi az adott képet a magasság alapján, megtartva a méretarányt
     *
     * @param height magasság
     * @return az átméretezett kép
     */
    public BufferedImage sizedInstanceByHeight(int height) {
        int width = (int) (height * (float) original().getWidth() / original().getHeight());
        return sizedInstance(width, height);
    }

    @Override
    public void dispose() {
        img = null;
        cpy = null;
        swingIcon = null;
    }
}
