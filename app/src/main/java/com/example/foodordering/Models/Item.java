package com.example.foodordering.Models;

import java.util.UUID;

public class Item {
    private String iID, category, description, name;
    private Boolean nonVeg;
    private String photoUrl;
    private Float price;

    public Item() {
    }

    public Item(String iID, String category, String description, String name, Boolean nonVeg, String photoUrl, Float price) {
        this.iID = iID;
        this.category = category;
        this.description = description;
        this.name = name;
        this.nonVeg = nonVeg;
        this.photoUrl = photoUrl;
        this.price = price;
    }

    public String getiID() {
        return iID;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNonVeg() {
        return nonVeg;
    }

    public void setNonVeg(Boolean nonVeg) {
        this.nonVeg = nonVeg;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
