/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.models;

/**
 *
 * @author Chau Le
 */
public class Product {

    int id;
    String name;
    int price;
    String note;
    String image;
    int category_id;

    public Product(int id, String name, int price, String note, String image, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
        this.category_id = category_id;
    }

    public Product(String name, int price, String note, String image, int category_id) {
        this.name = name;
        this.price = price;
        this.note = note;
        this.image = image;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

}
