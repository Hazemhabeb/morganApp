package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/10/18.
 */

public class JourNight {

    private int id = 0;
    private int night = 0;
    private double price = 0;


    public JourNight() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
