package com.merjanapp.merjan.model;

/**
 * Created by hazemhabeb on 11/27/18.
 */

public class SearchJourModel {

    private int id = 0;
    private String name = "";
    private String type = "";

    //1 for holiday 2 for country 3 for city
    private int status = 0;


    public SearchJourModel() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

        if (type.equals("Holiday")) {
            setStatus(1);
        } else if (type.equals("Country")) {
            setStatus(2);
        } else if (type.equals("City")) {
            setStatus(3);
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
