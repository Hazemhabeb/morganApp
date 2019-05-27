package com.merjanapp.merjan.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.ImagesHotelAdapter;
import com.merjanapp.merjan.adapter.JourExpectationsAdapter;
import com.merjanapp.merjan.adapter.JourInclusionsAdapter;
import com.merjanapp.merjan.adapter.JourTermsAdapter;
import com.merjanapp.merjan.adapter.JourTimelineAdapter;
import com.merjanapp.merjan.adapter.ServicesJourDetailAdapter;
import com.merjanapp.merjan.adapter.ViewPagerAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.fragment.JourDestinationDetailFragment;
import com.merjanapp.merjan.fragment.JourTripDetailFragment;
import com.merjanapp.merjan.fragment.JourVisaDetailFragment;
import com.merjanapp.merjan.model.JourActivityDetailModel;
import com.merjanapp.merjan.model.JourDetailFlight;
import com.merjanapp.merjan.model.JourDetailModel;
import com.merjanapp.merjan.model.JourDistinationModel;
import com.merjanapp.merjan.model.JourRoomDetailModel;
import com.merjanapp.merjan.model.JourServiceModel;
import com.merjanapp.merjan.model.JourTimelineModel;
import com.merjanapp.merjan.view.CircleIndicator2;
import com.merjanapp.merjan.view.WrapContentViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailJourActivity extends AppCompatActivity {


    //init the views
    @BindView(R.id.serviceRV)
    RecyclerView serviceRV;
    @BindView(R.id.inclusionsRV)
    RecyclerView inclusionsRV;
    @BindView(R.id.expetationsRV)
    RecyclerView expetationsRV;
    @BindView(R.id.timelineRV)
    RecyclerView timelineRV;
    @BindView(R.id.termsRV)
    RecyclerView termsRV;
    @BindView(R.id.cancelRV)
    RecyclerView cancelRV;
    @BindView(R.id.payRV)
    RecyclerView payRV;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.parentView_)
    FrameLayout parent;
    @BindView(R.id.imagesRV)
    RecyclerView imagesRV;
    @BindView(R.id.nameTV)TextView nameTV;
    @BindView(R.id.addressTV)TextView addressTV;
    @BindView(R.id.oldPriceTV)TextView oldPriceTV;
    @BindView(R.id.newPriceTV)TextView newPriceTV;
    @BindView(R.id.savePriceTV)TextView savePriceTV;




    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    WrapContentViewPager viewPager;

    //the data for the revycler
    ArrayList<String> imagesData = new ArrayList<>();

    ArrayList<JourServiceModel> dataService = new ArrayList<>();
    ArrayList<String> dataInclu = new ArrayList<>();
    ArrayList<String> dataExpect = new ArrayList<>();
    ArrayList<JourTimelineModel> dataTimeline = new ArrayList<>();
    ArrayList<String> dataTerms = new ArrayList<>();
    ArrayList<String> dataCancel = new ArrayList<>();
    ArrayList<String> dataPay = new ArrayList<>();


    //the adapter of the recycler
    private ImagesHotelAdapter imagesAdapter;

    private ServicesJourDetailAdapter serAdapter;
    private JourInclusionsAdapter incluAdapter;
    private JourExpectationsAdapter expectAdapter;
    private JourTimelineAdapter timelineAdapter;
    private JourTermsAdapter termsAdapter, cancelAdapter, payAdapter;

    //the url from the intent
    private String url;
    private int nightID;
    private int roomID;

    //the data from the server
    JourDetailModel jour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jour);
        ButterKnife.bind(this);


        url = getIntent().getStringExtra("data");
        nightID = getIntent().getIntExtra("night",0);
        roomID = getIntent().getIntExtra("room",0);


        //the data service
        serAdapter = new ServicesJourDetailAdapter(dataService, this);
        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        serviceRV.setLayoutManager(layoutManager);
        serviceRV.setAdapter(serAdapter);
        dataService.add(new JourServiceModel(R.drawable.airplane, "الطيران"));
        dataService.add(new JourServiceModel(R.drawable.hotel, "الفنادق"));
        dataService.add(new JourServiceModel(R.drawable.car, "خدمة النقل"));
        dataService.add(new JourServiceModel(R.drawable.room_service, "الوجبات"));
        dataService.add(new JourServiceModel(R.drawable.bus, "الأنشطة"));
        serAdapter.notifyDataSetChanged();

        //the view pager
//        setupViewPager(viewPager);


        //the data service
        incluAdapter = new JourInclusionsAdapter(dataInclu, this);
        RecyclerView.LayoutManager
                layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        inclusionsRV.setLayoutManager(layoutManager1);
        inclusionsRV.setAdapter(incluAdapter);



        //the data service
        expectAdapter = new JourExpectationsAdapter(dataExpect, this);
        RecyclerView.LayoutManager
                layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        expetationsRV.setLayoutManager(layoutManager2);
        expetationsRV.setAdapter(expectAdapter);



        timelineAdapter = new JourTimelineAdapter(dataTimeline, this);
        RecyclerView.LayoutManager
                layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        timelineRV.setLayoutManager(layoutManager3);
        timelineRV.setAdapter(timelineAdapter);



        termsAdapter = new JourTermsAdapter(dataTerms, this);
        RecyclerView.LayoutManager
                layoutManager4 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        termsRV.setLayoutManager(layoutManager4);
        termsRV.setAdapter(termsAdapter);



        cancelAdapter = new JourTermsAdapter(dataCancel, this);
        RecyclerView.LayoutManager
                layoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cancelRV.setLayoutManager(layoutManager5);
        cancelRV.setAdapter(cancelAdapter);


        payAdapter = new JourTermsAdapter(dataPay, this);
        RecyclerView.LayoutManager
                layoutManager6 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        payRV.setLayoutManager(layoutManager6);
        payRV.setAdapter(payAdapter);



        //init the recycler of the images
        RecyclerView.LayoutManager
                layoutManager7 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imagesRV.setLayoutManager(layoutManager7);
        imagesAdapter = new ImagesHotelAdapter(imagesData, this);
        imagesRV.setAdapter(imagesAdapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imagesRV);

        CircleIndicator2 indicator = findViewById(R.id.indicator);
        indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
        imagesAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

        //end of the images init


        // get the data from the server
        getDataDetail();

    }

    /**
     * the view pager of the detail
     *
     * @param viewPager from xml
     */
    private void setupViewPager(ViewPager viewPager,JourDetailModel data) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new JourTripDetailFragment(data), "البرنامج السياحي");
        adapter.addFragment(new JourVisaDetailFragment(data), "معلومات التأشيرة");
        adapter.addFragment(new JourDestinationDetailFragment(data), "معلومات عن الوجهة");
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }


    /**
     * here to get the data from the server
     */

    private void getDataDetail() {


        loading.setVisibility(View.VISIBLE);
        parent.setVisibility(View.GONE);

//        String url =  "http://172.107.175.236:800/api/journey/JourneyDetails?journeyid=14&RoomId=24&FlightId=17&NightId=15";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);

                extractJsonDetail(response);

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
        MySingleton.getInstance(DetailJourActivity.this).addToRequestQueue(jsObjRequest);
    }


    /**
     * here to extract the data from the server and get it
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    @SuppressLint("SetTextI18n")
    private void extractJsonDetail(JSONObject response) {
        jour = new JourDetailModel();
        try {


            JSONObject respObject = response.getJSONObject("Response");


            jour.setId(respObject.getInt("Id"));

            jour.setName(respObject.getString("JourneyName"));
            jour.setCityId(respObject.getInt("CityId"));
            jour.setCityName(respObject.getString("CityName"));
            jour.setCountryId(respObject.getInt("CountryId"));
            jour.setCountryName(respObject.getString("CountryName"));
            jour.setDetail(respObject.getString("Details"));

            // the detailDistination
            if (!respObject.getString("DetailsAboutDistinations").startsWith("null")) {
                JSONArray desArr = respObject.getJSONArray("DetailsAboutDistinations");
                ArrayList<JourDistinationModel> detailDistination = new ArrayList<>();
                for (int i = 0; i < desArr.length(); i++) {
                    JSONObject desObj = desArr.getJSONObject(i);
                    JourDistinationModel des = new JourDistinationModel();
                    des.setTitle(desObj.getString("Title"));
                    des.setDesc(desObj.getString("Description"));

                    detailDistination.add(des);
                }
                jour.setDetailDistination(detailDistination);
            }

            jour.setVisaDetail(respObject.getString("DetailsAboutVisa"));
            jour.setImageName(respObject.getString("ImageName"));
            jour.setNewPrice(respObject.getDouble("NewPrice"));
            jour.setOldPrice(respObject.getDouble("OldPrice"));
            jour.setSavedPrice(respObject.getDouble("SavedPrice"));

            // set the activity
            if (!respObject.getString("Activity").startsWith("null")) {
                JSONObject activityObj = respObject.getJSONObject("Activity");
                JourActivityDetailModel activity = new JourActivityDetailModel();
                activity.setId(activityObj.getInt("ActivityId"));
                activity.setName(activityObj.getString("ActivityName"));
                activity.setDuration(activityObj.getInt("Duration"));
                activity.setClass_(activityObj.getString("Class"));
                activity.setImage(activityObj.getString("ImageUrl"));
                activity.setPrice(activityObj.getDouble("Price"));

                jour.setActivity(activity);
            }

            // the cancel policy
            if (!respObject.getString("CancellingPolicys").startsWith("null")) {
                JSONArray calcelArr = respObject.getJSONArray("CancellingPolicys");
                ArrayList<String> cancelPolicy = new ArrayList<>();
                for (int i = 0; i < calcelArr.length(); i++) {
                    JSONObject calcelObj = calcelArr.getJSONObject(i);
                    cancelPolicy.add(calcelObj.getString("Description"));
                }
                jour.setCancelPolicy(cancelPolicy);
            }

            // the inclusions
            if (!respObject.getString("Inclusions").startsWith("null")) {
                JSONArray inclArr = respObject.getJSONArray("Inclusions");
                ArrayList<String> inclusion = new ArrayList<>();
                for (int i = 0; i < inclArr.length(); i++) {
                    JSONObject inclObj = inclArr.getJSONObject(i);
                    inclusion.add(inclObj.getString("Description"));
                }
                jour.setInclusion(inclusion);
            }
            // the Exceptions
            if (!respObject.getString("Exceptions").startsWith("null")) {
                JSONArray exceptArr = respObject.getJSONArray("Exceptions");
                ArrayList<String> exceptions = new ArrayList<>();
                for (int i = 0; i < exceptArr.length(); i++) {
                    JSONObject Obj = exceptArr.getJSONObject(i);
                    exceptions.add(Obj.getString("Description"));
                }
                jour.setExceptions(exceptions);
            }

            // the images
            if (!respObject.getString("Images").startsWith("null")) {
                JSONArray imageArr = respObject.getJSONArray("Images");
                ArrayList<String> images = new ArrayList<>();
                for (int i = 0; i < imageArr.length(); i++) {
                    JSONObject Obj = imageArr.getJSONObject(i);
                    images.add("http://172.107.175.236/MultiMedia/Journey/" + Obj.getString("ImageName"));
                }
                jour.setImages(images);
            }


            // the pay
            if (!respObject.getString("PayPolicys").startsWith("null")) {
                JSONArray payArr = respObject.getJSONArray("PayPolicys");
                ArrayList<String> pay = new ArrayList<>();
                for (int i = 0; i < payArr.length(); i++) {
                    pay.add(payArr.getString(i));
                }
                jour.setPayPolicy(pay);
            }

            // the room
            if (!respObject.getString("Room").startsWith("null")) {
                JSONObject roomObj = respObject.getJSONObject("Room");
                JourRoomDetailModel room = new JourRoomDetailModel();

                room.setName(roomObj.getString("Name"));
                room.setStars(roomObj.getDouble("Stars"));
                room.setAddress(roomObj.getString("Address"));
                room.setImage(roomObj.getString("MainImageUrl"));
                room.setRoomName(roomObj.getString("RoomName"));
                jour.setRoom(room);
            }

            // the terms
            if (!respObject.getString("TermsAndConditions").startsWith("null")) {
                JSONArray termsArr = respObject.getJSONArray("TermsAndConditions");
                ArrayList<String> terms = new ArrayList<>();
                for (int i = 0; i < termsArr.length(); i++) {
                    JSONObject Obj = termsArr.getJSONObject(i);
                    terms.add(Obj.getString("Description"));
                }
                jour.setTermsAndConditions(terms);
            }
            // the timeline
            if (!respObject.getString("TimeLines").startsWith("null")) {
                JSONArray timelineArr = respObject.getJSONArray("TimeLines");
                ArrayList<JourTimelineModel> timeline = new ArrayList<>();
                for (int i = 0; i < timelineArr.length(); i++) {
                    JSONObject obj = timelineArr.getJSONObject(i);
                    JourTimelineModel time = new JourTimelineModel();
                    time.setDay(obj.getInt("Day"));
                    time.setDesc(obj.getString("Decription"));
                    time.setHeader(obj.getString("Header"));
                    timeline.add(time);
                }
                jour.setTimeline(timeline);
            }

            // set the flight
            if (!respObject.getString("Flights").startsWith("null")) {
                JSONObject flightObj = respObject.getJSONObject("Flights");
                JourDetailFlight flight = new JourDetailFlight();
                flight.setId(flightObj.getInt("FlightId"));
                flight.setName(flightObj.getString("Name"));
                flight.setCityAmong(flightObj.getString("CitiesAmongDistination"));
                flight.setDuration(flightObj.getString("Duration"));
                flight.setFrom(flightObj.getString("From"));
                flight.setTo(flightObj.getString("To"));

                jour.setFlight(flight);
            }

            //todo here to add the data to the design
            setDataToDesign(jour);

        } catch (JSONException e) {
            e.printStackTrace();
//            setDataToDesign(jour);
        }


    }


    /**
     * here to set the data to the xml design
     *
     * @param data the data after extract the json
     */
    private void setDataToDesign(final JourDetailModel data) {

        // the images
        Log.d("google", "this is the images " + data.getImages());
        imagesData.addAll(data.getImages());
        imagesAdapter.notifyDataSetChanged();

        nameTV.setText(data.getName());
        addressTV.setText(data.getCityName()+" "+data.getCountryName());

        oldPriceTV.setText(" من "+data.getOldPrice()+" $ ");
        oldPriceTV.setPaintFlags(oldPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        newPriceTV.setText(" الي "+data.getNewPrice()+" $ ");
        savePriceTV.setText(" التوفير "+data.getSavedPrice()+" $ ");


        setupViewPager(viewPager,data);


        dataInclu.addAll(data.getInclusion());

        incluAdapter.notifyDataSetChanged();

        dataExpect.addAll(data.getExceptions());
        expectAdapter.notifyDataSetChanged();

        dataTimeline.addAll(data.getTimeline());
        timelineAdapter.notifyDataSetChanged();

        dataTerms.addAll(data.getTermsAndConditions());
        termsAdapter.notifyDataSetChanged();

        dataCancel.addAll(data.getCancelPolicy());
        cancelAdapter.notifyDataSetChanged();

        dataPay.addAll(data.getPayPolicy());
        payAdapter.notifyDataSetChanged();

    }


    /**
     * action the back button
     */
    @OnClick(R.id.backIV)void backAction(){
        finish();
    }

    /**
     * action the reservation button
     */
    @OnClick(R.id.jourReservBtn)void jourReservation(){

        String url = "http://172.107.175.236:800/api/journey/GetReserveData?journeyid="+jour.getId()+
                "&flightid="+jour.getFlight().getId()+"&nightid="+nightID+"&roomid="+roomID;

        //todo go to the new activity and get the data and make the reservations

        Intent i = new Intent(DetailJourActivity.this,ReservationJourActivity.class);
        i.putExtra("data",url);
        i.putExtra("jour",jour.getId());
        i.putExtra("flight",jour.getFlight().getId());
        i.putExtra("night",nightID);
        i.putExtra("activity",jour.getActivity().getId());
        i.putExtra("room",roomID);


        startActivity(i);
    }
}
