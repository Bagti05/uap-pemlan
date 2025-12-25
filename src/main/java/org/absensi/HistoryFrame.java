package org.absensi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HistoryFrame extends JFrame {

    public HistoryFrame() {
        setTitle("History Absensi");
        setSize(420, 300);
        setLocationRelativeTo(null);

        JTextArea area = new JTextArea();
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setEditable(false);

        ArrayList<Absensi> list = AbsensiFileUtil.loadData();
        for (Absensi a : list) {
            area.append(a.getNama() + " - " + a.getStatus() +
                    " (" + a.getTanggal() + ")\n");
        }

        add(new JScrollPane(area));
        setVisible(true);
    }
}