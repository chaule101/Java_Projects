/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.models;

/**
 *
 * @author hanhl
 */
public class User {
    private int ID;
    private String USERNAME;
    private String PASSWORD;
    private String FULLNAME;
    private String EMAIL;
    private String PHONE;
    private int role;

    public User() {
        
    }

    public User(String USERNAME, String PASSWORD, String FULLNAME, String EMAIL, String PHONE, int role) {
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.FULLNAME = FULLNAME;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.role = role;
    }
    
    

    public User(int ID, String USERNAME, String PASSWORD, String FULLNAME, String EMAIL, String PHONE, int role) {
        this.ID = ID;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.FULLNAME = FULLNAME;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.role = role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getFULLNAME() {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    
    
    
}
