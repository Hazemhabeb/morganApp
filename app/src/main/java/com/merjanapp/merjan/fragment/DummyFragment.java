package com.merjanapp.merjan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merjanapp.merjan.R;

import butterknife.ButterKnife;

/**
 * Created by M on 11/21/2016.
 */

public class DummyFragment extends Fragment {

    public DummyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_layout, container, false);
        ButterKnife.bind(this,view);

        //init the recycler view



        return view;
    }

//    private void getData() {
//
//
//        loading.setVisibility(View.VISIBLE);
//
//        String url =  Constant.baseUrl+"api/SelectFamilyAll";
//
//
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
//                null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("google", "response ----->  " + response.toString());
//                loading.setVisibility(View.GONE);
//
//                try {
////                    if (response.getBoolean("Status")){
////                        startActivity(new Intent(TreeActivity.this,TreeActivity.class));
//                    extractJson(response);
////                    }else {
////                        Toast.makeText(TreeActivity.this,"لا يمكنك الدخول ",Toast.LENGTH_LONG).show();
////                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("google", "error ----->   " + error.toString());
//                loading.setVisibility(View.GONE);
////                Toast.makeText(TreeActivity.this,"لا يمكنك الدخول ",Toast.LENGTH_LONG).show();
//            }
//        }) {
//
//            /**
//             * Passing some request headers
//             */
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json; charset=utf-8");
//                return headers;
//            }
//        };
//
////        jsObjRequest.setRetryPolicy(new RetryPolicy() {
////            @Override
////            public int getCurrentTimeout() {
////                return 50000;
////            }
////
////            @Override
////            public int getCurrentRetryCount() {
////                return 50000;
////            }
////
////            @Override
////            public void retry(VolleyError error) throws VolleyError {
////
////            }
////        });
//        // Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);
//    }

    //    {
//        "Response": [
//        {
//            "Id": 1,
//                "FirstNameAr": "عائلة السريع",
//                "ParentId": 0
//        },
//        {
//            "Id": 3,
//                "FirstNameAr": "الابن الاول",
//                "ParentId": 1
//        },
//        {
//    private void extractJson(JSONObject response) throws JSONException {
//
//
//        data.clear();
//
//        JSONArray respArray = response.getJSONArray("Response");
//
//
//        for (int i = 0; i < respArray.length(); i++) {
//
//            JSONObject object = respArray.getJSONObject(i);
//
//            //todo here to update the parent links
//            if (i == 0) {
//                familyTV.setText(object.getString("FirstNameAr"));
//                addParentLinks(object);
//                continue;
//            }
//
//            TreeModel treeModel=new TreeModel();
//            treeModel.setId(object.getInt("Id"));
//            treeModel.setName(object.getString("FirstNameAr"));
//            treeModel.setParentid(object.getInt("ParentId"));
//            treeModel.setEmail(object.getString("FaceBook"));
//            treeModel.setInstagram(object.getString("Insta"));
//            treeModel.setSnap(object.getString("Snap"));
//            treeModel.setTwitter(object.getString("Twitter"));
//            treeModel.setWhatsapp(object.getString("WhatsApp"));
//            treeModel.setPhone(object.getString("Jawal"));
//
//            data.add(treeModel);
//
//            madapter.notifyDataSetChanged();
//        }
//
//
//        if (data.size() == 0)
//            return;
//    }




}
