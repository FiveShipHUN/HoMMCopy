package me.eriknikli.homm.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 * Config file-t kezelő osztály
 */
public class Config {

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
     * Betölti a {@code config.properties} fájlból a Config-ot
     *
     * @return Az így készült objektum
     */
    public static Config load() {
        try (var reader = new FileReader("config.properties")) {
            Properties prop = new Properties();
            prop.load(reader);
            Config cfg = new Config();
            cfg.playerName = prop.getProperty("PlayerName");
            cfg.x = Integer.parseInt(prop.getProperty("X"));
            cfg.y = Integer.parseInt(prop.getProperty("Y"));
            cfg.width = Integer.parseInt(prop.getProperty("Width"));
            cfg.height = Integer.parseInt(prop.getProperty("Height"));
            return cfg;
        } catch (Exception e) {
            return new Config();
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
