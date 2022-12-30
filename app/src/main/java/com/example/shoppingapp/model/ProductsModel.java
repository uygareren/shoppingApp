package com.example.shoppingapp.model;

import java.io.Serializable;

public class ProductsModel implements Serializable {
    String name;
    String img_url;
    String rating;
    String type;
    int price;
    String documentId;

    public ProductsModel() {

    }

    public ProductsModel(String name, String img_url, String rating, String type, int price) {
        this.name = name;
        this.img_url = img_url;
        this.rating = rating;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
