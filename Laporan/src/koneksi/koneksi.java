/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author LENOVO
 */
import java.sql.*;

public class koneksi {
    private Connection koneksi;
    
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi.");
        } catch(ClassNotFoundException ex) {
            System.out.println("Gagal Koneksi." +ex);
        }
        
        String url = "jdbc:mysql://localhost:3306/toko_buku";
        try {
           koneksi =  DriverManager.getConnection(url,"root", "");
           System.out.println("Berhasil Koneksi Database.");
        } catch (SQLException ex) {
            System.out.println("Gagal Koneksi Database." +ex);
        }
        return koneksi;
    }
}
