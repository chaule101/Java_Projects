/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Chau Le
 */
public class ConnectDAO {

    protected Connection conn;

    public ConnectDAO() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=JAVA4_ASM";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
