package com.merjanapp.merjan.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.HotelRoomDetailAdapter;
import com.merjanapp.merjan.adapter.ImagesHotelAdapter;
import com.merjanapp.merjan.adapter.LandMarkHotelsAdapter;
import com.merjanapp.merjan.adapter.ServicesHotelsDetailAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.HotelDetailModel;
import com.merjanapp.merjan.model.HotelDetailRoom;
import com.merjanapp.merjan.model.HotelLandMarkModel;
import com.merjanapp.merjan.model.HotelRoomService;
import com.merjanapp.merjan.model.HotelService;
import com.merjanapp.merjan.util.Constant;
import com.merjanapp.merjan.view.CircleIndicator2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailHotelActivity extends AppCompatActivity {

    //init the views
    @BindView(R.id.nameTV)
    TextView nameTV;
    @BindView(R.id.addressTV)
    TextView addressTV;
    @BindView(R.id.starsRB)
    RatingBar starsRB;
    @BindView(R.id.detailTV)
    TextView detailTV;
    @BindView(R.id.policyTV)
    TextView policyTV;
    @BindView(R.id.serviceRV)
    RecyclerView serviceRV;
    @BindView(R.id.landMarkRV)
    RecyclerView landmarkRV;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.parentView_)
    FrameLayout parent;
    @BindView(R.id.imagesRV)
    RecyclerView imagesRV;
    @BindView(R.id.roomsRV)
    RecyclerView roomsRV;

    @BindView(R.id.moreTV)
    TextView moreTV;

    //the data of the recycler views
    ArrayList<HotelService> dataService = new ArrayList<>();
    ArrayList<HotelLandMarkModel> dataLandMark = new ArrayList<>();
    ArrayList<String> imagesData = new ArrayList<>();


    //the adapters
    private ServicesHotelsDetailAdapter serAdapter;
    private LandMarkHotelsAdapter landAdapter;
    private ImagesHotelAdapter imagesAdapter;
    private HotelRoomDetailAdapter roomAdpter;


    //here to get the data from the intent
    private int hotelId;


    //the more action text
    private boolean moreBolean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        ButterKnife.bind(this);


        //get the hotel id from the intent
        hotelId = getIntent().getIntExtra("data", 0);

        Log.d("google", "this is the hotel id " + hotelId);

        //init the services recycler views
        serAdapter = new ServicesHotelsDetailAdapter(dataService, this);
        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        serviceRV.setLayoutManager(layoutManager);
        serviceRV.setAdapter(serAdapter);


        //init the landmark recycler views
        landAdapter = new LandMarkHotelsAdapter(dataLandMark, this);
        RecyclerView.LayoutManager
                layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        landmarkRV.setLayoutManager(layoutManager1);
        landmarkRV.setAdapter(landAdapter);


        //init the recycler of the images
        RecyclerView.LayoutManager
                layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imagesRV.setLayoutManager(layoutManager2);
        imagesAdapter = new ImagesHotelAdapter(imagesData, this);
        imagesRV.setAdapter(imagesAdapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imagesRV);

        CircleIndicator2 indicator = findViewById(R.id.indicator);
        indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
        imagesAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

        //end of the images init


        //todo here the rooms
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(DetailHotelActivity.this, LinearLayoutManager.VERTICAL, false);

        roomsRV.setLayoutManager(layoutManager3);


        // here to call the data
        getDataDetail();


        moreBolean = true;
    }

    /**
     * here to action the back button
     */
    @OnClick(R.id.backIV)
    void back() {
        finish();

    }


    /**
     * here to get the data from the server
     */

    private void getDataDetail() {


        loading.setVisibility(View.VISIBLE);
        parent.setVisibility(View.GONE);

        String url = Constant.baseUrl + Constant.hotelDetail + hotelId;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);

                try {
                    extractJsonDetail(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("google", "error ----->   " + error.toString());
                loading.setVisibility(View.GONE);
//                Toast.makeText(TreeActivity.this,"لا يمكنك الدخول ",Toast.LENGTH_LONG).show();
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        //this comment to make the request time  is longer
//        jsObjRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(DetailHotelActivity.this).addToRequestQueue(jsObjRequest);
    }


    //todo here to get the data from the api and put it in the xml

    /**
     * here to extract the data from the server and get it
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    @SuppressLint("SetTextI18n")
    private void extractJsonDetail(JSONObject response) throws JSONException {


        JSONObject respObject = response.getJSONObject("Response");

        JSONArray dataArr = respObject.getJSONArray("data");

        JSONObject respData = dataArr.getJSONObject(0);


        HotelDetailModel hotel = new HotelDetailModel();

        hotel.setName(respData.getString("HotelName"));
        hotel.setAddress(respData.getString("Address"));
        hotel.setStars(respData.getDouble("Stars"));

        // here the images

        JSONArray imageArr = respData.getJSONArray("HotelImages");
        for (int i = 0; i < imageArr.length(); i++) {
            imagesData.add(Constant.HotelResultImage + imageArr.getString(i));
        }
        hotel.setImages(imagesData);
        imagesAdapter.notifyDataSetChanged();

        // here the hotel services

        JSONArray serArr = respData.getJSONArray("HotelServices");
        for (int i = 0; i < serArr.length(); i++) {
            JSONObject ser = serArr.getJSONObject(i);
            HotelService service = new HotelService();

            service.setImage(ser.getString("ImageUrl"));
            service.setName(ser.getString("Name"));
            dataService.add(service);
            serAdapter.notifyDataSetChanged();
        }
        hotel.setServices(dataService);

        // continue the data
        hotel.setDetail(respData.getString("Details"));
        hotel.setMoreDetail(respData.getString("MoreDetails"));
        hotel.setPolicy(respData.getString("Policy"));


        // LandMarks

        JSONArray landArr = respData.getJSONArray("LandMarks");


        for (int i = 0; i < landArr.length(); i++) {

            JSONObject land = landArr.getJSONObject(i);

            HotelLandMarkModel landMark = new HotelLandMarkModel();

            landMark.setName(land.getString("Name"));
            landMark.setImage(land.getString("ImageUrl"));
            landMark.setDistance((i + 5) + " كيلو متر ");

            if (i == 0)
                landMark.setImageR(R.drawable.room);
            else if (i == 1)
                landMark.setImageR(R.drawable.store);
            else if (i == 2)
                landMark.setImageR(R.drawable.home_variant);
            else if (i == 3)
                landMark.setImageR(R.drawable.airplane);

            dataLandMark.add(landMark);
            landAdapter.notifyDataSetChanged();
            //todo the distance here
//            landMark.setDistance("");
        }

        hotel.setLandMark(dataLandMark);


        //todo here the hotel rooms

        JSONArray roomArr = respData.getJSONArray("HotelRooms");
        ArrayList<HotelDetailRoom> rooms = new ArrayList<>();

        for (int i = 0; i < roomArr.length(); i++) {
            JSONObject rObj = roomArr.getJSONObject(i);
            HotelDetailRoom r = new HotelDetailRoom();

            r.setId(rObj.getInt("Id"));
            r.setRoomName(rObj.getString("RoomName"));

            JSONArray detailArr = rObj.getJSONArray("RoomDetails");
            JSONObject detailObj = detailArr.getJSONObject(0);
            r.setSupplement(detailObj.getString("Supplements"));
            r.setClose(detailObj.getString("Close"));
            r.setCloseDetail(detailObj.getString("CloseDetails"));
            r.setPayway(detailObj.getString("PayWay"));
            r.setPrice(detailObj.getDouble("Price"));
            rooms.add(r);
        }

        hotel.setRooms(rooms);

        ArrayList<HotelRoomService> roomServices = new ArrayList<>();

        JSONArray roomSerArr = respObject.getJSONArray("roomservices");

        for (int i = 0; i < roomSerArr.length(); i++) {
            JSONObject rso = roomSerArr.getJSONObject(i);
            HotelRoomService rs = new HotelRoomService();

            rs.setImage(rso.getString("Image"));
            rs.setName(rso.getString("Name"));
            roomServices.add(rs);
        }
        hotel.setRoomServices(roomServices);


        //todo here to add the data to the design
        setDataToDesign(hotel);


    }

    /**
     * here to set the data to the xml design
     *
     * @param data the data after extract the json
     */
    private void setDataToDesign(final HotelDetailModel data) {

        nameTV.setText(data.getName());
        addressTV.setText(data.getAddress());
        starsRB.setRating((float) data.getStars());
        detailTV.setText(data.getDetail());
        policyTV.setText(data.getPolicy());


        moreTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (moreBolean) {
                    moreTV.setText("إقرا اقل");
                    moreBolean = false;
                    detailTV.setText(data.getMoreDetail());

                } else {
                    moreTV.setText("إقرا المزيد");
                    moreBolean = true;
                    detailTV.setText(data.getDetail());
                }
            }
        });


        //todo room adapter
        roomAdpter = new HotelRoomDetailAdapter(hotelId, data.getRooms(), data.getRoomServices(), DetailHotelActivity.this);

        roomsRV.setAdapter(roomAdpter);

    }


}
