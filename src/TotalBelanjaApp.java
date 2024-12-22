import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TotalBelanjaApp extends JFrame {
    private JTextField namaBarangField, hargaBarangField, jumlahBarangField;
    private JTable belanjaTable;
    private DefaultTableModel tableModel;
    private JLabel imageLabel;
    private List<Item> belanjaList;
    private File selectedImageFile;

    public TotalBelanjaApp() {
        belanjaList = new ArrayList<>();

        // Set up frame
        setTitle("Aplikasi Total Belanja");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data Barang"));

        inputPanel.add(new JLabel("Nama Barang:"));
        namaBarangField = new JTextField();
        inputPanel.add(namaBarangField);

        inputPanel.add(new JLabel("Harga Barang:"));
        hargaBarangField = new JTextField();
        inputPanel.add(hargaBarangField);

        inputPanel.add(new JLabel("Jumlah Barang:"));
        jumlahBarangField = new JTextField();
        inputPanel.add(jumlahBarangField);

        JButton pilihGambarButton = new JButton("Pilih Gambar");
        inputPanel.add(pilihGambarButton);
        imageLabel = new JLabel();
        inputPanel.add(imageLabel);

        JButton tambahButton = new JButton("Tambah Barang");
        tambahButton.setPreferredSize(new Dimension(150, 30)); // Ukuran Tombol
        inputPanel.add(tambahButton);

        JButton updateButton = new JButton("Update Barang");
        updateButton.setPreferredSize(new Dimension(150, 30)); // Ukuran Tombol
        inputPanel.add(updateButton);

        JButton deleteButton = new JButton("Hapus Barang");
        deleteButton.setPreferredSize(new Dimension(150, 30)); // Ukuran Tombol
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.WEST);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"Nama", "Harga", "Jumlah", "Total", "Gambar"}, 0);
        belanjaTable = new JTable(tableModel);

        // Set ukuran kolom tabel
        belanjaTable.getColumnModel().getColumn(0).setPreferredWidth(200); // Nama Barang
        belanjaTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Harga
        belanjaTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Jumlah
        belanjaTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Total
        belanjaTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Gambar

        JScrollPane tableScrollPane = new JScrollPane(belanjaTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        JButton hitungTotalButton = new JButton("Hitung Total");
        bottomPanel.add(hitungTotalButton);

        JButton konversiButton = new JButton("Konversi ke USD");
        bottomPanel.add(konversiButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        pilihGambarButton.addActionListener(e -> pilihGambar());
        tambahButton.addActionListener(e -> tambahBarang());
        updateButton.addActionListener(e -> updateBarang());
        deleteButton.addActionListener(e -> hapusBarang());
        hitungTotalButton.addActionListener(e -> hitungTotal());
        konversiButton.addActionListener(e -> konversiKeUSD());

        setVisible(true);
    }

    private void pilihGambar() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());
            Image img = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        }
    }

    private void tambahBarang() {
        try {
            String nama = namaBarangField.getText();
            double harga = Double.parseDouble(hargaBarangField.getText());
            int jumlah = Integer.parseInt(jumlahBarangField.getText());

            if (nama.isEmpty() || selectedImageFile == null) {
                throw new IllegalArgumentException("Nama barang dan gambar harus diisi!");
            }

            double total = harga * jumlah;
            tableModel.addRow(new Object[]{nama, harga, jumlah, total, selectedImageFile.getName()});

            belanjaList.add(new Item(nama, harga, jumlah, total, selectedImageFile));
            clearInputFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBarang() {
        int selectedRow = belanjaTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nama = namaBarangField.getText();
            double harga = Double.parseDouble(hargaBarangField.getText());
            int jumlah = Integer.parseInt(jumlahBarangField.getText());

            if (nama.isEmpty() || selectedImageFile == null) {
                throw new IllegalArgumentException("Nama barang dan gambar harus diisi!");
            }

            double total = harga * jumlah;
            tableModel.setValueAt(nama, selectedRow, 0);
            tableModel.setValueAt(harga, selectedRow, 1);
            tableModel.setValueAt(jumlah, selectedRow, 2);
            tableModel.setValueAt(total, selectedRow, 3);
            tableModel.setValueAt(selectedImageFile.getName(), selectedRow, 4);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hapusBarang() {
        int selectedRow = belanjaTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.removeRow(selectedRow);
        belanjaList.remove(selectedRow);
    }

    private void hitungTotal() {
        double total = 0;
        for (Item item : belanjaList) {
            total += item.getTotal();
        }
        JOptionPane.showMessageDialog(this, "Total Belanja: Rp " + total, "Total", JOptionPane.INFORMATION_MESSAGE);
    }

    private void konversiKeUSD() {
        try {
            double total = 0;
            for (Item item : belanjaList) {
                total += item.getTotal();
            }

            // Dummy API Conversion Rate
            double conversionRate = 0.00007; // 1 Rupiah to USD (contoh saja)
            double totalUSD = total * conversionRate;

            JOptionPane.showMessageDialog(this, "Total dalam USD: $" + String.format("%.2f", totalUSD), "Konversi", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengonversi mata uang!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        namaBarangField.setText("");
        hargaBarangField.setText("");
        jumlahBarangField.setText("");
        imageLabel.setIcon(null);
        selectedImageFile = null;
    }

    public static void main(String[] args) {
        new TotalBelanjaApp();
    }
}

class Item {
    private String nama;
    private double harga;
    private int jumlah;
    private double total;
    private File gambar;

    public Item(String nama, double harga, int jumlah, double total, File gambar) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = total;
        this.gambar = gambar;
    }

    public double getTotal() {
        return total;
    }
}
