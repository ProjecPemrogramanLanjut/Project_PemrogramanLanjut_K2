package projekbesar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection koneksi;
    
    public static Connection getKoneksi(){
        if (koneksi==null) {
            try{
                String url ="jdbc:mysql://localhost/toko_buku";
                String user="root";
                String pass="";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url,user,pass);
                System.out.println("koneksi berhasil;");
            } catch(SQLException e) {
                System.out.println("Error");
            }
        }
        return koneksi;
    } 
    public static void main(String args[]){
        getKoneksi();
    }
}
