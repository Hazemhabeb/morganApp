package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/10/18.
 */

public class JourFlight {
    private int id = 0;
    private String name = "";
    private double price = 0;


    public JourFlight() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
