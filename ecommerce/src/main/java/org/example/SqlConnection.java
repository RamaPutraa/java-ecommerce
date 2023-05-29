package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    public static String Connection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:../../../../../SQLite/Database/ecommerce.db";

            conn = DriverManager.getConnection(url);

            String respon;
            respon = "Koneksi ke database berhasil!";
            return respon;


        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite tidak ditemukan!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal!");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Koneksi database ditutup!");
                }
            } catch (SQLException e) {
                System.out.println("Tidak dapat menutup koneksi database!");
                e.printStackTrace();
            }
        }
        return null;
    }
}