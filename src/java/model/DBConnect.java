/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HIEUPC
 */
public class DBConnect {

    Connection conn = null;

    public DBConnect(String url, String userName, String pass) {
        try {
            //goi driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //conect
            conn = DriverManager.getConnection(url, userName, pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1605", "sa", "123");
//        try {
//            //driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            //connection
//            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SE1605","sa","123");
//            System.out.println("Connected");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
    }
    
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rs;
    }
    public static void main(String[] args) throws SQLException {
        new DBConnect();
    }
}
