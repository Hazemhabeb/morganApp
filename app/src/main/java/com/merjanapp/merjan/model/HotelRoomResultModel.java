package com.merjanapp.merjan.model;

import java.util.ArrayList;

/**
 * Created by hazemhabeb on 11/26/18.
 */

public class HotelRoomResultModel {
    private int id=0;
    private int hotelId =0;
    private String name ="";
    private double price =0;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<HotelRoomService> services = new ArrayList<>();


    public HotelRoomResultModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<HotelRoomService> getServices() {
        return services;
    }

    public void setServices(ArrayList<HotelRoomService> services) {
        this.services = services;
    }
}
