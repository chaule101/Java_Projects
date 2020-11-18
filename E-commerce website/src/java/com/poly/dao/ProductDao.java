/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.models.Product;
import com.poly.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hanhl
 */
public class ProductDao extends ConnectDao {

    public ProductDao() {
    }

    public ArrayList<Product> fillAll() {
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from PRODUCTS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int categories_id = rs.getInt(2);
                String name = rs.getString(3);
                int price = rs.getInt(4);
                String note = rs.getString(5);
                String image = rs.getString(6);

                Product product = new Product(id, categories_id, name, price, note, image);
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
     public boolean insertProduct(Product product) {
         boolean result = false;
        try {
           PreparedStatement ps = con.prepareStatement("INSERT into PRODUCTS(CATEGORIES_ID,NAME,PRICE,NOTE,IMAGE) values(?,?,?,?,?)");
           ps.setInt(1,product.getCategories_id());
           ps.setString(2,product.getName());
           ps.setInt(3,product.getPrice());
           ps.setString(4,product.getNote());
           ps.setString(5,product.getImage());
          
           
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    

}
     
      public Product findById(int id) {
        Product product = null;
        try {
            Statement lenh = con.createStatement();
            String sql = "select * from PRODUCTS where PRODUCTS_ID=" + id;
            ResultSet dl = lenh.executeQuery(sql);
            while (dl.next()) {
                int id_products=dl.getInt(1);
                int id_categories=dl.getInt(2);
                String name=dl.getString(3);
                int price=dl.getInt(4);
                String note=dl.getString(5);
                String image = dl.getString(6);
               
                product = new  Product(id_products, id_categories, name, price, note, image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
      
      public boolean deleteProduct(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE from PRODUCTS where PRODUCTS_ID = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
      
      public boolean updateProduct(Product product) {
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement("update PRODUCTS set CATEGORIES_ID=?,NAME=?, PRICE=?, NOTE=?, IMAGE=? where PRODUCTS_ID=?");
            ps.setInt(1, product.getCategories_id());        
            ps.setString(2, product.getName());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getNote());
            ps.setString(5, product.getImage());
            ps.setInt(6, product.getId());          
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
