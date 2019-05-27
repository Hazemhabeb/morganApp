package com.merjanapp.merjan.model;

import java.io.Serializable;

/**
 * Created by hazemhabeb on 11/25/18.
 */

public class JourActivityDetailModel implements Serializable{
    private int id = 0;
    private String name = "";
    private int duration = 0;
    private String class_ ="";
    private String image ="";
    private double price = 0;

    public JourActivityDetailModel() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
