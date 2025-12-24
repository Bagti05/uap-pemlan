package org.absensi;

import java.awt.Desktop;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AbsensiFileUtil {

    private static final String DIR = "data";
    private static final String FILE_PATH = "data/absensi.csv";

    public static ArrayList<Absensi> loadData() {
        ArrayList<Absensi> list = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                if (header) { // skip header
                    header = false;
                    continue;
                }

                String[] d = line.split(",");
                if (d.length == 4) {
                    list.add(new Absensi(
                            d[0],
                            d[1],
                            d[2],
                            LocalDate.parse(d[3])
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveData(ArrayList<Absensi> list) {

        File dir = new File(DIR);
        if (!dir.exists()) dir.mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {

            pw.println("NIM,Nama,Status,Tanggal");

            for (Absensi a : list) {
                pw.println(
                        a.getNim() + "," +
                                a.getNama() + "," +
                                a.getStatus() + "," +
                                a.getTanggal()
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        openExcel();
    }

    public static void openExcel() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
