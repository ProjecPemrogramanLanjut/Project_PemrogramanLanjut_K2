/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekbesar.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
import javax.swing.table.DefaultTableModel;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import projekbesar.view.koneksi;
import projekbesar.view.FLogin;
/**
 *
 * @author hp
 */
public class Kasir extends javax.swing.JFrame implements Runnable, ThreadFactory {
public final com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) koneksi.getKoneksi();

JasperReport Report;
JasperPrint Print;
Map param = new HashMap();
JasperDesign JasDes;

String Tanggal;
    private DefaultTableModel model;
    public String id_barang;
    
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    
    public void totalBayar(){
        int jumlahBaris = jTable1.getRowCount();
        int totalBiaya = 0;
        int jumlahBarang, hargaBarang;
        for (int i = 0; i <jumlahBaris; i++) {
            jumlahBarang = Integer.parseInt(jTable1.getValueAt(i, 3).toString() );
            hargaBarang = Integer.parseInt(jTable1.getValueAt(i, 4).toString() );
            totalBiaya = totalBiaya + (jumlahBarang*hargaBarang);
            
        }
        txTotal.setText(String.valueOf(totalBiaya));
        txTampil.setText("Rp. "+totalBiaya+",00");
    }
    
    
    
    public void autonumber(){
        try{
            Connection c = koneksi.getKoneksi();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(no_penjualan,3))AS no_penjualan FROM penjualan";
            
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if(rs.first()==false){
                    txNoPenjualan.setText("TR001");
                }else{
                    rs.last();
                    int no_tr = rs.getInt(1)+1;
                    String no = String.valueOf(no_tr);
                    int no_next = no.length();
                    for (int a=0; a<2-no_next; a++){
                        no = "00"+no;
                    }
                    txNoPenjualan.setText("TR0"+no);
                }
            }
            rs.close();
            stat.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        }
    
   public void loadData(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object []{
            txNoPenjualan.getText(),
            txId_Barang.getText(),
            txNama_Barang.getText(),
            txJumlah.getText(),
            txHarga_Satuan.getText(),
            txTotal.getText()
        });
    }
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while (model.getRowCount()>0) {
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txNoPenjualan.setText("");
        txId_Barang.setText("");
        txNama_Barang.setText("");
        txHarga_Satuan.setText("");
        txJumlah.setText("");
        autonumber();
    }
    
    public void clear(){
        txId_Karyawan.setText("");
        txTotal.setText("0");
        txBayar.setText("0");
        txKembalian.setText("0");
        txTampil.setText("0");
    }
    
     public void clear2(){
        txId_Barang.setText("");
        txNama_Barang.setText("");
        txJumlah.setText("0");
        txHarga_Satuan.setText("0");
    }
     
     public void tambahTransaksi(){
         int jumlah, harga, total;
         
         jumlah = Integer.valueOf(txJumlah.getText());
         harga = Integer.valueOf(txHarga_Satuan.getText());
         total = jumlah * harga;
         
         txTotal.setText(String.valueOf(total));
         
         loadData();
         totalBayar();
         clear2();
         txId_Barang.requestFocus();
     }
     
     public void setID(String id){
        System.out.println("set id" + id);
        txId_Barang.repaint();
        txId_Barang.validate();
        
        txId_Barang.setText(id);
        txId_Barang.repaint();
        txId_Barang.validate();
    }
    
     private void initWebcam(){
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 270));

        executor.execute(this);
    }
     
    public void IdKasir(){
        try{
            Connection c = koneksi.getKoneksi();
            String query = "SELECT * FROM `log` ORDER BY id DESC LIMIT 1";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
            txId_Karyawan.setText(rs.getString("kd_kar"));
    }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    }
    
    public Kasir() {
        initComponents();
        initWebcam();
        autonumber();
        IdKasir();
        
        model = new DefaultTableModel();
        
        jTable1.setModel(model);
        
        model.addColumn("No Transaksi");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        
        txTanggal.setText(s.format(date));
        txTotal.setText("0");
        txBayar.setText("0");
        txKembalian.setText("0");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txNoPenjualan = new javax.swing.JTextField();
        txId_Karyawan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txId_Barang = new javax.swing.JTextField();
        enter = new javax.swing.JButton();
        txTanggal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txNama_Barang = new javax.swing.JTextField();
        txHarga_Satuan = new javax.swing.JTextField();
        txJumlah = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnStok = new javax.swing.JButton();
        txTotal = new javax.swing.JTextField();
        txBayar = new javax.swing.JTextField();
        txKembalian = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txTampil = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnKembali2 = new javax.swing.JButton();
        btnHapus1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(118, 86, 56));
        jPanel1.setPreferredSize(new java.awt.Dimension(977, 620));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("K  A  S  I  R");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("J u m l a h");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("T a n g g a l");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 53, 70, 20));

        txNoPenjualan.setEnabled(false);
        txNoPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNoPenjualanActionPerformed(evt);
            }
        });
        jPanel1.add(txNoPenjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 180, 30));

        txId_Karyawan.setEnabled(false);
        txId_Karyawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txId_KaryawanActionPerformed(evt);
            }
        });
        jPanel1.add(txId_Karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 180, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("I D  K a r y a w a n");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        txId_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txId_BarangActionPerformed(evt);
            }
        });
        txId_Barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txId_BarangKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txId_BarangKeyTyped(evt);
            }
        });
        jPanel1.add(txId_Barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 160, 40));

        enter.setText("input");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        jPanel1.add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, 20));

        txTanggal.setEnabled(false);
        jPanel1.add(txTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 180, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("K o d e  B a r a n g");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("K E M B A L I A N");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("H a r g a");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, -1, -1));

        txNama_Barang.setEnabled(false);
        txNama_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNama_BarangActionPerformed(evt);
            }
        });
        jPanel1.add(txNama_Barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 160, 30));

        txHarga_Satuan.setEnabled(false);
        txHarga_Satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txHarga_SatuanActionPerformed(evt);
            }
        });
        jPanel1.add(txHarga_Satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 160, 30));

        txJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJumlahActionPerformed(evt);
            }
        });
        jPanel1.add(txJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 150, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable1);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 920, 120));

        btnStok.setText("Stok");
        btnStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStokActionPerformed(evt);
            }
        });
        jPanel1.add(btnStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, 70, -1));

        txTotal.setEnabled(false);
        txTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 180, 30));

        txBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txBayarActionPerformed(evt);
            }
        });
        jPanel1.add(txBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 180, 30));

        txKembalian.setEnabled(false);
        jPanel1.add(txKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 550, 180, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("N a m a  B a r a n g");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("T O T A L");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("B A Y A R");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));
        jPanel1.add(txTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 190, 50));

        btnSimpan.setText("S A V E");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 520, 70, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 250, 190));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 270, 210));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("N o.  T r a n s a k s i");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        btnKembali2.setText("Kembali");
        btnKembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembali2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnKembali2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 520, 80, -1));

        btnHapus1.setText("Delete");
        btnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapus1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txNoPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNoPenjualanActionPerformed

    }//GEN-LAST:event_txNoPenjualanActionPerformed

    private void txId_KaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txId_KaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txId_KaryawanActionPerformed

    private void txId_BarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txId_BarangKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String kodebarang = txId_Barang.getText();

            try {
                Connection c = koneksi.getKoneksi();
                String query = "SELECT*FROM tb_barang where Kode_buku='"+txId_Barang.getText()+"'";
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    txNama_Barang.setText(rs.getString("Judul"));
                    txHarga_Satuan.setText(rs.getString("Harga_jual"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_txId_BarangKeyPressed

    private void txId_BarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txId_BarangKeyTyped
        if("".equals(txId_Barang.getText())){

        }
    }//GEN-LAST:event_txId_BarangKeyTyped

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        // TODO add your handling code here:
        String kodebarang = txId_Barang.getText();

        try {
            Connection c = koneksi.getKoneksi();
            String query = "SELECT*FROM tb_barang where Kode_buku='"+txId_Barang.getText()+"'";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                txNama_Barang.setText(rs.getString("Judul"));
                txHarga_Satuan.setText(rs.getString("Harga_jual"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_enterActionPerformed

    private void txNama_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNama_BarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNama_BarangActionPerformed

    private void txHarga_SatuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txHarga_SatuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txHarga_SatuanActionPerformed

    private void txJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJumlahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_txJumlahActionPerformed

    private void txBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txBayarActionPerformed
        // TODO add your handling code here:
        int total, bayar, kembalian;

        total = Integer.valueOf(txTotal.getText());
        bayar = Integer.valueOf(txBayar.getText());

        if (total>bayar){
            JOptionPane.showMessageDialog(null, "Uang tidak cukup!");
        }else{
            kembalian = bayar - total;
            txKembalian.setText(String.valueOf(kembalian));
        }
    }//GEN-LAST:event_txBayarActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "INSERT INTO penjualan VALUES ('"+txNoPenjualan.getText()+"', '"+txId_Barang.getText()+"', '"+txId_Karyawan.getText()+"', '"+txTanggal.getText()+"','"+txTotal.getText()+"')";
            java.sql.PreparedStatement pst=c.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        try {
            Connection c = koneksi.getKoneksi();
            int baris = jTable1.getRowCount();

            for (int i = 0; i < baris; i++) {
                String sql = "INSERT INTO rincian_penjualan (no_penjualan, id_barang, nama_barang, jumlah, harga_satuan, total) "
                + "VALUES ('"+jTable1.getValueAt(i, 0)+"','"+jTable1.getValueAt(i, 1)+"','"+jTable1.getValueAt(i, 2)+"'"
                + ",'"+jTable1.getValueAt(i, 3)+"','"+jTable1.getValueAt(i, 4)+"','"+jTable1.getValueAt(i, 5)+"')";
                java.sql.PreparedStatement pst=c.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                //PreparedStatement p = c.prepareStatement(sql);
                //p.executeUpdate();
                //p.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        clear();
        utama();
        autonumber();
        kosong();
        txTampil.setText("RP. 0");
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txId_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txId_BarangActionPerformed
        // TODO add your handling code here
        
    }//GEN-LAST:event_txId_BarangActionPerformed

    private void txTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTotalActionPerformed

    private void btnKembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembali2ActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembali2ActionPerformed

    private void btnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapus1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapus1ActionPerformed

    private void btnStokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStokActionPerformed
        new StokBarang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnStokActionPerformed

    public void isiOtomatis(){
        try{
            Connection c = koneksi.getKoneksi();
            String query = "SELECT*FROM tb_barang where Kode_buku='"+txId_Barang.getText()+"'";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
            txNama_Barang.setText(rs.getString("Judul"));
            txHarga_Satuan.setText(rs.getString("Harga_jual"));
    }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } 
    } 
                      
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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }
    
    void setiD(String id_barang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setaID(String id_barang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
//                System.out.println(e);
            }

            if (result != null) {
                txId_Barang.setText(result.getText());
                isiOtomatis();
                
//                p.setVisible(true);
//                webcam.close();
//                this.dispose();
               
            }
        } while (true);
    }
    

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus1;
    private javax.swing.JButton btnKembali2;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnStok;
    private javax.swing.JButton enter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txBayar;
    private javax.swing.JTextField txHarga_Satuan;
    private javax.swing.JTextField txId_Barang;
    private javax.swing.JTextField txId_Karyawan;
    private javax.swing.JTextField txJumlah;
    private javax.swing.JTextField txKembalian;
    private javax.swing.JTextField txNama_Barang;
    private javax.swing.JTextField txNoPenjualan;
    private javax.swing.JTextField txTampil;
    private javax.swing.JTextField txTanggal;
    private javax.swing.JTextField txTotal;
    // End of variables declaration//GEN-END:variables

}
