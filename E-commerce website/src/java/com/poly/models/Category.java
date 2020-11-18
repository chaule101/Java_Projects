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
public class Category {
    int categories_id;
    String name;
    String note;

    public Category() {
    }

    public Category(int categories_id, String name, String note) {
        this.categories_id = categories_id;
        this.name = name;
        this.note = note;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
