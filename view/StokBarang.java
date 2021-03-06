/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekbesar.view;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class StokBarang extends javax.swing.JFrame {

    private DefaultTableModel tabmode;
    
    private String Kode_buku;
    private String Judul;
    private String Stok;
    private String Harga_beli;
    private String Harga_jual;
    
    public StokBarang() {
        initComponents();
        table();
        bersih();
    }
    
    private void table(){
        Object[] Baris = {"Kode Buku", "Judul", "Stok", "Tanggal Masuk", "Harga Beli", "Harga Jual"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();
        try {
            Connection c = koneksi.getKoneksi();
            String sql = "SELECT tb_buku.Kode_buku, tb_buku.Judul, tb_buku.Stok, tb_buku.Tanggal_masuk, "
                    + "tb_barang.Harga_beli, tb_barang.Harga_jual FROM tb_buku, tb_barang "
                    + "WHERE tb_buku.Kode_buku=tb_barang.Kode_buku";
            java.sql.Statement stat = c.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
                hasil.getString(6)
            });
            }
            tabel.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dipanggil" + e);
        }
    }
    
    private void bersih() {
        txtcari.setText("");
    }
    
    private void datatable(){
        //Menampilkan data dari database
        Connection c = koneksi.getKoneksi();
        Object[] Baris = {"Kode Buku", "Judul", "Genre", "Pengarang", "Penerbit", "Tahun Terbit", "Stok", "Tanggal Masuk"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = txtcari.getText();
        try {
            String sql = "SELECT Kode_buku,Judul,Genre,Pengarang,Penerbit,Tahun_terbit, Stok,Tanggal_masuk "
                    + "FROM tb_buku WHERE Kode_buku like '%"+cariitem+"%' or Judul like "
                    + "'%"+cariitem+"%' or Genre like '%"+cariitem+"%' or Pengarang like '%"+cariitem+"%' "
                    + "or Penerbit like '%"+cariitem+"%' or Tahun_terbit like '%"+cariitem+"%' "
                    + "or Stok like '%"+cariitem+"%'  or Tanggal_masuk like '%"+cariitem+"%' order by Kode_buku asc";
            java.sql.Statement pst = c.createStatement();
            ResultSet hasil = pst.executeQuery(sql);
            while (hasil.next()){
                tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
                hasil.getString(6),
                hasil.getString(7),
                hasil.getString(8)
            });
            }
            tabel.setModel(tabmode);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dipanggil" + e);
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
        btncari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        btntambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnTabel = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(118, 86, 56));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("S T O K  B A R A N G");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        btncari.setText("Cari");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        jPanel1.add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, -1, 30));

        txtcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcariActionPerformed(evt);
            }
        });
        jPanel1.add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 350, 30));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tabel.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tabelInputMethodTextChanged(evt);
            }
        });
        tabel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabelPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 810, 200));

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        jPanel1.add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 80, -1));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 80, -1));

        btnTabel.setText("Tabel");
        btnTabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabelActionPerformed(evt);
            }
        });
        jPanel1.add(btnTabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 80, -1));

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        jPanel1.add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 80, -1));

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 440, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // TODO add your handling code here:
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void txtcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcariActionPerformed

    private void tabelInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabelInputMethodTextChanged
        // TODO add your handling code here:
        int i = tabel.getSelectedRow();
        if (i == -1) {
            return;
        }
        
        this.Kode_buku = (String) tabmode.getValueAt(i, 0);
        this.Judul = (String) tabmode.getValueAt(i, 1);
        this.Stok = (String) tabmode.getValueAt(i, 2);
        this.Harga_beli = (String) tabmode.getValueAt(i, 3);
        this.Harga_jual = (String) tabmode.getValueAt(i, 4);
    }//GEN-LAST:event_tabelInputMethodTextChanged

    private void tabelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabelPropertyChange
        // TODO add your handling code here:
        int i = tabel.getSelectedRow();
        if (i == -1) {
            return;
        }
        
        this.Kode_buku = (String) tabmode.getValueAt(i, 0);
        this.Judul = (String) tabmode.getValueAt(i, 1);
        this.Stok = (String) tabmode.getValueAt(i, 2);
        this.Harga_beli = (String) tabmode.getValueAt(i, 3);
        this.Harga_jual = (String) tabmode.getValueAt(i, 4);
    }//GEN-LAST:event_tabelPropertyChange

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        //menuju halaman stok barang
        try{
            Connection c = koneksi.getKoneksi();
            Statement stat = c.createStatement();
            String sql = "SELECT * FROM log ORDER BY id DESC LIMIT 1";
            
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                String level = (rs.getString("level"));
                if(level.equals("kasir")){
                   JOptionPane.showMessageDialog(this, "Not Your Place, Dude!");
                }else{
                    FormBarang fb = new FormBarang();
        fb.show();
        this.dispose();
                }
        }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        bersih();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnTabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabelActionPerformed
        // TODO add your handling code here:
        Tabel tb = new Tabel();
        tb.show();
        this.dispose();
    }//GEN-LAST:event_btnTabelActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        new MenuUtama().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int i = tabel.getSelectedRow();
        if (i == -1) {
            return;
        }
        
        try {
            System.out.println(this.Judul);
            System.out.println(this.Stok);
            System.out.println(this.Kode_buku);
            
            Connection c = koneksi.getKoneksi();
            String sql = "UPDATE tb_buku SET Judul = ?, Stok = ? WHERE tb_buku.Kode_buku = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, this.Judul);
            p.setString(2, this.Stok);
            p.setString(3, this.Kode_buku);
            
            p.executeUpdate();
            p.close();
            JOptionPane.showMessageDialog(null, "Data Terubah");
        } catch (HeadlessException | SQLException e) {
            System.out.println("update error" + e);
        }finally{
            table();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed
                                    

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
            java.util.logging.Logger.getLogger(StokBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StokBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StokBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StokBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StokBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTabel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btntambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
