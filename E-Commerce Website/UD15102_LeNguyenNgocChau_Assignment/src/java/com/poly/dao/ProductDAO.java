/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import com.poly.models.Category;
import com.poly.models.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Chau Le
 */
public class ProductDAO extends ConnectDAO {

    public ProductDAO() {

    }

    public ArrayList<Product> fillAll() {
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String note = rs.getString(4);
                String image = rs.getString(5);
                int category_id = rs.getInt(6);
                Product user = new Product(id, name, price, note, image, category_id);
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(int id) {
        Product product = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from PRODUCT where id=" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idproduct = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                String note = rs.getString(4);
                String image = rs.getString(5);
                int category_id = rs.getInt(6);
                product = new Product(idproduct, name, price, note, image, category_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public String getCategoryByID(int id) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select name from CATEGORY where id=" + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public ArrayList<Category> fillAllCategory() {
        ArrayList<Category> list = new ArrayList<Category>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from CATEGORY");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String note = rs.getString(3);
                Category category = new Category(id, name, note);
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertProduct(Product product) {
        boolean result = false;
        try {
            PreparedStatement ps = conn.prepareStatement("insert into PRODUCT(name, price, note, image, category_id) values (?,?,?,?,?)");
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getNote());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getCategory_id());
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
            PreparedStatement ps = conn.prepareStatement("update PRODUCT set name=?, price=?, note=?, image=?, category_id=? where id=?");
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getNote());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getCategory_id());
            ps.setInt(6,product.getId());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteProduct(int id) {
        boolean result = false;
        try {
            PreparedStatement ps = conn.prepareStatement("delete from PRODUCT where id = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
