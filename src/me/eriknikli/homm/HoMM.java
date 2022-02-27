package me.eriknikli.homm;

import me.eriknikli.homm.scenes.GameScene;
import me.eriknikli.homm.scenes.Scene;
import me.eriknikli.homm.utils.Utils;

import javax.swing.*;

/**
 * A játék class-e, itt található a main függvény is
 */
public class HoMM extends JFrame {

    private static HoMM game;

    public static HoMM game() {
        return game;
    }

    /**
     * A main függvény, innen indul a program
     *
     * @param args indítási argumentumok
     */
    public static void main(String[] args) {
        game = new HoMM();
        for (int i = 0; i < args.length; i++) {
            try {
                if (args[i].equalsIgnoreCase("-width")) {

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    /**
     * Éppen
     */
    private Scene scene;

    public HoMM() {
        initFrame();
    }

    /**
     * Beállítja a frame címét, méretét, stb...
     */
    public void initFrame() {
        setTitle("Heroes of Might and Magic - Copy");
        setBounds(0, 0, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setScene(new GameScene());
    }

    /**
     * @return az éppen aktív scene-t
     */
    public Scene scene() {
        return scene;
    }

    /**
     * Beállítja az új aktív scene-t, a jelenelgit pedig dispose-olja
     *
     * @param s az új Scene
     */
    public void setScene(Scene s) {
        Utils.tryDispose(scene());
        this.scene = s;
    }

}
