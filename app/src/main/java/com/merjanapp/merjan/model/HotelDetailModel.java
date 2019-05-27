package com.merjanapp.merjan.model;

import java.util.ArrayList;

/**
 * Created by hazemhabeb on 11/17/18.
 */

public class HotelDetailModel {

    private String name = "";
    private String address = "";
    private double stars = 0;
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<HotelService> services = new ArrayList<>();
    private String detail = "";
    private String moreDetail = "";
    private String policy = "";
    private ArrayList<HotelLandMarkModel> landMark = new ArrayList<>();


    // here to add the hotel rooms

    private ArrayList<HotelDetailRoom> rooms = new ArrayList<>();
    private ArrayList<HotelRoomService> roomServices = new ArrayList<>();

    public HotelDetailModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
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

    public String getMoreDetail() {
        return moreDetail;
    }

    public void setMoreDetail(String moreDetail) {
        this.moreDetail = moreDetail;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public ArrayList<HotelLandMarkModel> getLandMark() {
        return landMark;
    }

    public void setLandMark(ArrayList<HotelLandMarkModel> landMark) {
        this.landMark = landMark;
    }


    public ArrayList<HotelDetailRoom> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<HotelDetailRoom> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<HotelRoomService> getRoomServices() {
        return roomServices;
    }

    public void setRoomServices(ArrayList<HotelRoomService> roomServices) {
        this.roomServices = roomServices;
    }
}
