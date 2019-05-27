package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.activity.DetailJourActivity;
import com.merjanapp.merjan.activity.JourResultAskDetailActivity;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.JourResultCityModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultJourAdapter extends RecyclerView.Adapter<ResultJourAdapter.ViewHolder> {

    private List<JourResultCityModel> contents;
    private Context mContext;
    ArrayAdapter<CharSequence> nighAdapter, hotelAdapter, flightAdapter;


    public ResultJourAdapter(List<JourResultCityModel> contents, Context mContext) {
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
                .inflate(R.layout.item_journey, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (mContext != null)
            Glide.with(mContext).load(contents.get(position).getImage()).into(holder.image);

        holder.name.setText(contents.get(position).getName());
        holder.oldPrice.setText(" من " + contents.get(position).getOldPrice() + " $ ");
        holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.newPrice.setText(" الي " + contents.get(position).getNewPrice() + " $ ");
        holder.savePrice.setText(" التوفير " + contents.get(position).getSavedPrice() + " $ ");

        holder.detail.setText(contents.get(position).getDetail());

        //todo here the spinner


        //todo nights spinner
        nighAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.gavor_array, android.R.layout.simple_spinner_item);
        nighAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        holder.nightSP.setAdapter(nighAdapter);


        String d[] = new String[contents.get(position).getNights().size()];

        for (int i = 0; i < contents.get(position).getNights().size(); i++) {
            d[i] = contents.get(position).getNights().get(i).getNight() + " ليال ";
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(mContext, R.layout.spinner_center_item,
                d);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_center_item); // The drop down view
        holder.nightSP.setAdapter(spinnerArrayAdapter);


        //todo hotel spinner

        hotelAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.gavor_array, android.R.layout.simple_spinner_item);
        hotelAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        holder.hotelSP.setAdapter(hotelAdapter);


        String d1[] = new String[contents.get(position).getHotels().size()];

        for (int i = 0; i < contents.get(position).getHotels().size(); i++) {
            d1[i] = contents.get(position).getHotels().get(i).getStars() + " نجوم ";
        }

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>(mContext, R.layout.spinner_center_item,
                d1);
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_center_item); // The drop down view
        holder.hotelSP.setAdapter(spinnerArrayAdapter1);

        //todo flight spinner


        flightAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.gavor_array, android.R.layout.simple_spinner_item);
        flightAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        holder.flightSP.setAdapter(flightAdapter);


        String d2[] = new String[contents.get(position).getFlights().size()];

        for (int i = 0; i < contents.get(position).getFlights().size(); i++) {
            d2[i] = contents.get(position).getFlights().get(i).getName();
        }

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<>(mContext, R.layout.spinner_center_item,
                d2);
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_center_item); // The drop down view
        holder.flightSP.setAdapter(spinnerArrayAdapter2);


        //todo action the detail button
        holder.detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(mContext, DetailJourActivity.class);

                    String url = "http://172.107.175.236:800/api/journey/JourneyDetails?journeyid=" + contents.get(position).getId()
                            + "&RoomId=" + contents.get(position).getHotels().get(holder.hotelSP.getSelectedItemPosition()).getRoomId()
                            + "&FlightId=" + contents.get(position).getFlights().get(holder.flightSP.getSelectedItemPosition()).getId()
                            + "&NightId=" + contents.get(position).getNights().get(holder.nightSP.getSelectedItemPosition()).getId();


                    Log.d("google", "this is the detail journy url " + url);

                    i.putExtra("data", url);
                    i.putExtra("room", contents.get(position).getHotels().get(holder.hotelSP.getSelectedItemPosition()).getRoomId());
                    i.putExtra("night", contents.get(position).getNights().get(holder.nightSP.getSelectedItemPosition()).getId());
                    mContext.startActivity(i);
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Log.e("error", ex.getMessage());
                }
            }
        });


        //todo here the action
        holder.hotelSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    getDataResult(contents.get(position).getId(),
                            contents.get(position).getHotels().get(holder.hotelSP.getSelectedItemPosition()).getRoomId(),
                            contents.get(position).getFlights().get(holder.flightSP.getSelectedItemPosition()).getId(),
                            contents.get(position).getNights().get(holder.nightSP.getSelectedItemPosition()).getId(),
                            holder
                    );
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Log.e("error", ex.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.flightSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    getDataResult(contents.get(position).getId(),
                            contents.get(position).getHotels().get(holder.hotelSP.getSelectedItemPosition()).getRoomId(),
                            contents.get(position).getFlights().get(holder.flightSP.getSelectedItemPosition()).getId(),
                            contents.get(position).getNights().get(holder.nightSP.getSelectedItemPosition()).getId(),
                            holder
                    );
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Log.e("error", ex.getMessage());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.nightSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    getDataResult(contents.get(position).getId(),
                            contents.get(position).getHotels().get(holder.hotelSP.getSelectedItemPosition()).getRoomId(),
                            contents.get(position).getFlights().get(holder.flightSP.getSelectedItemPosition()).getId(),
                            contents.get(position).getNights().get(holder.nightSP.getSelectedItemPosition()).getId(),
                            holder
                    );
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Log.e("error", ex.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        holder.askBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, JourResultAskDetailActivity.class));
            }
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.nameTV)
        TextView name;
        @BindView(R.id.oldPriceTV)
        TextView oldPrice;
        @BindView(R.id.newPriceTV)
        TextView newPrice;
        @BindView(R.id.savePriceTV)
        TextView savePrice;
        @BindView(R.id.nightSP)
        Spinner nightSP;
        @BindView(R.id.hotelSP)
        Spinner hotelSP;
        @BindView(R.id.flightSP)
        Spinner flightSP;
        @BindView(R.id.detailTV)
        TextView detail;
        @BindView(R.id.askBtn)
        Button askBtn;
        @BindView(R.id.detailBtn)
        Button detailBtn;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


    private void getDataResult(int jour, int room, int flight, int night, final ViewHolder holder) {


//        loading.setVisibility(View.VISIBLE);

        String url = "http://172.107.175.236:800/api/journey/GetPrices?journeyid=" + jour +
                "&RoomId=" + room + "&FlightId=" + flight + "&NightId=" + night;


        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("google", "response ----->  " + response.toString());
//                {
//                    "Response": {
//                    "OldPrice": 1330,
//                            "NewPrice": 1280,
//                            "SavedPrice": 50
//                }
//                }

                try {

                    JSONObject res = response.getJSONObject("Response");

                    holder.oldPrice.setText(" من " + res.getString("OldPrice") + " $ ");
                    holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                    holder.newPrice.setText(" الي " + res.getString("NewPrice") + " $ ");
                    holder.savePrice.setText(" التوفير " + res.getString("SavedPrice") + " $ ");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("google", "error ----->   " + error.toString());
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


}