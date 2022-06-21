/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekbesar.view;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class FormBarang extends javax.swing.JFrame {

    private DefaultTableModel tabmode;
    private PreparedStatement stat;
    
    public FormBarang() {
        initComponents();
        aktif();
        bersih();
        autonumber();
        setLocationRelativeTo(getRootPane());
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        Tanggal.setText(s.format(date));
    }
    
    private void aktif(){
        txtKode_buku.requestFocus();
    }
    
    public void autonumber(){
        try{
            Connection c = koneksi.getKoneksi();
            java.sql.Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(Kode_buku,3))AS Kode_buku FROM tb_buku";
            
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if(rs.first()==false){
                    txtKode_buku.setText("KD001");
                }else{
                    rs.last();
                    int no_tr = rs.getInt(1)+1;
                    String no = String.valueOf(no_tr);
                    int no_next = no.length();
                    for (int a=0; a<2-no_next; a++){
                        no = "00"+no;
                    }
                    txtKode_buku.setText("KD"+no);
                }
            }
            rs.close();
            stat.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        }

    private void bersih() {
        txtKode_buku.setText("");
        txtJudul.setText("");
        txtGenre.setSelectedItem("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        txtTahun_terbit.setText("");
        txthalaman.setText("");
        txtStok.setText("");
        txtHarga_jual.setText("");
        txtHarga_beli.setText("");
    }
    
    public void GenerateQrCode(){
        try{
            String QrCodeData= txtKode_buku.getText();
            String filePath= "C:\\Users\\hp\\Documents\\NetBeansProjects\\ProjekBesar\\src\\QrCode\\Qr.png";
            String charset= "UTF-8";
            Map <EncodeHintType,ErrorCorrectionLevel> hintMap= new EnumMap <> (EncodeHintType.class);
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                BitMatrix  matrix= new MultiFormatWriter().encode(
                new String (QrCodeData.getBytes(charset),charset),
                BarcodeFormat.QR_CODE,200,200,hintMap);
    
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.')+1),new File(filePath));
        System.out.println("Qr code has been generated at the location "+filePath);
        
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("C:\\Users\\hp\\Documents\\NetBeansProjects\\ProjekBesar\\src\\QrCode\\Qr.png");
        JLabel label = new JLabel(icon);
        frame.add(label);
  frame.setDefaultCloseOperation
         (JFrame.EXIT_ON_CLOSE);
  frame.pack();
  frame.setVisible(true);    
        }catch(WriterException | HeadlessException | IOException e){
            
        }
    }
    
    private void Tanggal(){
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        Tanggal.setText(s.format(date));
    }
    public void MenuView(){
        new StokBarang().setVisible(true);
        dispose();
    }
    
    public void simpan(){
        try{
            Connection c = koneksi.getKoneksi();

            String sql = "INSERT INTO tb_buku (Kode_buku, Judul, Genre, Pengarang, Penerbit, Jumlah_halaman, Tahun_terbit, Tanggal_masuk, Stok) "
            + "VALUES('"+txtKode_buku.getText()+"', '"+txtJudul.getText()+"', "
            + "'"+txtGenre.getSelectedItem()+"', '"+txtPengarang.getText()+"', '"+txtPenerbit.getText()+"',"
            + "'"+txthalaman.getText()+"', '"+txtTahun_terbit.getText()+"','"+Tanggal.getText()+"','"+txtStok.getText()+"')";
            
            String query = "INSERT INTO tb_barang (Kode_buku, Judul, Harga_jual, Harga_beli) "
            + "VALUES('"+txtKode_buku.getText()+"', '"+txtJudul.getText()+"', '"+txtHarga_jual.getText()+"', "
            + "'"+txtHarga_beli.getText()+"')" ;

            java.sql.PreparedStatement pst=c.prepareStatement(sql);
            java.sql.PreparedStatement pstd=c.prepareStatement(query);
            pst.execute();
            pstd.execute();
            JOptionPane.showMessageDialog(null,"Berhasil Simpan");
            bersih();
            
            new StokBarang().setVisible(true);
            this.dispose();
            txtKode_buku.requestFocus();
        }catch (HeadlessException | SQLException e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
            aktif();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtKode_buku = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtGenre = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtPengarang = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTahun_terbit = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txthalaman = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtHarga_jual = new javax.swing.JTextField();
        txtHarga_beli = new javax.swing.JTextField();
        btnsmp = new javax.swing.JButton();
        btShow = new javax.swing.JButton();
        Tanggal = new javax.swing.JTextField();
        btnKembali = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(118, 86, 56));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("T A M B A H  S T O K");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projekbesar/icon/sunflower (1).png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 540, 530));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("P e n e r b i t");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 80, 20));

        txtKode_buku.setEnabled(false);
        txtKode_buku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKode_bukuActionPerformed(evt);
            }
        });
        jPanel1.add(txtKode_buku, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 220, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("H a r g a  B e l i");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 120, 20));
        jPanel1.add(txtJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 220, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("J u d u l");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 20));

        txtGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih--", "Aksi dan Petualang", "Klasik", "Komik", "Detektif dan Misteri", "Fantasi", "Fiksi Sejarah", "Horor", "Sastra Fiksi", "Romance", "Fiksi Ilmiah (Sci-Fi)", "Cerita Pendek", "Thriller", "Fiksi Perempuan", "Biografi dan Otobiografi", "Esai", "Sejarah", "Memoir", "Puisi", "Self-Help", "Self-Improvment", "Filosofi", "Non-Fiksi", "Tenlit", "Novel Remaja", "Agama", "Sastra Klasik" }));
        jPanel1.add(txtGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 220, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("G e n r e");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 80, 20));

        txtPengarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPengarangActionPerformed(evt);
            }
        });
        jPanel1.add(txtPengarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 220, -1));
        jPanel1.add(txtPenerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 220, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("P e n g a r a n g");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 80, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("K o d e  B u k u");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 80, 20));
        jPanel1.add(txtTahun_terbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 220, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("T a h u n  T e r b i t");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 100, 20));
        jPanel1.add(txthalaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, 220, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("J u m l a h  H a l a m a n");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 120, 20));
        jPanel1.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 220, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("S t o k");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 120, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("H a r g a  J u a l");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 120, 20));

        txtHarga_jual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHarga_jualActionPerformed(evt);
            }
        });
        jPanel1.add(txtHarga_jual, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 230, 220, -1));
        jPanel1.add(txtHarga_beli, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 220, -1));

        btnsmp.setText("Save");
        btnsmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsmpActionPerformed(evt);
            }
        });
        jPanel1.add(btnsmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, 70, -1));

        btShow.setText("View");
        btShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btShowActionPerformed(evt);
            }
        });
        jPanel1.add(btShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 70, -1));

        Tanggal.setEditable(false);
        Tanggal.setEnabled(false);
        Tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalActionPerformed(evt);
            }
        });
        jPanel1.add(Tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 160, -1));

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        jPanel1.add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 70, -1));

        jButton1.setText("QR Code");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 80, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKode_bukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKode_bukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKode_bukuActionPerformed

    private void txtPengarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPengarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPengarangActionPerformed

    private void txtHarga_jualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHarga_jualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHarga_jualActionPerformed

    private void btnsmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsmpActionPerformed
        // TODO add your handling code here:
        simpan();
    }//GEN-LAST:event_btnsmpActionPerformed

    private void btShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btShowActionPerformed
        // TODO add your handling code here:
        MenuView();
    }//GEN-LAST:event_btShowActionPerformed

    private void TanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GenerateQrCode();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Tanggal;
    private javax.swing.JButton btShow;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnsmp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> txtGenre;
    private javax.swing.JTextField txtHarga_beli;
    private javax.swing.JTextField txtHarga_jual;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtKode_buku;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtTahun_terbit;
    private javax.swing.JTextField txthalaman;
    // End of variables declaration//GEN-END:variables
}
