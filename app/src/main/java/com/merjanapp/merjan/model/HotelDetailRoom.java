package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/26/18.
 */

public class HotelDetailRoom {

    private int id = 0;
    private String roomName  ="";
    private String supplement="";
    private String close="";
    private String closeDetail ="";
    private String payway ="";
    private double price =0;

    public HotelDetailRoom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCloseDetail() {
        return closeDetail;
    }

    public void setCloseDetail(String closeDetail) {
        this.closeDetail = closeDetail;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
