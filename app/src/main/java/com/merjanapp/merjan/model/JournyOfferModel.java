package com.merjanapp.merjan.model;


import com.merjanapp.merjan.util.Constant;

public class JournyOfferModel {
    private int id = 0;
    private String image = "";
    private String name = "";

    public JournyOfferModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return Constant.ActivityOfferImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
