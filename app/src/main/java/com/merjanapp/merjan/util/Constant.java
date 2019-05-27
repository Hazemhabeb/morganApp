package com.merjanapp.merjan.util;


public class Constant {
    public static String baseUrl = "http://172.107.175.236:800/";
//    http://172.107.175.236:800/
//    http://yousif7182-001-site2.ctempurl.com/
    public static String activityOffers = "api/Activity/GetActivityOffers";

    public static String activityCity = "api/Activity/GetBestCity";
    public static String activityResult = "api/Activity/GetByCity/";
    public static String activityDetail = "/api/Activity/GetById/";



    public static String hotelOffers = "api/Hotel/GetActivityOffers";
    public static String hotelCity = "/api/Hotel/GetBestCity?Count=";
    public static String hotelResult = "api/Hotel/GetByCity/";

    public static String hotelDetail ="/api/Hotel/Get/";
    //    {

//        "Response": [
//        {
//            "ActivityID": 8,
//                "OfferEnd": "2018-12-29T00:00:00",
//                "OfferImageUrl": "hotel1.jpg2657413592018.jpg",
//                "OfferName": "العرض الاول",
//                "OfferStart": "2018-11-12T00:00:00"
//        }
//    ]
//    }

    //todo here the offer for the activity constants
    //the offer activity keys from the api
    public static String offerid = "ActivityID";
    public static String offerend = "OfferEnd";
    public static String offerstart = "OfferStart";
    public static String offername = "OfferName";
    public static String offerimage = "OfferImageUrl";

    //    Activity Offer Images Path :
//    http://yousif7182-001-site1.ctempurl.com/MultiMedia/Offer/
    public static String OfferBaseImage = "http://yousif7182-001-site1.ctempurl.com/MultiMedia/Offer/";


    //todo here the constants for the response of the activities city

    // {
//         "CityName": "اسطنبول",
//         "CityId": 16,
//         "ImageUrl": "اسطنبول.jpg6944458161682018.jpg",
//         "CountryName": "تركيا"
//         },

    public static String cityid = "CityId";
    public static String cityname = "CityName";
    public static String cityimage = "ImageUrl";
    public static String citycountry = "CountryName";

//    City Images Path:
//    http://yousif7182-001-site1.ctempurl.com/MultiMedia/City/

    public static String cityBaseImage = "http://yousif7182-001-site1.ctempurl.com/MultiMedia/City/";


    //todo here the result activity response

   /* {
        "Id": 8,
            "ActivityName": "نشاط 1",
            "ImageUrl": "63572121282018.jpg67012105102018.jpg",
            "Price": 100,
            "Details": "التفاصيل",
            "CityName": "اسطنبول",
            "cityId": 16
    },*/

    public static String resultacid = "Id";
    public static String resultacname = "ActivityName";
    public static String resultacimage = "ImageUrl";
    public static String resultacprice = "Price";
    public static String resultacdetail = "Details";
    public static String resultaccityname = "CityName";
    public static String resultaccityid = "cityId";

//    Activity Images Path:
//    http://yousif7182-001-site1.ctempurl.com/MultiMedia/Activity/

    public static String resultBaseActivityImage = "http://yousif7182-001-site1.ctempurl.com/MultiMedia/Activity/";



    //the journy constants
    public static String jourOffer = "/api//journey/Getoffers";

    public static String jourCity = "/api/journey/GetTops";
    public static String jourResult = "/api/journey/SearchByCity/";



    //todo here all the base url for the images

    public static String ImageBase = "http://172.107.175.236/";

    public static String ActivityOfferImage = ImageBase+"/MultiMedia/Offer/";

    public static String ActivityCityImage = ImageBase+"/MultiMedia/City/";

    public static String ActivityResultImage = ImageBase+"/MultiMedia/Activity/";

    public static String ActivityDetailImage = ImageBase+"/MultiMedia/Activity/";

    public static String HotelResultImage = ImageBase+"/MultiMedia/Hotel/";

    public static String HotelServiceImage = ImageBase+"/Content/imgs/";

    public static String JourCityImage = ImageBase+"/MultiMedia/Journey/";

    public static String JourResultImage = ImageBase+"/MultiMedia/Journey/";



















}
