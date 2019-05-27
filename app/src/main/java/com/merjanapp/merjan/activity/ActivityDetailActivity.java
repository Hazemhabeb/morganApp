package com.merjanapp.merjan.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
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
import com.merjanapp.merjan.adapter.ImagesActivityAdapter;
import com.merjanapp.merjan.adapter.InfoActivityAdapter;
import com.merjanapp.merjan.adapter.ServiceActivityAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.DetailActivityModel;
import com.merjanapp.merjan.model.DetailInfoModel;
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

public class ActivityDetailActivity extends AppCompatActivity {

    //init the views
    @BindView(R.id.infoRV)RecyclerView infoRV;
    @BindView(R.id.serviceRV)RecyclerView serviceRV;
    @BindView(R.id.imagesRV)RecyclerView imagesRV;
    @BindView(R.id.loading)ProgressBar loading;
    @BindView(R.id.parentView_)CoordinatorLayout parent;

    @BindView(R.id.priceTV)TextView priceTV;
    @BindView(R.id.nameTV)TextView nameTV;
    @BindView(R.id.placeTV)TextView placeTV;


    //offer adapter
    InfoActivityAdapter infoAdapter;
    ArrayList<DetailInfoModel> infoData =new ArrayList<>();


    ServiceActivityAdapter serviceAdapter;
    ArrayList<String> serviceData =new ArrayList<>();




    ArrayList<String> imagesData = new ArrayList<>();
    ImagesActivityAdapter imagesAdapter;



    //here to get the data

    private int activityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);





        activityId = getIntent().getIntExtra("data",0);



        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        imagesRV.setLayoutManager(layoutManager);

         imagesAdapter = new ImagesActivityAdapter(imagesData,this);

        imagesRV.setAdapter(imagesAdapter);



        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(imagesRV);

        CircleIndicator2 indicator = findViewById(R.id.indicator);
        indicator.attachToRecyclerView(imagesRV, pagerSnapHelper);
        imagesAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());







        //todo here the info recylerview
        RecyclerView.LayoutManager
                layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        infoRV.setLayoutManager(layoutManager1);

        infoAdapter = new InfoActivityAdapter(infoData, ActivityDetailActivity.this);
        infoRV.setAdapter(infoAdapter);



        //todo here the service recylerview
        RecyclerView.LayoutManager
                layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        serviceRV.setLayoutManager(layoutManager2);

        serviceAdapter = new ServiceActivityAdapter(serviceData, ActivityDetailActivity.this);
        serviceRV.setAdapter(serviceAdapter);



        getDataDetail();
    }
    /**
     * here to action the back button
     */
    @OnClick(R.id.backIV)void back(){
        finish();

    }


    private void getDataDetail() {


        loading.setVisibility(View.VISIBLE);
        parent.setVisibility(View.GONE);

        String url =  Constant.baseUrl+Constant.activityDetail+activityId;


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
        MySingleton.getInstance(ActivityDetailActivity.this).addToRequestQueue(jsObjRequest);
    }

//    {
//        "Response": {
//        "Id": 8,
//                "ActivityName": "نشاط 1",
//                "Price": 100,
//                "CityName": "اسطنبول",
//                "CountryName": "تركيا",
//                "Images": [
//        "430195020282018.jpg87312105102018.jpg",
//                "63572121282018.jpg67012105102018.jpg"
//        ],
//        "Services": [
//        "خدمه 1",
//                "خدمه2"
//        ],
//        "Info": [
//        {
//            "infoTittle": "معلومه",
//                "infoDetails": "تفاصيل المعلومه"
//        },
//        {
//            "infoTittle": "معلومه 2",
//                "infoDetails": "تفاصيل المعلومه 2"
//        }
//        ]
//    }
//    }
    /**
     * here to extract the data from the server and get it
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    @SuppressLint("SetTextI18n")
    private void extractJsonDetail(JSONObject response) throws JSONException {



        JSONObject respObject = response.getJSONObject("Response");


        DetailActivityModel detail = new DetailActivityModel();

        detail.setId(respObject.getInt("Id"));
        detail.setPrice(respObject.getInt("Id")+" $");
        detail.setName(respObject.getString("ActivityName"));
        detail.setCity(respObject.getString("CityName"));
        detail.setCountry(respObject.getString("CountryName"));


        priceTV.setText(detail.getPrice());
        nameTV.setText(detail.getName());
        placeTV.setText(detail.getCity()+" - "+detail.getCountry());


        //todo here to get the images

        JSONArray imag = respObject.getJSONArray("Images");

        for (int i =0;i<imag.length();i++){
            imagesData.add(Constant.ActivityDetailImage+imag.getString(i));
            imagesAdapter.notifyDataSetChanged();
        }
        //todo here to get the services
        JSONArray serv = respObject.getJSONArray("Services");
        for (int i =0;i<serv.length();i++){
            serviceData.add(serv.getString(i));
            serviceAdapter.notifyDataSetChanged();
        }


        //todo here to get the info

        JSONArray info = respObject.getJSONArray("Info");
        for (int i =0;i<info.length();i++){

            JSONObject infoObject = info.getJSONObject(i);

            DetailInfoModel in = new DetailInfoModel();


            in.setTitle(infoObject.getString("infoTittle"));
            in.setDetail(infoObject.getString("infoDetails"));

            infoData.add(in);
            infoAdapter.notifyDataSetChanged();
        }



    }

    public void reseverationAction(View view) {
        Intent i = new Intent(ActivityDetailActivity.this, ReservationActivity.class);
        i.putExtra("data",activityId);
        startActivity(i);
    }
}
