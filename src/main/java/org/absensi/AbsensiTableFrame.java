package org.absensi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AbsensiTableFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public AbsensiTableFrame() {
        setTitle("Data Absensi");
        setSize(750, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //TABEL
        model = new DefaultTableModel(
                new String[]{"NIM", "Nama", "Status", "Tanggal"}, 0
        );

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
        setStatusColor();

        //PANEL TOMBOL
        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(220, 235, 255));
        panelBtn.setPreferredSize(new Dimension(100, 70));

        JButton btnTambah = createButton("Tambah", new Color(90, 140, 240));
        JButton btnUpdate = createButton("Update", new Color(110, 190, 110));
        JButton btnDelete = createButton("Delete", new Color(220, 80, 80));

        panelBtn.add(btnTambah);
        panelBtn.add(btnUpdate);
        panelBtn.add(btnDelete);

        add(panelBtn, BorderLayout.SOUTH);

        // AKSI
        btnTambah.addActionListener(e ->
                new AbsensiFormFrame(null)
        );

        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());

        setVisible(true);
    }

    // LOAD DATA
    private void loadData() {
        model.setRowCount(0);
        ArrayList<Absensi> list = AbsensiFileUtil.loadData();
        for (Absensi a : list) {
            model.addRow(new Object[]{
                    a.getNim(),
                    a.getNama(),
                    a.getStatus(),
                    a.getTanggal()
            });
        }
    }

    //  UPDATE DATA
    private void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate");
            return;
        }

        Absensi data = new Absensi(
                model.getValueAt(row, 0).toString(),
                model.getValueAt(row, 1).toString(),
                model.getValueAt(row, 2).toString(),
                java.time.LocalDate.parse(model.getValueAt(row, 3).toString())
        );

        new AbsensiFormFrame(data);
    }

    // DELETE DATA
    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ArrayList<Absensi> list = AbsensiFileUtil.loadData();
            list.remove(row);
            AbsensiFileUtil.saveData(list);
            loadData();
        }
    }

    private JButton createButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(120, 40));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(baseColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Color hoverColor = baseColor.brighter();

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(baseColor);
            }
        });

        return btn;
    }

    //  WARNA STATUS =====
    private void setStatusColor() {
        table.getColumnModel().getColumn(2)
                .setCellRenderer(new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {

                        Component c = super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);

                        if (value != null && !isSelected) {
                            String status = value.toString();
                            if (status.equals("Hadir")) {
                                c.setBackground(new Color(150, 230, 150));
                            } else if (status.equals("Izin")) {
                                c.setBackground(new Color(255, 240, 150));
                            } else if (status.equals("Alpa")) {
                                c.setBackground(new Color(255, 170, 170));
                            } else {
                                c.setBackground(Color.WHITE);
                            }
                        } else if (isSelected) {
                            c.setBackground(new Color(180, 200, 240));
                        }

                        setHorizontalAlignment(CENTER);
                        return c;
                    }
                });
    }
}
