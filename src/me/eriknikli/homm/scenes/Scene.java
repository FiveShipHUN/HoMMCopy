package me.eriknikli.homm.scenes;

import me.eriknikli.homm.utils.Disposable;

import javax.swing.*;

/**
 * Scene olyan osztály, ami egy JPanel, ContentPane-nek van szánva, fő cél inkább az, hogy ne lehessen akármilyen JPanel-t beállítani ContentPane-nek
 */
public class Scene extends JPanel implements Disposable {
    /**
     * Inicializálja a scene-t
     */
    public Scene() {
    }

    public void _update() {
    }

    @Override
    public void dispose() {
        System.gc();
    }
}
