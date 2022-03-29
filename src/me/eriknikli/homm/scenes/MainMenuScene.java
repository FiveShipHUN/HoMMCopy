package me.eriknikli.homm.scenes;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.gameplay.Difficulty;
import me.eriknikli.homm.gameplay.Hero;
import me.eriknikli.homm.gameplay.PlayerHero;
import me.eriknikli.homm.utils.Log;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Főmenüt leíró Scene
 */
public class MainMenuScene extends Scene {


    /**
     * Inicializálja a scene-t
     */
    public MainMenuScene() {
        setLayout(new GridBagLayout());
        JLabel lbl = new JLabel("Heroes of Might and Magic - Duel");
        lbl.setFont(new Font("Times New Roman", Font.BOLD, 30));
        var c = new GridBagConstraints();
        add(lbl, c);
        JButton startGameBtn = new JButton("Start Game");
        startGameBtn.addActionListener(e -> {
            Object[] o = new Object[Difficulty.values().length];
            for (int i = 0; i < o.length; i++) {
                o[i] = Difficulty.values()[i].displayName();
            }
            int r = JOptionPane.showOptionDialog(null, "Select Difficulty", "Difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, o, o[0]);
            Log.debug(r + " result.");
            if (0 <= r && r < Difficulty.values().length) {
                var d = Difficulty.values()[r];
                var hero = new PlayerHero(HoMM.cfg().playerName, d);
                EventQueue.invokeLater(() -> {
                    toStartGame(hero);
                });
            }
        });
        int i = 1;
        c.gridx = 0;
        c.gridy = i++;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(startGameBtn, c);
        JButton closeBtn = new JButton("Close");
        c.gridx = 0;
        c.gridy = i++;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(closeBtn, c);
        closeBtn.addActionListener(e -> {
            HoMM.game().dispose();
            System.exit(0);
        });
    }

    public void toStartGame(Hero hero) {
        HoMM.game().setScene(new PrepScene(hero));
    }


}
