package me.eriknikli.homm;

import javax.swing.*;

public class HoMM extends JFrame {

    private static HoMM game;

    public static HoMM game() {
        return game;
    }

    public static void main(String[] args) {
        game = new HoMM();
    }

    public HoMM() {
        initFrame();
    }

    public void initFrame() {
        setTitle("Heroes of Might and Magic - Copy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
