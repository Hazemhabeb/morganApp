package com.merjanapp.merjan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.app.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReservationRoomActivity extends AppCompatActivity {


    //init the views
    @BindView(R.id.fnameEt)EditText fnameET;
    @BindView(R.id.lnameEt)EditText lnameEt;
    @BindView(R.id.emailET)EditText emailET;
    @BindView(R.id.countryEt)EditText countryEt;
    @BindView(R.id.phoneEt)EditText phoneEt;
    @BindView(R.id.descEt)EditText descET;
    @BindView(R.id.loading)ProgressBar loading;



    //get the data from the intent
    private int hotelId;
    private int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_hotel_room);
        ButterKnife.bind(this);

        hotelId = getIntent().getIntExtra("hotel",0);
        roomId = getIntent().getIntExtra("room",0);



    }


    /**
     * action to the button
     */
    @OnClick(R.id.reservationBtn)void resevationAction(){
        sendDataTOServer();
    }

    /**
     * here to send  the data from the server
     */

    private void sendDataTOServer() {


        loading.setVisibility(View.VISIBLE);

        String url =  "http://172.107.175.236:800/api/Hotel/Reserve";


        JSONObject object = new JSONObject();
        try {
            object.put("Name",fnameET.getText().toString());
            object.put("FName",lnameEt.getText().toString());
            object.put("Email",emailET.getText().toString());
            object.put("countery",countryEt.getText().toString());
            object.put("Phone",phoneEt.getText().toString());
            object.put("Notes",descET.getText().toString());
            object.put("HotelId",hotelId);
            object.put("RoomId",roomId);
            object.put("SaveReceives","true");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.d("google","this is the object  "+object.toString());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url,
                object, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                loading.setVisibility(View.GONE);
//                try {
//                    if (response.getString("Response").equals("true")){
                        Toast.makeText(ReservationRoomActivity.this,"تم ",Toast.LENGTH_LONG).show();
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
        MySingleton.getInstance(ReservationRoomActivity.this).addToRequestQueue(jsObjRequest);
    }

    /**
     * action the back button
     * @param view from xml
     */
    public void back(View view) {
        finish();
    }
}
