package me.eriknikli.homm.data;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Properties;

/**
 * Config file-t kezelő osztály
 */
public class Config {

    /**
     * Játékos neve, esetleges MP implementáció miatt
     */
    public String playerName = "Player";
    // FOR MP implementation public UUID playerID;

    /**
     * X poz. az ablaknak
     */
    public int x = 0;

    /**
     * Y poz. az ablaknak
     */
    public int y = 0;

    /**
     * Ablak szélessége
     */
    public int width = 1280;

    /**
     * Ablak magassága
     */
    public int height = 768;

    /**
     * Kinagyítsa-e az ablakot automatikusan? Ha igen, akkor az x, y, width és height paraméterek elveszítik értelmüket
     */
    public boolean maximizeWindow = false;

    /**
     * Betölti a {@code config.properties} fájlból a Config-ot
     * Ezt az objektumot a {@code Config.instance()}-en keresztül lehet elérni
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
            cfg.maximizeWindow = Boolean.parseBoolean(prop.getProperty("MaximizeWindow"));
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
        prop.put("PlayerName", playerName);
        prop.put("X", Objects.toString(x));
        prop.put("Y", Objects.toString(y));
        prop.put("Width", Objects.toString(width));
        prop.put("Height", Objects.toString(height));
        prop.put("MaximizeWindow", Objects.toString(maximizeWindow));
        try (var writer = new FileWriter("config.properties")) {
            prop.store(writer, "");
        } catch (Exception ignored) {
        }
    }

}
