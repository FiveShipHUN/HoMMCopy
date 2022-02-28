package me.eriknikli.homm.scenes;

import javax.swing.*;
import java.awt.*;

public class GameScene extends Scene {

    /**
     * A layout maga
     */
    private final GridLayout layout;

    /**
     * Inicializálja a scene-t
     */
    public GameScene() {
        layout = new GridLayout(1, 3);
        addActor(new GameBoard());
    }

    /**
     * @return a scene layout-ját adja vissza
     */
    public GridLayout gLayout() {
        return layout;
    }

    /**
     * Ezen keresztül lehet hozzáadni komponenseket a Scene-hez
     *
     * @param c komponens, amit szeretnénk hozzáadni a scene-hez
     */
    public void addActor(Component c) {
        add(c);
    }
}
