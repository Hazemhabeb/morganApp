package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/27/18.
 */

public class JourChangeActModel {
    private String image ="";
    private String name="";
    private double price =0;


    public JourChangeActModel() {
    }

    public String getImage() {
        return image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
