package org.absensi;

import java.time.LocalDate;

public class Absensi {
    private String nim;
    private String nama;
    private String status;
    private LocalDate tanggal;

    public Absensi(String nim, String nama, String status, LocalDate tanggal) {
        this.nim = nim;
        this.nama = nama;
        this.status = status;
        this.tanggal = tanggal;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getStatus() { return status; }
    public LocalDate getTanggal() { return tanggal; }

    public void setNim(String nim) { this.nim = nim; }
    public void setNama(String nama) { this.nama = nama; }
    public void setStatus(String status) { this.status = status; }
}
