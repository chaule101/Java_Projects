/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.models.Category;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;

/**
 *
 * @author hanhl
 */
public class CategoryDao extends ConnectDao {

   

     public ArrayList <Category> fillAllCategories() {
        ArrayList<Category> list = new ArrayList<Category>();
        try {
            Statement lenh = con.createStatement();
            String sql = "select * from CATEGORIES";
            ResultSet dl = lenh.executeQuery(sql);
            while (dl.next()) {
                int categories_id = dl.getInt(1);
                String name = dl.getString(2);
                String note = dl.getString(3);
                Category cate = new Category(categories_id, name, note);
                list.add(cate);

            }

        } catch (Exception ex) {
            System.out.println("Lỗi....." + ex.toString());
        }
        return list;
    }
      public String getCategoryByID(int id) {
        String name = "";
        try {
            Statement lenh = con.createStatement();
            String sql = "select NAME from CATEGORIES where CATEGORIES_ID=" + id;
            ResultSet dl = lenh.executeQuery(sql);
            while (dl.next()) {
                name = dl.getString(1);

            }
        } catch (Exception ex) {
            System.out.println("Lỗi...." + ex.toString());
        }
        return name;

    }
}
