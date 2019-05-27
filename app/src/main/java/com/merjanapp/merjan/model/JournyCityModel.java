package com.merjanapp.merjan.model;

import com.merjanapp.merjan.util.Constant;

import java.io.Serializable;


public class JournyCityModel implements Serializable{

    private int cityId =  0;

    private String name = "";
    private String image = "";


    public JournyCityModel() {
    }


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Constant.JourCityImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

