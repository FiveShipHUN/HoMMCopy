package me.eriknikli.homm.scenes;

import me.eriknikli.homm.utils.Disposable;

import javax.swing.JPanel;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Scene olyan osztály, ami egy JPanel, ContentPane-nek van szánva
 */
public class Scene extends JPanel implements Disposable {

    private List<Component> components = new ArrayList<>();

    /**
     * Inicializálja a scene-t
     */
    public Scene() {
    }

    @Override
    public void dispose() {
        System.gc();
    }
}
