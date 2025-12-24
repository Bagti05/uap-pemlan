# SISTEM ABSENSI MAHASISWA
Aplikasi Desktop Java – Pemrograman Lanjut

---

## Identitas Mahasiswa
- **Nama** : Althof Avisena
- **NIM** : 202410370110306
---
- **Nama** : Bagti Putra Pratama
- **NIM** : 202410370110330

---

## Deskripsi Aplikasi
Sistem Absensi Mahasiswa adalah aplikasi desktop berbasis **Java Swing** yang digunakan untuk mencatat, mengelola, dan menyimpan data absensi mahasiswa secara **permanen** menggunakan file **CSV (Excel)**.

Aplikasi ini menerapkan konsep **Object Oriented Programming (OOP)**, **Graphical User Interface (GUI)**, **Event Handling**, dan **File Handling** sesuai dengan pembelajaran **Modul 1 sampai Modul 6** pada mata kuliah Pemrograman Lanjut.

---

## Fitur Aplikasi
- Input data absensi mahasiswa (NIM, Nama, Status, Tanggal)
- Validasi input:
    - NIM hanya boleh angka
    - Nama hanya boleh huruf
- Menampilkan data absensi dalam tabel
- Menambah data absensi
- Mengupdate data absensi
- Menghapus data absensi
- Menampilkan riwayat (history) absensi
- Penyimpanan data secara permanen
- Data otomatis tersimpan dan ter-update di file CSV (Excel)
- Tampilan GUI berwarna dan interaktif
- Efek hover dan klik pada tombol

---

## Implementasi Modul 1 – 6

### Modul 1 – Dasar Java & Struktur Program
**Implementasi:**
- Struktur project Java
- Class utama sebagai entry point

**File terkait:**
- `App.java`

---

### Modul 2 – Object Oriented Programming (OOP)
**Implementasi:**
- Class dan Object
- Encapsulation (getter & setter)
- Model data absensi

**File terkait:**
- `Absensi.java`

---

### Modul 3 – GUI dengan Java Swing
**Implementasi:**
- JFrame, JPanel, JLabel, JTextField
- JButton, JTable, JScrollPane
- Layout Manager

**File terkait:**
- `DashboardFrame.java`
- `AbsensiFormFrame.java`
- `AbsensiTableFrame.java`
- `HistoryFrame.java`

---

### Modul 4 – Event Handling & Validasi
**Implementasi:**
- ActionListener dan MouseListener
- Validasi input NIM dan Nama
- Event klik tombol (Simpan, Update, Delete)
- Efek hover dan klik pada tombol

**File terkait:**
- `AbsensiFormFrame.java`
- `AbsensiTableFrame.java`

---

### Modul 5 – File Handling (CSV)
**Implementasi:**
- Membaca dan menulis file CSV
- Data tersimpan permanen
- Update dan delete langsung ke file
- File otomatis dibuat jika belum ada

**File terkait:**
- `AbsensiFileUtil.java`
- `data/absensi.csv`

---

### Modul 6 – Integrasi Aplikasi
**Implementasi:**
- Integrasi seluruh modul
- Navigasi antar halaman
- Aplikasi siap dijalankan

**File terkait:**
- Seluruh class dalam package `org.absensi`


---

## Cara Menjalankan Program
1. Pastikan **JDK** sudah terinstall.
2. Buka project menggunakan **IntelliJ IDEA**.
3. Pastikan `pom.xml` tidak error.
4. Jalankan file:
5. Aplikasi akan menampilkan **Dashboard Sistem Absensi Mahasiswa**.

---

## Penyimpanan Data (Excel / CSV)
- Data disimpan pada file:
- File CSV akan:
- Otomatis ter-update saat tambah data
- Otomatis ter-update saat update data
- Otomatis ter-update saat hapus data
- File CSV dapat langsung dibuka menggunakan **Microsoft Excel**
- Data tidak hilang meskipun aplikasi ditutup

---

## Catatan
- Aplikasi tidak menggunakan database
- Penyimpanan menggunakan File Handling CSV
- Cocok untuk tugas Pemrograman Lanjut
- Mudah dikembangkan lebih lanjut

---

## Kesimpulan
Aplikasi Sistem Absensi Mahasiswa ini berhasil mengimplementasikan **Modul 1 sampai Modul 6** mata kuliah Pemrograman Lanjut dengan menggabungkan konsep Java dasar, OOP, GUI Swing, Event Handling, dan File Handling ke dalam satu aplikasi desktop yang utuh dan fungsional.

---



