package com.merjanapp.merjan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merjanapp.merjan.R;
import com.merjanapp.merjan.model.HotelRoomService;
import com.merjanapp.merjan.util.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hazem_ on 12/8/2016.
 */

public class HotelRoomServiceAdapter extends RecyclerView.Adapter<HotelRoomServiceAdapter.ViewHolder> {

    List<HotelRoomService> contents;
    Context mContext;
    public HotelRoomServiceAdapter(List<HotelRoomService> contents, Context mContext) {
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
                .inflate(R.layout.item_service_detail_hotel, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.parent_categ.setBackgroundColor(mContext.getColor(contents.get(position).getColor()));

        holder.nameTV.setText(contents.get(position).getName());


        if (mContext != null)
            Glide.with(mContext).load(Constant.HotelServiceImage+contents.get(position).getImage()).into(holder.serviceIV);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.serviceIV)ImageView serviceIV;
        @BindView(R.id.nameTV)TextView nameTV;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);


        }
    }

}