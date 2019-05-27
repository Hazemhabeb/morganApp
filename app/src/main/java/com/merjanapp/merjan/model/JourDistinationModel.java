package com.merjanapp.merjan.model;


import java.io.Serializable;

public class JourDistinationModel implements Serializable{
    private String title="";
    private String desc = "";


    public JourDistinationModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
