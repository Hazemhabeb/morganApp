package com.merjanapp.merjan.model;


import java.io.Serializable;
import java.util.ArrayList;

public class JourDetailModel implements Serializable{

    private int id = 0;
    private String name = "";
    private int cityId = 0;
    private String cityName = "";
    private int countryId = 0;
    private String countryName = "";
    private String detail = "";
    private ArrayList<JourDistinationModel> detailDistination = new ArrayList<>();
    private String visaDetail = "";
    private String imageName = "";
    private double newPrice = 0;
    private double oldPrice = 0;
    private double savedPrice = 0;
    private JourActivityDetailModel activity = new JourActivityDetailModel();
    private ArrayList<String> cancelPolicy = new ArrayList<>();
    private ArrayList<String> inclusion = new ArrayList<>();
    private ArrayList<String> exceptions = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> payPolicy = new ArrayList<>();
    private JourRoomDetailModel room = new JourRoomDetailModel();
    private ArrayList<String> termsAndConditions = new ArrayList<>();
    private ArrayList<JourTimelineModel> timeline = new ArrayList<>();
    private JourDetailFlight flight = new JourDetailFlight();


    public JourDetailModel() {
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<JourDistinationModel> getDetailDistination() {
        return detailDistination;
    }

    public void setDetailDistination(ArrayList<JourDistinationModel> detailDistination) {
        this.detailDistination = detailDistination;
    }

    public String getVisaDetail() {
        return visaDetail;
    }

    public void setVisaDetail(String visaDetail) {
        this.visaDetail = visaDetail;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getSavedPrice() {
        return savedPrice;
    }

    public void setSavedPrice(double savedPrice) {
        this.savedPrice = savedPrice;
    }

    public JourActivityDetailModel getActivity() {
        return activity;
    }

    public void setActivity(JourActivityDetailModel activity) {
        this.activity = activity;
    }

    public ArrayList<String> getCancelPolicy() {
        return cancelPolicy;
    }

    public void setCancelPolicy(ArrayList<String> cancelPolicy) {
        this.cancelPolicy = cancelPolicy;
    }

    public ArrayList<String> getInclusion() {
        return inclusion;
    }

    public void setInclusion(ArrayList<String> inclusion) {
        this.inclusion = inclusion;
    }

    public ArrayList<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(ArrayList<String> exceptions) {
        this.exceptions = exceptions;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getPayPolicy() {
        return payPolicy;
    }

    public void setPayPolicy(ArrayList<String> payPolicy) {
        this.payPolicy = payPolicy;
    }

    public JourRoomDetailModel getRoom() {
        return room;
    }

    public void setRoom(JourRoomDetailModel room) {
        this.room = room;
    }

    public ArrayList<String> getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(ArrayList<String> termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public ArrayList<JourTimelineModel> getTimeline() {
        return timeline;
    }

    public void setTimeline(ArrayList<JourTimelineModel> timeline) {
        this.timeline = timeline;
    }

    public JourDetailFlight getFlight() {
        return flight;
    }

    public void setFlight(JourDetailFlight flight) {
        this.flight = flight;
    }
}
