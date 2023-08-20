/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customers;
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
public class DAOCustomers extends DBConnect {
    
    public boolean loginCustomers(String username,String password){
        boolean flag = false;
        String sql = "select * from Customers where email = ? and phone = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }
    public int getCurrentCusid(){
        int n = 0;
        String sql ="select top (1) customer_id from customers order by customer_id desc";
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                n = rs.getInt("customer_id");
            }        
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Customers getCustomers(String username){
        Customers cus = null;
        String sql ="select * from Customers where email = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                String f_name = rs.getString(2);
                String l_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String zip = rs.getString(9);
                cus = new Customers(id, f_name, l_name, phone, email, street, city, state, zip);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cus;
    }

    public int insertCustomers(Customers cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(" + cus.getCustomer_id() + ",'" + cus.getFirst_name() + "','" + cus.getLast_name() + "'"
                + ",'" + cus.getPhone() + "','" + cus.getEmail() + "','" + cus.getStreet() + "','" + cus.getCity() + "',"
                + "'" + cus.getState() + "','" + cus.getZip_code() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int insertCustomersByPre(Customers cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cus.getCustomer_id());
            pre.setString(2, cus.getFirst_name());
            pre.setString(3, cus.getLast_name());
            pre.setString(4, cus.getPhone());
            pre.setString(5, cus.getEmail());
            pre.setString(6, cus.getStreet());
            pre.setString(7, cus.getCity());
            pre.setString(8, cus.getState());
            pre.setString(9, cus.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCustomers(Customers cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET [first_name] = ?"                
                + "      ,[last_name] = ?"
                + "      ,[phone] = ?"
                + "      ,[email] = ?"
                + "      ,[street] = ?"
                + "      ,[city] = ?"
                + "      ,[state] = ?"
                + "      ,[zip_code] = ?"
                + " WHERE [customer_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getFirst_name());
            pre.setString(2, cus.getLast_name());
            pre.setString(3, cus.getPhone());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getStreet());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getState());
            pre.setString(8, cus.getZip_code());
            pre.setInt(9, cus.getCustomer_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int deleteCustomers(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[customers]\n"
                + "      WHERE [customer_id] = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector searchByName(String name){
        Vector<Customers> vector = new Vector<Customers>();
        String sql = "select * from Customers where first_name LIKE '%" + name + "%' OR last_name LIKE '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int id = rs.getInt(1);
                String f_name = rs.getString(2);
                String l_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String zip = rs.getString(9);
                Customers cus = new Customers(id, f_name, l_name, phone, email, street, city, state, zip);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector getAllCustomer(String sql){
        Vector<Customers> vector = new Vector<Customers>();
        try {
            
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String f_name = rs.getString(2);
                String l_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String zip = rs.getString(9);
                Customers cus = new Customers(id, f_name, l_name, phone, email, street, city, state, zip);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public void displayAll(){
        String sql ="select * from Customers";
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //dataType varName = rs.getDataType(fieldName|index);
                //int id = rs.getInt("product_id");
                int id = rs.getInt(1);
                String f_name = rs.getString(2);
                String l_name = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String state = rs.getString(8);
                String zip = rs.getString(9);
                Customers cus = new Customers(id, f_name, l_name, phone, email, street, city, state, zip);
                System.out.println(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
        Customers cus = new Customers(700, "first_name", "last_name", "phone"," email", "streets", "city", "state", "12345");
//        int n = dao.insertCustomers(cus);
//        if(n>0){
//            System.out.println("done");
//        }
//        int m = dao.deleteCustomers(700);
//        if(m>0){
//            System.out.println("oke");
//        }
        Vector<Customers> vector = dao.searchByName("Burks");
        for (Customers customers : vector) {
            System.out.println(customers);
        }
    }
}
