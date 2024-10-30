package com.mycompany.modul_4;
import java.util.List; // Menggunakan java.util.List yang benar untuk struktur data
import java.util.Random;
import javax.swing.JOptionPane;
public class TebakAngka extends javax.swing.JFrame {
    Random rd = new Random();
    int bilRandom = rd.nextInt(100) + 1;
    int kesempatan = 10;
    int tbk;
    public TebakAngka() {
        initComponents();
        setLocationRelativeTo(null);
        score.setText("100");
        riwayat.setEditable(false);
        cek.setText("CEK(" + kesempatan + ")");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tbak = new javax.swing.JTextField();
        rndm = new javax.swing.JTextField();
        score = new javax.swing.JTextField();
        cek = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        riwayat = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(255, 246, 227));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(358, 475));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(358, 510));

        jPanel1.setBackground(new java.awt.Color(255, 204, 234));
        jPanel1.setPreferredSize(new java.awt.Dimension(358, 475));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(91, 188, 255));
        jLabel1.setText("TEBAK ANGKA YU !!");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(173, 73, 225));
        jLabel2.setText("TEBAKAN :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 225, 37));
        jLabel3.setText("HASIL :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 70, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(13, 124, 102));
        jLabel4.setText("SCORE :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 80, -1));

        tbak.setBackground(new java.awt.Color(255, 204, 255));
        tbak.setPreferredSize(new java.awt.Dimension(65, 25));
        tbak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbakKeyReleased(evt);
            }
        });
        jPanel1.add(tbak, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 160, -1));

        rndm.setBackground(new java.awt.Color(255, 255, 204));
        rndm.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel1.add(rndm, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 160, -1));

        score.setBackground(new java.awt.Color(204, 255, 204));
        score.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel1.add(score, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 160, -1));

        cek.setBackground(new java.awt.Color(248, 223, 212));
        cek.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cek.setText("CEK");
        cek.setPreferredSize(new java.awt.Dimension(75, 25));
        cek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekActionPerformed(evt);
            }
        });
        jPanel1.add(cek, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\bgbg.jpg")); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, -1));

        jTabbedPane1.addTab("GAME", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 204, 234));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 64, 125));
        jLabel5.setText("SCORE");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        riwayat.setBackground(new java.awt.Color(255, 204, 204));
        riwayat.setColumns(20);
        riwayat.setRows(5);
        jScrollPane1.setViewportView(riwayat);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 167, 230));

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\SMT3\\bgbg.jpg")); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, -1));

        jTabbedPane1.addTab("SCORE", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekActionPerformed
        int skor = Integer.parseInt(score.getText());
        String input = tbak.getText();
        // Mengonversi input ke integer
        int tebakan = Integer.parseInt(input);
            kesempatan--;
            cek.setText("CEK(" + kesempatan + ")");
            score.setText(String.valueOf(kesempatan * 10));
            if(tebakan == bilRandom){
            JOptionPane.showMessageDialog(this, "Yeayy Kamu Berhasil");            
            String nama = JOptionPane.showInputDialog("You did it !! Masukkan nama Anda:");
            if(nama != null && !nama.trim().isEmpty()) {
            int skorAkhir = kesempatan * 10;
            riwayat.append(nama + " - Score: " + skorAkhir + "\n");
            bilRandom = rd.nextInt(100) + 1;
            kesempatan = 10;
            cek.setText("Cek(" + kesempatan + ")");
            tbak.setText("");
            rndm.setText("");
            score.setText("100");
            }}else{
            if(kesempatan > 0) {
                if (tebakan > bilRandom) {
                    rndm.setText("Gede amay ngab!");
                }else{
                    rndm.setText("Kekecilah deh!");
                }}else{
            JOptionPane.showMessageDialog(this, "Game Over deh !!");
            bilRandom = rd.nextInt(100) + 1;
            kesempatan = 10;
            cek.setText("Cek(" + kesempatan + ")");
            tbak.setText("");
            rndm.setText("");
            score.setText("100");
            }}
    }//GEN-LAST:event_cekActionPerformed

    private void tbakKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbakKeyReleased
        try {
            tbk = Integer.parseInt(tbak.getText());
        } catch (NumberFormatException nfe) {
            tbak.setText("");
    }
    }//GEN-LAST:event_tbakKeyReleased
    
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
            java.util.logging.Logger.getLogger(TebakAngka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TebakAngka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TebakAngka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TebakAngka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TebakAngka().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea riwayat;
    private javax.swing.JTextField rndm;
    private javax.swing.JTextField score;
    private javax.swing.JTextField tbak;
    // End of variables declaration//GEN-END:variables
}
