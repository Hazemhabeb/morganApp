package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/25/18.
 */

public class JourServiceModel {

    private int image =0;
    private String name = "";



    public JourServiceModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
