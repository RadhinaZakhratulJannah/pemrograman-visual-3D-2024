package modul.pkg3;
import javax.swing.JOptionPane;
public class Apotek extends javax.swing.JFrame {
// Deklarasi variabel untuk masing-masing obat
    private String amoxicilin = "Amoxicilin: Antibiotik. Mengobati infeksi bakteri seperti infeksi telinga, hidung, tenggorokan, dan saluran kemih.";
    private String antasida = "Antasida: Mengobati gejala maag, gastritis, atau refluks asam.";
    private String paracetamol = "Paracetamol: Penurun Panas. Mengurangi rasa sakit dan demam.";
    private String ibuprofen = "Ibuprofen: Mengurangi nyeri, peradangan, dan demam.";
    private String domperidone = "Domperidone: Mengatasi mual dan muntah, terutama akibat pengobatan atau gangguan pencernaan.";
    private String sentraline = "Sentraline: Mengobati depresi, kecemasan, dan gangguan obsesif-kompulsif.";
    private String centirizine = "Cetirizine: Mengatasi gejala alergi seperti hidung meler, bersin, dan gatal-gatal.";
    private String loperamid = "Loperamid: Mengatasi diare akut dan kronis.";
    private String omeprazole = "Omeprazole: Mengobati tukak lambung, refluks gastroesofageal, dan kondisi kelebihan asam lambung.";
    private String katerolac = "Katerolac: Mengurangi nyeri singkat, biasanya setelah operasi.";
    public Apotek() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        simpan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        Gejala = new javax.swing.JTextField();
        aaa = new javax.swing.JLabel();
        bbb = new javax.swing.JLabel();
        ccc = new javax.swing.JLabel();
        ddd = new javax.swing.JLabel();
        eee = new javax.swing.JLabel();
        fff = new javax.swing.JLabel();
        ggg = new javax.swing.JLabel();
        hhh = new javax.swing.JLabel();
        iii = new javax.swing.JLabel();
        jjj = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(222, 255, 255));

        simpan.setBackground(new java.awt.Color(255, 204, 255));
        simpan.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        simpan.setText("Simpan");
        simpan.setPreferredSize(new java.awt.Dimension(78, 30));
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        jPanel3.add(simpan);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(222, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(310, 70));
        jPanel2.setPreferredSize(new java.awt.Dimension(310, 80));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\aptk.png")); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 90, 60));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 68, 255));
        jLabel2.setText("APOTEK H.S.S.H (Hati Senang.Sakit Hilang)");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(3, 52, 110));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\amoxicillin.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\antasida.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\paracetamol.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 11;
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\ibuprofen.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\domperidone.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\sentraline.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel16, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\cetirizine.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\loperamid.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel18, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\omeprazole.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel19, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\ketorolac.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 15;
        jPanel1.add(jLabel20, gridBagConstraints);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 255, 255));
        jLabel27.setText("Masukkan Gejala anda : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipady = 10;
        jPanel1.add(jLabel27, gridBagConstraints);

        Gejala.setMinimumSize(new java.awt.Dimension(200, 22));
        Gejala.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 10;
        jPanel1.add(Gejala, gridBagConstraints);

        aaa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aaa.setForeground(new java.awt.Color(242, 242, 242));
        aaa.setText("Amoxicilin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 11;
        jPanel1.add(aaa, gridBagConstraints);

        bbb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bbb.setForeground(new java.awt.Color(242, 242, 242));
        bbb.setText("Antasida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 11;
        jPanel1.add(bbb, gridBagConstraints);

        ccc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ccc.setForeground(new java.awt.Color(242, 242, 242));
        ccc.setText("Paracetamol");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 11;
        jPanel1.add(ccc, gridBagConstraints);

        ddd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ddd.setForeground(new java.awt.Color(242, 242, 242));
        ddd.setText("Ibuprofen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 11;
        jPanel1.add(ddd, gridBagConstraints);

        eee.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eee.setForeground(java.awt.Color.white);
        eee.setText("Domperidone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 11;
        jPanel1.add(eee, gridBagConstraints);

        fff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fff.setForeground(java.awt.Color.white);
        fff.setText("Sentraline");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 11;
        jPanel1.add(fff, gridBagConstraints);

        ggg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ggg.setForeground(java.awt.Color.white);
        ggg.setText("Centirizine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 11;
        jPanel1.add(ggg, gridBagConstraints);

        hhh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hhh.setForeground(java.awt.Color.white);
        hhh.setText("Loperamid");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 11;
        jPanel1.add(hhh, gridBagConstraints);

        iii.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        iii.setForeground(java.awt.Color.white);
        iii.setText("Omeprazole");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 11;
        jPanel1.add(iii, gridBagConstraints);

        jjj.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jjj.setForeground(java.awt.Color.white);
        jjj.setText("Katerolac");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 11;
        jPanel1.add(jjj, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        String gejala = Gejala.getText().toLowerCase();
        String hasil = "";
    // Daftar obat dan fungsi berdasarkan gejala (hanya menggunakan if)
        if (gejala.contains("bakteri")) {
            hasil += amoxicilin + "\n"; 
        }if (gejala.contains("asam lambung")) {
            hasil += antasida + "\n"; 
        }if (gejala.contains("demam")) {
            hasil += paracetamol + "\n"; 
        }if (gejala.contains("peradangan")) {
            hasil += ibuprofen + "\n"; 
        }if (gejala.contains("muntah")) {
            hasil += domperidone + "\n"; 
        }if (gejala.contains("kecemasan")) {
            hasil += sentraline + "\n"; 
        }if (gejala.contains("alergi")) {
            hasil += centirizine + "\n"; 
        }if (gejala.contains("diare")) {
            hasil += loperamid + "\n"; 
        }if (gejala.contains("asam lambung")) {
            hasil += omeprazole + "\n"; 
        }if (gejala.contains("nyeri operasi")) {
            hasil += katerolac + "\n";}
        // Jika hasil masih kosong, berarti gejala tidak ditemukan
        if (hasil.isEmpty()) {
            hasil = "Gejala tidak ditemukan. Silakan periksa kembali.";
        }
        // Menampilkan hasil di dalam JOptionPane
        JOptionPane.showMessageDialog(null, hasil, "Hasil Pencarian Obat", JOptionPane.INFORMATION_MESSAGE);
        Gejala.setText("");
    }//GEN-LAST:event_simpanActionPerformed

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apotek().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Gejala;
    private javax.swing.JLabel aaa;
    private javax.swing.JLabel bbb;
    private javax.swing.JLabel ccc;
    private javax.swing.JLabel ddd;
    private javax.swing.JLabel eee;
    private javax.swing.JLabel fff;
    private javax.swing.JLabel ggg;
    private javax.swing.JLabel hhh;
    private javax.swing.JLabel iii;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jjj;
    private javax.swing.JButton simpan;
    // End of variables declaration//GEN-END:variables
}
