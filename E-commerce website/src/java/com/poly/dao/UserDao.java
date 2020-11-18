 package com.poly.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import com.poly.dao.ConnectDao;
import com.poly.models.User;
import java.sql.PreparedStatement;
import java.util.ArrayList;
public class UserDao extends ConnectDao  {

    public UserDao() {
    }
    
      public ArrayList<User> fillAll() {
        ArrayList<User> list = new ArrayList<User>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from USERS");
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
            Statement lenh = con.createStatement();
            String sql = "select * from USERS where ID=" + id;
            ResultSet dl = lenh.executeQuery(sql);
            while (dl.next()) {
                int id_user=dl.getInt(1);
                String username=dl.getString(2);
                String password=dl.getString(3);
                String fullname=dl.getString(4);
                String email=dl.getString(5);
                String phone = dl.getString(6);
                int role = dl.getInt(7);
                user = new  User(id_user, username, password, fullname, email, phone, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //update user
    public boolean update(User user) {
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement("update USERS set USERNAME=?,PASSWORD=?, FULLNAME=?, EMAIL=?, PHONE=?, ROLE=? where ID=?");
            ps.setString(1, user.getUSERNAME());
            ps.setString(2, user.getPASSWORD());
            ps.setString(3, user.getFULLNAME());
            ps.setString(4, user.getEMAIL());
            ps.setString(5, user.getPHONE());
            ps.setInt(6, user.getRole());
            ps.setInt(7, user.getID());
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
            PreparedStatement ps = con.prepareStatement("DELETE from USERS where ID = ?");
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
    //trong model tạo 1 constructor ko có đối số ID vì lúc insert vào ban đầu sẽ ko truyền ID dc
    public boolean insertUser(User user) {
         boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT into USERS(USERNAME,PASSWORD,FULLNAME,EMAIL,PHONE,ROLE) values(?,?,?,?,?,?)");
           ps.setString(1,user.getUSERNAME());
           ps.setString(2,user.getPASSWORD());
           ps.setString(3,user.getFULLNAME());
           ps.setString(4,user.getEMAIL());
           ps.setString(5,user.getPHONE());
           ps.setInt(6,user.getRole());
           
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    

}
     public User checkLogin(String u, String p) {
        User user = null;
        try {
            Statement lenh = con.createStatement();
            String sql = "select * from USERS where USERNAME='"+u+"' and PASSWORD='"+p+"'";
            ResultSet dl = lenh.executeQuery(sql);
            while (dl.next()) {
                int id_user=dl.getInt(1);
                String username=dl.getString(2);
                String password=dl.getString(3);
                String fullname=dl.getString(4);
                String email=dl.getString(5);
                String phone = dl.getString(6);
                int role = dl.getInt(7);
                user = new  User(id_user, username, password, fullname, email, phone, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
     
//     public User CHECKLOGIN(String u,String p){
//         User user =null;
//         try{
//             Statement lenh = con.createStatement();
//            String sql = "select USERS.USERNAME,USERS.PASSWORD from USERS where USERNAME='"+u+"' and PASSWORD='"+p+"'";
//            ResultSet dl = lenh.executeQuery(sql);
//            while (dl.next()) {
//                int id_user=dl.getInt(1);
//                String username=dl.getString(2);
//                String password=dl.getString(3);
//         
//         }
//     
//     }
     
}

