package com.merjanapp.merjan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.JourReservationModel;
import com.merjanapp.merjan.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReservationJourActivity extends AppCompatActivity {


    //init the views
    @BindView(R.id.fnameEt)
    EditText fnameET;
    @BindView(R.id.lnameEt)
    EditText lnameEt;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.countryEt)
    EditText countryEt;
    @BindView(R.id.phoneEt)
    EditText phoneEt;
    @BindView(R.id.descEt)
    EditText descET;
    @BindView(R.id.loading)
    ProgressBar loading;

    // get the data of the reservations
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.nameTV)
    TextView nameTV;
    @BindView(R.id.priceTV)
    TextView priceTV;
    @BindView(R.id.descTV)
    TextView descTV;
    @BindView(R.id.activityTV)
    TextView activityTV;
    @BindView(R.id.hotelTV)
    TextView hotelTV;
    @BindView(R.id.roomTV)
    TextView roomTV;


    //get the data from the intent
    private String url;

    //the data from the server
    JourReservationModel jour;
    int jourId;
    int flightId;
    int nightId;
    int roomId;
    int activityId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_jour);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra("data");
        jourId = getIntent().getIntExtra("jour",0);
        flightId = getIntent().getIntExtra("flight",0);
        nightId = getIntent().getIntExtra("night",0);
        activityId = getIntent().getIntExtra("activity",0);
        roomId = getIntent().getIntExtra("room",0);

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
        if (flightId==0)
            flightId=6;

        String url = Constant.baseUrl+"/api/journey/Reserve?JourneyId="+jourId
                +"&FlightId="+flightId+"&NightId="+nightId
                +"&RoomId="+nightId+"&ActivityId="+activityId
                +"&FirstName="+fnameET.getText().toString()+"&LastName="+lnameEt.getText().toString()
                +"&Email="+emailET.getText().toString()+"&Country="+countryEt.getText().toString()
                +"&Phone="+phoneEt.getText().toString()+"&Details="+descET.getText().toString();

        Log.d("google",url);

        JSONObject object = new JSONObject();


        Log.d("google", "this is the object  " + object.toString());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
//                try {
//                    if (response.getString("Response").equals("true")){
                Toast.makeText(ReservationJourActivity.this, "تم ", Toast.LENGTH_LONG).show();
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

                Toast.makeText(ReservationJourActivity.this, "تم ", Toast.LENGTH_LONG).show();
                finish();
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
        MySingleton.getInstance(ReservationJourActivity.this).addToRequestQueue(jsObjRequest);
    }

    /**
     * action the back button
     *
     * @param view from xml
     */
    public void back(View view) {
        finish();
    }


    // here to get the data from the server

    private void getDataResult() {


        loading.setVisibility(View.VISIBLE);

//        String url =  Constant.baseUrl+Constant.activityResult+city.getId();


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
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

        jour = new JourReservationModel();

        JSONArray respArr = response.getJSONArray("Response");


        JSONObject respObj = respArr.getJSONObject(0);

        jour.setDetail(respObj.getString("Details"));
        jour.setImage(respObj.getString("ImageName"));
        jour.setPrice(respObj.getDouble("Price"));
        jour.setName(respObj.getString("JourneyName"));

        // get the hotel
        JSONObject hotel = respObj.getJSONObject("Hotel");
        jour.setRoomName(hotel.getString("RoomName"));
        jour.setRoomId(hotel.getInt("RoomId"));
        jour.setHotelId(hotel.getInt("HotelId"));
        jour.setHotelName(hotel.getString("HotelName"));

        // get the activity
        JSONArray actArr = respObj.getJSONArray("Activities");
        if (actArr.length()!=0) {
            JSONObject activity = actArr.getJSONObject(0);
            jour.setActivityName(activity.getString("ActivityName"));
            jour.setActivityPrice(activity.getDouble("Price"));
        }

        // to set the data to the ui

//        http://172.107.175.236/MultiMedia/Journey/


        Glide.with(ReservationJourActivity.this).load("http://172.107.175.236/MultiMedia/Journey/"
                + jour.getImage()).into(image);

        nameTV.setText(jour.getName());
        priceTV.setText(jour.getPrice()+" $ ");
        descTV.setText(jour.getDetail());
        activityTV.setText(jour.getActivityName());
        hotelTV.setText(" الفندق : "+jour.getHotelName());
        roomTV.setText(" الغرفة : "+jour.getRoomName());
        roomId = jour.getRoomId();


    }


}
