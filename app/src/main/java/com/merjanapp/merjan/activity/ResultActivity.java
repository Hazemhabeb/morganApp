package com.merjanapp.merjan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import com.merjanapp.merjan.adapter.ResultActivityAdapter;
import com.merjanapp.merjan.app.MySingleton;
import com.merjanapp.merjan.model.CityActivityModel;
import com.merjanapp.merjan.model.ResultActivityModel;
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

public class ResultActivity extends AppCompatActivity {

    //init the views
    @BindView(R.id.resultRv)
    RecyclerView resultRV;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.cityTV)
    TextView cityTV;


    //offer adapter
    ResultActivityAdapter resultAdapter;
    ArrayList<ResultActivityModel> resultData = new ArrayList<>();

    //to get the data from the intent
    CityActivityModel city;

    String searchUrl;
    String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        //to get the data from the intent

        city = (CityActivityModel) getIntent().getSerializableExtra("data");

        searchUrl = getIntent().getStringExtra("searchUrl");
        titleName = getIntent().getStringExtra("title");


        if (city != null)
            cityTV.setText(city.getName());
        else
            cityTV.setText(titleName);


        initView();
    }

    /**
     * here to init the views
     */
    private void initView() {

        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        resultRV.setLayoutManager(layoutManager);

        resultAdapter = new ResultActivityAdapter(resultData, ResultActivity.this);
        resultRV.setAdapter(resultAdapter);


        getDataResult();

    }

    /**
     * here to action to the back button
     */
    @OnClick(R.id.backIV)
    void back() {
        finish();
    }


    private void getDataResult() {


        loading.setVisibility(View.VISIBLE);
        String url;
        if (searchUrl == null)
             url = Constant.baseUrl + Constant.activityResult + city.getId();
        else
            url = searchUrl;


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


        resultData.clear();

        JSONArray respArray = response.getJSONArray("Response");


        for (int i = 0; i < respArray.length(); i++) {

            JSONObject object = respArray.getJSONObject(i);


            ResultActivityModel result = new ResultActivityModel();


            result.setId(object.getInt(Constant.resultacid));
            result.setCityId(object.getInt(Constant.resultaccityid));
            result.setCityName(object.getString(Constant.resultaccityname));
            result.setDetail(object.getString(Constant.resultacdetail));
            result.setImage(object.getString(Constant.resultacimage));
            result.setName(object.getString(Constant.resultacname));
            result.setPrice(object.getInt(Constant.resultacprice) + "");


            resultData.add(result);

            resultAdapter.notifyDataSetChanged();
        }


    }


}
