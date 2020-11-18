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
 * @author hanhl
 */
public class ConnectDao {
    protected Connection con;    
    public ConnectDao(){
        
        try {
            String conStr = "jdbc:sqlserver://localhost:1433;databaseName=QLBH";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(conStr, "sa", "123456");
            
        } catch (Exception ex) {
          ex.printStackTrace();
    
        }
            
            
    }
}
