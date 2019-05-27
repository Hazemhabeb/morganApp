package com.merjanapp.merjan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.adapter.DummyHotelsAdapter;
import com.merjanapp.merjan.model.dummyModel;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private DummyHotelsAdapter dummyAdapter2;

    ArrayList<dummyModel> dummyData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        recyclerView =  findViewById(R.id.cityRV);

        RecyclerView.LayoutManager
                layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        dummyAdapter2 = new DummyHotelsAdapter(dummyData, HotelsActivity.this);
        recyclerView.setAdapter(dummyAdapter2);


        dummyData.add(new dummyModel());dummyData.add(new dummyModel());dummyData.add(new dummyModel());
        dummyData.add(new dummyModel());dummyData.add(new dummyModel());dummyData.add(new dummyModel());
        dummyData.add(new dummyModel());dummyData.add(new dummyModel());dummyData.add(new dummyModel());
        dummyData.add(new dummyModel());dummyData.add(new dummyModel());dummyData.add(new dummyModel());
        recyclerView.setAdapter(dummyAdapter2);


    }

    public void back(View view) {
        finish();
    }
}
