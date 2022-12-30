package com.example.shoppingapp.model;

import java.io.Serializable;

public class MyBagModel implements Serializable {

    String productName;
    String productPrice;
    String totalQuantity;
    int totalPrice;
    String img_url;
    String documentId;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public MyBagModel() {
    }

    public MyBagModel(String productName, String img_url, String productPrice,String totalQuantity, int totalPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.img_url = img_url;

    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }


    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return (Integer.valueOf(this.getTotalQuantity()) * Integer.valueOf(this.getProductPrice()));
    }
}