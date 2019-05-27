package com.merjanapp.merjan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.ResultJourAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.JourFlight;
import com.merjanapp.merjan.model.JourHotel;
import com.merjanapp.merjan.model.JourNight;
import com.merjanapp.merjan.model.JourResultCityModel;
import com.merjanapp.merjan.model.JournyCityModel;
import com.merjanapp.merjan.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultJourActivity extends AppCompatActivity {

    //init the views
    @BindView(R.id.resultRv)RecyclerView resultRV;
    @BindView(R.id.loading)ProgressBar loading;
    @BindView(R.id.cityTV)TextView cityTV;



    //offer adapter
    ResultJourAdapter resultAdapter;
    ArrayList<JourResultCityModel> resultData =new ArrayList<>();

    //to get the data from the intent
    JournyCityModel city;

    String searchUrl;
    String titleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        //to get the data from the intent

        city = (JournyCityModel) getIntent().getSerializableExtra("data");


        searchUrl = getIntent().getStringExtra("searchUrl");
        titleName = getIntent().getStringExtra("title");


        if (city != null)
            cityTV.setText(city.getName());
        else
            cityTV.setText(titleName);


        initView();
    }

    /**
     * here to init the views
     */
    private void initView(){

        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        resultRV.setLayoutManager(layoutManager);

        resultAdapter = new ResultJourAdapter(resultData, ResultJourActivity.this);
        resultRV.setAdapter(resultAdapter);


        getDataResult();

//        resultData.add(new JourResultCityModel());resultData.add(new JourResultCityModel());
//        resultData.add(new JourResultCityModel());resultData.add(new JourResultCityModel());
//        resultData.add(new JourResultCityModel());resultData.add(new JourResultCityModel());
//        resultData.add(new JourResultCityModel());resultData.add(new JourResultCityModel());
//
//        resultAdapter.notifyDataSetChanged();

    }

    /**
     * here to action to the back button
     */
    @OnClick(R.id.backIV)void back(){
        finish();
    }


    private void getDataResult() {


        loading.setVisibility(View.VISIBLE);



        String url;
        if (searchUrl == null)
            url = Constant.baseUrl+Constant.jourResult+city.getCityId();
        else
            url = searchUrl;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);

                try {
                    extractJsonResult(response);

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
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    /**
     * here to extract the data from the server and get it
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    private void extractJsonResult(JSONObject response) throws JSONException {


        resultData.clear();

        JSONObject respObje = response.getJSONObject("Response");

        JSONArray jourArr = respObje.getJSONArray("Journeys");




        for (int i = 0; i < jourArr.length(); i++) {

            JSONObject object = jourArr.getJSONObject(i);


            JourResultCityModel result=new JourResultCityModel();

            result.setId(object.getInt("JourneyId"));
            result.setName(object.getString("JourneyName"));
            result.setImage(object.getString("ImageName"));
            result.setOldPrice(object.getDouble("OldPrice"));
            result.setNewPrice(object.getDouble("NewPrice"));
            result.setSavedPrice(object.getDouble("SavedPrice"));
            result.setDetail(object.getString("Details"));


            //todo here to add the nights

            JSONArray nightArr = object.getJSONArray("Nights");
            ArrayList<JourNight> nights = new ArrayList<>();


            for (int j = 0; j<nightArr.length();j++){

                JSONObject __n = nightArr.getJSONObject(j);

                JourNight n = new JourNight();
                n.setId(__n.getInt("NightId"));
                n.setNight(__n.getInt("Night"));
                n.setPrice(__n.getDouble("Price"));

                nights.add(n);

            }


            JSONArray hotelArr = object.getJSONArray("Hotels");
            ArrayList<JourHotel> hotels = new ArrayList<>();


            for (int j = 0; j<hotelArr.length();j++){

                JSONObject __h = hotelArr.getJSONObject(j);

                JourHotel h = new JourHotel();
                h.setRoomId(__h.getInt("RoomId"));
                h.setNewPrice(__h.getDouble("NewPrice"));
                h.setOldPrice(__h.getDouble("OldPrice"));

                h.setStars(__h.getDouble("HotelStars"));

                hotels.add(h);

            }


            JSONArray flightArr = object.getJSONArray("Flights");
            ArrayList<JourFlight> flights = new ArrayList<>();


            for (int j = 0; j<flightArr.length();j++){

                JSONObject __f = flightArr.getJSONObject(j);
                JourFlight f = new JourFlight();

                f.setId(__f.getInt("FlightId"));
                f.setName(__f.getString("FlightName"));
                f.setPrice(__f.getDouble("FlightPrice"));

                flights.add(f);

            }





            result.setFlights(flights);
            result.setHotels(hotels);
            result.setNights(nights);




            resultData.add(result);

            resultAdapter.notifyDataSetChanged();
        }


    }








}
