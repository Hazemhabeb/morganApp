package com.merjanapp.merjan.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.merjanapp.merjan.activity.DetailHotelActivity;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.HotelModel;
import com.merjanapp.merjan.model.HotelRoomResultModel;
import com.merjanapp.merjan.model.HotelRoomService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultHotelAdapter extends RecyclerView.Adapter<ResultHotelAdapter.ViewHolder> {

    private List<HotelModel> contents;
    private Context mContext;
    private ServicesHotelsAdapter serAdapter ;
    private HotelRoomResultAdapter roomsAdapter ;

    public ResultHotelAdapter(List<HotelModel> contents, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(contents.get(position).getName());

        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);

        holder.address.setText(contents.get(position).getAddress());
        holder.detail.setText(contents.get(position).getDetail());
        holder.price.setVisibility(View.GONE);
        holder.rate.setRating((float) contents.get(position).getStar());
        serAdapter = new ServicesHotelsAdapter(contents.get(position).getServices(),mContext);

        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);

        holder.serviceRV.setLayoutManager(layoutManager);

        holder.serviceRV.setAdapter(serAdapter);


        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailHotelActivity.class);
                i.putExtra("data",contents.get(position).getId());

                mContext.startActivity(i);
            }
        });



        //todo here the rooms ask room

        holder.askBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataDetail(contents.get(position).getId(),holder);
                holder.roomParent.setVisibility(View.VISIBLE);
            }
        });

        holder.cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.roomParent.setVisibility(View.GONE);
            }
        });
        holder.cancelV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.roomParent.setVisibility(View.GONE);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        //init the views
        @BindView(R.id.hotelIV) ImageView image;
        @BindView(R.id.nameTV) TextView name;
        @BindView(R.id.priceTV) TextView price;
        @BindView(R.id.addressTV)TextView address;
        @BindView(R.id.detailTV)TextView detail;
        @BindView(R.id.rateRB)RatingBar rate;
        @BindView(R.id.serviceRV)RecyclerView serviceRV;
        @BindView(R.id.detailBtn)Button detailBtn;
        @BindView(R.id.askBtn)Button askBtn;

        //todo the room action fot the ask button
        @BindView(R.id.roomParent)FrameLayout roomParent;
        @BindView(R.id.resultRoomRv)RecyclerView resultRoomRv;
        @BindView(R.id.cancelV)View cancelV;
        @BindView(R.id.cancelImage)ImageView cancelImage;
        @BindView(R.id.loading)ProgressBar loading;







        @BindView(R.id.hotelParent)CardView hotelParent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }

    /**
     * here to get the data from the server
     */

    private void getDataDetail(final int hotelId, final ViewHolder holder) {


        holder.loading.setVisibility(View.VISIBLE);
//        parent.setVisibility(View.GONE);

        String url =  "http://172.107.175.236:800/api/Hotel/GetHotelRooms/"+hotelId;
//

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
                holder.loading.setVisibility(View.GONE);
//                parent.setVisibility(View.VISIBLE);

                try {
                    extractJsonDetail(response,holder);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("google", "error ----->   " + error.toString());
                holder.loading.setVisibility(View.GONE);
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
        MySingleton.getInstance(mContext).addToRequestQueue(jsObjRequest);
    }

    /**
     * here to extract the data from the server and get it
     *
     * @param response the json object is returned from the server
     * @param holder
     */
    @SuppressLint("SetTextI18n")
    private void extractJsonDetail(JSONObject response, ViewHolder holder) throws JSONException {

        JSONArray jsonArray = response.getJSONArray("Response");

        ArrayList<HotelRoomResultModel> rooms = new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            JSONObject respObj = jsonArray.getJSONObject(i);

            HotelRoomResultModel room = new HotelRoomResultModel();

            room.setHotelId(respObj.getInt("HotelID"));
            room.setId(respObj.getInt("RoomID"));
            room.setName(respObj.getString("RoomName"));
            room.setPrice(respObj.getDouble("Price"));

            JSONArray imArr = respObj.getJSONArray("Images");
            ArrayList<String >images = new ArrayList<>();
            for (int j=0;j<imArr.length();j++){
                images.add(imArr.getString(j));
            }
            room.setImages(images);

            JSONArray serArr = respObj.getJSONArray("Services");
            ArrayList<HotelRoomService> services = new ArrayList<>();
            for (int j=0;j<serArr.length();j++){
                JSONObject s = serArr.getJSONObject(j);
                HotelRoomService se = new HotelRoomService();
                se.setName(s.getString("ServiceName"));
                se.setImage(s.getString("IamgeUrl"));
                services.add(se);
            }
            room.setServices(services);

            rooms.add(room);



        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);

        holder.resultRoomRv.setLayoutManager(layoutManager);

        roomsAdapter = new HotelRoomResultAdapter(rooms,mContext);
        holder.resultRoomRv.setAdapter(roomsAdapter);

    }

}