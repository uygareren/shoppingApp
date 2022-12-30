package com.example.shoppingapp.model;

import java.io.Serializable;

public class FavoriteModel implements Serializable {

    String favname;
    int favprice;
    String favrating;
    String img_url;
    String documentId;



    public FavoriteModel() {
    }

    public FavoriteModel(String favname, int favprice, String favrating, String img_url, String documentId) {
        this.favname = favname;
        this.favprice = favprice;
        this.favrating = favrating;
        this.img_url = img_url;
        this.documentId = documentId;
    }
    public String getFavname() {
        return favname;
    }

    public void setFavname(String favname) {
        this.favname = favname;
    }

    public int getFavprice() {
        return favprice;
    }

    public void setFavprice(int favprice) {
        this.favprice = favprice;
    }

    public String getFavRating() {
        return favrating;
    }

    public void setFavRating(String rating) {
        this.favrating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
