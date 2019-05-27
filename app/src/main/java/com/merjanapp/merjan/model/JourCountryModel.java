package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/27/18.
 */

public class JourCountryModel {
    private int id=0;
    private String name="";
    private String image ="";

    public JourCountryModel() {
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
