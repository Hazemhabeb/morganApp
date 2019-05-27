package com.merjanapp.merjan.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.JourChangeActAdapter;
import com.merjanapp.merjan.adapter.JourChangeHotelAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.JourChangeActModel;
import com.merjanapp.merjan.model.JourChangeHotelModel;
import com.merjanapp.merjan.model.JourDetailModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by M on 11/21/2016.
 */

@SuppressLint("ValidFragment")
public class JourTripDetailFragment extends Fragment {

    JourDetailModel data;
    @SuppressLint("ValidFragment")
    public JourTripDetailFragment(JourDetailModel data) {
        // Required empty public constructor
        this.data=data;
    }


    //init the views
    @BindView(R.id.fromTV)TextView fromTv;
    @BindView(R.id.toTV)TextView toTV;
    @BindView(R.id.durationTV)TextView durationTV;
    @BindView(R.id.cityNameTV)TextView cityNameTV;
    @BindView(R.id.tripNameTV)TextView tripNameTV;
    @BindView(R.id.hotelNameTV)TextView hotelNameTV;
    @BindView(R.id.jouneyName)TextView jouneyName;
    @BindView(R.id.roomAddrssTV)TextView roomAddrssTV;
    @BindView(R.id.hotelImage)ImageView hotelImage;
    @BindView(R.id.roomNameTV)TextView roomNameTV;
    @BindView(R.id.rateRB)RatingBar rateRB;

    @BindView(R.id.activityImage)ImageView activityImage;
    @BindView(R.id.activityTV)TextView activityTV;
    @BindView(R.id.classTV)TextView classTV;
    @BindView(R.id.durTV)TextView durTV;
    @BindView(R.id.acPriceTV)TextView acPriceTV;
    @BindView(R.id.loading)ProgressBar loading;







    // the chose of the hotel

    @BindView(R.id.parentJourChose)LinearLayout parentJourChose;
    @BindView(R.id.jourChoseRV)RecyclerView jourChoseRV;


    //the data of the recycler
    ArrayList<JourChangeHotelModel> hotels = new ArrayList<>();
    ArrayList<JourChangeActModel> acts = new ArrayList<>();



    //the adapter
    JourChangeHotelAdapter changeAdapter;
    JourChangeActAdapter changeActAdapter;


    //todo the activity
    @BindView(R.id.parentActivity)LinearLayout parentActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jour_trip_detail, container, false);
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);

        //init the recycler view


        Log.d("google","detail fragment "+data.getCityName());
        fromTv.setText(data.getFlight().getFrom());
        toTV.setText(data.getFlight().getTo());
        durationTV.setText(data.getFlight().getDuration()+" ساعة ");
        cityNameTV.setText(data.getFlight().getCityAmong());
        tripNameTV.setText(data.getFlight().getName());


        // the hotel
        hotelNameTV.setText(data.getRoom().getName());
        jouneyName.setText(data.getName());
        roomAddrssTV.setText(data.getRoom().getAddress());
        roomNameTV.setText(data.getRoom().getRoomName());

        if (getContext() != null)
            Glide.with(getContext()).load
                    ("http://172.107.175.236/MultiMedia/Hotel/"+data.getRoom().getImage()).into(hotelImage);


        rateRB.setRating((float) data.getRoom().getStars());



        // the activity


        if (getContext() != null)
            Glide.with(getContext()).load
                    ("http://172.107.175.236/MultiMedia/Activity/"+data.getActivity().getImage()).into(activityImage);


        activityTV.setText(data.getActivity().getName());
        classTV.setText(" الفئة : "+data.getActivity().getClass_());
        durTV.setText(" المدة : "+data.getActivity().getDuration()+ " ساعات ");
        acPriceTV.setText(data.getActivity().getPrice()+ " $ ");



        return view;
    }


    /**
     * action the change button
     */

    @OnClick(R.id.changeHotelBtn)void changeAction(){
        parentJourChose.setVisibility(View.VISIBLE);

        // to get the hotels
        getHotelsResult();
    }

    /**
     * action the dismiss
     */

    @OnClick(R.id.parentJourChose)void dismissHotels(){
        parentJourChose.setVisibility(View.GONE);
    }

    private void getHotelsResult() {


        loading.setVisibility(View.VISIBLE);

        String url =  "http://172.107.175.236:800/api/journey/GetHotels?journeyid="+data.getId();


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);

                try {
                    extracthotelDataResult(response);

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
        MySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);
    }




    /**
     * here to extract the data from the server and get it
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    private void extracthotelDataResult(JSONObject response) throws JSONException {

        hotels.clear();
        JSONArray respArr = response.getJSONArray("Response");

        for(int i=0;i<respArr.length();i++){
            JSONObject respObj = respArr.getJSONObject(i);
            JourChangeHotelModel hotel = new JourChangeHotelModel();

            hotel.setName(respObj.getString("Name"));
            hotel.setImage(respObj.getString("MainImageUrl"));
            hotel.setRoomName(respObj.getString("RoomName"));
            hotel.setRoomImage(respObj.getString("RoomImageUrl"));
            hotel.setPrice(respObj.getDouble("Price"));

            hotels.add(hotel);
        }


        changeAdapter = new JourChangeHotelAdapter(hotels,getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);

        jourChoseRV.setLayoutManager(layoutManager);
        jourChoseRV.setAdapter(changeAdapter);
    }


    // here to change the hotel data
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHotelChangeEvent(JourChangeHotelModel event) {

        parentJourChose.setVisibility(View.GONE);
        hotelNameTV.setText(event.getName());
        roomNameTV.setText(event.getRoomName());

        if (getContext() != null)
            Glide.with(getContext()).load
                    ("http://172.107.175.236/MultiMedia/Hotel/"+event.getImage()).into(hotelImage);

    }


    /**
     * action the activity remove
     */
    @OnClick(R.id.actRemoveBtn)void RemoveAct(){
        parentActivity.setVisibility(View.GONE);
    }

    /**
     * action the activity add
     */
    @OnClick(R.id.actAddBtn)void addAct(){

        parentJourChose.setVisibility(View.VISIBLE);
        // to get the activity
        getActivityResult();
    }



    private void getActivityResult() {


        loading.setVisibility(View.VISIBLE);

        String url =  "http://172.107.175.236:800/api/journey/GetActvivities?journeyid="+data.getId();


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);

                try {
                    extractActDataResult(response);

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
        MySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);
    }


    /**
     * here to extract the data from the server and get it
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    private void extractActDataResult(JSONObject response) throws JSONException {

        acts.clear();
        JSONArray respArr = response.getJSONArray("Response");

        for(int i=0;i<respArr.length();i++){
            JSONObject respObj = respArr.getJSONObject(i);
            JourChangeActModel hotel = new JourChangeActModel();

            hotel.setName(respObj.getString("ActivityName"));
            hotel.setImage(respObj.getString("ImageUrl"));
            hotel.setPrice(respObj.getDouble("Price"));

            acts.add(hotel);
        }


        changeActAdapter = new JourChangeActAdapter(acts,getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);

        jourChoseRV.setLayoutManager(layoutManager);
        jourChoseRV.setAdapter(changeActAdapter);
    }

    // here to change the actvity data
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityChangeEvent(JourChangeActModel event) {

        parentJourChose.setVisibility(View.GONE);
        parentActivity.setVisibility(View.VISIBLE);
        if (getContext() != null)
            Glide.with(getContext()).load
                    ("http://172.107.175.236/MultiMedia/Activity/"+event.getImage()).into(activityImage);


        activityTV.setText(event.getName());
        acPriceTV.setText(event.getPrice()+ " $ ");



    }

}
