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
     private static final String url = "jdbc:mysql://localhost:3306/vrms";
    private static final String username = "root";
    private static final String userpassword = "";
    
    private static Connection kon;
    private static DBKonek dbKon;
        
    public DBKonek(){
        //get Connection Method
        kon = null;
        try {
            kon = DriverManager.getConnection(url, username, userpassword);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Connected Unsuccesfull");
           
        }
    }
    
    public static DBKonek getDBConnection(){
        if(dbKon == null){
            dbKon = new DBKonek();
        }
        return dbKon;
    }
    
    public static Connection getConnection(){
    if (kon == null) {
        new DBKonek();
    }
    return kon;
}
}

