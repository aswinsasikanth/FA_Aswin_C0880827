package com.fa_aswinsasikanth_c0880827_android.Models;


public class ProductModel {

    private int id;
    private String name;
    private String description;
    private String price;
    private String location;

    public ProductModel() {}
    public ProductModel(String name, String description, String price, String location) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public ProductModel(int id, String name, String description, String price, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
