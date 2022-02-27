package me.eriknikli.homm;

import me.eriknikli.homm.scenes.GameScene;
import me.eriknikli.homm.scenes.Scene;
import me.eriknikli.homm.utils.LaunchParameter;
import me.eriknikli.homm.utils.Utils;

import javax.swing.*;

/**
 * A játék class-e, itt található a main függvény is
 */
public class HoMM extends JFrame {

    private static HoMM game;
    private static LaunchParameter params;

    public static HoMM game() {
        return game;
    }

    /**
     * A main függvény, innen indul a program
     *
     * @param args indítási argumentumok<br>
     *             <ul>
     *              <li>
     *                  -x (szám), x pozíció
     *              </li>
     *              <li>
     *                  -y (szám), y pozíció
     *              </li>
     *              <li>
     *                  -width | -w (szám), szélesség
     *              </li>
     *             <li>
     *                   -height | -h (szám), magasság
     *              </li>
     *             </ul>
     */
    public static void main(String[] args) {
        processArgs(args);
        game = new HoMM();
    }

    /**
     * Feldolgozza a kapott argumentumokat
     * @param args az argumentumok
     */
    public static void processArgs(String[] args) {
        params = new LaunchParameter();
        for (int i = 0; i < args.length; i++) {
            try {
                if (args[i].equalsIgnoreCase("-x")) {
                    params.x = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equalsIgnoreCase("-y")) {
                    params.y = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equalsIgnoreCase("-width") || args[i].equalsIgnoreCase("-w")) {
                    params.width = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equalsIgnoreCase("-height") || args[i].equalsIgnoreCase("-h")) {
                    params.height = Integer.parseInt(args[i + 1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Éppen aktuális scene
     */
    private Scene scene;

    /**
     * Konstruktor, meghívja az {@code initFrame()} függvényt
     */
    public HoMM() {
        initFrame();
    }

    /**
     * Beállítja a frame címét, méretét, stb...
     */
    public void initFrame() {
        setTitle("Heroes of Might and Magic - Copy");
        setBounds(params.x, params.y, params.width, params.height);
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
