package me.eriknikli.homm.scenes;

import me.eriknikli.homm.utils.Disposable;

import javax.swing.*;
import java.awt.*;

/**
 * Scene olyan osztály, ami egy JPanel, ContentPane-nek van szánva
 */
public class Scene extends JPanel implements Disposable {

    /**
     * Inicializálja a scene-t
     */
    public Scene() {
    }

    @Override
    public void dispose() {
    }
}
