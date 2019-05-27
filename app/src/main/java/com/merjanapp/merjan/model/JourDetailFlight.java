package com.merjanapp.merjan.model;

import java.io.Serializable;

/**
 * Created by hazemhabeb on 11/18/18.
 */

public class JourDetailFlight implements Serializable{
    private int id = 0;
    private String cityAmong = "";
    private String duration = "";
    private String name = "";
    private String from = "";
    private String to ="";


    public JourDetailFlight() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityAmong() {
        return cityAmong;
    }

    public void setCityAmong(String cityAmong) {
        this.cityAmong = cityAmong;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
