package com.merjanapp.merjan.model;

import java.util.ArrayList;



public class DetailActivityModel {

    private int id=0;
    private String name="";
    private String price="";
    private String city="";
    private String country="";
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> services = new ArrayList<>();
    private ArrayList<DetailInfoModel> info = new ArrayList<>();


    public DetailActivityModel() {

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public ArrayList<DetailInfoModel> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<DetailInfoModel> info) {
        this.info = info;
    }
}
