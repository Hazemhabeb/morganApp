package com.merjanapp.merjan.model;


import com.merjanapp.merjan.util.Constant;

import java.io.Serializable;

public class CityActivityModel implements Serializable{
    private int id = 0;
    private String name = "";
    private String image = "";
    private String county = "";

    public CityActivityModel() {
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

    public String getImage() {
        return Constant.ActivityCityImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
