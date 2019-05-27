package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.merjanapp.merjan.R;
import com.merjanapp.merjan.activity.ReservationRoomActivity;
import com.merjanapp.merjan.model.HotelDetailRoom;
import com.merjanapp.merjan.model.HotelRoomService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class HotelRoomDetailAdapter extends RecyclerView.Adapter<HotelRoomDetailAdapter.ViewHolder> {

    List<HotelDetailRoom> contents;
    List<HotelRoomService> services;

    Context mContext;
    HotelRoomServiceAdapter serviceAdapter;
    private int hotelId;
    public HotelRoomDetailAdapter(int hotelId,List<HotelDetailRoom> contents, List<HotelRoomService> services, Context mContext) {
        this.contents = contents;
        this.mContext = mContext;
        this.services = services;
        this.hotelId = hotelId;
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;


        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel_detail_room, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.roomNameTV.setText(contents.get(position).getRoomName());
        holder.payTV.setText(contents.get(position).getPayway());
        holder.priceTV.setText(contents.get(position).getPrice()+" $ ");
        holder.supplyTV.setText(contents.get(position).getSupplement());
        holder.closeTV.setText(contents.get(position).getClose());
        holder.closeDetailTV.setText(contents.get(position).getCloseDetail());


        serviceAdapter = new HotelRoomServiceAdapter(services,mContext);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);

        holder.serviceRV.setLayoutManager(layoutManager);
        holder.serviceRV.setAdapter(serviceAdapter);


        //todo the room reservations
        holder.reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext, ReservationRoomActivity.class);
                i.putExtra("hotel",hotelId);
                i.putExtra("room",contents.get(position).getId());

                mContext.startActivity(i);
            }
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.roomNameTV)TextView roomNameTV;
        @BindView(R.id.payTV)TextView payTV;
        @BindView(R.id.priceTV)TextView priceTV;
        @BindView(R.id.supplyTV)TextView supplyTV;
        @BindView(R.id.closeTV)TextView closeTV;
        @BindView(R.id.closeDetailTV)TextView closeDetailTV;
        @BindView(R.id.serviceRV)RecyclerView serviceRV;
        @BindView(R.id.reservationBtn)Button reservationBtn;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }
    }

}