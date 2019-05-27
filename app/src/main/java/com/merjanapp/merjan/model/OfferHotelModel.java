package com.merjanapp.merjan.model;

import com.merjanapp.merjan.util.Constant;

/**
 * Created by hazemhabeb on 10/13/18.
 */

public class OfferHotelModel {
    private int hotelID = 0;
    private String OfferEnd = "";
    private String OfferImageUrl = "";
    private String OfferName = "";
    private String OfferStart = "";


    public OfferHotelModel() {

    }


    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getOfferEnd() {
        return OfferEnd;
    }

    public void setOfferEnd(String offerEnd) {
        OfferEnd = offerEnd;
    }

    public String getOfferImageUrl() {
        return OfferImageUrl;
    }

    public void setOfferImageUrl(String offerImageUrl) {

        OfferImageUrl = Constant.ActivityOfferImage + offerImageUrl;
    }

    public String getOfferName() {
        return OfferName;
    }

    public void setOfferName(String offerName) {
        OfferName = offerName;
    }

    public String getOfferStart() {
        return OfferStart;
    }

    public void setOfferStart(String offerStart) {
        OfferStart = offerStart;
    }
}
