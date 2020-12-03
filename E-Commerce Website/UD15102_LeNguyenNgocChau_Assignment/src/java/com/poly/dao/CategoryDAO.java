/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Chau Le
 */
public class CategoryDAO extends ConnectDAO {

    public CategoryDAO() {

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
        }
        return name;
    }
}
