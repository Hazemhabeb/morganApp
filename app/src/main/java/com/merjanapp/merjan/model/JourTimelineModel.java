package com.merjanapp.merjan.model;

import java.io.Serializable;

/**
 * Created by hazemhabeb on 11/18/18.
 */

public class JourTimelineModel implements Serializable{

    private int day = 0;
    private String header = "";
    private String desc ="";

    public JourTimelineModel() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
