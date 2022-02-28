package me.eriknikli.homm.scenes;

import javax.swing.*;
import java.awt.*;

public class GameScene extends Scene {

    /**
     * A layout maga
     */
    private final GridBagLayout layout;

    /**
     * A layout beállításait tartalmazza, ezen keresztül kontrollálhatjuk a komponenseket
     */
    private final GridBagConstraints constraints;

    /**
     * Inicializálja a scene-t
     */
    public GameScene() {
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        setLayout(layout);
        addActor(new GameBoard());
    }

    /**
     * Beállítások a komponensre
     */
    public GridBagConstraints actorSettings() {
        return constraints;
    }

    /**
     * Ezen keresztül lehet hozzáadni komponenseket a Scene-hez
     *
     * @param c komponens, amit szeretnénk hozzáadni a scene-hez
     */
    public void addActor(Component c) {
        add(c, constraints);
    }


}
