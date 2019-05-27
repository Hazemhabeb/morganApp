package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.activity.ReservationRoomActivity;
import com.merjanapp.merjan.model.HotelRoomResultModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class HotelRoomResultAdapter extends RecyclerView.Adapter<HotelRoomResultAdapter.ViewHolder> {

    List<HotelRoomResultModel> contents;

    Context mContext;
    HotelRoomServiceAdapter serviceAdapter;
    public HotelRoomResultAdapter( List<HotelRoomResultModel> contents, Context mContext) {
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
                .inflate(R.layout.item_room_result, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.roomNameTV.setText(contents.get(position).getName());
        holder.priceTV.setText(contents.get(position).getPrice()+" $ ");

        if (mContext != null)
            Glide.with(mContext).load("http://172.107.175.236//MultiMedia/Rooms/"+contents.get(position)
                    .getImages().get(0)).into(holder.roomImage);


        serviceAdapter = new HotelRoomServiceAdapter(contents.get(position).getServices(),mContext);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);

        holder.serviceRV.setLayoutManager(layoutManager);
        holder.serviceRV.setAdapter(serviceAdapter);


        //todo the room reservations
        holder.reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, ReservationRoomActivity.class);
                i.putExtra("hotel",contents.get(position).getHotelId());
                i.putExtra("room",contents.get(position).getId());

                mContext.startActivity(i);
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.roomImage)ImageView roomImage;
        @BindView(R.id.roomNameTV)TextView roomNameTV;
        @BindView(R.id.priceTV)TextView priceTV;
        @BindView(R.id.serviceRV)RecyclerView serviceRV;
        @BindView(R.id.reservationBtn)Button reservationBtn;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }

}