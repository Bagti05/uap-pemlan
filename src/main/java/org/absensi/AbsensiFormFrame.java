package org.absensi;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class AbsensiFormFrame extends JFrame {

    private JTextField txtNim, txtNama;
    private JComboBox<String> cbStatus;
    private Absensi editData;


    private String nimLama;

    public AbsensiFormFrame(Absensi data) {
        this.editData = data;

        setTitle("Form Absensi Mahasiswa");
        setSize(520, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //WARNA
        Color bgColor   = new Color(214, 168, 124);
        Color btnNormal = new Color(235, 200, 160);
        Color btnHover  = new Color(255, 225, 190); // LEBIH TERANG
        Color btnClick  = new Color(200, 220, 236);

        getContentPane().setBackground(bgColor);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.CENTER;

        // LABEL
        JLabel lblNim = new JLabel("NIM");
        JLabel lblNama = new JLabel("Nama");
        JLabel lblStatus = new JLabel("Status");

        Font labelFont = new Font("Segoe UI", Font.BOLD, 15);
        lblNim.setFont(labelFont);
        lblNama.setFont(labelFont);
        lblStatus.setFont(labelFont);

        // INPUT
        txtNim = new JTextField(18);
        txtNama = new JTextField(18);

        txtNim.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNama.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        ((AbstractDocument) txtNim.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((AbstractDocument) txtNama.getDocument()).setDocumentFilter(new TextOnlyFilter());

        cbStatus = new JComboBox<>(new String[]{"Hadir", "Izin", "Alpa"});
        cbStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        //TOMBOL SIMPAN
        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setPreferredSize(new Dimension(160, 42));
        btnSimpan.setBackground(btnNormal);
        btnSimpan.setForeground(Color.BLACK);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setBorderPainted(false);
        btnSimpan.setOpaque(true);

        // CURSOR JADI TANGAN
        btnSimpan.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //HOVER TERANG
        btnSimpan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSimpan.setBackground(btnHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSimpan.setBackground(btnNormal);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btnSimpan.setBackground(btnClick);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btnSimpan.setBackground(btnHover);
            }
        });

        //LAYOUT
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblNim, gbc);
        gbc.gridx = 1;
        add(txtNim, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblNama, gbc);
        gbc.gridx = 1;
        add(txtNama, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblStatus, gbc);
        gbc.gridx = 1;
        add(cbStatus, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(22, 10, 10, 10);
        add(btnSimpan, gbc);


        if (editData != null) {
            nimLama = editData.getNim();
            txtNim.setText(editData.getNim());
            txtNama.setText(editData.getNama());
            cbStatus.setSelectedItem(editData.getStatus());
        }

        btnSimpan.addActionListener(e -> saveData());
        setVisible(true);
    }

    //SIMPAN / UPDATE
    private void saveData() {
        if (txtNim.getText().isEmpty() || txtNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "NIM dan Nama tidak boleh kosong",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<Absensi> list = AbsensiFileUtil.loadData();

        if (editData == null) {
            list.add(new Absensi(
                    txtNim.getText(),
                    txtNama.getText(),
                    cbStatus.getSelectedItem().toString(),
                    LocalDate.now()
            ));
        } else {
            for (Absensi a : list) {
                if (a.getNim().equals(nimLama)) {
                    a.setNim(txtNim.getText());
                    a.setNama(txtNama.getText());
                    a.setStatus(cbStatus.getSelectedItem().toString());
                    break;
                }
            }
        }

        AbsensiFileUtil.saveData(list);
        JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
        dispose();
    }

    // FILTER ANGKA
    static class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int o, String s, AttributeSet a)
                throws BadLocationException {
            if (s.matches("\\d+")) super.insertString(fb, o, s, a);
        }

        @Override
        public void replace(FilterBypass fb, int o, int l, String s, AttributeSet a)
                throws BadLocationException {
            if (s.matches("\\d*")) super.replace(fb, o, l, s, a);
        }
    }

    // FILTER HURUF
    static class TextOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int o, String s, AttributeSet a)
                throws BadLocationException {
            if (s.matches("[a-zA-Z ]+")) super.insertString(fb, o, s, a);
        }

        @Override
        public void replace(FilterBypass fb, int o, int l, String s, AttributeSet a)
                throws BadLocationException {
            if (s.matches("[a-zA-Z ]*")) super.replace(fb, o, l, s, a);
        }
    }
}
