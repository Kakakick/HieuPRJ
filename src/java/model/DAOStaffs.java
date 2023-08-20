/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Staffs;
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
public class DAOStaffs extends DBConnect {

    public boolean loginStaffs(String username, String password) {
        boolean flag = false;
        String sql = "select * from Staffs where email = ? and phone = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }
    
    public Staffs getStaffs(String username){
        Staffs sta = null;
        String sql ="select * from Staffs where email = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                int staff_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                sta = new Staffs(staff_id, fname, lname, email, phone, active, store_id, manager_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sta;
    }


    public int insertStaffs(Staffs st) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id])\n"
                + "     VALUES(" + st.getStaff_id() + ",'" + st.getFirst_name() + "','" + st.getLast_name() + "',"
                + "'" + st.getEmail() + "','" + st.getPhone() + "',"
                + "" + st.getActive() + "," + st.getStore_id() + "," + st.getManager_id() + ")";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int insertStaffsByPre(Staffs st) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st.getStaff_id());
            pre.setString(2, st.getFirst_name());
            pre.setString(3, st.getLast_name());
            pre.setString(4, st.getEmail());
            pre.setString(5, st.getPhone());
            pre.setInt(6, st.getActive());
            pre.setInt(7, st.getStore_id());
            pre.setInt(8, st.getManager_id());
            n = pre.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateStaff(Staffs st) {
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [first_name] = ?"
                + "      ,[last_name] = ?"
                + "      ,[email] = ?"
                + "      ,[phone] = ?"
                + "      ,[active] = ?"
                + "      ,[store_id] = ?"
                + "      ,[manager_id] = ?"
                + " WHERE [staff_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, st.getFirst_name());
            pre.setString(2, st.getLast_name());
            pre.setString(3, st.getEmail());
            pre.setString(4, st.getPhone());
            pre.setInt(5, st.getActive());
            pre.setInt(6, st.getStore_id());
            pre.setInt(7, st.getManager_id());
            pre.setInt(8, st.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteStaff(int id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[staffs]\n"
                + "      WHERE [staff_id] = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Vector searchByNameStaff(String name) {
        Vector<Staffs> vector = new Vector<Staffs>();
        String sql = "select * from Customers where first_name LIKE '%" + name + "%' OR last_name LIKE '%" + name + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int staff_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                Staffs st = new Staffs(staff_id, fname, lname, email, phone, active, store_id, manager_id);
                vector.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public void displayAll() {
        String sql = "select * from Staffs";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //dataType varName = rs.getDataType(fieldName|index);
                //int id = rs.getInt("product_id");
                int staff_id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);

                Staffs st = new Staffs(staff_id, fname, lname, email, phone, active, store_id, manager_id);
                System.out.println(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
