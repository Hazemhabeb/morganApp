package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/27/18.
 */

public class JourChangeHotelModel {
    private String name ="";
    private String image ="";
    private String roomName="";
    private String roomImage = "";
    private double price =0;


    public JourChangeHotelModel() {
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

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
