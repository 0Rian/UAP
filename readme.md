# TotalBelanjaApp

**TotalBelanjaApp** adalah aplikasi manajemen total belanja berbasis GUI menggunakan Java Swing. Aplikasi ini memungkinkan pengguna untuk menambah, memperbarui, menghapus barang, menghitung total harga belanjaan, serta mengonversi total belanja dari Rupiah ke USD. Pengguna juga dapat memilih gambar produk untuk setiap barang yang ditambahkan.

## Fitur
- Menambahkan barang dengan nama, harga, jumlah, dan gambar produk.
- Memperbarui data barang yang sudah ditambahkan.
- Menghapus barang dari daftar belanja.
- Menghitung total harga seluruh barang dalam daftar belanja.
- Mengonversi total belanja dari Rupiah ke USD dengan kurs fiktif.
- Tampilan GUI yang user-friendly menggunakan Java Swing.

## Cara Menjalankan
1. Pastikan Java sudah terinstall di sistem Anda.
2. Clone repository ini:
   ```bash
   git clone https://github.com/0Rian/UAP.git
3. Run the program to see the output.

## Teknologi yang Digunakan
- **Java**: Bahasa pemrograman utama yang digunakan.
- **Java Swing**: Untuk membuat antarmuka pengguna berbasis GUI.
- **JTable**: Untuk menampilkan daftar barang dalam bentuk tabel.
- **JFileChooser**: Untuk memilih gambar dari sistem file.
- **File Handling**: Untuk menangani file gambar yang dipilih.
## Cara Penggunaan

1. **Menambah Barang**:
   - Isi kolom "Nama Barang", "Harga Barang", dan "Jumlah Barang".
   - Klik tombol **Pilih Gambar** untuk memilih gambar barang.
   - Klik **Tambah Barang** untuk menambahkan barang ke tabel.

2. **Mengupdate Barang**:
   - Pilih barang di tabel yang ingin diperbarui.
   - Isi ulang kolom input dan pilih gambar baru jika diperlukan.
   - Klik **Update Barang** untuk menyimpan perubahan.

3. **Menghapus Barang**:
   - Pilih barang di tabel yang ingin dihapus.
   - Klik **Hapus Barang** untuk menghapus barang dari tabel.

4. **Menghitung Total Belanja**:
   - Klik **Hitung Total** untuk menghitung total harga semua barang.

5. **Konversi ke USD**:
- Klik **Konversi ke USD** untuk mengonversi total belanja ke mata uang USD (menggunakan kurs dummy).


## Code Review
Berikan kami ulasan terkait source code ini dalam bentuk komentar, yang dalam tools peninjauan kode, menjelaskan masalah apa yang anda temukan. Penulis kode serta reviewer akan terlibat dalam diskusi tentang perubahan kode.

**Penulis kode akan membuat/memperbarui kode berdasarkan umpan balik yang diterima**.

Setelah perubahan terjadi dan semua permasalahan diselesaikan, perubahan kode yang disetujui akan diintegrasikan ke dalam basis kode utama, biasanya menggunakan sistem kontrol versi.

## **Struktur Kode:**
-  Kode disusun dengan baik menggunakan kelas terpisah ( Item) untuk merepresentasikan barang.
- Logika aplikasi dipisahkan menjadi fungsi kecil untuk mempermudah pemeliharaan.
  **Antarmuka Pengguna (GUI):**
- Menggunakan komponen GUI yang relevan seperti JTable, JFileChooser, dan JOptionPane.
- Tampilannya intuitif dengan tata letak BorderLayoutdan GridLayout.
  **Penanganan Pengecualian:**
- menyertakan kesalahan seperti input kosong, format angka tidak valid, atau gambar yang tidak dipilih dengan baik menggunakan try-catch.
  **Fitur CRUD:**
- Implementasi lengkap fitur CRUD untuk data barang, termasuk update tabel secara langsung.
  **Penanganan Gambar:**
- Mendukung gambar untuk setiap barang menggunakan JFileChooserdan ImageIcon.


## License
(2024) Program ini dibuat dalam penyelesaian tugas mata kuliah Program Lanjut, dan dipublikasikan untuk bebas studi.