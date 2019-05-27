package com.merjanapp.merjan.model;

import com.merjanapp.merjan.util.Constant;

import java.util.ArrayList;


public class JourResultCityModel {

    private int id = 0;
    private String name = "";
    private String image = "";
    private double oldPrice = 0;
    private double newPrice = 0;
    private double savedPrice = 0;
    private String detail = "";
    private ArrayList<JourNight> nights = new ArrayList<>();
    private ArrayList<JourFlight> flights = new ArrayList<>();
    private ArrayList<JourHotel> hotels = new ArrayList<>();

    public JourResultCityModel() {
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
        return Constant.JourResultImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public double getSavedPrice() {
        return savedPrice;
    }

    public void setSavedPrice(double savedPrice) {
        this.savedPrice = savedPrice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<JourNight> getNights() {
        return nights;
    }

    public void setNights(ArrayList<JourNight> nights) {
        this.nights = nights;
    }

    public ArrayList<JourFlight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<JourFlight> flights) {
        this.flights = flights;
    }

    public ArrayList<JourHotel> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<JourHotel> hotels) {
        this.hotels = hotels;
    }
}
