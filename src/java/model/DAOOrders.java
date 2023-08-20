/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
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
public class DAOOrders extends DBConnect {

    public int insertOrders(Orders od) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ([order_id]\n"
                + "           ,[customer_id]\n"
                + "           ,[order_status]\n"
                + "           ,[order_date]\n"
                + "           ,[required_date]\n"
                + "           ,[shipped_date]\n"
                + "           ,[store_id]\n"
                + "           ,[staff_id])\n"
                + "     VALUES(" + od.getOrder_id() + "," + od.getCustomer_id() + "," + od.getOrder_status() + ""
                + ",'" + od.getOrder_date() + "','" + od.getRequired_date() + "','" + od.getShipped_date() + "'," + od.getStore_id() + ","
                + "" + od.getStaff_id() + ")";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int insertOrdersByPre(Orders od) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ([order_id]\n"
                + "           ,[customer_id]\n"
                + "           ,[order_status]\n"
                + "           ,[order_date]\n"
                + "           ,[required_date]\n"
                + "           ,[shipped_date]\n"
                + "           ,[store_id]\n"
                + "           ,[staff_id])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrder_id());
            pre.setInt(2, od.getCustomer_id());
            pre.setInt(3, od.getOrder_status());
            pre.setString(4, od.getOrder_date());
            pre.setString(5, od.getRequired_date());
            pre.setString(6, od.getShipped_date());
            pre.setInt(7, od.getStore_id());
            pre.setInt(8, od.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public int getCurrentOdid(){
        int n = 0;
        String sql ="select top (1) order_id from orders order by order_id desc";
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                n = rs.getInt("order_id");
            }        
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    

    public int updateOrders(Orders od) {
        int n = 0;
        String sql = "UPDATE [dbo].[orders]\n"
                + "     SET   [customer_id] = ?"
                + "           ,[order_status] = ?"
                + "           ,[order_date] = ?"
                + "           ,[required_date] = ?"
                + "           ,[shipped_date] = ?"
                + "           ,[store_id] = ?"
                + "           ,[staff_id] = ?"
                + "     WHERE [order_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getCustomer_id());
            pre.setInt(2, od.getOrder_status());
            pre.setString(3, od.getOrder_date());
            pre.setString(4, od.getRequired_date());
            pre.setString(5, od.getShipped_date());
            pre.setInt(6, od.getStore_id());
            pre.setInt(7, od.getStaff_id());
            pre.setInt(8, od.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteOrders(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[orders]\n"
                + "      WHERE [order_id] = "+id;
        try {
            Statement state = conn.createStatement();
            n =  state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector searchById(int id){
        Vector<Orders> vector = new Vector<Orders>();
        String sql = "select * from Orders where order_id ="+id+"";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int order_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                int status = rs.getInt(3);               
                String o_date = rs.getString(4);
                String required = rs.getString(5);
                String shipped = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Orders od = new Orders(order_id, customer_id, status, o_date, required, shipped, store_id, staff_id);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
      
    }
    public Vector getAllOrders(String sql){
        Vector<Orders> vector = new Vector<Orders>();
        try {
            
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                int status = rs.getInt(3);               
                String o_date = rs.getString(4);
                String required = rs.getString(5);
                String shipped = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Orders od = new Orders(order_id, customer_id, status, o_date, required, shipped, store_id, staff_id);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector getField(String name) {
        Vector<String> vector = new Vector<String>();
        ResultSet rs = this.getData("select distinct " + name + " from Orders");
        try {
            while (rs.next()) {
                vector.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    
    
    public void displayAll(){
        String sql ="select * from Orders";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                //dataType varName = rs.getDataType(fieldName|index);
                //int id = rs.getInt("product_id");
                int order_id = rs.getInt(1);
                int customer_id = rs.getInt(2);
                int status = rs.getInt(3);               
                String o_date = rs.getString(4);
                String required = rs.getString(5);
                String shipped = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Orders od = new Orders(order_id, customer_id, status, o_date, required, shipped, store_id, staff_id);
                System.out.println(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        Vector<Orders> vector = dao.searchById(1);
        for (Orders od : vector) {
            System.out.println(od);
        }
    }
}
