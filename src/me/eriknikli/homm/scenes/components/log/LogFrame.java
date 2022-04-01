package me.eriknikli.homm.scenes.components.log;

import me.eriknikli.homm.HoMM;
import me.eriknikli.homm.gameplay.Hero;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Az eseményeket itt lehet megnézni
 */
public class LogFrame extends JFrame {

    private final JList<String> logs = new JList<>();
    private final List<LogNode> log = new ArrayList<>();

    public LogFrame() {
        setVisible(true);
        setIconImage(HoMM.game().getIconImage());
        setBounds(10, 10, 200, 500);
        setTitle("History");
        var pane = new JScrollPane(logs);
        setContentPane(pane);
        logs.setModel(new AbstractListModel<String>() {

            @Override
            public int getSize() {
                return log.size();
            }

            @Override
            public String getElementAt(int index) {
                return log.get(index).log;
            }
        });
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _update();
    }

    public void log(String log, Hero which) {
        this.log.add(new LogNode(log, which));
        logs.setModel(new AbstractListModel<String>() {

            @Override
            public int getSize() {
                return LogFrame.this.log.size();
            }

            @Override
            public String getElementAt(int index) {
                return LogFrame.this.log.get(index).log;
            }
        });
        logs.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                var which = LogFrame.this.log.get(index).caller;
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(which == null ? Color.DARK_GRAY : which.color());
                c.setForeground(Color.WHITE);
                return c;
            }
        });
        _update();
    }

    public void _update() {
        //pack();
    }

    public static class LogNode {

        public String log;
        public Hero caller;

        public LogNode(String log, Hero caller) {
            this.log = log;
            this.caller = caller;
        }
    }
}
