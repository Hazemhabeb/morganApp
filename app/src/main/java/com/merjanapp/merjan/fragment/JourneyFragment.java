package com.merjanapp.merjan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.CityJourneyAdapter;
import com.merjanapp.merjan.adapter.OfferJourneyAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.JournyCityModel;
import com.merjanapp.merjan.model.JournyOfferModel;
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


public class JourneyFragment extends Fragment {


    //init the views
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.offerAcRV)
    RecyclerView offersRV;
    @BindView(R.id.offersCiRV)
    RecyclerView cityRV;
    @BindView(R.id.parent_activity_fragment)
    LinearLayout parent;


    //init the adapter and the data

    //offer adapter
    OfferJourneyAdapter offerAdapter;
    ArrayList<JournyOfferModel> offerData = new ArrayList<>();

    //city adapter
    CityJourneyAdapter cityAdapter;
    ArrayList<JournyCityModel> cityData = new ArrayList<>();


    public JourneyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journey, container, false);
        ButterKnife.bind(this, view);

        initView(view);
        return view;
    }


    /**
     * here to init the views from the xml
     *
     * @param view the view from the  xml here
     */
    private void initView(View view) {
        RecyclerView.LayoutManager
                layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        offersRV.setLayoutManager(layoutManager1);

        offerAdapter = new OfferJourneyAdapter(offerData, getContext());
        offersRV.setAdapter(offerAdapter);


        RecyclerView.LayoutManager
                layoutManager = new GridLayoutManager(getContext(), 2);
        cityRV.setLayoutManager(layoutManager);

        cityAdapter = new CityJourneyAdapter(cityData, getContext());
        cityRV.setAdapter(cityAdapter);


        getDataOffers();

        getDataCity();




    }


    /**
     * action the float action button
     */
    @OnClick(R.id.searchFB)
    void searchAction() {
        
    }

    private void getDataOffers() {


        loading.setVisibility(View.VISIBLE);
        parent.setVisibility(View.GONE);

        String url = Constant.baseUrl + Constant.jourOffer;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                loading.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);

                try {
                    extractJsonOffers(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    private void extractJsonOffers(JSONObject response) throws JSONException {


        offerData.clear();

        JSONArray respArray = response.getJSONArray("Response");


        for (int i = 0; i < respArray.length(); i++) {

            JSONObject object = respArray.getJSONObject(i);


            JournyOfferModel offer = new JournyOfferModel();

            offer.setId(object.getInt("Id"));
            offer.setName(object.getString("Name"));
            offer.setImage(object.getString("ImageName"));


            offerData.add(offer);

            offerAdapter.notifyDataSetChanged();
        }


    }


    private void getDataCity() {


        loading.setVisibility(View.VISIBLE);
//        parent.setVisibility(View.GONE);

        String url = Constant.baseUrl +Constant.jourCity ;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
//                parent.setVisibility(View.VISIBLE);

                try {
                    extractJsonCity(response);

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
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    private void extractJsonCity(JSONObject response) throws JSONException {


        cityData.clear();

        JSONArray respArray = response.getJSONArray("Response");


        for (int i = 0; i < respArray.length(); i++) {

            JSONObject object = respArray.getJSONObject(i);


            JournyCityModel city = new JournyCityModel();

            city.setCityId(object.getInt("CityId"));
            city.setName(object.getString("Name"));
            city.setImage(object.getString("ImageName"));
//            city.setImage(object.getString());


            cityData.add(city);

            cityAdapter.notifyDataSetChanged();
        }


    }


}
