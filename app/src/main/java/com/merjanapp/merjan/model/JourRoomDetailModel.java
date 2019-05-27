package com.merjanapp.merjan.model;

import java.io.Serializable;

/**
 * Created by hazemhabeb on 11/25/18.
 */

public class JourRoomDetailModel implements Serializable{
    private String name = "";
    private String image = "";
    private String roomName = "";
    private String address ="";
    private double stars = 0;

    public JourRoomDetailModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
}
