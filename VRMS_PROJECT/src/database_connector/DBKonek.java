/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database_connector;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
    public class DBKonek {
    private static final String url = "jdbc:mysql://localhost:3306/vrms_database";
    private static final String username = "root";
    private static final String userpassword = "";

    private static Connection kon;
    private static DBKonek dbKon;

    public DBKonek() {
        try {
            kon = DriverManager.getConnection(url, username, userpassword);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Unsuccessful:\n" + e.getMessage());
        }
    }

    public static DBKonek getDBConnection() {
        if (dbKon == null) {
            dbKon = new DBKonek();
        }
        return dbKon;
    }

    public static Connection getConnection() {
        try {
            if (kon == null || kon.isClosed()) {
                kon = DriverManager.getConnection(url, username, userpassword);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database reconnection failed:\n" + e.getMessage());
            kon = null;
        }
        return kon;
    }
}

