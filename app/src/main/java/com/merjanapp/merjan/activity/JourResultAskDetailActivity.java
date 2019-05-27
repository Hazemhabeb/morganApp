package com.merjanapp.merjan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.JourCountryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JourResultAskDetailActivity extends AppCompatActivity {


    //init the views
    @BindView(R.id.nameET)
    EditText nameET;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.phoneEt)
    EditText phoneEt;
    @BindView(R.id.personET)
    EditText personET;
    @BindView(R.id.childET)
    EditText childET;

    @BindView(R.id.calenderSP)
    Spinner calenderSP;
    @BindView(R.id.countrySP)
    Spinner countrySP;


    @BindView(R.id.loading)
    ProgressBar loading;


    //get the data from the intent
    private int hotelId;
    private int roomId;

    //todo here the spinners
    ArrayAdapter<CharSequence> countryAdapter, calenderAdapter;


    ArrayList<JourCountryModel> countrys = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_jour_detail);
        ButterKnife.bind(this);

        hotelId = getIntent().getIntExtra("hotel", 0);
        roomId = getIntent().getIntExtra("room", 0);


        // the spinner calender
        calenderAdapter = ArrayAdapter.createFromResource(JourResultAskDetailActivity.this,
                R.array.calender_array, android.R.layout.simple_spinner_item);
        calenderAdapter.setDropDownViewResource(R.layout.spinner_center_gray_item);
        calenderSP.setAdapter(calenderAdapter);

        //todo the spinner country

        countryAdapter = ArrayAdapter.createFromResource(JourResultAskDetailActivity.this,
                R.array.gavor_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(R.layout.spinner_center_gray_item);
        countrySP.setAdapter(countryAdapter);


        //todo get the data to the spinners

        getDataResult();
    }


    /**
     * action to the button
     */
    @OnClick(R.id.reservationBtn)
    void resevationAction() {
        sendDataTOServer();
    }

    /**
     * here to send  the data from the server
     */

    private void sendDataTOServer() {


        loading.setVisibility(View.VISIBLE);

        String url = "http://172.107.175.236:800/api/journey/Enquiry";


        JSONObject object = new JSONObject();
        try {
            object.put("FullName",nameET.getText().toString());
            object.put("CounteryId",countrys.get(countrySP.getSelectedItemPosition()));
            object.put("Email",emailET.getText().toString());
            object.put("Phone",phoneEt.getText().toString());
            object.put("Visitor",personET.getText().toString());
            object.put("Child",childET.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("google", "this is the object  " + object.toString());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url,
                object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
//                try {
//                    if (response.getString("Response").equals("true")){
                Toast.makeText(JourResultAskDetailActivity.this, "تم ", Toast.LENGTH_LONG).show();
                finish();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("google", "error ----->   " + error.toString());
                loading.setVisibility(View.GONE);
//                Toast.makeText(ReservationActivity.this,"Cannot Login",Toast.LENGTH_LONG).show();
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
        MySingleton.getInstance(JourResultAskDetailActivity.this).addToRequestQueue(jsObjRequest);
    }

    /**
     * action the back button
     *
     * @param view from xml
     */
    public void back(View view) {
        finish();
    }


    private void getDataResult() {


        loading.setVisibility(View.VISIBLE);

        String url = "http://172.107.175.236:800/api/country/get";


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
     *
     * @param response the json object is returned from the server
     * @throws JSONException the json exception
     */
    private void extractJsonResult(JSONObject response) throws JSONException {

        JSONArray respArr = response.getJSONArray("Response");


        for(int i=0;i<respArr.length();i++){
            JSONObject respObj = respArr.getJSONObject(i);
            JourCountryModel country = new JourCountryModel();

            country.setId(respObj.getInt("CountryId"));
            country.setName(respObj.getString("Name"));
            country.setImage(respObj.getString("ImageName"));

            countrys.add(country);
        }

        String d [] =new String[countrys.size()];

        for (int i=0;i<countrys.size();i++){
            d[i]=countrys.get(i).getName();
        }


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(JourResultAskDetailActivity.this,
                R.layout.spinner_center_gray_item,
                d);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_center_gray_item); // The drop down view

        countrySP.setAdapter(spinnerArrayAdapter);
    }


}
