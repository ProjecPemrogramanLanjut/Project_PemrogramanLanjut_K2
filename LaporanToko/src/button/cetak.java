/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.util.Date;
import java.sql.*;
/**
 *
 * @author LENOVO
 */
public class cetak extends javax.swing.JFrame {
    public final Connection conn = (Connection) new koneksi().connect();
    JasperReport Report;
    JasperPrint Print;
    Map param = new HashMap();
    JasperDesign JasDes;
    

    /**
     * Creates new form cetak_laporan
     */
    
    public cetak() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCetakStok = new javax.swing.JButton();
        btnBarangMasuk = new javax.swing.JButton();
        btnBarangKeluar = new javax.swing.JButton();
        btnTransaksi = new javax.swing.JButton();
        dawal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dakhir = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(141, 216, 239));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Laporan Toko Buku Full Sun");

        btnCetakStok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCetakStok.setText("Laporan Stok Barang");
        btnCetakStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakStokActionPerformed(evt);
            }
        });

        btnBarangMasuk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBarangMasuk.setText("Laporan Barang Masuk");
        btnBarangMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangMasukActionPerformed(evt);
            }
        });

        btnBarangKeluar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBarangKeluar.setText("Laporan Barang Keluar");
        btnBarangKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangKeluarActionPerformed(evt);
            }
        });

        btnTransaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTransaksi.setText("Laporan Transaksi Penjualan");
        btnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaksiActionPerformed(evt);
            }
        });

        jLabel2.setText("Tanggal Awal");

        jLabel3.setText("Tanggal Akhir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnCetakStok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBarangMasuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBarangKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnTransaksi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dawal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dakhir, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)))
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dawal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dakhir, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnCetakStok, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBarangMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBarangKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnCetakStokActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        try {
            File file = new File("src/laporan/laporan_stok.jrxml");
            JasDes = JRXmlLoader.load(file);
            Report = JasperCompileManager.compileReport(JasDes);
            Print = JasperFillManager.fillReport(Report, param, conn);
            JasperViewer viewer = new JasperViewer(Print, false);
            viewer.setExtendedState(viewer.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
            viewer.setVisible(true);
            viewer.toFront();
        } catch (JRException e){
            JOptionPane.showMessageDialog(null, "Gagal koneksi laporan" +e);
        }
    }                                            

    private void btnBarangMasukActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        try {
            param.put("awal", dawal.getDate());
            param.put("akhir", dakhir.getDate());
            File file = new File("src/laporan/laporan_bmasuk.jrxml");
            JasDes = JRXmlLoader.load(file);
            Report = JasperCompileManager.compileReport(JasDes);
            Print = JasperFillManager.fillReport(Report, param, conn);
            JasperViewer viewer = new JasperViewer(Print, false);
            viewer.setExtendedState(viewer.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
            viewer.setVisible(true);
            viewer.toFront();
        } catch (JRException e){
            JOptionPane.showMessageDialog(null, "Gagal koneksi laporan" +e);
        }
    }                                              

    private void btnBarangKeluarActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        try {
            param.put("awal", dawal.getDate());
            param.put("akhir", dakhir.getDate());
            File file = new File("src/laporan/laporan_bkeluar.jrxml");
            JasDes = JRXmlLoader.load(file);
            Report = JasperCompileManager.compileReport(JasDes);
            Print = JasperFillManager.fillReport(Report, param, conn);
            JasperViewer viewer = new JasperViewer(Print, false);
            viewer.setExtendedState(viewer.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
            viewer.setVisible(true);
            viewer.toFront();
        } catch (JRException e){
            JOptionPane.showMessageDialog(null, "Gagal koneksi laporan" +e);
        }
    }                                               

    private void btnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        try {
            param.put("awal", dawal.getDate());
            param.put("akhir", dakhir.getDate());
            File file = new File("src/laporan/laporan_transaksi.jrxml");
            JasDes = JRXmlLoader.load(file);
            Report = JasperCompileManager.compileReport(JasDes);
            Print = JasperFillManager.fillReport(Report, param, conn);
            JasperViewer viewer = new JasperViewer(Print, false);
            viewer.setExtendedState(viewer.getExtendedState() | javax.swing.JFrame.MAXIMIZED_BOTH);
            viewer.setVisible(true);
            viewer.toFront();
        }
        catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Gagal koneksi laporan" +e);
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
            java.util.logging.Logger.getLogger(cetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cetak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cetak().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnBarangKeluar;
    private javax.swing.JButton btnBarangMasuk;
    private javax.swing.JButton btnCetakStok;
    private javax.swing.JButton btnTransaksi;
    private com.toedter.calendar.JDateChooser dakhir;
    private com.toedter.calendar.JDateChooser dawal;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
