package com.merjanapp.merjan.model;

import com.merjanapp.merjan.util.Constant;

import java.util.ArrayList;

/**
 * Created by hazemhabeb on 10/24/18.
 */

public class HotelModel {
    private int id = 0;
    private String name = "";
    private String image ="";
    private double star = 0;
    private String address = "";
    private ArrayList<HotelService> services  = new ArrayList<>();
    private String detail = "";
    private String cityName ="";
    private int cityId =0;


    public HotelModel() {
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
        return Constant.HotelResultImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public void setServices(ArrayList<HotelService> services) {
        this.services = services;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
