/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Stores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HIEUPC
 */
public class DAOStores extends DBConnect {

    public int insertStores(Stores sto) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id]\n"
                + "           ,[store_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(" + sto.getStore_id() + ",'" + sto.getStore_name() + "',"
                + "'" + sto.getPhone() + "','" + sto.getEmail() + "','" + sto.getStreet() + "',"
                + "'" + sto.getCity() + "','" + sto.getStreet() + "','" + sto.getZip_code() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int insertStoresByPre(Stores sto) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id]\n"
                + "           ,[store_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sto.getStore_id());
            pre.setString(2, sto.getStore_name());
            pre.setString(3, sto.getPhone());
            pre.setString(4, sto.getEmail());
            pre.setString(5, sto.getStreet());
            pre.setString(6, sto.getCity());
            pre.setString(7, sto.getState());
            pre.setString(8, sto.getZip_code());
            n = pre.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateStore(Stores sto) {
        int n = 0;
        String sql = "UPDATE [dbo].[stores]\n"
                + "   SET [store_name] = ?"
                + "      ,[phone] = ?"
                + "      ,[email] = ?"
                + "      ,[street] = ?"
                + "      ,[city] = ?"
                + "      ,[state] = ?"
                + "      ,[zip_code] = ?"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sto.getStore_name());
            pre.setString(2, sto.getPhone());
            pre.setString(3, sto.getEmail());
            pre.setString(4, sto.getStreet());
            pre.setString(5, sto.getCity());
            pre.setString(6, sto.getState());
            pre.setString(7, sto.getZip_code());
            pre.setInt(8, sto.getStore_id());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteStores(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[stores]\n"
                + "      WHERE [store_id] = "+id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector searchByNameStore(String name){
        Vector<Stores> vector = new Vector<Stores>();
        String sql ="select * from Stores where store_name LIKE '%"+name+"%'";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int id = rs.getInt(1);
                String namest = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip = rs.getString(8);
                Stores sto = new Stores(id, state, phone, email, street, city, state, zip);
                vector.add(sto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public void displayAll(){
        String sql ="select * from Stores";
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //dataType varName = rs.getDataType(fieldName|index);
                //int id = rs.getInt("product_id");
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String state = rs.getString(7);
                String zip = rs.getString(8);
                Stores sto = new Stores(id, state, phone, email, street, city, state, zip);
                System.out.println(sto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
