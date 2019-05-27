package com.merjanapp.merjan.model;

import com.merjanapp.merjan.util.Constant;

/**
 * Created by hazemhabeb on 10/24/18.
 */

public class HotelService {

    private String name="";
    private String image="";


    public HotelService() {
    }


    public HotelService(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Constant.HotelServiceImage+image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
