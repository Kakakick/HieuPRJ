/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Stocks;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HIEUPC
 */
public class DAOStocks extends DBConnect {

    public int insertStocks(Stocks sto) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES(" + sto.getStore_id() + "," + sto.getProduct_id() + "," + sto.getQuantity() + ")";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int updateStocks(Stocks sto) {
        int n = 0;
        String sql = "UPDATE [dbo].[stocks]\n"
                + "   SET [product_id] = ?"
                + "      ,[quantity] = ?"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sto.getProduct_id());
            pre.setInt(2, sto.getQuantity());
            pre.setInt(3, sto.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int deleteStocks(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[stocks]\n"
                + "      WHERE [store_id] =" + id;
        Statement st;
        try {
            st = conn.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void displayAll() {
        String sql = "select * from Stocks";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName = rs.getDataType(fieldName|index);
                //int id = rs.getInt("product_id");
                int store_id = rs.getInt(1);
                int product_id = rs.getInt(2);
                int quantity = rs.getInt(3);
                Stocks sto = new Stocks(store_id, product_id, quantity);
                System.out.println(sto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
