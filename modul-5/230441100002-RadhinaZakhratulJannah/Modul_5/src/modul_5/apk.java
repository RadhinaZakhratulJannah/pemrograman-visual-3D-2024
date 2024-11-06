package modul_5;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class apk extends javax.swing.JFrame {
    // Deklarasi variabel koneksi ke database dan model tabel untuk Karyawan, Proyek, dan Transaksi
    Connection conn;
    private DefaultTableModel model_tblKaryawan;
    private DefaultTableModel model_tblProyek;
    private DefaultTableModel model_tblTransaksi;
    int durasiP;    
    public apk() {
        initComponents();
        setLocationRelativeTo(null);
        conn = koneksi.getConnection();//membuat koneksi ke database
        model_tblKaryawan = new DefaultTableModel();
        tblK.setModel(model_tblKaryawan);
        
        model_tblProyek = new DefaultTableModel();
        tblP.setModel(model_tblProyek); 
        
        model_tblTransaksi = new DefaultTableModel();
        tblT.setModel(model_tblTransaksi);
        
        model_tblKaryawan.addColumn("ID");//untuk mengisi kolom tabel Karyawan
        model_tblKaryawan.addColumn("Nama");
        model_tblKaryawan.addColumn("Jabatan");
        model_tblKaryawan.addColumn("Departemen"); 
        
        model_tblProyek.addColumn("ID");//untuk mengisi kolom tabel Proyek
        model_tblProyek.addColumn("Nama Proyek");
        model_tblProyek.addColumn("Durasi Pengerjaan"); 
        
        model_tblTransaksi.addColumn("Karyawan");//untuk mengisi kolom tabel Transaksi
        model_tblTransaksi.addColumn("Proyek");
        model_tblTransaksi.addColumn("Peran");
        
        loadDataKaryawan();//memuat data ke tabel
        loadDataProyek();
        loadDataTransaksi(); 
        loadNamaKaryawanComboBox();//memuat data ke comboBox
        loadNamaProyekComboBox();
        
        // Listener untuk memantau perubahan pada input Karyawan
        Tnama.getDocument().addDocumentListener(new InputListenerKaryawan());
        Tjabatan.getDocument().addDocumentListener(new InputListenerKaryawan());
        Tdepartemen.getDocument().addDocumentListener(new InputListenerKaryawan());
        Tid.getDocument().addDocumentListener(new InputListenerKaryawan());
                
        // Listener untuk memantau perubahan pada input Proyek
        TnmProyek.getDocument().addDocumentListener(new InputListenerProyek());
        Tdurasi.getDocument().addDocumentListener(new InputListenerProyek());
        
        //Listener untuk memantau perubahan pada input proyek
        cbKaryawan.addItemListener(e -> checkButtonStatusTransaksi());
        cbProyek.addItemListener(e -> checkButtonStatusTransaksi());
        Tperan.getDocument().addDocumentListener(new InputListenerProyek());
    }
 
    // Metode untuk memuat nama karyawan ke dalam JComboBox cbKaryawan
    private void loadNamaKaryawanComboBox() {
        cbKaryawan.removeAllItems();
        cbKaryawan.addItem("Pilih !"); // Tambahkan sebagai opsi pertama
        try {
            String sql = "SELECT id, nama FROM karyawan";//query untuk mengambil data
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            while (hasil.next()) {
                int idKaryawan = hasil.getInt("id");
                String namaKaryawan = hasil.getString("nama");
                cbKaryawan.addItem(idKaryawan + " - " + namaKaryawan); // Menampilkan "ID - Nama"
            }
        } catch (SQLException e) {
            System.out.println("Error loading Karyawan ComboBox: " + e.getMessage());
        }
    }

    // Metode untuk memuat nama proyek ke dalam JComboBox cbProyek
    private void loadNamaProyekComboBox() {
        cbProyek.removeAllItems();
        cbProyek.addItem("Pilih !"); // Tambahkan sebagai opsi pertama
        try {
            String sql = "SELECT id, nama_proyek FROM proyek";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet hasil = ps.executeQuery();
            while (hasil.next()) {
                int idProyek = hasil.getInt("id");
                String namaProyek = hasil.getString("nama_proyek");
                cbProyek.addItem(idProyek + " - " + namaProyek); // Menampilkan "ID - Nama Proyek"
            }
        } catch (SQLException e) {
            System.out.println("Error loading Proyek ComboBox: " + e.getMessage());
        }
    }    

    //Load Data Karyawan
    private void loadDataKaryawan() {
      model_tblKaryawan.setRowCount(0);
      try {
          String sql = "SELECT * FROM karyawan";
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet hasil = ps.executeQuery();
          while (hasil.next()) {
             // Menambahkan baris ke dalam model tabel
             model_tblKaryawan.addRow(new Object[]{
             hasil.getInt("id"),
             hasil.getString("nama"),
             hasil.getString("jabatan"),
             hasil.getString("departemen"),
           });}
            loadNamaKaryawanComboBox(); // Memuat ulang ComboBox setelah data di-refresh
      }catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }}//end Load Data
    
    //Load Data Proyek
    private void loadDataProyek() {
      model_tblProyek.setRowCount(0);
      try {
          String sql = "SELECT * FROM proyek";
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet hasil = ps.executeQuery();
          while (hasil.next()) {
             // Menambahkan baris ke dalam model tabel
            int durasi = hasil.getInt("durasiPengerjaan"); 
            String durasiFormatted = durasi + " Minggu";
            model_tblProyek.addRow(new Object[]{
            hasil.getInt("id"),
            hasil.getString("nama_proyek"),
            durasiFormatted
           });}
            loadNamaProyekComboBox(); // Memuat ulang ComboBox setelah data di-refresh
      }catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }}//end Load Data Proyek
    
    //Load Data Transaksi
    private void loadDataTransaksi() {
      model_tblTransaksi.setRowCount(0);
      try {
          String sql = "SELECT * FROM transaksi";
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet hasil = ps.executeQuery();
          while (hasil.next()) {
             // Menambahkan baris ke dalam model tabel
            model_tblTransaksi.addRow(new Object[]{
            hasil.getInt("id_karyawan"),
            hasil.getInt("id_proyek"),
            hasil.getString("peran"),
           });}
      }catch (SQLException e) {
         System.out.println("Error Save Data" + e.getMessage());
       }}//end Load Data Transaksi
    
    //Simpan Data Karyawan
    private void saveDataKaryawan() {
        try {
            String sql = "INSERT INTO karyawan (nama, jabatan, departemen) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Tnama.getText());
            ps.setString(2, Tjabatan.getText());
            ps.setString(3, Tdepartemen.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved successfully");
            loadDataKaryawan();
            loadNamaKaryawanComboBox();
            Tnama.setText("");
            Tjabatan.setText("");
            Tdepartemen.setText("");
        }catch (SQLException e) {
            System.out.println("Error Save Data: " + e.getMessage());
        }}//End Simpan Data Karyawan
    
    //Simpan Data proyek
    private void saveDataProyek() {
        try {
            // Pastikan durasi diambil sebagai integer
            int durasi = Integer.parseInt(Tdurasi.getText()); // Ini sudah ada dalam kode Anda
            String sql = "INSERT INTO proyek (nama_proyek, durasiPengerjaan) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, TnmProyek.getText());
            ps.setInt(2, durasi); // Menggunakan durasi yang berupa integer
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data saved successfully");
            loadDataProyek();
            loadNamaProyekComboBox();
            TnmProyek.setText("");
            Tdurasi.setText("");
        } catch (SQLException e) {
            System.out.println("Error Save Data: " + e.getMessage());
        }}//End Simpan Data Karyawan
    
    // Simpan Data Transaksi dengan memasukkan Nama dan ID ke dalam tabel
    private void saveDataTransaksi() {
        try {
            String Karyawan = (String) cbKaryawan.getSelectedItem();
            String Proyek = (String) cbProyek.getSelectedItem();

            // Periksa apakah pengguna memilih selain "Pilih !"
            if ("Pilih !".equals(Karyawan) || "Pilih !".equals(Proyek)) {
                JOptionPane.showMessageDialog(this, "Silakan pilih karyawan dan proyek!");
                return;
            }

            int idKaryawan = Integer.parseInt(Karyawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(Proyek.split(" - ")[0]);
            String peran = Tperan.getText();

            // Simpan ke database
            String sql = "INSERT INTO transaksi (id_karyawan, id_proyek, peran) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);
            ps.setString(3, peran);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Transaksi saved successfully");
            loadDataTransaksi();

            // Reset ComboBox dan text field
            cbKaryawan.setSelectedItem("Pilih !");
            cbProyek.setSelectedItem("Pilih !");
            Tperan.setText("");
            checkButtonStatusTransaksi(); // Cek ulang status tombol
        } catch (SQLException e) {
            System.out.println("Error saving Transaksi: " + e.getMessage());
        }
    }   

    //Update Data Karyawan
    private void updateDataKaryawan() {
      try {
          String sql = "UPDATE karyawan SET nama = ?, jabatan = ?, departemen = ? WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, Tnama.getText());
          ps.setString(2, Tjabatan.getText());
          ps.setString(3, Tdepartemen.getText());
          ps.setInt(4, Integer.parseInt(Tid.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data updated successfully");
          loadDataKaryawan();
          loadNamaKaryawanComboBox();
          Tid.setText("");
          Tnama.setText("");
          Tjabatan.setText("");
          Tdepartemen.setText("");
        }catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }}//End Simpan Data Karyawan
    
    //Update Data Proyek
    private void updateDataProyek() {
      try {
          String sql = "UPDATE proyek SET nama_proyek = ?, durasiPengerjaan = ? WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, TnmProyek.getText());
          ps.setString(2, Tdurasi.getText());
          ps.setInt(3, Integer.parseInt(TidProyek.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data updated successfully");
          loadDataProyek();
          loadNamaProyekComboBox();
          TidProyek.setText("");
          TnmProyek.setText("");
          Tdurasi.setText("");
        }catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }}//End Update Data Proyek
    
    //update data Transaksi
    private void updateDataTransaksi() {
        try {
            String selectedKaryawan = (String) cbKaryawan.getSelectedItem();
            String selectedProyek = (String) cbProyek.getSelectedItem();
            int idKaryawan = Integer.parseInt(selectedKaryawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(selectedProyek.split(" - ")[0]);
            String peran = Tperan.getText();
            String sql = "UPDATE transaksi SET id_karyawan = ?, id_proyek = ?, peran = ? WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);
            ps.setString(3, peran);
            ps.setInt(4, idKaryawan);  // Assuming we're using the same for update
            ps.setInt(5, idProyek); 
            ps.executeUpdate();
            loadDataTransaksi();
            // Reset combo box dan text field
            cbKaryawan.setSelectedItem("Pilih !");
            cbProyek.setSelectedItem("Pilih !");
            Tperan.setText("");
            JOptionPane.showMessageDialog(this, "Transaksi updated successfully");
        } catch (SQLException e) {
            System.out.println("Error updating Transaksi: " + e.getMessage());
        }}//End Update Data Transaksi
    
    //Delete Data Karyawan
    private void deleteDataKaryawan() {
     try  {
          String sql = "DELETE FROM karyawan WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(Tid.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataKaryawan();
          loadNamaKaryawanComboBox();
          Tid.setText("");
          Tnama.setText("");
          Tjabatan.setText("");
          Tdepartemen.setText("");
     }catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }}//End Delete Data
    
    //Delete Data Proyek
    private void deleteDataProyek() {
     try  {
          String sql = "DELETE FROM proyek WHERE id = ?";
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, Integer.parseInt(TidProyek.getText()));
          ps.executeUpdate();
          JOptionPane.showMessageDialog(this, "Data deleted successfully");
          loadDataProyek();
          loadNamaProyekComboBox();
          TidProyek.setText("");
          TnmProyek.setText("");
          Tdurasi.setText("");
     }catch (SQLException e) {
          System.out.println("Error Save Data" + e.getMessage());
      }}//End Delete Data Proyek
    
    //Delete Data Transaksi
    private void deleteDataTransaksi() {
        try {
            String Karyawan = (String) cbKaryawan.getSelectedItem();
            String Proyek = (String) cbProyek.getSelectedItem();
            int idKaryawan = Integer.parseInt(Karyawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(Proyek.split(" - ")[0]);
            String sql = "DELETE FROM transaksi WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);
            ps.executeUpdate();
            loadDataTransaksi();
            // Reset combo box dan text field
            cbKaryawan.setSelectedItem("Pilih !");
            cbProyek.setSelectedItem("Pilih !");
            Tperan.setText("");
            JOptionPane.showMessageDialog(this, "Transaksi deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error deleting Transaksi: " + e.getMessage());
        }}//End Delete Data Transaksi
    
    // Method untuk memeriksa status tombol berdasarkan input
    private void checkButtonStatusKaryawan() {
        boolean isIdFilled = !Tid.getText().trim().isEmpty();
        boolean isNameFilled = !Tnama.getText().trim().isEmpty();
        boolean isJobFilled = !Tjabatan.getText().trim().isEmpty();
        boolean isDeptFilled = !Tdepartemen.getText().trim().isEmpty();

        // Reset semua tombol dan label status ke kondisi off
        btnSimpan.setEnabled(false);
        lblSimpanStatus.setText("Off");
        btnUpdate.setEnabled(false);
        lblUpdateStatus.setText("Off");
        btndelete.setEnabled(false);
        lblDeleteStatus.setText("Off");

        // Tombol Simpan aktif hanya jika Nama, Jabatan, dan Departemen terisi
        if (!isIdFilled && isNameFilled && isJobFilled && isDeptFilled) {
            btnSimpan.setEnabled(true);
            lblSimpanStatus.setText("On");
        }

        // Kondisi untuk tombol Delete dan Update
        if (isIdFilled && isNameFilled && !isJobFilled && !isDeptFilled) {
            btndelete.setEnabled(true);
            lblDeleteStatus.setText("On");
        } else {
            btndelete.setEnabled(false);
            lblDeleteStatus.setText("Off");
        }

        if (isIdFilled && isNameFilled && isJobFilled && isDeptFilled) {
            btnUpdate.setEnabled(true);
            lblUpdateStatus.setText("On");
        } else {
            btnUpdate.setEnabled(false);
            lblUpdateStatus.setText("Off");
        }
    }

    // Method untuk memeriksa status tombol berdasarkan input
    private void checkButtonStatusProyek() {
        boolean isIdFilled = !TidProyek.getText().trim().isEmpty();
        boolean isNameFilled = !TnmProyek.getText().trim().isEmpty();
        boolean isDurFilled = !Tdurasi.getText().trim().isEmpty();
        // Reset semua tombol dan label status ke kondisi off
        btnSimpan1.setEnabled(false);
        lblSimpanStatus1.setText("Off");
        btnUpdate1.setEnabled(false);
        lblUpdateStatus1.setText("Off");
        btndelete1.setEnabled(false);
        lblDeleteStatus1.setText("Off");
        // Kondisi jika hanya Nama Proyek dan Durasi yang diisi (ID kosong)
        if (!isIdFilled && isNameFilled && isDurFilled) {
            btnSimpan1.setEnabled(true);
            lblSimpanStatus1.setText("On");
        // Kondisi jika hanya ID dan Nama Proyek yang diisi (Durasi kosong)
        }else if (isIdFilled && isNameFilled && !isDurFilled) {
            btndelete1.setEnabled(true);
            lblDeleteStatus1.setText("On");
        // Kondisi jika ID, Nama Proyek, dan Durasi semuanya diisi
        }else if (isIdFilled && isNameFilled && isDurFilled) {
            btnUpdate1.setEnabled(true);
            lblUpdateStatus1.setText("On");
        }}
    
    // Method untuk memeriksa status tombol berdasarkan input
    private void checkButtonStatusTransaksi() {
        boolean isKaryawanSelected = cbKaryawan.getSelectedItem() != null && !cbKaryawan.getSelectedItem().equals("Pilih !");
        boolean isProyekSelected = cbProyek.getSelectedItem() != null && !cbProyek.getSelectedItem().equals("Pilih !");
        boolean isPeranFilled = !Tperan.getText().trim().isEmpty();
        boolean isDataExist = isKaryawanSelected && isProyekSelected && isDataExistInDatabase();

        // Reset semua tombol ke kondisi off
        btnSimpan2.setEnabled(false);
        lblSimpanStatus2.setText("Off");
        btnUpdate2.setEnabled(false);
        lblUpdateStatus2.setText("Off");
        btndelete2.setEnabled(false);
        lblDeleteStatus2.setText("Off");

        // Aktifkan tombol Simpan jika Karyawan dan Proyek sudah dipilih dan Peran diisi
        if (isKaryawanSelected && isProyekSelected && isPeranFilled && !isDataExist) {
            btnSimpan2.setEnabled(true);
            lblSimpanStatus2.setText("On");
        }

        // Aktifkan tombol Update jika data ditemukan di database dan Peran diisi
        if (isDataExist && isPeranFilled) {
            btnUpdate2.setEnabled(true);
            lblUpdateStatus2.setText("On");
        }

        // Aktifkan tombol Delete jika data ditemukan di database
        if (isDataExist) {
            btndelete2.setEnabled(true);
            lblDeleteStatus2.setText("On");
        }
    }

    // Tambahkan metode untuk cek apakah data sudah ada di database
    private boolean isDataExistInDatabase() {
        try {
            String Karyawan = (String) cbKaryawan.getSelectedItem();
            String Proyek = (String) cbProyek.getSelectedItem();

            if ("Pilih !".equals(Karyawan) || "Pilih !".equals(Proyek)) {
                return false;
            }

            int idKaryawan = Integer.parseInt(Karyawan.split(" - ")[0]);
            int idProyek = Integer.parseInt(Proyek.split(" - ")[0]);

            String sql = "SELECT COUNT(*) FROM transaksi WHERE id_karyawan = ? AND id_proyek = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idKaryawan);
            ps.setInt(2, idProyek);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // True jika data ditemukan
            }
        } catch (SQLException e) {
            System.out.println("Error checking data existence: " + e.getMessage());
        }
        return false;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblK = new javax.swing.JTable();
        panelCustom1 = new custom.panelCustom();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Tnama = new javax.swing.JTextField();
        Tjabatan = new javax.swing.JTextField();
        Tdepartemen = new javax.swing.JTextField();
        Tid = new javax.swing.JTextField();
        panelCustom2 = new custom.panelCustom();
        btnSimpan = new javax.swing.JButton();
        lblSimpanStatus = new javax.swing.JLabel();
        panelCustom3 = new custom.panelCustom();
        btnUpdate = new javax.swing.JButton();
        lblUpdateStatus = new javax.swing.JLabel();
        panelCustom4 = new custom.panelCustom();
        btndelete = new javax.swing.JButton();
        lblDeleteStatus = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelCustom7 = new custom.panelCustom();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        TnmProyek = new javax.swing.JTextField();
        Tdurasi = new javax.swing.JTextField();
        TidProyek = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        panelCustom5 = new custom.panelCustom();
        btnSimpan1 = new javax.swing.JButton();
        lblSimpanStatus1 = new javax.swing.JLabel();
        panelCustom6 = new custom.panelCustom();
        btnUpdate1 = new javax.swing.JButton();
        lblUpdateStatus1 = new javax.swing.JLabel();
        panelCustom8 = new custom.panelCustom();
        btndelete1 = new javax.swing.JButton();
        lblDeleteStatus1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblP = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelCustom9 = new custom.panelCustom();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbKaryawan = new javax.swing.JComboBox<>();
        cbProyek = new javax.swing.JComboBox<>();
        Tperan = new javax.swing.JTextField();
        panelCustom10 = new custom.panelCustom();
        btnSimpan2 = new javax.swing.JButton();
        lblSimpanStatus2 = new javax.swing.JLabel();
        panelCustom11 = new custom.panelCustom();
        btnUpdate2 = new javax.swing.JButton();
        lblUpdateStatus2 = new javax.swing.JLabel();
        panelCustom12 = new custom.panelCustom();
        btndelete2 = new javax.swing.JButton();
        lblDeleteStatus2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblT = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 248, 232));

        jPanel2.setBackground(new java.awt.Color(255, 244, 183));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblK);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 610, 190));

        panelCustom1.setBackground(new java.awt.Color(179, 19, 18));
        panelCustom1.setMinimumSize(new java.awt.Dimension(400, 150));
        panelCustom1.setPreferredSize(new java.awt.Dimension(400, 150));
        panelCustom1.setRoundBottomLeft(30);
        panelCustom1.setRoundBottomRight(30);
        panelCustom1.setRoundTopLeft(30);
        panelCustom1.setRoundTopRight(30);
        panelCustom1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 2, 12, 0);
        panelCustom1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Nama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 0);
        panelCustom1.add(jLabel3, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Jabatan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 0);
        panelCustom1.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Departemen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 0);
        panelCustom1.add(jLabel6, gridBagConstraints);

        Tnama.setPreferredSize(new java.awt.Dimension(110, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 5);
        panelCustom1.add(Tnama, gridBagConstraints);

        Tjabatan.setPreferredSize(new java.awt.Dimension(110, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 5);
        panelCustom1.add(Tjabatan, gridBagConstraints);

        Tdepartemen.setPreferredSize(new java.awt.Dimension(110, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 5);
        panelCustom1.add(Tdepartemen, gridBagConstraints);

        Tid.setPreferredSize(new java.awt.Dimension(110, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 5);
        panelCustom1.add(Tid, gridBagConstraints);

        jPanel2.add(panelCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 390, 90));

        panelCustom2.setBackground(new java.awt.Color(255, 69, 69));
        panelCustom2.setPreferredSize(new java.awt.Dimension(300, 50));
        panelCustom2.setRoundBottomLeft(30);
        panelCustom2.setRoundBottomRight(30);
        panelCustom2.setRoundTopLeft(30);
        panelCustom2.setRoundTopRight(30);

        btnSimpan.setText("Simpan");
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        panelCustom2.add(btnSimpan);

        lblSimpanStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSimpanStatus.setForeground(java.awt.Color.white);
        lblSimpanStatus.setText("on");
        panelCustom2.add(lblSimpanStatus);

        jPanel2.add(panelCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 40));

        panelCustom3.setBackground(new java.awt.Color(255, 69, 69));
        panelCustom3.setForeground(java.awt.Color.white);
        panelCustom3.setPreferredSize(new java.awt.Dimension(300, 50));
        panelCustom3.setRoundBottomLeft(30);
        panelCustom3.setRoundBottomRight(30);
        panelCustom3.setRoundTopLeft(30);
        panelCustom3.setRoundTopRight(30);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        panelCustom3.add(btnUpdate);

        lblUpdateStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUpdateStatus.setForeground(java.awt.Color.white);
        lblUpdateStatus.setText("off");
        panelCustom3.add(lblUpdateStatus);

        jPanel2.add(panelCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 130, 40));

        panelCustom4.setBackground(new java.awt.Color(255, 69, 69));
        panelCustom4.setPreferredSize(new java.awt.Dimension(390, 30));
        panelCustom4.setRoundBottomLeft(30);
        panelCustom4.setRoundBottomRight(30);
        panelCustom4.setRoundTopLeft(30);
        panelCustom4.setRoundTopRight(30);

        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        panelCustom4.add(btndelete);

        lblDeleteStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDeleteStatus.setForeground(java.awt.Color.white);
        lblDeleteStatus.setText("on");
        panelCustom4.add(lblDeleteStatus);

        jPanel2.add(panelCustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 130, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\mm5.png")); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 240, 170));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(93, 14, 65));
        jLabel8.setText("Input Data Karyawan");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        jTabbedPane1.addTab("Karyawan", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 244, 183));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelCustom7.setBackground(new java.awt.Color(92, 70, 156));
        panelCustom7.setMinimumSize(new java.awt.Dimension(400, 150));
        panelCustom7.setPreferredSize(new java.awt.Dimension(400, 150));
        panelCustom7.setRoundBottomLeft(30);
        panelCustom7.setRoundBottomRight(30);
        panelCustom7.setRoundTopLeft(30);
        panelCustom7.setRoundTopRight(30);
        panelCustom7.setLayout(new java.awt.GridBagLayout());

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(java.awt.Color.white);
        jLabel14.setText("ID Proyek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 3, 12, 13);
        panelCustom7.add(jLabel14, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setForeground(java.awt.Color.white);
        jLabel17.setText("Nama Proyek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 0);
        panelCustom7.add(jLabel17, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setForeground(java.awt.Color.white);
        jLabel20.setText("Durasi Pengerjaan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 0);
        panelCustom7.add(jLabel20, gridBagConstraints);

        TnmProyek.setPreferredSize(new java.awt.Dimension(90, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 1, 8, 3);
        panelCustom7.add(TnmProyek, gridBagConstraints);

        Tdurasi.setPreferredSize(new java.awt.Dimension(90, 28));
        Tdurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TdurasiKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 3);
        panelCustom7.add(Tdurasi, gridBagConstraints);

        TidProyek.setPreferredSize(new java.awt.Dimension(90, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 1, 8, 3);
        panelCustom7.add(TidProyek, gridBagConstraints);

        jPanel3.add(panelCustom7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 430, 90));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(29, 38, 125));
        jLabel9.setText("Input Data Proyek");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        panelCustom5.setBackground(new java.awt.Color(204, 204, 255));
        panelCustom5.setPreferredSize(new java.awt.Dimension(300, 50));
        panelCustom5.setRoundBottomLeft(30);
        panelCustom5.setRoundBottomRight(30);
        panelCustom5.setRoundTopLeft(30);
        panelCustom5.setRoundTopRight(30);

        btnSimpan1.setText("Simpan");
        btnSimpan1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSimpan1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan1ActionPerformed(evt);
            }
        });
        panelCustom5.add(btnSimpan1);

        lblSimpanStatus1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSimpanStatus1.setText("on");
        panelCustom5.add(lblSimpanStatus1);

        jPanel3.add(panelCustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, 40));

        panelCustom6.setBackground(new java.awt.Color(204, 204, 255));
        panelCustom6.setPreferredSize(new java.awt.Dimension(300, 50));
        panelCustom6.setRoundBottomLeft(30);
        panelCustom6.setRoundBottomRight(30);
        panelCustom6.setRoundTopLeft(30);
        panelCustom6.setRoundTopRight(30);

        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });
        panelCustom6.add(btnUpdate1);

        lblUpdateStatus1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUpdateStatus1.setText("off");
        panelCustom6.add(lblUpdateStatus1);

        jPanel3.add(panelCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 130, 40));

        panelCustom8.setBackground(new java.awt.Color(204, 204, 255));
        panelCustom8.setPreferredSize(new java.awt.Dimension(390, 30));
        panelCustom8.setRoundBottomLeft(30);
        panelCustom8.setRoundBottomRight(30);
        panelCustom8.setRoundTopLeft(30);
        panelCustom8.setRoundTopRight(30);

        btndelete1.setText("Delete");
        btndelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelete1ActionPerformed(evt);
            }
        });
        panelCustom8.add(btndelete1);

        lblDeleteStatus1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDeleteStatus1.setText("on");
        panelCustom8.add(lblDeleteStatus1);

        jPanel3.add(panelCustom8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 130, 40));

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\m5.png")); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 200, 160));

        tblP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblP);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 610, 190));

        jTabbedPane1.addTab("Proyek", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 244, 183));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(80, 60, 60));
        jLabel11.setText("Input Data Transaksi Proyek");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        panelCustom9.setBackground(new java.awt.Color(128, 61, 59));
        panelCustom9.setMinimumSize(new java.awt.Dimension(400, 150));
        panelCustom9.setPreferredSize(new java.awt.Dimension(400, 150));
        panelCustom9.setRoundBottomLeft(30);
        panelCustom9.setRoundBottomRight(30);
        panelCustom9.setRoundTopLeft(30);
        panelCustom9.setRoundTopRight(30);
        panelCustom9.setLayout(new java.awt.GridBagLayout());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(java.awt.Color.white);
        jLabel16.setText("Karyawan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 2, 12, 0);
        panelCustom9.add(jLabel16, gridBagConstraints);

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setForeground(java.awt.Color.white);
        jLabel22.setText("Peran");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 8, 0);
        panelCustom9.add(jLabel22, gridBagConstraints);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setForeground(java.awt.Color.white);
        jLabel24.setText("Proyek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 2, 8, 0);
        panelCustom9.add(jLabel24, gridBagConstraints);

        cbKaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih !", "Item 2", "Item 3", "Item 4" }));
        cbKaryawan.setPreferredSize(new java.awt.Dimension(99, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        panelCustom9.add(cbKaryawan, gridBagConstraints);

        cbProyek.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih !", "Item 2", "Item 3", "Item 4" }));
        cbProyek.setPreferredSize(new java.awt.Dimension(99, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        panelCustom9.add(cbProyek, gridBagConstraints);

        Tperan.setPreferredSize(new java.awt.Dimension(99, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panelCustom9.add(Tperan, gridBagConstraints);

        jPanel4.add(panelCustom9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 480, 60));

        panelCustom10.setBackground(new java.awt.Color(175, 143, 111));
        panelCustom10.setPreferredSize(new java.awt.Dimension(300, 50));
        panelCustom10.setRoundBottomLeft(30);
        panelCustom10.setRoundBottomRight(30);
        panelCustom10.setRoundTopLeft(30);
        panelCustom10.setRoundTopRight(30);

        btnSimpan2.setText("Simpan");
        btnSimpan2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSimpan2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpan2ActionPerformed(evt);
            }
        });
        panelCustom10.add(btnSimpan2);

        lblSimpanStatus2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSimpanStatus2.setForeground(java.awt.Color.white);
        lblSimpanStatus2.setText("on");
        panelCustom10.add(lblSimpanStatus2);

        jPanel4.add(panelCustom10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 130, 40));

        panelCustom11.setBackground(new java.awt.Color(175, 143, 111));
        panelCustom11.setPreferredSize(new java.awt.Dimension(300, 50));
        panelCustom11.setRoundBottomLeft(30);
        panelCustom11.setRoundBottomRight(30);
        panelCustom11.setRoundTopLeft(30);
        panelCustom11.setRoundTopRight(30);

        btnUpdate2.setText("Update");
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });
        panelCustom11.add(btnUpdate2);

        lblUpdateStatus2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblUpdateStatus2.setForeground(java.awt.Color.white);
        lblUpdateStatus2.setText("off");
        panelCustom11.add(lblUpdateStatus2);

        jPanel4.add(panelCustom11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 130, 40));

        panelCustom12.setBackground(new java.awt.Color(175, 143, 111));
        panelCustom12.setPreferredSize(new java.awt.Dimension(390, 30));
        panelCustom12.setRoundBottomLeft(30);
        panelCustom12.setRoundBottomRight(30);
        panelCustom12.setRoundTopLeft(30);
        panelCustom12.setRoundTopRight(30);

        btndelete2.setText("Delete");
        btndelete2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelete2ActionPerformed(evt);
            }
        });
        panelCustom12.add(btndelete2);

        lblDeleteStatus2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDeleteStatus2.setForeground(java.awt.Color.white);
        lblDeleteStatus2.setText("on");
        panelCustom12.add(lblDeleteStatus2);

        jPanel4.add(panelCustom12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 130, 40));

        tblT.setBackground(new java.awt.Color(242, 242, 242));
        tblT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblT);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 610, 210));

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\m55.png")); // NOI18N
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 160, 140));

        jTabbedPane1.addTab("Transaksi", jPanel4);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("APLIKASI MANAJEMEN KARYAWAN DAN PROYEK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        deleteDataKaryawan();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btndelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelete1ActionPerformed
        deleteDataProyek();
    }//GEN-LAST:event_btndelete1ActionPerformed

    private void btndelete2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelete2ActionPerformed
        deleteDataTransaksi();
    }//GEN-LAST:event_btndelete2ActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        saveDataKaryawan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateDataKaryawan();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        updateDataProyek();
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan1ActionPerformed
        saveDataProyek();
    }//GEN-LAST:event_btnSimpan1ActionPerformed

    private void TdurasiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdurasiKeyReleased
        try {
            durasiP = Integer.parseInt(Tdurasi.getText());
        } catch (NumberFormatException nfe) {
            Tdurasi.setText("");
    }
    }//GEN-LAST:event_TdurasiKeyReleased

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed
        updateDataTransaksi();
    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void btnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpan2ActionPerformed
        saveDataTransaksi();
    }//GEN-LAST:event_btnSimpan2ActionPerformed

    // Listener untuk perubahan pada setiap field input
    private class InputListenerKaryawan implements javax.swing.event.DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusKaryawan();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusKaryawan();
        }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusKaryawan();
        }
    }
    
    // Listener untuk perubahan pada setiap field input
    private class InputListenerProyek implements javax.swing.event.DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusProyek();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusProyek();
        }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusProyek();
        }
    }
    
        // Listener untuk perubahan pada setiap field input
    private class InputListenerTransaksi implements javax.swing.event.DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusTransaksi();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusTransaksi();
        }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            checkButtonStatusTransaksi();
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(apk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(apk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(apk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(apk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new apk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Tdepartemen;
    private javax.swing.JTextField Tdurasi;
    private javax.swing.JTextField Tid;
    private javax.swing.JTextField TidProyek;
    private javax.swing.JTextField Tjabatan;
    private javax.swing.JTextField Tnama;
    private javax.swing.JTextField TnmProyek;
    private javax.swing.JTextField Tperan;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnSimpan1;
    private javax.swing.JButton btnSimpan2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btndelete1;
    private javax.swing.JButton btndelete2;
    private javax.swing.JComboBox<String> cbKaryawan;
    private javax.swing.JComboBox<String> cbProyek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDeleteStatus;
    private javax.swing.JLabel lblDeleteStatus1;
    private javax.swing.JLabel lblDeleteStatus2;
    private javax.swing.JLabel lblSimpanStatus;
    private javax.swing.JLabel lblSimpanStatus1;
    private javax.swing.JLabel lblSimpanStatus2;
    private javax.swing.JLabel lblUpdateStatus;
    private javax.swing.JLabel lblUpdateStatus1;
    private javax.swing.JLabel lblUpdateStatus2;
    private custom.panelCustom panelCustom1;
    private custom.panelCustom panelCustom10;
    private custom.panelCustom panelCustom11;
    private custom.panelCustom panelCustom12;
    private custom.panelCustom panelCustom2;
    private custom.panelCustom panelCustom3;
    private custom.panelCustom panelCustom4;
    private custom.panelCustom panelCustom5;
    private custom.panelCustom panelCustom6;
    private custom.panelCustom panelCustom7;
    private custom.panelCustom panelCustom8;
    private custom.panelCustom panelCustom9;
    private javax.swing.JTable tblK;
    private javax.swing.JTable tblP;
    private javax.swing.JTable tblT;
    // End of variables declaration//GEN-END:variables
}
