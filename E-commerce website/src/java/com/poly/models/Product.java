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
public class Product {
    int id;
    int categories_id;
    String name;
    int price;
    String note;
    String image;

    public Product() {
    }

    public Product(int id, int categories_id, String name, int price, String note, String image) {
        this.id = id;
        this.categories_id = categories_id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
    }

    public Product(int categories_id, String name, int price, String note, String image) {
        this.categories_id = categories_id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
}
