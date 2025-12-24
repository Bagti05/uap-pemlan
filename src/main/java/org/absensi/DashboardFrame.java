package org.absensi;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {
        setTitle("Sistem Absensi Mahasiswa");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== WARNA =====
        Color HEADER = new Color(26, 35, 126);      // coklat tua
        Color BG = new Color(40, 53, 147);         // coklat utama
        Color BTN_NORMAL = new Color(12, 51, 73); // soft
        Color BTN_HOVER = BTN_NORMAL.brighter();    // terang

        setLayout(new BorderLayout());

        // ===== HEADER =====
        JLabel title = new JLabel("SISTEM ABSENSI MAHASISWA", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(HEADER);
        headerPanel.setPreferredSize(new Dimension(700, 80));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(title, BorderLayout.CENTER);

        // ===== BUTTON =====
        JButton btnInput = createMenuButton("Input Absensi", BTN_NORMAL, BTN_HOVER);
        JButton btnData = createMenuButton("Data Absensi", BTN_NORMAL, BTN_HOVER);
        JButton btnHistory = createMenuButton("History Absensi", BTN_NORMAL, BTN_HOVER);

        btnInput.addActionListener(e -> new AbsensiFormFrame(null));
        btnData.addActionListener(e -> new AbsensiTableFrame());
        btnHistory.addActionListener(e -> new HistoryFrame());

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 25));
        centerPanel.setBackground(BG);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));

        centerPanel.add(btnInput);
        centerPanel.add(btnData);
        centerPanel.add(btnHistory);

        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ===== MENU BUTTON DENGAN HOVER =====
    private JButton createMenuButton(String text, Color normal, Color hover) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setBackground(normal);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hover);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(normal);
                btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return btn;
    }
}
