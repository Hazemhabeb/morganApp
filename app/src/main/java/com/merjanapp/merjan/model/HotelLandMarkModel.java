package com.merjanapp.merjan.model;

import com.merjanapp.merjan.util.Constant;

/**
 * Created by hazemhabeb on 11/17/18.
 */

public class HotelLandMarkModel {

    private String name="";
    private String image = "";
    private String distance = "";
    private int imageR =0;


    public HotelLandMarkModel() {
    }

    public String getName() {
        return name;
    }

    public int getImageR() {
        return imageR;
    }

    public void setImageR(int imageR) {
        this.imageR = imageR;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return  Constant.HotelServiceImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
