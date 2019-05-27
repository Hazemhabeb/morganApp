package com.merjanapp.merjan.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.SearchActivityAdapter;
import com.merjanapp.merjan.adapter.SearchHotelAdapter;
import com.merjanapp.merjan.adapter.SearchJourAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.SearchActivityModel;
import com.merjanapp.merjan.model.SearchHotelModel;
import com.merjanapp.merjan.model.SearchJourModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {


    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.t_hotel)
    TextView t_hotel;
    @BindView(R.id.t_jour)
    TextView t_jour;
    @BindView(R.id.t_activity)
    TextView t_activity;
    @BindView(R.id.v_hotel)
    View v_hotel;
    @BindView(R.id.v_jour)
    View v_jour;
    @BindView(R.id.v_activity)
    View v_activity;


    @BindView(R.id.s_activity)
    LinearLayout s_activity;
    @BindView(R.id.s_hotel)
    LinearLayout s_hotel;
    @BindView(R.id.s_jour)
    LinearLayout s_jour;

    @BindView(R.id.actSearchET)
    EditText actSearchET;
    @BindView(R.id.jourSearchET)
    EditText jourSearchET;
    @BindView(R.id.searchRV)
    RecyclerView searchRV;
    @BindView(R.id.searchBtn)
    Button searchBtn;

    @BindView(R.id.hotelSearchET)
    EditText hotelSearchET;
    @BindView(R.id.fromET)
    EditText fromET;
    @BindView(R.id.toET)
    EditText toET;


    //1 for hotel 2 for activity 3 for jour
    private int tabIndex;

    // the get  search
    ArrayList<SearchActivityModel> acts = new ArrayList<>();
    ArrayList<SearchJourModel> jours = new ArrayList<>();
    ArrayList<SearchHotelModel> hotels = new ArrayList<>();

    SearchActivityAdapter actAdapter;
    SearchJourAdapter jourAdapter;
    SearchHotelAdapter hotelAdapter;


    //todo here to get the calender data
    //calender to select date
    private DatePickerDialog fromDatePickerDialog,toDatePickerDialog;
    private java.text.SimpleDateFormat dateFormatter;
    Date dueDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);


        tabIndex = 1;

        actSearchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                getDataResult();
            }
        });

        jourSearchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                getDataResult();
            }
        });
        hotelSearchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                getDataResult();
            }
        });


        //init the calender
        dateFormatter = new java.text.SimpleDateFormat("EEE, d MMM yyyy", Locale.US);
        setDateTimeField();


        fromET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.show();
            }
        });
        toET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDatePickerDialog.show();
            }
        });
    }

    /**
     * to set the date time from the calender
     */
    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dueDate=newDate.getTime();

                fromET.setVisibility(View.VISIBLE);
                fromET.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dueDate=newDate.getTime();

                toET.setVisibility(View.VISIBLE);
                toET.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    // the action the tabs

    /**
     * action the hotel tab
     */
    @OnClick(R.id.p_hotel)
    void tabHotelAction() {
        tabIndex = 1;
        selectTab(t_hotel, v_hotel);
        unselectTab(t_jour, v_jour);
        unselectTab(t_activity, v_activity);
        searchVisibility();
    }

    /**
     * action the activity tab
     */
    @OnClick(R.id.p_activity)
    void tabActivityAction() {
        tabIndex = 2;
        unselectTab(t_hotel, v_hotel);
        unselectTab(t_jour, v_jour);
        selectTab(t_activity, v_activity);
        searchVisibility();
    }

    /**
     * action the jour tab
     */
    @OnClick(R.id.p_jour)
    void tabJourAction() {
        tabIndex = 3;
        unselectTab(t_hotel, v_hotel);
        selectTab(t_jour, v_jour);
        unselectTab(t_activity, v_activity);
        searchVisibility();
    }


    /**
     * action the back button
     *
     * @param view from xml
     */
    public void back(View view) {
        finish();
    }


    private void selectTab(TextView textView, View view) {
        textView.setTextColor(getResources().getColor(R.color.yellow_star));
        view.setBackgroundColor(getResources().getColor(R.color.yellow_star));
    }

    private void unselectTab(TextView textView, View view) {
        textView.setTextColor(getResources().getColor(R.color.wight));
        view.setBackgroundColor(getResources().getColor(R.color.wight));
    }


    private void searchVisibility() {

        acts.clear();
        actSearchET.setText("");
        jourSearchET.setText("");

        if (tabIndex == 1) {
            s_hotel.setVisibility(View.VISIBLE);
            s_activity.setVisibility(View.GONE);
            s_jour.setVisibility(View.GONE);
        } else if (tabIndex == 2) {
            s_hotel.setVisibility(View.GONE);
            s_activity.setVisibility(View.VISIBLE);
            s_jour.setVisibility(View.GONE);
        } else if (tabIndex == 3) {
            s_hotel.setVisibility(View.GONE);
            s_activity.setVisibility(View.GONE);
            s_jour.setVisibility(View.VISIBLE);
        }
    }


    private void getDataResult() {


        loading.setVisibility(View.VISIBLE);

        String url = "";

        if (tabIndex == 1) {
            url = "http://172.107.175.236:800/api/Hotel/Search?term="
                    +hotelSearchET.getText() + "&CheckIn="+fromET.getText().toString()
                    +"&CheckOut=" + toET.getText().toString();

        } else if (tabIndex == 2)
            url = "http://172.107.175.236:800/api/Activity/Search?term=" + actSearchET.getText().toString();
        else if (tabIndex == 3)
            url = "http://172.107.175.236:800/api/Journey/Search?term=" + jourSearchET.getText().toString();

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

        JSONArray respArr = response.getJSONArray("Response");

        if (tabIndex == 1) {
            hotels.clear();
            for (int i = 0; i < respArr.length(); i++) {
                JSONObject respObj = respArr.getJSONObject(i);
                SearchHotelModel act = new SearchHotelModel();
                act.setId(respObj.getInt("Id"));
                act.setName(respObj.getString("Name"));
                act.setType(respObj.getString("Type"));
                hotels.add(act);
            }

            hotelAdapter = new SearchHotelAdapter(hotels, SearchActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this
                    , LinearLayoutManager.VERTICAL, false);
            searchRV.setLayoutManager(layoutManager);
            searchRV.setAdapter(hotelAdapter);

        }else if (tabIndex == 2) {
            acts.clear();
            for (int i = 0; i < respArr.length(); i++) {
                JSONObject respObj = respArr.getJSONObject(i);
                SearchActivityModel act = new SearchActivityModel();
                act.setId(respObj.getInt("Id"));
                act.setName(respObj.getString("Name"));
                act.setType(respObj.getString("Type"));
                acts.add(act);
            }

            actAdapter = new SearchActivityAdapter(acts, SearchActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this
                    , LinearLayoutManager.VERTICAL, false);
            searchRV.setLayoutManager(layoutManager);
            searchRV.setAdapter(actAdapter);
        } else if (tabIndex == 3) {
            jours.clear();
            for (int i = 0; i < respArr.length(); i++) {
                JSONObject respObj = respArr.getJSONObject(i);
                SearchJourModel act = new SearchJourModel();
                act.setId(respObj.getInt("Id"));
                act.setName(respObj.getString("Name"));
                act.setType(respObj.getString("Type"));
                jours.add(act);
            }

            jourAdapter = new SearchJourAdapter(jours, SearchActivity.this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this
                    , LinearLayoutManager.VERTICAL, false);
            searchRV.setLayoutManager(layoutManager);
            searchRV.setAdapter(jourAdapter);
        }
    }


    //todo action the activity search button
    // here to change the actvity data
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityChangeEvent(final SearchActivityModel event) {

        actSearchET.setText(event.getName() + "   ( " + event.getType() + " ) ");


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1 for activity 2 for country 3 for city

                String url = "";
                if (event.getStatus() == 1) {
                    Intent i = new Intent(SearchActivity.this, ActivityDetailActivity.class);
                    i.putExtra("data", event.getId());

                    startActivity(i);
                    return;
                } else if (event.getStatus() == 2) {
                    url = "http://172.107.175.236:800/api/Activity/GetByCountry/" + event.getId();
                } else if (event.getStatus() == 3) {
                    url = "http://172.107.175.236:800/api/Activity/GetByCity/" + event.getId();
                }

                Intent i = new Intent(SearchActivity.this, ResultActivity.class);
                i.putExtra("searchUrl", url);
                i.putExtra("title", actSearchET.getText().toString());
                startActivity(i);


            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onjourChangeEvent(final SearchJourModel event) {

        jourSearchET.setText(event.getName() + "   ( " + event.getType() + " ) ");


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1 for activity 2 for country 3 for city

                String url = "";
                if (event.getStatus() == 1) {
                    Intent i = new Intent(SearchActivity.this, DetailJourActivity.class);

                    url = "http://172.107.175.236:800/api/journey/JourneyDetails?journeyid=" + event.getId()
                            + "&RoomId=" + 0
                            + "&FlightId=" + 0
                            + "&NightId=" + 0;


                    Log.d("google", "this is the detail journy url " + url);

                    i.putExtra("data", url);
                    i.putExtra("room", 0);
                    i.putExtra("night", 0);
                    startActivity(i);
                    return;
                } else if (event.getStatus() == 2) {
                    url = "http://172.107.175.236:800/api/journey/SearchCountry/" + event.getId();
                } else if (event.getStatus() == 3) {
                    url = "http://172.107.175.236:800/api/journey/SearchByCity/" + event.getId();
                }

                Intent i = new Intent(SearchActivity.this, ResultJourActivity.class);
                i.putExtra("searchUrl", url);
                i.putExtra("title", jourSearchET.getText().toString());
                startActivity(i);


            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHotelChangeEvent(final SearchHotelModel event) {

        hotelSearchET.setText(event.getName() + "   ( " + event.getType() + " ) ");


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1 for activity 2 for country 3 for city

                String url = "";
                if (event.getStatus() == 1) {
                    Intent i = new Intent(SearchActivity.this, DetailHotelActivity.class);
                    i.putExtra("data",event.getId());

                    startActivity(i);

                    return;
                } else if (event.getStatus() == 2) {
                    url = "http://172.107.175.236:800/api/Hotel/GetByCountry/" + event.getId();
                } else if (event.getStatus() == 3) {
                    url = "http://172.107.175.236:800/api/Hotel/GetByCity/" + event.getId();
                }

                Intent i = new Intent(SearchActivity.this, ResultHotelActivity.class);
                i.putExtra("searchUrl", url);
                i.putExtra("title", hotelSearchET.getText().toString());
                startActivity(i);


            }
        });

    }

}
