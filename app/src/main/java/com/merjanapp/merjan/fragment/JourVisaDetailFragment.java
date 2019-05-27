package com.merjanapp.merjan.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.JourDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by M on 11/21/2016.
 */

@SuppressLint("ValidFragment")
public class JourVisaDetailFragment extends Fragment {

    JourDetailModel data;
    public JourVisaDetailFragment(JourDetailModel data) {
        // Required empty public constructor
        this.data = data;

    }


    //init the views
    @BindView(R.id.visaTV)TextView visaTV;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jour_visa_detail, container, false);
        ButterKnife.bind(this,view);

        //init the recycler view


        visaTV.setText(data.getVisaDetail());



        return view;
    }




}
