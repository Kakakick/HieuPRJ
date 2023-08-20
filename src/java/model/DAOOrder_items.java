/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order_items;
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
public class DAOOrder_items extends DBConnect {

    public int insertOrder_items(Order_items od) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES(" + od.getOrder_id() + "," + od.getItem_id() + ","
                + "" + od.getProduct_id() + "," + od.getQuantity() + ","
                + "" + od.getList_price() + "," + od.getDiscount() + ")";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int insertOrder_itemsByPre(Order_items od) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getOrder_id());
            pre.setInt(2, od.getItem_id());
            pre.setInt(3, od.getProduct_id());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getList_price());
            pre.setDouble(6, od.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateOrder_items(Order_items od) {
        int n = 0;
        String sql = "UPDATE [dbo].[order_items]\n"
                + "   SET [item_id] = ?"
                + "      ,[product_id] = ?"
                + "      ,[quantity] = ?"
                + "      ,[list_price] = ?"
                + "      ,[discount] = ?"
                + " WHERE [order_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, od.getItem_id());
            pre.setInt(2, od.getProduct_id());
            pre.setInt(3, od.getQuantity());
            pre.setDouble(4, od.getList_price());
            pre.setDouble(5, od.getDiscount());
            pre.setInt(6, od.getOrder_id());
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteOrder_items(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[order_items]\n"
                + "      WHERE [order_id]"+id;
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector searchById(int id){
        Vector<Order_items> vector = new Vector<Order_items>();
        String sql = "select * from Orders where order_id ="+id+"";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                Order_items od = new Order_items(order_id, item_id, product_id, quantity, price, discount);
                vector.add(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
      
    }
    
    public void displayAll(){
        String sql ="select * from order_items";
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                //dataType varName = rs.getDataType(fieldName|index);
                //int id = rs.getInt("product_id");
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                Order_items od = new Order_items(order_id, item_id, product_id, quantity, price, discount);
                System.out.println(od);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
