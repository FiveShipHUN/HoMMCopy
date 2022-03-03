package me.eriknikli.homm.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Config file-t kezelő osztály
 */
public class Config {

    /**
     * Config instance
     */
    private static Config instance;

    /**
     * Játékos neve, esetleges MP implementáció miatt
     */
    public String playerName;
    // FOR MP implementation public UUID playerID;

    /**
     * X poz. az ablaknak
     */
    public int x;

    /**
     * Y poz. az ablaknak
     */
    public int y;

    /**
     * Ablak szélessége
     */
    public int width;

    /**
     * Ablak magassága
     */
    public int height;

    /**
     * @return a Config instance-t, ha még nincs betöltve, akkor betölti
     */
    public static Config instance() {
        if (instance == null) {
            load();
        }
        return instance;
    }

    /**
     * Betölti a {@code config.properties} fájlból a Config-ot
     * Ezt az objektumot a {@code Config.instance()}-en keresztül lehet elérni
     */
    public static void load() {
        try (var reader = new FileReader("config.properties")) {
            Properties prop = new Properties();
            prop.load(reader);
            Config cfg = new Config();
            cfg.playerName = prop.getProperty("PlayerName");
            cfg.x = Integer.parseInt(prop.getProperty("X"));
            cfg.y = Integer.parseInt(prop.getProperty("Y"));
            cfg.width = Integer.parseInt(prop.getProperty("Width"));
            cfg.height = Integer.parseInt(prop.getProperty("Height"));
            instance = cfg;
        } catch (Exception e) {
            instance = new Config();
        }
    }

    /**
     * Elmenti a {@code config.properties} fájlba ezt az objektumot
     */
    public void save() {
        Properties prop = new Properties();
        prop.setProperty("PlayerName", playerName);
        try (var writer = new FileWriter("config.properties")) {
            prop.store(writer, "");
        } catch (Exception ignored) {
        }
    }

}
