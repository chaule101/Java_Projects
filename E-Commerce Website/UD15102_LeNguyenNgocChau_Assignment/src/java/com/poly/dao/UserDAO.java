/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Chau Le
 */
public class UserDAO extends ConnectDAO {

    public UserDAO() {

    }

    //check login
    public User checkLogin(String u, String p) {
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from USERS where username='" + u + "' and password='" + p + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String fullname = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                int role = rs.getInt(7);
                user = new User(id, username, password, fullname, email, phone, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //danh sách tất cả user
    public ArrayList<User> fillAll() {
        ArrayList<User> list = new ArrayList<User>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from USERS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String fullname = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                int role = rs.getInt(7);
                User user = new User(id, username, password, fullname, email, phone, role);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //tìm một user
    public User findById(int id) {
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from USERS where id=" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int iduser = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String fullname = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                int role = rs.getInt(7);
                user = new User(iduser, username, password, fullname, email, phone, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //update user
    public boolean updateUser(User user) {
        boolean result = false;
        try {
            PreparedStatement ps = conn.prepareStatement("update USERS set username=?, password=?, fullname=?, email=?, phone=?, role=? where id=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getRole());
            ps.setInt(7, user.getId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //delete user
    public boolean deleteUser(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from USERS where id = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //insert user
    public boolean insertUser(User user) {
        boolean result = false;
        try {
            PreparedStatement ps = conn.prepareStatement("insert into USERS(username, password, fullname, email, phone, role) values (?,?,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getRole());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean regUser(User user) {
        boolean result = false;
        try {
            PreparedStatement ps = conn.prepareStatement("insert into USERS(username, password, fullname, email, phone) values (?,?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
