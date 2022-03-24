package me.eriknikli.homm;

import me.eriknikli.homm.data.Config;
import me.eriknikli.homm.data.Registry;
import me.eriknikli.homm.gameplay.Difficulty;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.scenes.PrepScene;
import me.eriknikli.homm.scenes.Scene;
import me.eriknikli.homm.utils.Disposable;
import me.eriknikli.homm.utils.LaunchParameter;
import me.eriknikli.homm.utils.Log;
import me.eriknikli.homm.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Objects;

/**
 * A játék class-e, itt található a main függvény is
 */
public class HoMM extends JFrame implements Disposable {

    /**
     * A játék objektumot tartalmazza
     */
    private static HoMM game;

    /**
     * Indítási paramétereket tartalmazó osztály
     */
    private static LaunchParameter params;

    /**
     * Configot tartalmazza
     */
    private static Config cfg;

    /**
     * @return a játék objektuma
     */
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
     *              <li>
     *                  -resizable, beállítja, hogy az ablak átméretezhető legyen
     *              </li>
     *              <li>
     *                  -notresizable, beállítja, hogy az ablak ne legyen átméretezhető
     *              </li>
     *              <li>
     *                  -loglevel, beállítja, hogy milyen szinten logoljon a program, NONE = 0, INFO = 1, WARN = 2, ERR = 3, DEBUG = 4
     *              </li>
     *             <li>
     *                 -ignoreconfig, beállítja, hogy a config fájlt ne töltse be
     *              </li>
     *             </ul>
     */
    public static void main(String[] args) {
        Registry.init();
        cfg = Config.load();
        SwingUtilities.invokeLater(() -> {
            cfg.save();
            processArgs(args);
            trySettingNimbusLaF();
            game = new HoMM();
        });
    }

    /**
     * @return a Configot
     */
    public static Config cfg() {
        return cfg;
    }

    /**
     * Feldolgozza a kapott argumentumokat
     *
     * @param args az argumentumok
     */
    public static void processArgs(String[] args) {
        params = new LaunchParameter();
        boolean ignoreConfig = false;
        for (int i = 0; i < args.length; i++) {
            try {
                if (args[i].startsWith("-")) {
                    if (args[i].equalsIgnoreCase("-ignoreConfig")) {
                        ignoreConfig = true;
                    }
                    if (args[i].equalsIgnoreCase("-resizable")) {
                        params.resizable = true;
                    }
                    if (args[i].equalsIgnoreCase("-notresizable")) {
                        params.resizable = false;
                    }
                    if (args[i].equalsIgnoreCase("-loglevel")) {
                        try {
                            Log.setLogLevel(Integer.parseInt(args[i + 1]));
                        } catch (NumberFormatException e) {
                            Log.setLogLevel(switch (args[i + 1].toLowerCase()) {
                                //  case "none" -> 0;, redundáns a default case miatt
                                case "info" -> Log.INFO;
                                case "warn" -> Log.WARN;
                                case "err" -> Log.ERR;
                                case "debug" -> Log.DEBUG;
                                default -> Log.NONE;
                            });
                        }
                        i++;
                    }
                    if (!ignoreConfig) {
                        if (args[i].equalsIgnoreCase("-x")) {
                            params.x = Integer.parseInt(args[i + 1]);
                            i++;
                        }
                        if (args[i].equalsIgnoreCase("-y")) {
                            params.y = Integer.parseInt(args[i + 1]);
                            i++;
                        }
                        if (args[i].equalsIgnoreCase("-width") || args[i].equalsIgnoreCase("-w")) {
                            params.width = Integer.parseInt(args[i + 1]);
                            i++;
                        }
                        if (args[i].equalsIgnoreCase("-height") || args[i].equalsIgnoreCase("-h")) {
                            params.height = Integer.parseInt(args[i + 1]);
                            i++;
                        }
                        if (args[i].equalsIgnoreCase("-mw") || args[i].equalsIgnoreCase("-maximize") || args[i].equalsIgnoreCase("-maximizewindow")) {
                            params.maximizeWindow = true;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Megpróbálja a Nimbus kinézetet beállítani
     */
    public static void trySettingNimbusLaF() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
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
     * Beállítja a frame címét, ikonját, méretét, stb...
     */
    public void initFrame() {
        params.initFromCfg();
        try {
            setIconImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/units/icon.png"))));
        } catch (Exception e) {
            Log.err("Error occurred while setting up the icon for JFrame.", e);
        }
        setTitle("Heroes of Might and Magic - Copy");
        setBounds(params.x, params.y, params.width, params.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(params.resizable);
        if (params.maximizeWindow) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setVisible(true);
        setScene(new PrepScene(new PlayerHero(cfg().playerName, Difficulty.NORMAL)));
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
        if (scene() != null) {
            remove(scene());
            Utils.tryDispose(scene());
        }
        this.scene = s;
        add(this.scene);
        setContentPane(this.scene);
    }

    public static void update() {
        game()._update();
    }

    public void _update() {
        if (scene() != null) {
            scene()._update();
            scene().repaint();
        }
    }

    @Override
    public void dispose() {
        Registry.dispose();
    }
}
